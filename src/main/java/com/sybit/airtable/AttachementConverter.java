/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sybit.airtable;


import com.sybit.airtable.vo.Thumbnail;
import org.apache.commons.beanutils.converters.AbstractConverter;

/**
 *
 * @author fzr
 */
public class AttachementConverter extends AbstractConverter {
    
    private String id;
    private String url;
    private String filename;
    private Float size;
    private String type;
    private Thumbnail thumbnail;
    
    public AttachementConverter(){
        super();
    }

    @Override
    protected <T> T convertToType(Class<T> type, Object value) throws Throwable {
        
        final Class<?> sourceType = value.getClass();
        
        if(value instanceof String){
            
        } else {
        }
        
        return null;
    }

    @Override
    protected Class<?> getDefaultType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

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
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return the size
     */
    public Float getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Float size) {
        this.size = size;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the thumbnail
     */
    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    /**
     * @param thumbnail the thumbnail to set
     */
    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }
    
    
}
