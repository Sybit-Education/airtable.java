package com.sybit.airtable;/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */

import java.util.List;

/**
 * Interface for specific queries.
 */
@SuppressWarnings("WeakerAccess")
public interface Query {

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
     *
     * @return
     */
    String filterByFormula();
}
