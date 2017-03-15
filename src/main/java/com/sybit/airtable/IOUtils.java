package com.sybit.airtable;

/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */

import java.io.Closeable;

final class IOUtils {
    private IOUtils() {}

    public static void closeQuietly(Closeable... closeables) {
        for (Closeable c : closeables) {
            if (c != null) try {
                c.close();
            } catch(Exception ex) {}
        }
    }
}

