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
import com.sybit.airtable.movies.Movie;
import com.sybit.airtable.mock.WireMockBaseTest;
import com.sybit.airtable.vo.Attachment;
import com.sybit.airtable.vo.Thumbnail;
import java.util.List;
import java.util.Map;
import org.apache.http.client.HttpResponseException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author fzr
 */
public class TableConverterTest extends WireMockBaseTest {
    
    //TODO Test für nicht gülitiges bzw String 
    
    @Test
    public void testConvertMovie() throws AirtableException, HttpResponseException {

        
        Table<Movie> movieTable = base.table("Movies", Movie.class);
        Movie movie = movieTable.find("recFj9J78MLtiFFMz");
        assertNotNull(movie);
        
        assertEquals(movie.getId(),"recFj9J78MLtiFFMz");
        assertEquals(movie.getName(),"The Godfather");
        assertEquals(movie.getPhotos().size(),2);
        assertEquals(movie.getDirector().size(),1);
        assertEquals(movie.getActors().size(),2);
        assertEquals(movie.getGenre().size(),1);
        //TODO Test für Datum
                  
    }
    
    @Test
    public void testConvertAttachement() throws AirtableException, HttpResponseException {
    
        
        Table<Movie> movieTable = base.table("Movies", Movie.class);
        Movie movie = movieTable.find("recFj9J78MLtiFFMz");
        assertNotNull(movie);
        
        assertEquals(movie.getPhotos().size(),2);
        
        Attachment photo1 = movie.getPhotos().get(0);
        assertNotNull(photo1);
        Attachment photo2 = movie.getPhotos().get(0);
        assertNotNull(photo2);
        
        assertEquals(photo1.getId(),"attk3WY5B28GVcFGU");
        assertEquals(photo1.getUrl(),"https://dl.airtable.com/9UhUUeAtSym1PzBdA0q0_AlPacinoandMarlonBrando.jpg");
        assertEquals(photo1.getFilename(),"AlPacinoandMarlonBrando.jpg");
        assertEquals(photo1.getSize(),35698,0);
        assertEquals(photo1.getType(),"image/jpeg");
        assertEquals(photo1.getThumbnails().size(),2);
        
    }
    
    @Test
    public void testConvertThumbnails() throws AirtableException, HttpResponseException {
                
        Table<Movie> movieTable = base.table("Movies", Movie.class);
        Movie movie = movieTable.find("recFj9J78MLtiFFMz");
        assertNotNull(movie);
        
        assertEquals(movie.getPhotos().get(0).getThumbnails().size(),2);
        assertEquals(movie.getPhotos().get(1).getThumbnails().size(),2);
        Map<String, Thumbnail> thumbnails = movie.getPhotos().get(1).getThumbnails();
        Thumbnail thumb = thumbnails.get("small");
        assertEquals(thumb.getUrl(),"https://dl.airtable.com/rlQ8MyQ4RuqN7rT03ALq_small_The%20Godfather%20poster.jpg");
        assertEquals(thumb.getHeight(),36.0, 0);
        assertEquals(thumb.getWidth(),24.0, 0);
        
    }
    
}
