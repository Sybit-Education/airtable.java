/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable.test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.sybit.airtable.Airtable;
import com.sybit.airtable.exception.AirtableException;
import org.junit.Before;
import org.junit.Rule;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

/**
 * Base Class to test using WireMock.
 *
 * Config files for the requests are stored at
 * 'src/test/resources/__files' and 'src/test/resources/mappings'.
 */
public class WireMockBaseTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8080));

    protected Airtable airtable = new Airtable();

    @Before
    public void setup() throws AirtableException {
        airtable.configure();
        airtable.setEndpointUrl("http://localhost:8080/v0");

        //set 404 as default
        stubFor(any(anyUrl())
                .atPriority(10)
                .willReturn(aResponse()
                        .withStatus(404)
                        .withBody("{\"error\":{\"type\":\"NOT_FOUND\",\"message\":\"Not found\"}}")));

    }



    public String endpointUrl() {
        return airtable.endpointUrl();
    }
}
