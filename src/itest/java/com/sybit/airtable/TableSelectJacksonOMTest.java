/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.mashape.unirest.http.ObjectMapper;
import com.sybit.airtable.exception.AirtableException;
import com.sybit.airtable.movies.Movie;
import com.sybit.airtable.mock.WireMockBaseTest;
import org.apache.http.client.HttpResponseException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by kobisuissa on 18/04/17.
 */
public class TableSelectJacksonOMTest extends WireMockBaseTest {

    @Before
    public void setup() throws AirtableException {
        airtable.configure(new ObjectMapper() {

            final com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

            @Override
            public <T> T readValue(final String value, final Class<T> valueType) {
                try {
                    return objectMapper.readValue(value, valueType);
                } catch (UnrecognizedPropertyException e) {
                    try {
                        // dummy instance to follow code flow and execute HttpResponseExceptionHandler.onResponse
                        T instance = valueType.newInstance();
                        return instance;
                    }
                    catch (IllegalAccessException | InstantiationException e1) {
                        throw new RuntimeException(e);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public String writeValue(final Object value) {
                try {
                    return objectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        airtable.setEndpointUrl("http://localhost:8080");
        this.base = airtable.base("appTtHA5PfJnVfjdu");

        //set 404 as default
//        stubFor(any(anyUrl())
//            .atPriority(10)
//            .willReturn(aResponse()
//                .withStatus(404)
//                .withBody("{\"error\":{\"type\":\"NOT_FOUND\",\"message\":\"Not found\"}}")));

    }

    @Test
    public void testSelectTable() throws AirtableException, HttpResponseException {


        List<Movie> retval = base.table("Movies", Movie.class).select();
        assertNotNull(retval);
        assertEquals(9, retval.size());

    }

    @Test
    public void testSelectTableMaxRecords() throws AirtableException, HttpResponseException {


        List<Movie> retval = base.table("Movies", Movie.class).select(2);
        assertNotNull(retval);
        assertEquals(2, retval.size());

    }

    @Test
    public void testSelectTableSorted() throws AirtableException, HttpResponseException {

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
    public void testSelectTableView() throws AirtableException, HttpResponseException {

        List<Movie> retval = base.table("Movies", Movie.class).select("Main View");
        assertNotNull(retval);
        assertEquals(9, retval.size());
        Movie mov = retval.get(0);
        assertEquals("The Godfather", mov.getName());
    }

    @Test(expected = AirtableException.class)
    public void testSelectNonExistingTable() throws AirtableException, HttpResponseException {


        List<Movie> retval = base.table("NotExists", Movie.class).select();
        assertNotNull(retval);
    }


    @Test
    public void testSelectNonExistingExceptionMessageTable() throws HttpResponseException {

        String message;
        try {
            base.table("NotExists", Movie.class).select();
            message = "Success";
        } catch (AirtableException e) {
            message = e.getMessage();
        }
        assertEquals("Could not find table NotExists in application " + base.name() + " (TABLE_NOT_FOUND) [Http code 404]", message);
    }
}
