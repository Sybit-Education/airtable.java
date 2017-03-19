/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;

import com.sybit.airtable.exception.AirtableException;
import com.sybit.airtable.movies.Movie;
import com.sybit.airtable.test.WireMockBaseTest;
import org.apache.http.client.HttpResponseException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class TableSelectTest extends WireMockBaseTest {


    @Test
    public void testSelectTable() throws AirtableException, HttpResponseException {

        Base base = airtable.base("appe9941ff07fffcc");

        String tableName = "Movies";
        List<Movie> retval = base.table(tableName, Movie.class).select();
        assertNotNull(retval);
        assertEquals(10, retval.size());
        Movie mov = retval.get(0);
        assertEquals("Sister Act", mov.getName());

    }

    @Test
    public void testSelectTableMaxRecords() throws AirtableException, HttpResponseException {

        Base base = airtable.base("appe9941ff07fffcc");

        String tableName = "Movies";
        List<Movie> retval = base.table(tableName, Movie.class).select(2);
        assertNotNull(retval);
        assertEquals(2, retval.size());
        Movie mov = retval.get(0);
        assertEquals("Sister Act", mov.getName());

    }

    @Test
    public void testSelectTableSorted() throws AirtableException, HttpResponseException {

        Base base = airtable.base("appe9941ff07fffcc");

        String tableName = "Movies";
        List<Movie> retval = base.table(tableName, Movie.class).select(new Sort("Name", Sort.Sorting.asc));
        assertNotNull(retval);
        assertEquals(10, retval.size());
        Movie mov = retval.get(0);
        assertEquals("Billy Madison", mov.getName());

        retval = base.table(tableName, Movie.class).select(new Sort("Name", Sort.Sorting.desc));
        assertNotNull(retval);
        assertEquals(10, retval.size());
        mov = retval.get(0);
        assertEquals("You've got Mail", mov.getName());

    }

    @Test
    public void testSelectTableView() throws AirtableException, HttpResponseException {

        Base base = airtable.base("appe9941ff07fffcc");

        String tableName = "Movies";
        List<Movie> retval = base.table(tableName, Movie.class).select("Main View");
        assertNotNull(retval);
        assertEquals(10, retval.size());
        Movie mov = retval.get(0);
        assertEquals("The Godfather", mov.getName());

    }

    @Test(expected = AirtableException.class)
    public void testSelectNonExistingTable() throws AirtableException, HttpResponseException {

        Base base = airtable.base("appe9941ff07fffcc");

        List<Movie> retval = base.table("NotExists", Movie.class).select();
        assertNotNull(retval);
    }

}
