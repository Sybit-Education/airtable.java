/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable.vo;

/**
 * Value Object for the Delete Response.
 *
 * @author fzr
 */
public class Delete {

    /**
     *
     */
    private boolean deleted;
    /**
     * ID of the deleted item.
     */
    private String id;

      /**
     * If it is deleted.
     * @return the deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Set if it is deleted.
     * @param deleted the deleted to set
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Get the id.
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Set  the id.
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }



}
