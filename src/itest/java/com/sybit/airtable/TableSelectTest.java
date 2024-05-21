/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;

import com.sybit.airtable.exception.AirtableException;
import com.sybit.airtable.movies.Movie;
import com.sybit.airtable.mock.WireMockBaseTest;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class TableSelectTest extends WireMockBaseTest {


    @Test
    public void testSelectTable() throws AirtableException {


        List<Movie> retval = base.table("Movies", Movie.class).select();
        assertNotNull(retval);
        assertEquals(9, retval.size());

    }

    @Test
    public void testSelectTableMaxRecords() throws AirtableException {

        List<Movie> retval = base.table("Movies", Movie.class).select(2);
        assertNotNull(retval);
        assertEquals(2, retval.size());

    }

    @Test
    public void testSelectTableSorted() throws AirtableException {

        Table table = base.table("Movies", Movie.class);

        List<Movie> retval = table.select(new Sort("Name", Sort.Direction.asc));
        assertNotNull(retval);
        assertEquals(9, retval.size());
        Movie mov = retval.get(0);
        assertEquals("Billy Madison", mov.getName());

        retval = table.select(new Sort("Name", Sort.Direction.desc));
        assertNotNull(retval);
        assertEquals(9, retval.size());
        mov = retval.get(0);
        assertEquals("You've Got Mail", mov.getName());

    }

    @Test
    public void testSelectTableView() throws AirtableException {


        List<Movie> retval = base.table("Movies", Movie.class).select("Main View");
        assertNotNull(retval);
        assertEquals(9, retval.size());
        Movie mov = retval.get(0);
        assertEquals("The Godfather", mov.getName());
    }

    @Test(expected = AirtableException.class)
    public void testSelectNonExistingTable() throws AirtableException {

        List<Movie> retval = base.table("NotExists", Movie.class).select();
        assertNotNull(retval);
    }

    @Test
    public void testSelectNonExistingTableExceptionMessage() {

        String message;
        try {
            base.table("NotExists", Movie.class).select();
            message = "Success";
        } catch (AirtableException e) {
            message = e.getMessage();
        }
        assertEquals("Could not find table NotExists in application " + base.name() + " (TABLE_NOT_FOUND) [Http code 404]", message);
    }

//    @Test
//    public void testSelectWithSerializedNames() throws AirtableException, HttpResponseException {
//
//
//        List<ActorSerializedNames> retval = base.table("SerializedNames", ActorSerializedNames.class).select();
//        assertNotNull(retval);
//        assertEquals("Marlon Brando", retval.get(0).getName());
//    }
}
