/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kong.unirest.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Default mapper based on GSON.
 *
 * @since 0.1
 * @author fzr
 */
class GsonObjectMapper implements ObjectMapper {
    private static final Logger LOG = Logger.getLogger( GsonObjectMapper.class.getName() );
    private final Gson gson;
                
    public GsonObjectMapper() {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
     
    }

    @Override
    public <T> T readValue(String value, Class<T> valueType) {
        LOG.log(Level.FINE, "readValue: \n{0}", value);
        return gson.fromJson(value, valueType);
    }

    @Override
    public String writeValue(Object value) {
        LOG.log(Level.FINE, "writeValue: \n{0}", value);
        return gson.toJson(value);
    }

}
