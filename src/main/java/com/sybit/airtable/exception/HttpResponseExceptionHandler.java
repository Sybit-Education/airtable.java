package com.sybit.airtable.exception;/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */

import com.mashape.unirest.http.HttpResponse;
import org.apache.http.client.HttpResponseException;

public class HttpResponseExceptionHandler {

    public static void onResponse(HttpResponse response) throws HttpResponseException {
        throw new HttpResponseException(response.getStatus(), response.getStatusText());
    }
}
