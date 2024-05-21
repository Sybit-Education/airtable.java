/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable.vo;

import java.io.Serializable;
import java.util.Map;

/**
 * Value Object for the Record Item.
 */
public class RecordItem implements Serializable {
    private String id;

    private Map<String, Object> fields;

    private String createdTime;

    /**
     * Constructor.
     */
    public RecordItem() {
    }

    /**
     * Get the id.
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id.
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the fields.
     * @return the fields
     */
    public Map<String, Object> getFields() {
        return fields;
    }

    /**
     * Set the fields.
     * @param fields the fields to set
     */
    public void setFields(Map<String, Object> fields) {
        this.fields = fields;
    }

    /**
     * Get the created time.
     * @return the created time
     */
    public String getCreatedTime() {
        return createdTime;
    }

    /**
     * Set the created time.
     * @param createdTime the created time to set
     */
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
