/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.sybit.airtable.exception.AirtableException;
import org.apache.http.HttpHost;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Representation Class of Airtable.
 * It is the entry class to access Airtable data.
 *
 * @since 0.1
 */
public class Airtable {

    private static final Logger LOG = Logger.getLogger( Airtable.class.getName() );
    private static final String ENDPOINT_URL = "https://api.airtable.com/v0";

    private String  endpointUrl;
    private String apiKey;

    /**
     * Configure, using java property vareiable 'AirtableAPI' to set API-Key.
     *
     * @return
     */
    public Airtable configure() throws AirtableException {
        String property = "AIRTABLE_API_KEY";
        LOG.log(Level.CONFIG, "System-Property: Using Java property '-D" + property + "' to get apikey.");
        String airtableApi = System.getProperty(property);

        if(airtableApi == null) {
            LOG.log(Level.CONFIG, "Environment-Variable: Using OS environment '" + property + "' to get apikey.");
            airtableApi = System.getenv(property);
        }
        if(airtableApi == null) {
            String file = "/credentials.properties";
            LOG.log(Level.CONFIG, "credentials file: Using file '" + file + "' using key '" + property + "' to get apikey.");

            try {
                Properties prop = new Properties();
                InputStream in = getClass().getResourceAsStream(file);
                prop.load(in);
                in.close();
                airtableApi = prop.getProperty(property);
            } catch (IOException | NullPointerException e) {
                LOG.throwing(this.getClass().getName(), "configure", e);
            }
        }

        return this.configure(airtableApi);
    }

    /**
     * Configure Airtable.
     *
     * @param apiKey API-Key of Airtable.
     * @return
     */
    public Airtable configure(String apiKey) throws AirtableException {
        return configure(apiKey, ENDPOINT_URL);
    }

    /**
     *
     * @param apiKey
     * @param endpointUrl
     * @return
     */
    public Airtable configure(String apiKey, String endpointUrl) throws AirtableException {
        if(apiKey == null) {
            throw new AirtableException("Missing Airtable API-Key");
        }
        if(endpointUrl == null) {
            throw new AirtableException("Missing endpointUrl");
        }

        this.apiKey = apiKey;
        this.endpointUrl = endpointUrl;

        final String httpProxy = System.getenv("http_proxy");
        if(httpProxy != null) {
            LOG.log( Level.INFO, "Use Proxy: Environment variable 'http_proxy' found and used: " + httpProxy);
            Unirest.setProxy(HttpHost.create(httpProxy));
        }

        // Only one time
        Unirest.setObjectMapper(new ObjectMapper() {
            final Gson gson = new GsonBuilder().create();

            public <T> T readValue(String value, Class<T> valueType) {
                return gson.fromJson(value, valueType);
            }

            public String writeValue(Object value) {
                return gson.toJson(value);
            }
        });

        return this;
    }

    /**
     * Getting the base by given Java VM property <code>AirtableBase</code> (<code>-DAirtableBase=xyz</code>.
     * @return the base object.
     */
    public Base base() throws AirtableException {
        String property = "AIRTABLE_BASE";
        LOG.log(Level.CONFIG, "Using Java property '-D" + property + "' to get key.");
        String val = System.getProperty(property);

        if(val == null) {
            LOG.log(Level.CONFIG, "Environment-Variable: Using OS environment '" + property + "' to get apikey.");
            val = System.getenv(property);
        }
        if(val == null) {
            String file = "/credentials.properties";
            LOG.log(Level.CONFIG, "credentials file: Using file '" + file + "' using key '" + property + "' to get apikey.");

            try {
                Properties prop = new Properties();
                InputStream in = getClass().getResourceAsStream(file);
                prop.load(in);
                in.close();
                val = prop.getProperty(property);
            } catch (IOException | NullPointerException e) {
                LOG.throwing(this.getClass().getName(), "configure", e);
            }
        }


        return base(val);
    }

    /**
     *
     * @param base
     * @return
     */
    public Base base(String base) throws AirtableException {
        if(base == null) {
            throw new AirtableException("base was null");
        }
        Base b = new Base(base);
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

}
