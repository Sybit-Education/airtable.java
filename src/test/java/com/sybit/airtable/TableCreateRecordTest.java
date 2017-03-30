/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sybit.airtable;

import com.sybit.airtable.exception.AirtableException;
import com.sybit.airtable.movies.Actor;
import com.sybit.airtable.test.WireMockBaseTest;
import java.lang.reflect.InvocationTargetException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author fzr
 */
public class TableCreateRecordTest extends WireMockBaseTest  {
    
    @Test
    public void createRecordTest() throws AirtableException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        
        Base base = airtable.base("appe9941ff07fffcc");
        
        Table<Actor> actorTable = base.table("Actors", Actor.class);
        Actor newActor = new Actor();
        newActor.setName("Neuer Actor");
        newActor.setId("10");
        Actor test = actorTable.create(newActor);
        assertEquals(test.getName(),newActor.getName());
        assertEquals(test.getId(),"rec123456789");
        
    }
    
}
