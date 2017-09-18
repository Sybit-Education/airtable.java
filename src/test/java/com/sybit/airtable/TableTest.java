/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;

import com.sybit.airtable.exception.AirtableException;
import com.sybit.airtable.movies.Actor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 *
 * @author fzr
 */
public class TableTest {

    private Base base;

    @Before
    public void before() throws AirtableException {

        Airtable airtable = new Airtable().configure(new Configuration("123", "https://url", null));
        this.base = new Base("base", airtable);

    }

    @Test(expected = java.lang.AssertionError.class)
    public void testTableAssertions() {
        Table table = new Table(null, null);

    }

    @Test
    public void testTable() {

        Table table = new Table("table", Actor.class, this.base);
        assertNotNull(table);

    }

    @Test
    public void setParentTest() {

        Table table = new Table("table", Actor.class);
        table.setParent(this.base);
        assertNotNull(table);
    }

    @Test
    public void key2properties() {
        Table table = new Table("table", Actor.class);

        String actual = table.key2property("FirstName");
        Assert.assertEquals("firstName", actual);

        actual = table.key2property("First-Name");
        Assert.assertEquals("first-Name", actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void key2properties_EmptyArgument() {
        Table table = new Table("table", Actor.class);
        String actual = table.key2property("");
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void key2properties_NullArgument() {
        Table table = new Table("table", Actor.class);
        String actual = table.key2property(null);
        Assert.fail();
    }

}
