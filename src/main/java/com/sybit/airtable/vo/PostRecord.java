/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sybit.airtable.vo;


/**
 *
 * @author fzr
 */
public class PostRecord<T> {
    
    private T fields;

    /**
     * @return the fields
     */
    public T getFields() {
        return fields;
    }

    /**
     * @param fields the fields to set
     */
    public void setFields(T fields) {
        this.fields = fields;
    }
    
    
}
