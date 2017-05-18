/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sybit.airtable;

import com.sybit.airtable.exception.AirtableException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author fzr
 */
public class AirtableTest {
    
    @Test
    public void airtableTest() throws AirtableException{
    
        Airtable airtable = new Airtable();
        assertNull(airtable.getConfig());
        airtable.configure();
        assertNotNull(airtable.getConfig());
        
    }
    
    @Test
    public void airtableConfigAPIKeyTest() throws AirtableException {
    
        Airtable airtable = new Airtable();
        assertNull(airtable.getConfig());
        airtable.configure("KEY");
        assertEquals(airtable.apiKey(),"KEY");
    }
    
    @Test
    public void airtableConfigTest() throws AirtableException{
        
        Airtable airtable = new Airtable();
        assertNull(airtable.getConfig());
        airtable.configure(new Configuration("KEY","URL","PROXY"));
        assertEquals(airtable.apiKey(),"KEY");
        assertEquals(airtable.endpointUrl(),"URL");
        
    }
    
    @Test
    public void airtableEndpointTest() throws AirtableException{
    
        Airtable airtable = new Airtable();
        assertNull(airtable.getConfig());
        airtable.configure();
        airtable.setEndpointUrl("URL");
        assertEquals(airtable.endpointUrl(),"URL");
    
    }
    
    @Test
    public void airtableBaseTest() throws AirtableException{
    
        Airtable airtable = new Airtable();
        assertNull(airtable.getConfig());
        assertNotNull(airtable.base("base"));
        
    }
    
}
