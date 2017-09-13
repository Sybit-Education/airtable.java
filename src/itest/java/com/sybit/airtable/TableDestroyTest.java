/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sybit.airtable;

import com.sybit.airtable.Base;
import com.sybit.airtable.Table;
import com.sybit.airtable.exception.AirtableException;
import com.sybit.airtable.movies.Actor;
import com.sybit.airtable.mock.WireMockBaseTest;
import java.util.List;
import org.apache.http.client.HttpResponseException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author fzr
 */
public class TableDestroyTest extends WireMockBaseTest {
    
    
    
//    @Test
//    public void testDestroyMovie() throws AirtableException, HttpResponseException{
//    
//        Table<Actor> actorTable = base.table("Actors", Actor.class);
//              
//        actorTable.destroy("recAt6z10EYD6NtEH");   
//               
//    }
    
    @Test (expected = AirtableException.class)
    public void testDestroyMovieException() throws AirtableException{

        Table<Actor> actorTable = base.table("Actors", Actor.class);
              
        actorTable.destroy("not succesfull"); 
    }
    
}
