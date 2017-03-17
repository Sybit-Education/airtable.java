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
    public void testSelectExistingTable() throws AirtableException, HttpResponseException {

        Base base = airtable.base("appe9941ff07fffcc");

        String tableName = "Movies";
        String testUrl = endpointUrl() + "/" + base.name() + "/" + tableName;


        List<Movie> retval = base.table(tableName, Movie.class).select();
        assertNotNull(retval);
        assertEquals(3, retval.size());
    }

    @Test(expected = AirtableException.class)
    public void testSelectNonExistingTable() throws AirtableException, HttpResponseException {

        Base base = airtable.base("appe9941ff07fffcc");

        List<Movie> retval = base.table("NotExists", Movie.class).select();
        assertNotNull(retval);
    }

}
