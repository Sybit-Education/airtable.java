/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable.vo;


/**
 *
 * @author fzr
 */
public class Thumbnail {
    
        private String name;
    
        private String url;
        private Float width;
        private Float height;

        /**
         * @return the url
         */
        public String getUrl() {
            return url;
        }

        /**
         * @param url the url to set
         */
        public void setUrl(String url) {
            this.url = url;
        }

    /**
     * @return the width
     */
    public Float getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(Float width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public Float getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(Float height) {
        this.height = height;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
        
        


        
}
