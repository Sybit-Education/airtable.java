/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;

import com.google.gson.annotations.SerializedName;
import com.sybit.airtable.exception.AirtableException;
import com.sybit.airtable.exception.HttpResponseExceptionHandler;
import com.sybit.airtable.vo.Attachment;
import com.sybit.airtable.vo.Delete;
import com.sybit.airtable.vo.PostRecord;
import com.sybit.airtable.vo.RecordItem;
import com.sybit.airtable.vo.Records;
import kong.unirest.GetRequest;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.SourceVersion;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


/**
 * Representation Class of Airtable Tables.
 *
 * @param <T> Type of Table.
 * @since 0.1
 */
public class Table<T> {

    private static final Logger LOG = LoggerFactory.getLogger(Table.class);

    private static final String MIME_TYPE_JSON = "application/json";

    private static final String FIELD_ID = "id";
    private static final String FIELD_CREATED_TIME = "createdTime";

    private final String name;
    private final Class<T> type;

    private Base parent;

    /**
     * Constructor of Table.
     * @param name name of table.
     * @param type class to represent table row
     */
    public Table(String name, Class<T> type) {
        assert name != null : "name was null";
        assert type != null : "type was null";

        this.name = name;
        this.type = type;
    }

    /**
     * Constructor of Table.
     *
     * @param name Name of Table
     * @param type Class to map the rows.
     * @param base Base containing table.
     */
    @SuppressWarnings("WeakerAccess")
    public Table(String name, Class<T> type, Base base) {
        this(name, type);
        setParent(base);
    }

    /**
     * Set the parent base.
     *
     * @param parent parent base of table.
     */
    public void setParent(Base parent) {
        this.parent = parent;
    }

    /**
     * Select all rows of table.
     *
     * @return List of all items.
     * @throws AirtableException if error occurs.
     */
    public List<T> select() throws AirtableException {
        return select(new Query() {
            @Override
            public Integer getMaxRecords() {
                return null;
            }

            @Override
            public String getView() {
                return null;
            }

            @Override
            public List<Sort> getSort() {
                return null;
            }

            @Override
            public String filterByFormula() {
                return null;
            }

            @Override
            public String[] getFields() {
                return null;
            }

            @Override
            public Integer getPageSize() {
                return null;
            }

            @Override
            public String getOffset() {
                return null;
            }
        });

    }

    /**
     * Select List of data of table with defined Query Parameters.
     *
     * @param query defined query
     * @return list of table items
     * @throws AirtableException if error occurs.
     */
    @SuppressWarnings("WeakerAccess")
    public List<T> select(final Query query) throws AirtableException {
        HttpResponse<Records> response;
        try {
            final GetRequest request = Unirest.get(getTableEndpointUrl())
                    .header("accept", MIME_TYPE_JSON)
                    .header("Authorization", getBearerToken())
                    .header("Content-type" , MIME_TYPE_JSON);

            if (query.getFields() != null && query.getFields().length > 0) {
                String[] fields = query.getFields();
                for (String field : fields) {
                    request.queryString("fields[]", field);

                }
            }
            if (query.getMaxRecords() != null) {
                request.queryString("maxRecords", query.getMaxRecords());
            }
            if (query.getView() != null) {
                request.queryString("view", query.getView());
            }
            if (query.filterByFormula() != null) {
                request.queryString("filterByFormula", query.filterByFormula());
            }
            if (query.getPageSize() != null) {
                if (query.getPageSize() > 100) {
                    LOG.warn("pageSize is limited to max 100 but was {}", query.getPageSize());
                    request.queryString("pageSize", 100);
                } else {
                    request.queryString("pageSize", query.getPageSize());
                }
            }
            if (query.getSort() != null) {
                int i = 0;
                for (Sort sort : query.getSort()) {
                    request.queryString("sort[" + i + "][field]", sort.getField());
                    request.queryString("sort[" + i + "][direction]", sort.getDirection());
                }
            }
            if (query.getOffset()!= null) {
                request.queryString("offset", query.getOffset());
            }

            LOG.debug("URL={}", request.getUrl());

            response = request.asObject(Records.class);
        } catch (UnirestException e) {
            throw new AirtableException(e);
        }

        int code = response.getStatus();
        List<T> list;
        if (200 == code) {
            list = getList(response);

            final String offset = response.getBody().getOffset();

            if (offset != null) {
                list.addAll(this.select(query, offset));
            }
        } else if (404 == code) {
            throw new AirtableException("Table not found: " + this.name );
        } else if (429 == code) {
            randomWait();
            return select(query);
        } else {
            HttpResponseExceptionHandler.onResponse(response);
            list = null;
        }

        return list;
    }

