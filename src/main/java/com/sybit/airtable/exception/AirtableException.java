/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable.exception;

import org.apache.http.conn.ConnectTimeoutException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * General Exception of API.
 *
 * @since 0.1
 */
public class AirtableException extends Exception {
    private static final Logger LOG = Logger.getLogger( AirtableException.class.getName() );

    public AirtableException(String msg) {
        super(msg);
    }

    public AirtableException(Throwable e) {
        super(e);

        if(e.getCause() instanceof ConnectTimeoutException) {
            LOG.log(Level.SEVERE, "possible forgotten to set correct apiKey or base?");
        }
    }
}
