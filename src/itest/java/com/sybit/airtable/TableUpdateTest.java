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
import java.lang.reflect.InvocationTargetException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author fzr
 */
public class TableUpdateTest extends WireMockBaseTest {
    
    @Test
    public void testUpdateActor() throws AirtableException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        
        
        Table<Actor> actorTable = base.table("Actors", Actor.class);
        Actor marlonBrando = new Actor();
        marlonBrando.setId("recEtUIW6FWtbEDKz");
        marlonBrando.setName("Neuer Name");
                   
        Actor updated = actorTable.update(marlonBrando);
        
        assertEquals(updated.getName(),"Neuer Name");
        assertNotNull(updated.getBiography());
        assertNotNull(updated.getFilmography());
        assertNotNull(updated.getPhoto());
        assertNotNull(updated.getId());
         
    }
}
