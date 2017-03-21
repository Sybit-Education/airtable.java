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
    
    private Map<String, size> records;
    
    private class size{
        private String url;
        private int width;
        private int height;

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
        public int getWidth() {
            return width;
        }

        /**
         * @param width the width to set
         */
        public void setWidth(int width) {
            this.width = width;
        }

        /**
         * @return the height
         */
        public int getHeight() {
            return height;
        }

        /**
         * @param height the height to set
         */
        public void setHeight(int height) {
            this.height = height;
        }
    }

    /**
     * @return the records
     */
    public Map<String, size> getRecords() {
        return records;
    }

    /**
     * @param records the records to set
     */
    public void setRecords(Map<String, size> records) {
        this.records = records;
    }
    
}
