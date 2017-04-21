/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable.movies;

import com.sybit.airtable.movies.*;
import com.google.gson.annotations.SerializedName;

public class ActorSerializedNames {

    private String id;

    @SerializedName("First- & Lastname")
    private String name;

    @SerializedName("Biography of Actor")
    private String biography;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
