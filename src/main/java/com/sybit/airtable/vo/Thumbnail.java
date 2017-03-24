/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sybit.airtable.vo;

import java.util.List;
import java.util.Map;

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
