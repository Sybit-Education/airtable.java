/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;

/**
 * Configuration settings for Airtable.
 * Used by class <code>Airtable</code> to configure basic settings.
 */
public class Configuration {

    public static final String ENDPOINT_URL = "https://api.airtable.com/v0";

    private String  endpointUrl;
    private String apiKey;
    private Long timeout;

    /**
     * Configure API using given API Key and default endpoint.
     *
     * @param apiKey
     */
    public Configuration(String apiKey) {
        this(apiKey, ENDPOINT_URL);

    }
    /**
     * Configure API using given API Key and default endpointURL.
     *
     * @param apiKey
     * @param endpointUrl
     */
    public Configuration(String apiKey, String endpointUrl) {
        this.apiKey = apiKey;
        this.endpointUrl = endpointUrl;
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Get connection timeout.
     * @return
     */
    public Long getTimeout() {
        return timeout;
    }

    /**
     * Set connection timeout.
     * @param timeout
     */
    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }
}
