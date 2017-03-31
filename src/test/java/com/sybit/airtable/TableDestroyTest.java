/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sybit.airtable;

import com.sybit.airtable.exception.AirtableException;
import com.sybit.airtable.movies.Actor;
import com.sybit.airtable.test.WireMockBaseTest;
import java.util.List;
import org.apache.http.client.HttpResponseException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author fzr
 */
public class TableDestroyTest extends WireMockBaseTest {
    
    
    
    @Test
    public void testDestroyMovie() throws AirtableException, HttpResponseException{
    
        Base base = airtable.base("appe9941ff07fffcc");
        Table<Actor> actorTable = base.table("Actors", Actor.class);
              
        actorTable.destroy("recapJ3Js8AEwt0Bf");   
               
    }
    
    @Test (expected = AirtableException.class)
    public void testDestroyMovieException() throws AirtableException{
        
        Base base = airtable.base("appe9941ff07fffcc");
        Table<Actor> actorTable = base.table("Actors", Actor.class);
              
        actorTable.destroy("not succesfull"); 
    }
    
}
