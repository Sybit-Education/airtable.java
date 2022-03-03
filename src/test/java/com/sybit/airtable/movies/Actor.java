/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable.movies;



import com.sybit.airtable.movies.*;
import com.google.gson.annotations.SerializedName;
import com.sybit.airtable.vo.Attachment;
import java.util.List;

public class Actor {

    private String id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Photo")
    private List<Attachment> photo;
    @SerializedName("Biography")
    private String biography;
    @SerializedName("Filmography")
    private String[] filmography;
    
   

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

    public String[] getFilmography() {
        return filmography;
    }

    public void setFilmography(String[] Filmography) {
        this.filmography = Filmography;
    }

    public List<Attachment> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Attachment> Photo) {
        this.photo = Photo;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String Biography) {
        this.biography = Biography;
    }
    
    
}
