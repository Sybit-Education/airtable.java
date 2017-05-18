package com.sybit.airtable;/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */

import java.util.List;

/**
 * Interface for specific queries.
 *
 * @since 0.1
 */
@SuppressWarnings("WeakerAccess")
public interface Query {
    
    /**
     * @return Fields to be loaded 
     */
    String [] getFields();
    
    /**
     * @return the number of records per page
     */
    Integer getPageSize();

    /**
     * @return number of max rows to load.
     */
    Integer getMaxRecords();

    /**
     * @return Name of view to load.
     */
    String getView();

    /**
     * @return sortation of result set.
     */
    List<Sort> getSort();

    /**
     * Define a filter formula.
     *
     * see https://support.airtable.com/hc/en-us/articles/203255215-Formula-Field-Reference
     * @return get the filter formula.
     */
    String filterByFormula();
}
