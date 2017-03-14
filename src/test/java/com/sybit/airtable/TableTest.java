/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;

import org.apache.http.client.HttpResponseException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class TableTest {

    Airtable airtable = new Airtable();

    @Test
    public void testSelectExistingTable() throws AirtableException, HttpResponseException {

        Base base = airtable.configure().base();

        List<Location> retval = base.table("Location", Location.class).select();
        assertNotNull(retval);
        assertEquals(1, retval.size());
    }

    @Test(expected = HttpResponseException.class)
    public void testSelectNonExistingTable() throws AirtableException, HttpResponseException {

        Base base = airtable.configure().base();

        List<Location> retval = base.table("NotExists", Location.class).select();
        assertNotNull(retval);
    }

    @Test
    public void testFind() throws AirtableException, HttpResponseException {

        Base base = airtable.configure().base();

        Table<Frage> fragen = base.table("Fragen", Frage.class);
        Frage q = fragen.find("rec2WDMty0TznUoh8");
        assertNotNull(q);
    }

    @Test(expected = HttpResponseException.class)
    public void testFindNotFound() throws AirtableException, HttpResponseException {

        Base base = airtable.configure().base();

        Table<Frage> fragen = base.table("Fragen", Frage.class);
        Frage q = fragen.find("notexistend");
        assertNotNull(q);
    }

}
