/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;

import com.sybit.airtable.exception.AirtableException;
import com.sybit.airtable.movies.Actor;
import com.sybit.airtable.mock.WireMockBaseTest;
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

        Table<Actor> actorTable = base.table("Actors", Actor.class);
        Actor actor = actorTable.find("recEtUIW6FWtbEDKz");
        assertNotNull(actor);
        assertEquals("recEtUIW6FWtbEDKz", actor.getId());
        assertEquals("Marlon Brando", actor.getName());
    }

    @Test(expected = AirtableException.class)
    public void testFindNotFound() throws AirtableException, HttpResponseException {

        Table<Actor> actorTable = base.table("Actors", Actor.class);
        Actor actor = actorTable.find("notexistend");
        assertNotNull(actor);
    }

}
