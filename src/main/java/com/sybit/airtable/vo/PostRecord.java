/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable.vo;


import java.io.Serializable;

/**
 * Value Object for the Post Record.
 * @author fzr
 * @param <T> the type of the fields
 */
public class PostRecord<T> implements Serializable {

    private T fields;

    /**
     * Constructor.
     */
    public PostRecord() {}

    /**
     * Get the fields.
     * @return the fields
     */
    public T getFields() {
        return fields;
    }

    /**
     * Set the fields.
     * @param fields the fields to set
     */
    public void setFields(T fields) {
        this.fields = fields;
    }


}
