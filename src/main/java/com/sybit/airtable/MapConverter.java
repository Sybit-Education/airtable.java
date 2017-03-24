/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sybit.airtable;

import com.google.gson.internal.LinkedTreeMap;
import com.sybit.airtable.vo.Thumbnail;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.converters.AbstractConverter;

/**
 *
 * org.apache.commons.beanutils.Converter implementaion
 * that handles conversion to and from Map&lt;String,T&gt; objects.
 *
 * This implementation converts Map&lt;String,T&gt; to Map<String,mapClass>.
 * The mapClass can be set to the Class that is needed.
 * 
 * @author fzr
 */
public class MapConverter extends AbstractConverter{

    private Class mapClass;

    /**
     * Converts the Input Object into a Map.
     * 
     *  
     * @param type The type of the Input Object
     * @param value The value of the Input Object
     * @return A Map
     * @throws Throwable 
     */
    @Override
    protected <T> T convertToType(Class<T> type, Object value) throws Throwable {
              
        Class<T> sourceType = (Class<T>) value.getClass();
        Map<String, Object> returnMap = new HashMap<String, Object>();
            
        if(value instanceof LinkedTreeMap){
            for (String key : ((LinkedTreeMap<String, Object>) value).keySet()) {
                Object instanz = this.mapClass.newInstance();
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
     * 
     * Default Conversion to specified Class.
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
     * 
     * Default toString Conversion.
     * 
     * @param type The Class of the type
     * @param value The String value 
     * @param returnMap A Map of all currently converted Objects
     * @return A Map
     */
    
     private Map<String,Object> toStringMap(final Class type, final String value,Map<String, Object> returnMap) {
        
        List returnList = new ArrayList();
        
        if (type.equals(String.class)) {      
            returnList.add(type.cast(String.valueOf(value)));
            return (Map<String, Object>) returnList;
        }
        
        returnList.add(type.cast(String.valueOf(value)));    
        return (Map<String, Object>) returnList;
    }

    @Override
    protected Class<?> getDefaultType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setMapClass(Class<Thumbnail> aClass) {
        this.mapClass = aClass;
    }
    
}
