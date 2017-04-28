/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sybit.airtable;

import com.sybit.airtable.exception.AirtableException;
import com.sybit.airtable.movies.Actor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 *
 * @author fzr
 */
public class TableTest {
    
    private Base base;
    
    @Before
    public void before() throws AirtableException{
    
        Airtable airtable = new Airtable().configure(new Configuration("123","url"));
        this.base = new Base("base", airtable);
        
    }

    @Test(expected = java.lang.AssertionError.class )
    public void testTableAssertions(){
        Table table = new Table(null, null);

    }

    @Test
    public void testTable(){
    
        Table table = new Table("table", Actor.class, this.base);
        assertNotNull(table);
                
    }
    
    @Test
    public void setParentTest(){
    
        Table table = new Table("table", Actor.class);
        table.setParent(this.base);
        assertNotNull(table);
    }
    
}
