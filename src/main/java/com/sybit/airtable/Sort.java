/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;

/**
 * Sortation helper.
 */
public class Sort {

    public enum Sorting {asc, desc};

    private String field;

    private Sorting sort;

    /**
     *
     * @param field
     * @param sort
     */
    public Sort(String field, Sorting sort) {
        this.field = field;
        this.sort = sort;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Sorting getSort() {
        return sort;
    }

    public void setSort(Sorting sort) {
        this.sort = sort;
    }
}
