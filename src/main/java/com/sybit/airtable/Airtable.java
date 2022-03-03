/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;

import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.sybit.airtable.converter.ListConverter;
import com.sybit.airtable.converter.MapConverter;
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
 * Representation Class of Airtable. It is the entry class to access Airtable
 * data.
 *
 * The API key could be passed to the app by + defining Java property
 * <code>AIRTABLE_API_KEY</code> (e.g. <code>-DAIRTABLE_API_KEY=foo</code>). +
 * defining OS environment variable <code>AIRTABLE_API_KEY</code> (e.g.
 * <code>export AIRTABLE_API_KEY=foo</code>). + defining property file
 * `credentials.properties` in root classpath containing key/value
 * <code>AIRTABLE_API_KEY=foo</code>. + On the other hand the API-key could also
 * be added by using the method <code>Airtable.configure(String apiKey)</code>.
 *
 * @since 0.1
 */
public class Airtable {

    private static final Logger LOG = LoggerFactory.getLogger(Airtable.class);

    private static final String AIRTABLE_API_KEY = "AIRTABLE_API_KEY";
    private static final String AIRTABLE_BASE = "AIRTABLE_BASE";

    private Configuration config;

    /**
     * Configure, <code>AIRTABLE_API_KEY</code> passed by Java property,
     * enviroment variable or within credentials.properties.
     *
     * @return An Airtable instance configured with GsonObjectMapper
     * @throws com.sybit.airtable.exception.AirtableException Missing API-Key
     */
    @SuppressWarnings("UnusedReturnValue")
    public Airtable configure() throws AirtableException {
        return this.configure(new GsonObjectMapper());
    }

    /**
     * Configure, <code>AIRTABLE_API_KEY</code> passed by Java property,
     * enviroment variable or within credentials.properties.
     *
     * @param objectMapper A custom ObjectMapper implementation
     * @return An Airtable instance configured with supplied ObjectMapper
     * @throws com.sybit.airtable.exception.AirtableException Missing API-Key
     */
    @SuppressWarnings("UnusedReturnValue")
    public Airtable configure(ObjectMapper objectMapper) throws AirtableException {

        LOG.info("System-Property: Using Java property '-D" + AIRTABLE_API_KEY + "' to get apikey.");
        String airtableApi = System.getProperty(AIRTABLE_API_KEY);

        if (airtableApi == null) {
            LOG.info("Environment-Variable: Using OS environment '" + AIRTABLE_API_KEY + "' to get apikey.");
            airtableApi = System.getenv(AIRTABLE_API_KEY);
        }
        if (airtableApi == null) {
            airtableApi = getCredentialProperty(AIRTABLE_API_KEY);
        }

        return this.configure(airtableApi, objectMapper);
    }

    /**
     * Configure Airtable.
     *
     * @param apiKey API-Key of Airtable.
     * @return An Airtable instance configured with GsonObjectMapper
     * @throws com.sybit.airtable.exception.AirtableException Missing API-Key
     */
    @SuppressWarnings("WeakerAccess")
    public Airtable configure(String apiKey) throws AirtableException {
        return configure(apiKey, new GsonObjectMapper());
    }

    /**
     * Configure Airtable.
     *
     * @param apiKey API-Key of Airtable.
     * @param objectMapper A custom ObjectMapper implementation
     * @return
     * @throws com.sybit.airtable.exception.AirtableException Missing API-Key
     */
    @SuppressWarnings("WeakerAccess")
    public Airtable configure(String apiKey, ObjectMapper objectMapper) throws AirtableException {
        return configure(new Configuration(apiKey, Configuration.ENDPOINT_URL, null), objectMapper);
    }

    /**
     * Configure the Airtable client by given config.
     * 
     * @param config Configuration of client.
     * @return
     * @throws AirtableException Missing API-Key or Endpoint
     */
    @SuppressWarnings("WeakerAccess")
    public Airtable configure(Configuration config) throws AirtableException {
        return configure(config, new GsonObjectMapper());
    }

