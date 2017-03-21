/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;

import com.sybit.airtable.vo.Records;

import java.util.HashMap;
import java.util.Map;

/**
 * Representation Class of Airtable Base.
 *
 * @since 0.1
 */
public class Base {

    private final Map<String, Table> tableMap = new HashMap<>();

    private final String base;

    private Airtable parent;

    /**
     * Create Airtable Base with given base ID.
     *
     * ID could be found at https://airtable.com if you select your current base.
     * @param base base ID could be found at https://airtable.com if you select your current base.
     */
    public Base(String base) {
        this.base = base;
    }

    /**
     * Create Airtable Base with given base ID.
     *
     * @param base base ID could be found at https://airtable.com if you select your current base.
     * @param airtable parent airtable object
     */
    public Base(String base, Airtable airtable) {
        this(base);
        setParent(airtable);
    }

    /**
     * Set Airtable object as parent.
     * @param parent the base Airtable object.
     */
    protected void setParent(Airtable parent) {
        this.parent = parent;
    }

    /**
     * Get Airtable object as parent.
     * @return
     */
    public Airtable airtable() {
        return parent;
    }

    /**
     * Get Table object of given table.
     * @param name Name of required table.
     * @return Object to access table.
     */
    public Table table(String name) {

        return table(name, Records.class);
    }

    /**
     * Get Table object of given table.
     * @param name Name of required table.
     * @param clazz
     * @return Object to access table.
     */
    public Table table(String name, Class clazz) {

        if(!tableMap.containsKey(name)) {
            Table t = new Table(name, clazz);
            t.setParent(this);
            tableMap.put(name, t);
        }

        return  tableMap.get(name);
    }

    /**
     * Get base id of base.
     * @return base id
     */
    public String name() {
        return base;
    }
}
