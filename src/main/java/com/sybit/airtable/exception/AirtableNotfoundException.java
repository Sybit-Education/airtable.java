/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable.exception;

/**
 * Thrown if data is not found.
 *
 * @since 0.1
 */
public class AirtableNotfoundException extends AirtableException {
    public AirtableNotfoundException(String msg) {
        super(msg);
    }

    public AirtableNotfoundException(Throwable e) {
        super(e);
    }
}