    /**
     * Configure the Airtable client by given config.
     * 
     * @param config Configuration of client.
     * @param objectMapper A custom ObjectMapper implementation
     * @return
     * @throws AirtableException Missing API-Key or Endpoint
     */
    @SuppressWarnings("WeakerAccess")
    public Airtable configure(Configuration config, ObjectMapper objectMapper) throws AirtableException {
        assert config != null : "config was null";
        assert objectMapper != null : "objectMapper was null";

        if (config.getApiKey() == null) {
            throw new AirtableException("Missing Airtable API-Key");
        }
        if (config.getEndpointUrl() == null) {
            throw new AirtableException("Missing endpointUrl");
        }

        this.config = config;

        if (config.getTimeout() != null) {
            LOG.info("Set connection timeout to: " + config.getTimeout() + "ms.");
            Unirest.setTimeouts(config.getTimeout(), config.getTimeout());
        }

        configureProxy(config.getEndpointUrl());

        // Only one time
        Unirest.setObjectMapper(objectMapper);

        // Add specific Converter for Date
        DateTimeConverter dtConverter = new DateConverter();
        dtConverter.setPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        ConvertUtils.register(dtConverter, Date.class);

        ListConverter lConverter = new ListConverter();
        lConverter.setListClass(Attachment.class);
        ConvertUtils.register(lConverter, List.class);

        MapConverter thConverter = new MapConverter();
        thConverter.setMapClass(Thumbnail.class);
        ConvertUtils.register(thConverter, Map.class);

        return this;
    }

    /**
     * Set Proxy manually.
     *
     * @param proxy the proxy.
     */
    public void setProxy(String proxy) {

        this.config.setProxy(proxy);
        if (proxy == null) {
            Unirest.setProxy(null);
        } else {
            Unirest.setProxy(HttpHost.create(this.config.getProxy()));
        }

    }

    /**
     * Set Proxy environment in Configuration.
     *
     * Proxy will be ignored for endpointUrls containing <code>localhost</code>
     * or <code>127.0.0.1</code>.
     *
     * @param endpointUrl
     */
    private void configureProxy(String endpointUrl) {
        if (this.config.getProxy() == null) {
            final String httpProxy = System.getenv("http_proxy");
            final String httpsProxy = System.getenv("https_proxy");
            if (httpsProxy != null
                    && (endpointUrl.contains("https"))) {
                LOG.info("Use Proxy: Environment variable 'https_proxy' found and used: " + httpsProxy);
                setProxy(httpProxy);
            } else if (httpProxy != null
                    && (endpointUrl.contains("http"))) {
                LOG.info("Use Proxy: Environment variable 'http_proxy' found and used: " + httpProxy);
                setProxy(httpsProxy);
            } else {
                setProxy(null);
            }
        } else if ((endpointUrl.contains("127.0.0.1")
                || endpointUrl.contains("localhost"))) {
            LOG.info("Use Proxy: ignored for 'localhost' and '127.0.0.1'");
            setProxy(null);
        } else {
            setProxy(this.config.getProxy());
        }
    }

    /**
     * Getting the base by given property <code>AIRTABLE_BASE</code>.
     *
     * @return the base object.
     * @throws com.sybit.airtable.exception.AirtableException Missing
     * Airtable_BASE
     */
    public Base base() throws AirtableException {

        LOG.info("Using Java property '-D" + AIRTABLE_BASE + "' to get key.");
        String val = System.getProperty(AIRTABLE_BASE);

        if (val == null) {
            LOG.info("Environment-Variable: Using OS environment '" + AIRTABLE_BASE + "' to get base name.");
            val = System.getenv(AIRTABLE_BASE);
        }
        if (val == null) {
            val = getCredentialProperty(AIRTABLE_BASE);
        }

        return base(val);
    }

    /**
     * Builder method to create base of given base id.
     *
     * @param base the base id.
     * @return
     * @throws com.sybit.airtable.exception.AirtableException AIRTABLE_BASE was
     * Null
     */
    public Base base(String base) throws AirtableException {
        if (base == null) {
            throw new AirtableException("base was null");
        }
        final Base b = new Base(base, this);

        return b;
    }

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        assert config != null : "config was null";

        this.config = config;
        configureProxy(config.getEndpointUrl());
    }

    /**
     *
     * @return
     */
    public String endpointUrl() {
        return this.config.getEndpointUrl();
    }

    /**
     *
     * @return
     */
    public String apiKey() {
        return this.config.getApiKey();
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
        this.config.setEndpointUrl(endpointUrl);
        configureProxy(endpointUrl);
    }
}
