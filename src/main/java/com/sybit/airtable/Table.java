/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sybit.airtable.vo.RecordItem;
import com.sybit.airtable.vo.Records;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.http.client.HttpResponseException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
class Table<T> {

    private static final Logger LOG = Logger.getLogger( Table.class.getName() );

    private final String name;
    private final Class<T>  type;

    private Base parent;

    /**
     *
     * @param name
     * @param type
     */
    public Table(String name, Class<T> type) {
        this.type = type;
        this.name = name;
    }

    /**
     *
     * @param name
     * @param type
     * @param base
     */
    public Table(String name, Class<T> type, Base base){
        this(name, type);
        setParent(base);
    }

    /**
     *
     * @param parent
     */
    public void setParent(Base parent) {
        this.parent = parent;
    }

    /**
     *
     * @return
     * @throws AirtableException
     */
    public List<T> select() throws AirtableException, HttpResponseException {

        HttpResponse<Records> response;
        try {
            response = Unirest.get( getTableEndpointUrl())
                            .header("accept", "application/json")
                            .header("Authorization", "Bearer " + base().airtable().apiKey())
                            .asObject(Records.class);
        }
        catch (UnirestException e) {

            throw new AirtableException(e);
        }

        int code = response.getStatus();
        Records records;

        if(200 == code) {
            records = response.getBody();
        } else {
            throw new HttpResponseException(code, response.getStatusText());
        }

        List<T> list = new ArrayList<T>(records.getRecords().size());
        for(Map<String, Object> record : records.getRecords()) {
            T item = null;
            try {
                item = transform(record, this.type.newInstance());
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                LOG.throwing(this.getClass().getName(), "select", e);
            }
            list.add(item);
        }

        return list;
    }

    /**
     *
     * @param id
     * @return
     * @throws AirtableException
     */
    public T find(String id) throws AirtableException, HttpResponseException {

        RecordItem body;
        try {
            HttpResponse<RecordItem> response = Unirest.get( getTableEndpointUrl() + "/" + id)
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + base().airtable().apiKey())
                .asObject(RecordItem.class);
            int code = response.getStatus();
            //InputStream rawBody = response.getRawBody();
            if(200 == code) {
                body = response.getBody();
            } else {
                throw new HttpResponseException(code, response.getStatusText());
            }
        } catch (UnirestException e) {
            throw new AirtableException(e);
        }


        try {
            return transform(body, this.type.newInstance() );
        } catch (NoSuchMethodException | InvocationTargetException |
                IllegalAccessException | InstantiationException e) {
            LOG.throwing(this.getClass().getName(), "find", e);
        }

        return null;
    }

    public T create(T item) {

        return item;
    }

    public T update(T item) {

        return item;
    }

    public T replace(T item) {

        return item;
    }

    public T destroy(T item) {

        return item;
    }

    /**
     *
     * @return
     */
    private Base base() {
        return parent;
    }

    /**
     *
     * @return
     */
    private String getTableEndpointUrl() {
        String url = base().airtable().endpointUrl() + "/" + base().name() + "/" + this.name;

        return  url;
    }

    /**
     *
     * @param record
     * @param retval
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private T transform(Map<String, Object> record, T retval) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        for(String key: record.keySet()) {
            if(key.equals("fields")) {
                retval = transform((Map<String, Object>)record.get("fields"), retval);
            } else {
                setProperty(retval, key, record.get(key));
            }
        }

        return retval;
    }

    /**
     *
     * @param record
     * @param retval
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private T transform(RecordItem record, T retval) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        setProperty(retval, "id", record.getId());
        setProperty(retval, "createdTime", record.getCreatedTime());

        retval = transform((Map<String, Object>)record.getFields(), retval);

        return retval;
    }

    /**
     *
     * @param retval
     * @param key
     * @param value
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private void setProperty(T retval, String key, Object value) throws IllegalAccessException, InvocationTargetException {
        final String property = key2property(key);

        if (propertyExists(retval, property)) {
            BeanUtils.setProperty(retval, property, value);
        } else if (false) {
            //Todo: get @SerializedName value ant then method.

        }else {
            LOG.log( Level.INFO,retval.getClass() + " does not support public setter for existing property [" + property + "]");
        }
    }

    /**
     * Convert AirTable ColumnName to Java PropertyName
     * @param key
     * @return
     */
    private String key2property(String key) {

        if(key.contains(" ") ) {
            LOG.log( Level.SEVERE, "Do not use spaces in column names: [" + key + "]");
        }
        if(key.contains("-") ) {
            LOG.log( Level.WARNING, "Do not use '-' in column names: [" + key + "]");
        }

        String property = key.trim();
        property = property.substring(0,1).toLowerCase() + property.substring(1, property.length());
        property = property.replace("-", "_");
        return property;
    }

    /**
     *
     * @param bean
     * @param property
     * @return
     */
    static boolean propertyExists (Object bean, String property) {
        return PropertyUtils.isReadable(bean, property) &&
                PropertyUtils.isWriteable(bean, property);
    }
}
