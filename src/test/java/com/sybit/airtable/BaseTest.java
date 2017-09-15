/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;

import com.sybit.airtable.exception.AirtableException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author fzr
 */
public class BaseTest {
    
    private Airtable airtable;
    
    @Before
    public void before() throws AirtableException{
    
        this.airtable = new Airtable().configure(new Configuration("123","http://localhost",null));
        
        
    }
    
    @Test
    public void baseTest(){
    
        Base base = new Base("base", this.airtable);
        assertNotNull(base);        
    }
    
    
    @Test
    public void airtableTest(){
    
        Base base = new Base("base", this.airtable);
        assertEquals(base.airtable(),this.airtable);
    }
    
    @Test
    public void baseNameTest(){
        
        Base base = new Base("base", this.airtable);
        assertEquals(base.name(),"base");
    }

    @Test(expected = java.lang.AssertionError.class )
    public void baseAssertationTest() {
        Base base = new Base(null,null);
    }
    
}
