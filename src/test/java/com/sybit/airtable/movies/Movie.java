/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable.movies;

import com.google.gson.annotations.SerializedName;
import com.sybit.airtable.vo.Attachment;

import java.util.Date;
import java.util.List;

/**
 *
 */
public class Movie {
    private String id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Description")
    private String description;
    private List<Attachment> photos;
    @SerializedName("Director")
    private List<String> director;
    @SerializedName("Actors")
    private List<String> actors;
    @SerializedName("Genre")
    private List<String> genre;
    private Date createdTime;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Attachment> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Attachment> photos) {
        this.photos = photos;
    }

    public List<String> getDirector() {
        return director;
    }

    public void setDirector(List<String> director) {
        this.director = director;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
