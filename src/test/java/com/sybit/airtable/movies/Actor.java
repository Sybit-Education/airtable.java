/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable.movies;


import com.sybit.airtable.vo.Attachment;
import java.util.List;

public class Actor {

    private String id;
    private String name;
    private List<Attachment> Photo;
    private String Biography;
    private String[] Filmography;
    
   

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
        return Filmography;
    }

    public void setFilmography(String[] Filmography) {
        this.Filmography = Filmography;
    }

    public List<Attachment> getPhoto() {
        return Photo;
    }

    public void setPhoto(List<Attachment> Photo) {
        this.Photo = Photo;
    }

    public String getBiography() {
        return Biography;
    }

    public void setBiography(String Biography) {
        this.Biography = Biography;
    }
    
    
}
