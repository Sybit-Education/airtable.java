/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sybit.airtable;

import com.sybit.airtable.exception.AirtableException;
import com.sybit.airtable.movies.Actor;
import com.sybit.airtable.movies.Movie;
import com.sybit.airtable.test.WireMockBaseTest;
import java.util.List;
import org.apache.http.client.HttpResponseException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author fzr
 */
public class TableConverterTest extends WireMockBaseTest {
    
    @Test
    public void testConvertAttachements() throws AirtableException, HttpResponseException {

        Base base = airtable.base("appe9941ff07fffcc");
        
        Table<Movie> movieTable = base.table("Movies", Movie.class);
        Movie movie = movieTable.find("rec6733da527dd0f1");
        assertNotNull(movie);

    }
    
}
