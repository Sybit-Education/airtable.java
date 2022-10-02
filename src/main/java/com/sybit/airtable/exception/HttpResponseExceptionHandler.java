/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable.exception;

import com.google.gson.Gson;
import com.sybit.airtable.vo.Error;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestParsingException;

import java.util.Map;
import java.util.Optional;

/**
 * Handle HTTP responses and create exceptions.
 *
 * @since 0.1
 */
public class HttpResponseExceptionHandler {

    public static void onResponse(HttpResponse response) throws AirtableException {

        final Integer statusCode = response.getStatus();
        Optional<UnirestParsingException> parsingError = response.getParsingError();
        if (parsingError.isPresent()) {
            UnirestParsingException unirestParsingException = parsingError.get();
            String originalBody = unirestParsingException.getOriginalBody();
            Error err = extractError(originalBody);

            throw new AirtableException(err.getType(), err.getMessage(), statusCode);
        }
        throw new AirtableException(response.getStatusText(), "Unable to parse response: " + response.getBody(), statusCode);
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

}
