/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sybit.airtable;

import com.sybit.airtable.exception.AirtableException;
import com.sybit.airtable.movies.Actor;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author fzr
 */
public class TableTest {
    
    private Airtable airtable;
    private Base base;
    
    @Before
    public void before() throws AirtableException{
    
        this.airtable = new Airtable().configure(new Configuration("123","url"));
        this.base = new Base("base", this.airtable);
        
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
