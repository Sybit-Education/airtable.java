/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable.vo;


/**
 * Value Object for the Thumbnail.
 */
public class Thumbnail {

    /**
     * Name of the thumbnail.
     */
    private String name;

    /**
     * URL of the thumbnail.
     */
    private String url;
    /**
     * Width of the thumbnail.
     */
    private Float width;
    /**
     * Height of the thumbnail.
     */
    private Float height;

    /**
     * get the url.
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * set the url.
     * @param url the url to set
     */
    public void setUrl(String url) {
            this.url = url;
        }

    /**
     * get the width.
     * @return the width
     */
    public Float getWidth() {
        return width;
    }

    /**
     * set the width.
     * @param width the width to set
     */
    public void setWidth(Float width) {
        this.width = width;
    }

    /**
     * get the height.
     * @return the height
     */
    public Float getHeight() {
        return height;
    }

    /**
     * set the height.
     * @param height the height to set
     */
    public void setHeight(Float height) {
        this.height = height;
    }

    /**
     * get the name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * set the name.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
