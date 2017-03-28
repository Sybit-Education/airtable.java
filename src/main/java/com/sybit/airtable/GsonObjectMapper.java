/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sybit.airtable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fzr
 */
class GsonObjectMapper implements ObjectMapper{
    private static final Logger LOG = Logger.getLogger( GsonObjectMapper.class.getName() );
    private final Gson gson;
                
    public GsonObjectMapper() {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
     
    }

    public <T> T readValue(String value, Class<T> valueType) {
        LOG.log(Level.INFO, "readValue: \n" + value);
        return gson.fromJson(value, valueType);
    }

    public String writeValue(Object value) {
        return gson.toJson(value);
    }

}
