/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;

import com.sybit.airtable.exception.AirtableException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author fzr
 */
public class AirtableTest {

    @Test
    public void airtableTest() throws AirtableException{

        Airtable airtable = new Airtable();
        assertNull(airtable.getConfig());
        airtable.configure();
        assertNotNull(airtable.getConfig());

    }

    @Test
    public void airtableConfigAPIKeyTest() throws AirtableException {

        Airtable airtable = new Airtable();
        assertNull(airtable.getConfig());
        airtable.configure("KEY");
        assertEquals("KEY", airtable.accessToken());
    }

    @Test
    public void airtableConfigTest() throws AirtableException{

        Airtable airtable = new Airtable();
        assertNull(airtable.getConfig());
        airtable.configure(new Configuration("KEY","URL","PROXY"));
        assertEquals("KEY", airtable.accessToken());
        assertEquals("URL", airtable.endpointUrl());

    }

    @Test
    public void airtableEndpointTest() throws AirtableException{

        Airtable airtable = new Airtable();
        assertNull(airtable.getConfig());
        airtable.configure();
        airtable.setEndpointUrl("URL");
        assertEquals("URL", airtable.endpointUrl());

    }

    @Test
    public void airtableBaseTest() throws AirtableException{

        Airtable airtable = new Airtable();
        assertNull(airtable.getConfig());
        assertNotNull(airtable.base("base"));

    }

}
