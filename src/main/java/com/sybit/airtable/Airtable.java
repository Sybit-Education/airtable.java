/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;


import com.mashape.unirest.http.Unirest;
import com.sybit.airtable.exception.AirtableException;
import com.sybit.airtable.vo.Attachment;
import com.sybit.airtable.vo.Thumbnail;

import org.apache.http.HttpHost;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * Representation Class of Airtable.
 * It is the entry class to access Airtable data.
 *
 * The API key could be passed to the app by
 * + defining Java property <code>AIRTABLE_API_KEY</code> (e.g. <code>-DAIRTABLE_API_KEY=foo</code>).
 * + defining OS environment variable <code>AIRTABLE_API_KEY</code> (e.g. <code>export AIRTABLE_API_KEY=foo</code>).
 * + defining property file `credentials.properties` in root classpath containing key/value <code>AIRTABLE_API_KEY=foo</code>.
 * + On the other hand the API-key could also be added by using the method <code>Airtable.configure(String apiKey)</code>.
 *
 * @since 0.1
 */
public class Airtable {

    private static final Logger LOG = LoggerFactory.getLogger( Airtable.class );
    private static final String ENDPOINT_URL = "https://api.airtable.com/v0";
    private static final String AIRTABLE_API_KEY = "AIRTABLE_API_KEY";
    private static final String AIRTABLE_BASE = "AIRTABLE_BASE";

    private String  endpointUrl;
    private String apiKey;

    /**
     * Configure, <code>AIRTABLE_API_KEY</code> passed by Java property, enviroment variable
     * or within credentials.properties.
     *
     * @return configured Airtable object.
     * @throws com.sybit.airtable.exception.AirtableException Missing API-Key
     */
    @SuppressWarnings("UnusedReturnValue")
    public Airtable configure() throws AirtableException {

        LOG.info( "System-Property: Using Java property '-D" + AIRTABLE_API_KEY + "' to get apikey.");
        String airtableApi = System.getProperty(AIRTABLE_API_KEY);

        if(airtableApi == null) {
            LOG.info( "Environment-Variable: Using OS environment '" + AIRTABLE_API_KEY + "' to get apikey.");
            airtableApi = System.getenv(AIRTABLE_API_KEY);
        }
        if(airtableApi == null) {
            airtableApi = getCredentialProperty(AIRTABLE_API_KEY);
        }

        return this.configure(airtableApi);
    }



    /**
     * Configure Airtable.
     *
     * @param apiKey API-Key of Airtable.
     * @return
     * @throws com.sybit.airtable.exception.AirtableException Missing API-Key
     */
    @SuppressWarnings("WeakerAccess")
    public Airtable configure(String apiKey) throws AirtableException {
        return configure(apiKey, ENDPOINT_URL);
    }

    /**
     *
     * @param apiKey
     * @param endpointUrl
     * @return
     * @throws com.sybit.airtable.exception.AirtableException Missing API-Key or Endpoint
     */
    @SuppressWarnings("WeakerAccess")
    public Airtable configure(String apiKey, String endpointUrl) throws AirtableException {
        if(apiKey == null) {
            throw new AirtableException("Missing Airtable API-Key");
        }
        if(endpointUrl == null) {
            throw new AirtableException("Missing endpointUrl");
        }

        this.apiKey = apiKey;
        this.endpointUrl = endpointUrl;

        setProxy(endpointUrl);

        // Only one time
        Unirest.setObjectMapper(new GsonObjectMapper());

                
        // Add specific Converter for Date
        DateTimeConverter dtConverter = new DateConverter();
        ListConverter lConverter = new ListConverter();
        MapConverter thConverter = new MapConverter();
        
        lConverter.setListClass(Attachment.class);
        thConverter.setMapClass(Thumbnail.class);
        dtConverter.setPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        
        ConvertUtils.register(dtConverter, Date.class);
        ConvertUtils.register(lConverter, List.class);
        ConvertUtils.register(thConverter, Map.class);
        

        return this;
    }

    /**
     * Set Proxy environment for Unirest.
     *
     * Proxy will be ignored for endpointUrls containing <code>localhost</code> or <code>127.0.0.1,/code>
     * @param endpointUrl
     */
    private void setProxy(String endpointUrl) {
        final String httpProxy = System.getenv("http_proxy");
        if(httpProxy != null
                && (endpointUrl.contains("127.0.0.1")
                || endpointUrl.contains("localhost"))) {
            LOG.info("Use Proxy: ignored for 'localhost' ann '127.0.0.1'");
            Unirest.setProxy(null);
        } else if(httpProxy != null) {
            LOG.info("Use Proxy: Environment variable 'http_proxy' found and used: " + httpProxy);
            Unirest.setProxy(HttpHost.create(httpProxy));
        } else {
            Unirest.setProxy(null);
        }
    }

    /**
     * Getting the base by given property <code>AIRTABLE_BASE</code>.
     *
     * @return the base object.
     * @throws com.sybit.airtable.exception.AirtableException Missing Airtable_BASE
     */
    public Base base() throws AirtableException {

        LOG.info("Using Java property '-D" + AIRTABLE_BASE + "' to get key.");
        String val = System.getProperty(AIRTABLE_BASE);

        if(val == null) {
            LOG.info("Environment-Variable: Using OS environment '" + AIRTABLE_BASE + "' to get base name.");
            val = System.getenv(AIRTABLE_BASE);
        }
        if(val == null) {
            val = getCredentialProperty(AIRTABLE_BASE);
        }

        return base(val);
    }

    /**
     * Builder method to create base of given base id.
     * @param base the base id.
     * @return
     * @throws com.sybit.airtable.exception.AirtableException AIRTABLE_BASE was Null
     */
    public Base base(String base) throws AirtableException {
        if(base == null) {
            throw new AirtableException("base was null");
        }
        final Base b = new Base(base);
        b.setParent(this);

        return b;
    }

    /**
     *
     * @return
     */
    public String endpointUrl() {
        return endpointUrl;
    }

    /**
     *
     * @return
     */
    public String apiKey() {
        return apiKey;
    }

    /**
     * Get property value from <code>/credentials.properties</code> file.
     *
     * @param key key of property.
     * @return value of property.
     */
    private String getCredentialProperty(String key) {

        final String file = "/credentials.properties";
        LOG.info("credentials file: Using file '" + file + "' using key '" + key + "' to get value.");
        String value;

        InputStream in = null;
        try {
            final Properties prop = new Properties();
            in = getClass().getResourceAsStream(file);
            prop.load(in);
            value = prop.getProperty(key);
        } catch (IOException | NullPointerException e) {
            LOG.error(e.getMessage(), e);
            value = null;
        } finally {
            org.apache.commons.io.IOUtils.closeQuietly(in);
        }

        return value;
    }

    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
        setProxy(endpointUrl);
    }
}
