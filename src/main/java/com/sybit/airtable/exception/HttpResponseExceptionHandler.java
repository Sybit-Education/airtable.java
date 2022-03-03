/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable.exception;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.sybit.airtable.vo.Error;

import java.util.Map;

/**
 * Handle HTTP responses and create exceptions.
 *
 * @since 0.1
 */
public class HttpResponseExceptionHandler {

    public static void onResponse(HttpResponse response) throws AirtableException {

        final Integer statusCode = response.getStatus();
        String message = convertStreamToString(response.getRawBody());

        Error err = extractError(message);

        throw new AirtableException(err.getType(), err.getMessage(), statusCode);
    }

    private static Error extractError(String message) {

        final Gson gson = new Gson();

        Error err;
        try {
            Map<String, Object> jsonMap = gson.fromJson(message, Map.class);
            String innerJson = gson.toJson(jsonMap.get("error"));
            err = gson.fromJson(innerJson, Error.class);
        } catch (Exception ignored) {
            err = new Error();
            err.setType("UNDEFINED_ERROR");
            err.setMessage(message);
        }

        return err;
    }

    public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
