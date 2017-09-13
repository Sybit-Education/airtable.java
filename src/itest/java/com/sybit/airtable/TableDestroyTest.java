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
import org.apache.http.client.HttpResponseException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author fzr
 */
public class TableDestroyTest extends WireMockBaseTest {

    @Test
    public void testDestroyMovie() throws AirtableException, HttpResponseException {

        Base base = airtable.base("appe9941ff07fffcc");
        Table<Actor> actorTable = base.table("Actors", Actor.class);

        boolean destroyed = actorTable.destroy("recapJ3Js8AEwt0Bf");
        assertTrue(destroyed);

    }

    /**
     * No Condition found under which Airtable returns false for deleting an Object. Either it doesent find it, which results in a 404 HTTP Exception
     * Or something else is wrong with the Syntax, which results in another Exception.
     * Therefore this test is Ignored as long as we dont have an Example of a failed delete from Airtable.
     * @throws AirtableException 
     */
    
    @Ignore
    @Test
    public void testDestroyMovieFailure() throws AirtableException {

        Base base = airtable.base("appe9941ff07fffcc");
        Table<Actor> actorTable = base.table("Actors", Actor.class);

        boolean destroyed = actorTable.destroy("failed");
        assertFalse(destroyed);
    }

    @Test(expected = AirtableException.class)
    public void testDestroyMovieException() throws AirtableException {

        Base base = airtable.base("appe9941ff07fffcc");
        Table<Actor> actorTable = base.table("Actors", Actor.class);

        boolean destroyed = actorTable.destroy("not succesfull");
    }

}
