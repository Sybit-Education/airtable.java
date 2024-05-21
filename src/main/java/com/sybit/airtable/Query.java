/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;

import java.util.List;

/**
 * Interface for specific queries.
 *
 * @since 0.1
 */
@SuppressWarnings("WeakerAccess")
public interface Query {

    /**
     * get the fields to load.
     * @return Fields to be loaded
     */
    String [] getFields();

    /**
     * get the number of records per page.
     * @return the number of records per page
     */
    Integer getPageSize();

    /**
     * get the max number of records to load.
     * @return number of max rows to load.
     */
    Integer getMaxRecords();

    /**
     * get the view to load.
     * @return Name of view to load.
     */
    String getView();

    /**
     * get the sortation of result set.
     * @return sortation of result set.
     */
    List<Sort> getSort();

    /**
     * Define a filter formula.
     * see https://support.airtable.com/hc/en-us/articles/203255215-Formula-Field-Reference
     * @return get the filter formula.
     */
    String filterByFormula();

    /**
     * Offset to get more than 100 records.
     * The offset is provided by previous result.
     * @return the offset.
     */
    String getOffset();
}