    /**
     * Performs a sleep for random time period between 30 and 35 seconds
     */
    private void randomWait() {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(30, 36));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Get <code>List</code> by given offset.
     *
     * @param query query to execute.
     * @param offset offset of records.
     * @return list of items.
     * @throws AirtableException if error occurs.
     */
    private List<T> select(Query query, String offset) throws AirtableException {
        return select(new Query() {
            @Override
            public Integer getMaxRecords() {
                return query.getMaxRecords();
            }

            @Override
            public String getView() {
                return query.getView();
            }

            @Override
            public List<Sort> getSort() {
                return query.getSort();
            }

            @Override
            public String filterByFormula() {
                return query.filterByFormula();
            }

            @Override
            public String[] getFields() {
                return query.getFields();
            }

            @Override
            public Integer getPageSize() {
                return query.getPageSize();
            }

            @Override
            public String getOffset() {
                return offset;
            }
        });
    }

    /**
     * Select with parameter maxRecords
     *
     * @param maxRecords maximum of records per request.
     * @return list of items.
     * @throws AirtableException if error occurs.
     */
    public List<T> select(Integer maxRecords) throws AirtableException {
        return select(new Query() {
            @Override
            public Integer getMaxRecords() {
                return maxRecords;
            }

            @Override
            public String getView() {
                return null;
            }

            @Override
            public List<Sort> getSort() {
                return null;
            }

            @Override
            public String filterByFormula() {
                return null;
            }

            @Override
            public String[] getFields() {
                return null;
            }

            @Override
            public Integer getPageSize() {
                return null;
            }

            @Override
            public String getOffset() {
                return null;
            }
        });
    }

    /**
     * Select data of table by defined view.
     *
     * @param view name of view.
     * @return list of items.
     * @throws AirtableException if error occurs.
     */
    public List<T> select(String view) throws AirtableException {
        return select(new Query() {
            @Override
            public Integer getMaxRecords() {
                return null;
            }

            @Override
            public String getView() {
                return view;
            }

            @Override
            public List<Sort> getSort() {
                return null;
            }

            @Override
            public String filterByFormula() {
                return null;
            }

            @Override
            public String[] getFields() {
                return null;
            }

            @Override
            public Integer getPageSize() {
                return null;
            }

            @Override
            public String getOffset() {
                return null;
            }
        });
    }

    /**
     * select Table data with defined sortation.
     *
     * @param sortation  sortation of result set.
     * @return list of items.
     * @throws AirtableException if error occurs.
     */
    public List<T> select(Sort sortation) throws AirtableException {
        final List<Sort> sortList = new ArrayList<>();
        sortList.add(sortation);

        return select(new Query() {
            @Override
            public Integer getMaxRecords() {
                return null;
            }

            @Override
            public String getView() {
                return null;
            }

            @Override
            public List<Sort> getSort() {
                return sortList;
            }

            @Override
            public String filterByFormula() {
                return null;
            }

            @Override
            public String[] getFields() {
                return null;
            }

            @Override
            public Integer getPageSize() {
                return null;
            }

            @Override
            public String getOffset() {
                return null;
            }
        });
    }

    /**
     * Select only Table data with defined fields.
     *
     * @param fields array of requested fields.
     * @return list of item using only requested fields.
     * @throws AirtableException if error occurs.
     */
    public List<T> select(String[] fields) throws AirtableException {

        return select(new Query() {
            @Override
            public Integer getMaxRecords() {
                return null;
            }

            @Override
            public String getView() {
                return null;
            }

            @Override
            public List<Sort> getSort() {
                return null;
            }

            @Override
            public String filterByFormula() {
                return null;
            }

            @Override
            public String[] getFields() {
                return fields;
            }

            @Override
            public Integer getPageSize() {
                return null;
            }

            @Override
            public String getOffset() {
                return null;
            }
        });
    }

