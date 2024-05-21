/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable.converter;

import com.google.gson.internal.LinkedTreeMap;
import com.sybit.airtable.vo.Thumbnail;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.converters.AbstractConverter;

/**
 *
 * Implementaion of <code>org.apache.commons.beanutils.Converter</code>
 * that handles conversion to and from Map&lt;String,T&gt; objects.
 *
 * <p>This implementation converts Map&lt;String,T&gt; to Map&lt;String,mapClass&gt;.
 * The mapClass can be set to the Class that is needed.</p>
 *
 * @author fzr
 */
public class MapConverter extends AbstractConverter{

    private Class mapClass;

    /**
     * Constructor.
     */
    public MapConverter() {
    }

    /**
     * Converts the input object into a <code>Map</code> object.
     *
     * @param <T> Target type of the conversion.
     * @param type Data type to which this value should be converted.
     * @param value TThe input value to be converted.
     * @return The converted <code>Map</code>
     * @throws Throwable if an error occurs converting to the specified type
     */
    @Override
    protected <T> T convertToType(Class<T> type, Object value) throws Throwable {

        if(mapClass == null) {
            throw new IllegalAccessException("mapClass is not initialized by setListClass().");
        }

        final Class<T> sourceType = (Class<T>) value.getClass();
        Map<String, Object> returnMap = new HashMap<>();

        if(value instanceof LinkedTreeMap){
            for (String key : ((LinkedTreeMap<String, Object>) value).keySet()) {
                Object instanz = this.mapClass.getDeclaredConstructor().newInstance();
                Object val = ((LinkedTreeMap) value).get(key);
                BeanUtils.setProperty(instanz,"name",key);
                for (String key2 : ((LinkedTreeMap<String, Object>) val).keySet()) {
                    Object val2 = ((LinkedTreeMap) val).get(key2);
                    BeanUtils.setProperty(instanz,key2,val2);
                }
                returnMap = toClassMap(sourceType,instanz,returnMap);
            }
            return (T) returnMap;
        }

        if(value instanceof String){
            return (T) toStringMap(sourceType,value.toString(),returnMap);
        }

        final String stringValue = value.toString().trim();
        if (stringValue.length() == 0) {
            return handleMissing(type);
        }

        return (T) toStringMap(sourceType,stringValue,returnMap);
    }

    /**
     * Default Conversion to specified <code>Class</code>.
     *
     * @param type The Class of the type
     * @param value The value of the Object
     * @param returnMap A Map of all currently converted Objects
     * @return A Map
     */
    private Map<String,Object> toClassMap(final Class type, final Object value,Map<String, Object> returnMap) {

        if (type.equals(LinkedTreeMap.class)) {
            if (value.getClass().equals(Thumbnail.class)) {
                returnMap.put(((Thumbnail)value).getName(),value);
            }
            return returnMap;
        }

        return toStringMap(type,value.toString(),returnMap);
    }

    /**
     * Default toString Conversion.
     *
     * @param type The Class of the type
     * @param value The String value
     * @param returnMap A Map of all currently converted Objects
     * @return A Map
     */

     private Map<String,Object> toStringMap(final Class type, final String value,Map<String, Object> returnMap) {

        if (type.equals(String.class)) {
            returnMap.put(value,value);
            return  returnMap;
        }

        returnMap.put(value,value);
        return returnMap;
    }

    @Override
    protected Class<?> getDefaultType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Set-Method for the MapClass
     *
     * @param aClass  The Parameter that is used
     */
    public void setMapClass(Class<Thumbnail> aClass) {
        this.mapClass = aClass;
    }

    /**
     * Get-Method for the MapClass
     *
     * @return this.mapClass
     */
    public Class getMapClass(){
        return this.mapClass;
    }
}
