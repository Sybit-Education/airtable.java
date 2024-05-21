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
 *
 * @since 0.1
 */
public class Configuration {

    /**
     * Default endpoint URL.
     */
    public static final String ENDPOINT_URL = "https://api.airtable.com/v0";

    private String endpointUrl;
    private String accessToken;
    private String proxy;
    private Integer timeout;

    /**
     * Configure API using given access token,default endpoint and no Proxy.
     *
     * @param accessToken the private access token.
     */
    public Configuration(String accessToken) {
        this(accessToken, ENDPOINT_URL,null);

    }
    /**
     * Configure API using given API Key and default endpointURL.
     *
     * @param accessToken the private access token.
     * @param endpointUrl the endpoint URL.
     *                    Default is <code>https://api.airtable.com/v0</code>
     * @param proxy the proxy URL.
     */
    public Configuration(String accessToken, String endpointUrl, String proxy) {
        this.accessToken = accessToken;
        this.endpointUrl = endpointUrl;
        this.proxy = proxy;
    }

    /**
     * Get the endpoint URL.
     * @return the endpoint URL
     */
    public String getEndpointUrl() {
        return endpointUrl;
    }

    /**
     * Set the endpoint URL.
     * @param endpointUrl the endpoint URL
     */
    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    /**
     * Get the API Key.
     * @return the API Key
     * @deprecated since 0.3
     */
    @Deprecated(forRemoval = true, since = "0.3")
    public String getApiKey() {
        return accessToken;
    }

    /**
     * Set the API Key.
     * @param apiKey the API Key
     * @deprecated since 0.3
     */
    @Deprecated(forRemoval = true, since = "0.3")
    public void setApiKey(String apiKey) {
        this.accessToken = apiKey;
    }

    /**
     * Get the access token.
     * @return the access token
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Set the access token.
     * @param accessToken the access token
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Get connection timeout.
     *
     * @return timeout in milliseconds
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * Set connection timeout.
     * @param timeout timeout in milliseconds
     */
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    /**
     * Get the proxy.
     * @return the proxy
     */
    public String getProxy() {
        return proxy;
    }

    /**
     * Set the proxy.
     * @param proxy the proxy to set
     */
    public void setProxy(String proxy) {
        this.proxy = proxy;
    }
}