    /**
     * Get List of records of response.
     *
     * @param response response of request.
     * @return list of records.
     */
    private List<T> getList(HttpResponse<Records> response) {

        final Records records = response.getBody();
        final List<T> list = new ArrayList<>();

        for (Map<String, Object>  rec: records.getRecords()) {
            T item = null;
            try {
                item = transform(rec, this.type.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                LOG.error(e.getMessage(), e);
            }
            list.add(item);
        }
        return list;
    }

    /**
     * Find record by given id.
     *
     * @param id id of record.
     * @return searched record.
     * @throws AirtableException if error occurs.
     */
    public T find(final String id) throws AirtableException {

        RecordItem body;

        HttpResponse<RecordItem> response;
        try {
            response = Unirest.get(getTableEndpointUrl() + "/" + id)
                    .header("accept", MIME_TYPE_JSON)
                    .header("Authorization", getBearerToken())
                    .asObject(RecordItem.class);
        } catch (UnirestException e) {
            throw new AirtableException(e);
        }
        int code = response.getStatus();

        if (200 == code) {
            body = response.getBody();
        } else if (429 == code) {
            randomWait();
            return find(id);
        } else {
            HttpResponseExceptionHandler.onResponse(response);
            body = null;
        }

        try {
            if(body != null) {
                return transform(body, this.type.getDeclaredConstructor().newInstance());
            } else {
                return null;
            }
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            throw new AirtableException(e);
        }
    }

    /**
     * Create Record of given Item.
     *
     * @param item the item to be created
     * @return the created item
     * @throws AirtableException if error occurs.
     * @throws IllegalAccessException if error occurs.
     * @throws InvocationTargetException if error occurs.
     * @throws NoSuchMethodException if error occurs.
     */
    public T create(final T item) throws AirtableException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        RecordItem responseBody = null;

        checkProperties(item);

        PostRecord<T> body = new PostRecord<>();
        body.setFields(item);

        HttpResponse<RecordItem> response;
        try {
            response = Unirest.post(getTableEndpointUrl())
                    .header("accept", MIME_TYPE_JSON)
                    .header("Authorization", getBearerToken())
                    .header("Content-type", MIME_TYPE_JSON)
                    .body(body)
                    .asObject(RecordItem.class);
        } catch (UnirestException e) {
            throw new AirtableException(e);
        }

        int code = response.getStatus();

        if (200 == code) {
            responseBody = response.getBody();
        } else if (429 == code) {
            randomWait();
            return create(item);
        } else {
            HttpResponseExceptionHandler.onResponse(response);
        }

        try {
            if(responseBody!= null) {
                return transform(responseBody, this.type.getDeclaredConstructor().newInstance());
            } else {
                return null;
            }
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            LOG.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * Update given <code>item</code> in storage.
     *
     * @param item Item to update.
     * @return updated <code>item</code> returned by airtable.
     * @throws AirtableException if error occurs.
     * @throws IllegalAccessException if error occurs.
     * @throws InvocationTargetException if error occurs.
     * @throws NoSuchMethodException if error occurs.
     */
    public T update(final T item) throws AirtableException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        RecordItem responseBody = null;

        String id = getIdOfItem(item);

        PostRecord<T> body = new PostRecord<>();
        body.setFields(filterFields(item));

        HttpResponse<RecordItem> response;
        try {
            response = Unirest.patch(getTableEndpointUrl() + "/" + id)
                    .header("accept", MIME_TYPE_JSON)
                    .header("Authorization", getBearerToken())
                    .header("Content-type", MIME_TYPE_JSON)
                    .body(body)
                    .asObject(RecordItem.class);
        } catch (UnirestException e) {
            throw new AirtableException(e);
        }

        int code = response.getStatus();

        if (200 == code) {
            responseBody = response.getBody();
        } else if (429 == code) {
            randomWait();
            return update(item);
        } else {
            HttpResponseExceptionHandler.onResponse(response);
        }

        T result;
        try {
            if(responseBody != null) {
                result = transform(responseBody, this.type.getDeclaredConstructor().newInstance());
            } else {
                result = null;
            }
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            LOG.error(e.getMessage(), e);
            result = null;
        }

        return result;
    }

    /**
     * Replace the given item in storage.
     * @param item item to replace.
     * @return replaced item.
     */
    public T replace(T item) {

        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * Delete Record by given id
     *
     * @param id Id of the row to delete.
     * @return true if success.
     * @throws AirtableException if error occurs.
     */
    public boolean destroy(String id) throws AirtableException {

        boolean isDeleted;

        HttpResponse<Delete> response;
        try {
            response = Unirest.delete(getTableEndpointUrl() + "/" + id)
                    .header("accept", MIME_TYPE_JSON)
                    .header("Authorization", getBearerToken())
                    .asObject(Delete.class);
        } catch (UnirestException e) {
            throw new AirtableException(e);
        }
        int code = response.getStatus();

        if (200 == code) {
            Delete body = response.getBody();
            isDeleted = body.isDeleted();
        } else if (429 == code) {
            randomWait();
            return destroy(id);
        } else {
            isDeleted = false;
            HttpResponseExceptionHandler.onResponse(response);
        }

//        if (!body.isDeleted()) {
//            throw new AirtableException("Record id: " + body.getId() + " could not be deleted.");
//        }

        return isDeleted;
    }

    /**
     * Get the parent base.
     * @return parent base.
     */
    private Base base() {
        return parent;
    }

    /**
     * Get the endpoint for the specified table.
     *
     * @return URL of tables endpoint.
     */
    private String getTableEndpointUrl() {

        return base().airtable().endpointUrl() + "/" + base().name() + "/" + this.name;
    }

    /**
     * Get Bearer Token for Authentication Header.
     *
     * @return Bearer Token.
     */
    private String getBearerToken() {
        return "Bearer " + base().airtable().accessToken();
    }

    /**
     * Transform the record to the given type.
     * @param rec record to transform.
     * @param type type of transformation.
     * @return transformed record.
     * @throws IllegalAccessException if error occurs.
     */
    private T transform(Map<String, Object> rec, T type) throws InvocationTargetException, IllegalAccessException {
        T retval = type;
        for (String key : rec.keySet()) {
            if ("fields".equals(key)) {
                //noinspection unchecked
                retval = transform((Map<String, Object>) rec.get("fields"), type);
            } else {
                setProperty(retval, key, rec.get(key));
            }
        }

        return retval;
    }

    /**
     * Transform the record to the given type.
     * @param rec record to transform.
     * @param type Type to  transform into
     * @return transformed record.
     * @throws InvocationTargetException if error occurs.
     * @throws IllegalAccessException if error occurs.
     */
    private T transform(RecordItem rec, T type) throws InvocationTargetException, IllegalAccessException {
        T retval = type;
        setProperty(retval, FIELD_ID, rec.getId());
        setProperty(retval, FIELD_CREATED_TIME, rec.getCreatedTime());

        retval = transform(rec.getFields(), retval);

        return retval;
    }

    /**
     *
     * @param retval The object upon which to perform the setting operation
     * @param key The name of the field on retval to set
     * @param value The object representing the new value of field
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException when key cannot be matched to any field of retval
     */
    private void setProperty(T retval, String key, Object value) throws IllegalAccessException, InvocationTargetException, IllegalArgumentException {
        String property = key2property(key);

        boolean foundSerializedNameAnnotation = false;

        for (final Field f : this.type.getDeclaredFields()) {
            final SerializedName annotation = f.getAnnotation(SerializedName.class);

            if (annotation != null && property.equalsIgnoreCase(annotation.value())) {
                property = f.getName();
                foundSerializedNameAnnotation = true;
                break;
            }
        }

        if (propertyExists(retval, property)) {
            BeanUtils.setProperty(retval, property, value);
        } else {
            if(!foundSerializedNameAnnotation && !SourceVersion.isName(property)) {
                LOG.error("Key '{}' contains illegal characters for a java identifier, but no field with a matching @SerializedName is present for type {}.", property, this.type.getName());
            }
            throw new IllegalArgumentException(this.type.getName() + " does not have a property corresponding to [" + property + "]");
        }
    }

    /**
     * Convert AirTable ColumnName to Java PropertyName.
     *
     * @param key column name
     * @return property name
     */
    String key2property(final String key) {

        if(key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key was null or empty.");
        }

        String property = key.trim();
        property = property.substring(0, 1).toLowerCase() + property.substring(1);

        return property;
    }

    /**
     * Check if writable property exists.
     *
     * @param bean bean to inspect
     * @param property name of property
     * @return true if writable property exists.
     */
    private static boolean propertyExists(Object bean, String property) {
        return PropertyUtils.isReadable(bean, property)
                && PropertyUtils.isWriteable(bean, property);
    }

    /**
     * Checks if the Property Values of the item are valid for the Request.
     *
     * @param item the item to check
     * @throws AirtableException if error occurs.
     * @throws IllegalAccessException  if error occurs.
     * @throws InvocationTargetException if error occurs.
     * @throws NoSuchMethodException if error occurs.
     */
    private void checkProperties(T item) throws AirtableException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        if (propertyExists(item, FIELD_ID) || propertyExists(item, FIELD_CREATED_TIME)) {
            Field[] attributes = item.getClass().getDeclaredFields();
            for (Field attribute : attributes) {
                String attrName = attribute.getName();
                if (FIELD_ID.equals(attrName) || FIELD_CREATED_TIME.equals(attrName)) {
                    if (BeanUtils.getProperty(item, attribute.getName()) != null) {
                        throw new AirtableException("Property " + attrName + " should be null!");
                    }
                } else if ("photos".equals(attrName)) {
                    List<Attachment> obj = (List<Attachment>) BeanUtilsBean.getInstance().getPropertyUtils().getProperty(item, "photos");
                    checkPropertiesOfAttachement(obj);
                }
            }
        }

    }

    /**
     * Check properties of Attachement objects.
     *
     * @param attachments list of attachments.
     * @throws AirtableException if error occurs.
     * @throws IllegalAccessException if error occurs.
     * @throws InvocationTargetException if error occurs.
     * @throws NoSuchMethodException if error occurs.
     */
    private void checkPropertiesOfAttachement(List<Attachment> attachments) throws AirtableException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        if (attachments != null) {
            for (int i = 0; i < attachments.size(); i++) {
                if (propertyExists(attachments.get(i), FIELD_ID) || propertyExists(attachments.get(i), "size")
                        || propertyExists(attachments.get(i), "type") || propertyExists(attachments.get(i), "filename")) {

                    final Field[] attributesPhotos = attachments.getClass().getDeclaredFields();
                    for (Field attributePhoto : attributesPhotos) {
                        final String namePhotoAttribute = attributePhoto.getName();
                        if ((FIELD_ID.equals(namePhotoAttribute)
                                || "size".equals(namePhotoAttribute)
                                || "type".equals(namePhotoAttribute)
                                || "filename".equals(namePhotoAttribute)
                            ) && (BeanUtils.getProperty(attachments.get(i), namePhotoAttribute) != null)) {
                            throw new AirtableException("Property " + namePhotoAttribute + " should be null!");
                        }
                    }
                }
            }
        }
    }

