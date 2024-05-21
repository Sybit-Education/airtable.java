/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable.vo;

/**
 * Error object.
 */
public class Error {
    /**
     * Type of error.
     */
    private String type;
    /**
     * Error message.
     */
    private String message;

    /**
     * Get the type.
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type.
     * @param type the type to set

     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the message.
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message.
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
