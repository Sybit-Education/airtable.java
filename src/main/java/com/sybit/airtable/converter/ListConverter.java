/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sybit.airtable.converter;

import com.google.gson.internal.LinkedTreeMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.converters.AbstractConverter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * org.apache.commons.beanutils.Converter implementaion
 * that handles conversion to and from List&lt;T&gt; objects.
 *
 * <p>This implementation converts List&lt;T&gt; to List&lt;innerClass&gt;.
 * The innerClass can be set to the Class that is needed.</p>
 *
 * @author fzr
 */
public class ListConverter extends AbstractConverter {
    
    private Class listClass;
    
    /**
     * 
     * Method overwritten so it doesent return only the first Element of the Array. 
     * 
     * @param value The Input Value
     * @return value The value to be returned
     */
    @Override
    protected Object convertArray(final Object value) {
        return value;
    }

    /**
     * 
     * Convert the Input Object into a List of Attachements
     * 
     * This Method handles the conversion from a List of LinkedHashMaps to a List of Attachement Objects.
     * 
     * <p>If the Input Object is no List or the List Item is no LinkedHashMap everything will be converted into a (List?) String.</p>
     * 
     * @param type The type of the Input Object
     * @param value The value of the Input Object
     * @return A List
     * @throws java.lang.InstantiationException If no Instance of the listClass can be instantiated
     * @throws java.lang.IllegalAccessException If the Object can't be accest
     * @throws java.lang.reflect.InvocationTargetException If th Target can't be accessed
     */
    @Override
    protected <T> T convertToType(final Class<T> type, Object value) throws InstantiationException, IllegalAccessException, InvocationTargetException  {
        
        List<T> returnList = new ArrayList<>();
          
        if(value instanceof List){
            for (T item: ((List<T>) value)){
                if(item instanceof LinkedTreeMap){
                    Object instanz = this.listClass.newInstance();
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
    
    /**
     * Default toString conversion
     * 
     * @param type The type of the Class
     * @param value The String value
     * @param returnList A List of all currently converted Objects
     * @return A List
     */
    private List toStringList(final Class type, final String value,List returnList) {

        //FIXME why this if?
        if (type.equals(String.class)) {      
            returnList.add(String.valueOf(value));
            return returnList;
        }
        
        returnList.add(String.valueOf(value));    
        return returnList;
    }
    
    /**
     * Default conversion to specified Class.
     * 
     * <p>If conversion is not possible default toString.</p>
     * 
     * @param type The type of the Class
     * @param value The value of the Input Object
     * @param returnList A List of all currently converted Objects
     * @return A List
     */
    private List toClassList(final Class type, final Object value, List returnList) {
        
        if (type.equals(LinkedTreeMap.class)) {
            returnList.add(value);
            return returnList;
        }
        
        return toStringList(type,value.toString(),returnList);
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
