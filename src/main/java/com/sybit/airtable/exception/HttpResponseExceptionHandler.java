package com.sybit.airtable.exception;/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.sybit.airtable.IOUtils;
import com.sybit.airtable.vo.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpResponseExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger( HttpResponseExceptionHandler.class );

    public static void onResponse(HttpResponse response) throws AirtableException {

        Integer statusCode = response.getStatus();
        String message = IOUtils.convertStreamToString(response.getRawBody());

        Gson gson = new Gson();
        Error err = gson.fromJson(message, Error.class);
        switch (statusCode) {
            case 401:
                throw new AirtableException("AUTHENTICATION_REQUIRED", "You should provide valid api key to perform this operation", statusCode);
            case 403:
                throw new AirtableException("NOT_AUTHORIZED", "You are not authorized to perform this operation", statusCode);

            case 404:
                message = ( err.getMessage() != null) ?  err.getMessage() : "Could not find what you are looking for";
                throw new AirtableException("NOT_FOUND", message, statusCode);

            case 413:
                throw new AirtableException("REQUEST_TOO_LARGE", "Request body is too large", statusCode);

            case  422:
                throw new AirtableException(err.getType(), err.getMessage(), statusCode);

            case  429:
                throw new AirtableException("TOO_MANY_REQUESTS", "You have made too many requests in a short period of time. Please retry your request later", statusCode);

            case 500:
                throw new AirtableException("SERVER_ERROR", "Try again. If the problem persists, contact support.", statusCode);

            case  503:
                throw new AirtableException("SERVICE_UNAVAILABLE", "The service is temporarily unavailable. Please retry shortly.", statusCode);

            default:
                throw new AirtableException("UNDEFINED_ERROR", message, statusCode);
        }
    }
}
