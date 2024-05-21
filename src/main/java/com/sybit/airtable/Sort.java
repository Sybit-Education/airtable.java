/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;

/**
 * Sorting of query results.
 *
 * @since 0.1
 */
public class Sort {

    /**
     * Sort directions.
     */
    public enum Direction {
        /** Ascending sort. */
        asc,
        /** Descending sort. */
        desc
    }

    private final String field;

    private final Direction direction;

    /**
     * Sort ascending given field.
     *
     * @param field name of field
     */
    public Sort(String field) {
        this(field, Direction.asc);
    }
    /**
     * Sort given field by defined direction.
     *
     * @param field name of field
     * @param direction sort direction
     */
    public Sort(String field, Direction direction) {
        this.field = field;
        this.direction = direction;
    }

    /**
     * Get the field name.
     * @return field name
     */
    public String getField() {
        return field;
    }

    /**
     * Get the sort direction.
     * @return sort direction
     */
    public Direction getDirection() {
        return direction;
    }
}
