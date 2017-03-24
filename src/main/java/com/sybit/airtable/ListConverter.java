/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sybit.airtable;

import com.google.gson.internal.LinkedTreeMap;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.converters.AbstractConverter;

/**
 *
 * @author fzr
 */
public class ListConverter extends AbstractConverter {
    
    private Class listClass;
    
    @Override
    protected Object convertArray(final Object value) {
        return value;
    }

    @Override
    protected <T> T convertToType(final Class<T> type, Object value) throws Throwable {
        
        List<T> returnList = new ArrayList<T>();
          
        if(value instanceof List){
            for (T item: ((List<T>) value)){
                if(item instanceof LinkedTreeMap){
                    Object instanz = this.getListClass().newInstance();
                    for (String key : ((LinkedTreeMap<String, Object>) item).keySet()) {
                        Object val = ((LinkedTreeMap) item).get(key);            
                        BeanUtils.setProperty(instanz,key,val);     
                    }         
                    returnList = toClassList(item.getClass(),instanz,returnList);        
                }
                if(item instanceof String){
                    returnList = toStringList(item.getClass(),item.toString(),returnList);
                }
                
            }       
            return (T) returnList;  
        }
        
        
        //TODO überarbeiten
        if(value instanceof String){
            return (T) toStringList(value.getClass(),value.toString(),returnList);
        }
        
        final String stringValue = value.toString().trim();
        if (stringValue.length() == 0) {
            return handleMissing(type);
        }
            
        return (T) toStringList(value.getClass(),stringValue,returnList);
    }
    
    private List toStringList(final Class type, final String value,List returnList) {
         
        if (type.equals(String.class)) {      
            returnList.add(String.valueOf(value));
            return returnList;
        }
        
        returnList.add(String.valueOf(value));    
        return returnList;
    }
    
    private List toClassList(final Class type, final Object value, List returnList) {
        
        if (type.equals(LinkedTreeMap.class)) {
            returnList.add(value);
            return returnList;
        }
        
        return toStringList(type,value.toString(),returnList);
    }

    /**
     * @return the listClass
     */
    public Class getListClass() {
        return listClass;
    }

    /**
     * @param listClass the listClass to set
     */
    public void setListClass(Class listClass) {
        this.listClass = listClass;
    }
    
    

    //TODO Default überlegen
    @Override
    protected Class<?> getDefaultType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }







    

    
    
}
