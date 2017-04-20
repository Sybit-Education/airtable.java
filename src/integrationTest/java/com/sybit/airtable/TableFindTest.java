/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable.integrationTests;

import com.sybit.airtable.Base;
import com.sybit.airtable.Table;
import com.sybit.airtable.exception.AirtableException;
import com.sybit.airtable.movies.Actor;
import com.sybit.airtable.test.WireMockBaseTest;
import org.apache.http.client.HttpResponseException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class TableFindTest extends WireMockBaseTest {

    @Test
    public void testFind() throws AirtableException, HttpResponseException {

        Base base = airtable.base("appe9941ff07fffcc");

        Table<Actor> actorTable = base.table("Actors", Actor.class);
        Actor actor = actorTable.find("rec514228ed76ced1");
        assertNotNull(actor);
        assertEquals("rec514228ed76ced1", actor.getId());
        assertEquals("Marlon Brando", actor.getName());
    }

    @Test(expected = AirtableException.class)
    public void testFindNotFound() throws AirtableException, HttpResponseException {

        Base base = airtable.base("appe9941ff07fffcc");

        Table<Actor> actorTable = base.table("Actors", Actor.class);
        Actor actor = actorTable.find("notexistend");
        assertNotNull(actor);
    }

}
