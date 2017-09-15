/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
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
