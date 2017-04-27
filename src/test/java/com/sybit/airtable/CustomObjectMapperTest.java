/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sybit.airtable;

import com.google.gson.internal.LinkedTreeMap;
import com.sybit.airtable.converter.ListConverter;
import com.sybit.airtable.converter.MapConverter;
import com.sybit.airtable.vo.Attachment;
import com.sybit.airtable.vo.Thumbnail;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.map.HashedMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author fzr
 */
public class CustomObjectMapperTest {
    
    private ListConverter listConverter;
    private MapConverter mapConverter;
    
    @Before
    public void before(){
        
        this.listConverter = new ListConverter();
        this.mapConverter = new MapConverter();
        
    }
    
    @Test
    public void listClassTest(){
        
        listConverter.setListClass(Attachment.class);
        assertEquals(listConverter.getListClass(),Attachment.class);             
    }
    
    @Test 
    public void mapClassTest(){
    
        mapConverter.setMapClass(Thumbnail.class);
        assertEquals(mapConverter.getMapClass(),Thumbnail.class);
    }
        
    @Test
    public void convertListTest(){
    
        listConverter.setListClass(Attachment.class);
        
        Class type = List.class;
        List value = new ArrayList();
        
        LinkedTreeMap ltm = new LinkedTreeMap();
        ltm.put("id","id0001");
        ltm.put("url","http://test.com");
        ltm.put("filename","filename.txt");
        ltm.put("size","10");
        ltm.put("type","image/jpeg");
        
        Map<String,Thumbnail> thumbnails = new HashedMap<>();
        Thumbnail tmb = new Thumbnail();
        
        tmb.setName("Thumbnail");
        tmb.setUrl("http:example.com");
        tmb.setWidth(Float.valueOf(10));
        tmb.setHeight(Float.valueOf(10));
        
        thumbnails.put("small", tmb);
                
        ltm.put("thumbnails",thumbnails);
        
        value.add(0, ltm);
        
        List<Attachment> list = (List<Attachment>) listConverter.convert(type, value);
        assertNotNull(list);
        assertNotNull(list.get(0).getId());
        assertNotNull(list.get(0).getFilename());
        assertNotNull(list.get(0).getSize());
        assertNotNull(list.get(0).getType());
        assertNotNull(list.get(0).getUrl());
        assertNotNull(list.get(0).getThumbnails());        
        
    }
    
    @Test
    public void convertMapTest(){
    
        mapConverter.setMapClass(Thumbnail.class);
        
        Class type = Map.class;
        
        LinkedTreeMap<String, Object> value = new LinkedTreeMap<>();
        LinkedTreeMap<String, Object> innerMap = new LinkedTreeMap<>();
        
        innerMap.put("url","http://example.com");
        value.put("small",innerMap);    
        
        
        Map<String,Thumbnail> thumb = (Map<String,Thumbnail>) mapConverter.convert(type,value);
        System.out.println(thumb);
        assertNotNull(thumb);
        assertNotNull(thumb.get("small"));
        assertNotNull(thumb.get("small").getUrl());
        
        
        
    }
    
}
