/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Value Object for the Records.
 */
public class Records {

    /**
     * List of records.
     */
    private List<Map<String, Object>> records;

    /**
     * Offset.
     */
    private String offset;

    /**
     * Constructor.
     */
    public Records() {
        // default constructor
    }

    /**
     * Get the records.
     * @return the records
     */
    public List<Map<String, Object>> getRecords() {
        return records;
    }

    /**
     * Set the records.
     * @param records the records to set
     */
    public void setRecords(List<Map<String, Object>> records) {
        this.records = records;
    }

    /**
     * Get the offset.
     * @return the offset
     */
    public String getOffset() {
        return offset;
    }

    /**
     * Set the offset.
     * @param offset the offset to set
     */
    public void setOffset(String offset) {
        this.offset = offset;
    }
}
