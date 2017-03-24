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
    
    @Test
    public void testConvertMovie() throws AirtableException, HttpResponseException {

        Base base = airtable.base("appe9941ff07fffcc");
        
        Table<Movie> movieTable = base.table("Movies", Movie.class);
        Movie movie = movieTable.find("rec6733da527dd0f1");
        assertNotNull(movie);
        
        assertEquals(movie.getId(),"rec6733da527dd0f1");
        assertEquals(movie.getName(),"The Godfather");
        assertEquals(movie.getDescription(),"The Godfather is a 1972 American crime film film directed by Francis Ford Coppola and produced by Albert S. Ruddy and based on Mario Puzo's best-selli...");
        assertEquals(movie.getPhotos().size(),2);
        assertEquals(movie.getDirector(),"recfaf64fe0db19a9");
        assertEquals(movie.getActors().size(),2);
        assertEquals(movie.getGenre().size(),1);
        //TODO Test für Datum
                  
    }
    
    @Test
    public void testConvertAttachement() throws AirtableException, HttpResponseException {
    
        
        Base base = airtable.base("appe9941ff07fffcc");
        
        Table<Movie> movieTable = base.table("Movies", Movie.class);
        Movie movie = movieTable.find("rec6733da527dd0f1");
        assertNotNull(movie);
        
        assertEquals(movie.getPhotos().size(),2);
        
        Attachment photo1 = movie.getPhotos().get(0);
        assertNotNull(photo1);
        Attachment photo2 = movie.getPhotos().get(0);
        assertNotNull(photo2);
        
        assertEquals(photo1.getId(),"att6dba4af5786df1");
        assertEquals(photo1.getUrl(),"https://www.filepicker.io/api/file/akW7wUX7QM66a2hjxb9k");
        assertEquals(photo1.getFilename(),"220px-TheGodfatherAlPacinoMarlonBrando.jpg");
        assertEquals(photo1.getSize(),16420.0,0);
        assertEquals(photo1.getType(),"image/jpeg");
        assertEquals(photo1.getThumbnails().size(),2);
        
    }
    
    @Test
    public void testConvertThumbnails() throws AirtableException, HttpResponseException {
        
        Base base = airtable.base("appe9941ff07fffcc");
        
        Table<Movie> movieTable = base.table("Movies", Movie.class);
        Movie movie = movieTable.find("rec6733da527dd0f1");
        assertNotNull(movie);
        
        assertEquals(movie.getPhotos().get(0).getThumbnails().size(),2);
        assertEquals(movie.getPhotos().get(1).getThumbnails().size(),2);
        Map<String, Thumbnail> thumbnails = movie.getPhotos().get(1).getThumbnails();
        Thumbnail thumb = thumbnails.get("small");
        assertEquals(thumb.getUrl(),"https://dl.airtable.com/MbdRAn4ZQLuNyUqrHONp_small_Lighthouse.jpg");
        assertEquals(thumb.getHeight(),36.0, 0);
        assertEquals(thumb.getWidth(),48.0, 0);
        
    }
    
}
