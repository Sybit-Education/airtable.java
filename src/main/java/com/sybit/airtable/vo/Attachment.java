/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable.vo;

import java.io.Serializable;
import java.util.Map;


/**
 * Airtable Attachment items.
 *
 */
public class Attachment {

    /**
     * ID of attachment.
     */
    private String id;
    /**
     * URL of attachment.
     */
    private String url;
    /**
     * Filename of attachment.
     */
    private String filename;
    /**
     * Size of attachment.
     */
    private Float size;
    /**
     * Type of attachment.
     */
    private String type;
    /**
     * Thumbnails of attachment.
     */
    private Map<String,Thumbnail> thumbnails;

    /**
     * get the id.
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * set the id.
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

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
     * get the filename.
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * set the filename.
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * get the size.
     * @return the size
     */
    public Float getSize() {
        return size;
    }

    /**
     * set the size.
     * @param size the size to set
     */
    public void setSize(Float size) {
        this.size = size;
    }

    /**
     * get the type.
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * set the type.
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * get the thumbnails.
     * @return the thumbnails
     */
    public Map<String,Thumbnail> getThumbnails() {
        return thumbnails;
    }

    /**
     * set the thumbnails.
     * @param thumbnails the thumbnails to set
     */
    public void setThumbnails(Map<String,Thumbnail> thumbnails) {
        this.thumbnails = thumbnails;
    }
}