    /**
     * Get the String id of the item.
     *
     * @param item the item to get the id from.
     * @return the id of the item.
     * @throws AirtableException if error occurs.
     * @throws IllegalAccessException if error occurs.
     * @throws InvocationTargetException if error occurs.
     * @throws NoSuchMethodException if error occurs.
     */
    private String getIdOfItem(T item) throws AirtableException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        if (propertyExists(item, FIELD_ID)) {
            final String id = BeanUtils.getProperty(item, FIELD_ID);
            if (id != null) {
                return id;
            }
        }
        throw new AirtableException("Id of " + item + " not Found!");
    }

    /**
     * Filter the Fields of the PostRecord Object. Id and created Time are set
     * to null so Object Mapper doesn't convert them to JSON.
     *
     * @param item the item to filter
     * @return the filtered item
     * @throws IllegalAccessException if error occurs.
     * @throws InvocationTargetException if error occurs.
     * @throws NoSuchMethodException if error occurs.
     */
    private T filterFields(T item) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        final Field[] attributes = item.getClass().getDeclaredFields();

        for (Field attribute : attributes) {
            String attrName = attribute.getName();
            if ((FIELD_ID.equals(attrName) || FIELD_CREATED_TIME.equals(attrName))
                    && (BeanUtils.getProperty(item, attrName) != null)) {
                BeanUtilsBean.getInstance().getPropertyUtils().setProperty(item, attrName, null);
            }
        }

        return item;
    }
}
