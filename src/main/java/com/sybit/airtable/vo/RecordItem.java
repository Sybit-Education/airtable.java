package com.sybit.airtable.vo;

import java.util.Map;

/**
 * Created by Stephan on 07.03.2017.
 */
public class RecordItem {
    private String id;

    private Map<String, Object> fields;

    private String createdTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getFields() {
        return fields;
    }

    public void setFields(Map<String, Object> fields) {
        this.fields = fields;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
