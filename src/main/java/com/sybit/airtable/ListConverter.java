/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sybit.airtable;

import com.google.gson.internal.LinkedTreeMap;
import java.util.Iterator;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.converters.AbstractConverter;

/**
 *
 * @author fzr
 */
public class ListConverter extends AbstractConverter {
    
    private LinkedTreeMap treeMap;
    private Class listClass;

    @Override
    protected <T> T convertToType(Class<T> type, Object value) throws Throwable {
        
        final Class<?> sourceType = value.getClass();
        
        if(value instanceof LinkedTreeMap){
            for (String key : ((LinkedTreeMap<String, Object>) value).keySet()) {
                Object val = ((LinkedTreeMap) value).get(key);
                BeanUtils.setProperty(this.listClass,key,val);
            }         
        }
        return null;
    }

    @Override
    protected Class<?> getDefaultType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setListClass(Class clazz){
        this.listClass = clazz;
    }
    
}
