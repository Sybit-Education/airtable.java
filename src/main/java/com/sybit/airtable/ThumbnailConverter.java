/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sybit.airtable;

import com.google.gson.internal.LinkedTreeMap;
import com.sybit.airtable.vo.Thumbnail;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.converters.AbstractConverter;

/**
 *
 * @author fzr
 */
public class ThumbnailConverter extends AbstractConverter{

    private Class mapClass;

    @Override
    protected <T> T convertToType(Class<T> type, Object value) throws Throwable {
        
        
        Class<T> sourceType = (Class<T>) value.getClass();
        Object instanz = this.mapClass.newInstance();
        
        if(value instanceof LinkedTreeMap){
            for (String key : ((LinkedTreeMap<String, Object>) value).keySet()) {
                Object val = ((LinkedTreeMap) value).get(key);            
                BeanUtils.setProperty(instanz,key,val);          
            }         
            return toClassList(sourceType,instanz);
        }
        
        if(value instanceof String){
            return toStringList(sourceType,value.toString());
        }
        
        final String stringValue = value.toString().trim();
        if (stringValue.length() == 0) {
            return handleMissing(type);
        }
            
        return toStringList(sourceType,stringValue);
    }
    
    private <T> T toClassList(final Class<T> type, final Object value) {
        
        if (type.equals(LinkedTreeMap.class)) {
            List<T> returnList = new ArrayList<T>();
            returnList.add((T) value);
            return (T) returnList;
        }
        
        return toStringList(type,value.toString());
    }
    
     private <T> T toStringList(final Class<T> type, final String value) {
        
        List<T> returnList = new ArrayList<T>();
        
        if (type.equals(String.class)) {      
            returnList.add(type.cast(String.valueOf(value)));
            return (T) returnList;
        }
        
        returnList.add(type.cast(String.valueOf(value)));    
        return (T) returnList;
    }

    @Override
    protected Class<?> getDefaultType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setMapClass(Class<Thumbnail> aClass) {
        this.mapClass = aClass;
    }
    
}
