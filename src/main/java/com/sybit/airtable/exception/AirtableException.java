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

    /**
     * Constructs a new exception with the specified detail message.
     * @param message Detail message. 
     */
    public AirtableException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified  cause.
     * @param cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).
     */
    public AirtableException(Throwable cause) {
        super(cause);

        if(cause.getCause() instanceof ConnectTimeoutException) {
            LOG.log(Level.SEVERE, "possible forgotten to set correct apiKey or base?");
        }
    }

    /**
     * Default Exception simmilar to AirtableError of JavaScript Library.
     * @param error Error code.
     * @param message Detail message. 
     * @param status HTTP Status Code.
     */
    public AirtableException(String error, String message, Integer status) {
        super(message + " (" + error + ")" + ((status != null) ? " [Http code " + status + "]": ""));
    }
}
