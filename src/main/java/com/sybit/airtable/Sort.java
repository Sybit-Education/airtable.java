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

    public enum Direction {asc, desc}

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
     *
     * @return
     */
    public String getField() {
        return field;
    }

    /**
     *
     * @return
     */
    public Direction getDirection() {
        return direction;
    }
}
