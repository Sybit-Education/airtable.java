/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sybit.airtable.integrationTest;

import com.sybit.airtable.Base;
import com.sybit.airtable.Table;
import com.sybit.airtable.exception.AirtableException;
import com.sybit.airtable.movies.Actor;
import com.sybit.airtable.movies.Movie;
import com.sybit.airtable.test.WireMockBaseTest;
import com.sybit.airtable.vo.Attachment;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author fzr
 */
public class TableCreateRecordTest extends WireMockBaseTest  {
    
    @Test(expected = AirtableException.class)
    public void createMovieWithPhotoIdTest() throws AirtableException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        
        Base base = airtable.base("appe9941ff07fffcc");
        
        Table<Movie> movieTable = base.table("Movies", Movie.class);
        Movie newMovie = new Movie();       
        newMovie.setName("Neuer Film");       
           
        List<Attachment> photos = new ArrayList<Attachment>();
        Attachment photo1 = new Attachment();
        
        photo1.setUrl("https://www.example.imgae.file1.de");
        photo1.setId("1");
              
        photos.add(photo1);      
           
        newMovie.setPhotos(photos);
        
        Movie test = movieTable.create(newMovie);
    
    }
    
    @Test(expected = AirtableException.class)
    public void createMovieWithIdTest() throws AirtableException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, NoSuchFieldException{
        
        Base base = airtable.base("appe9941ff07fffcc");
        
        Table<Movie> movieTable = base.table("Movies", Movie.class);
        Movie newMovie = new Movie();       
        newMovie.setName("Neuer Film");       
        newMovie.setId("1");     
        Movie test = movieTable.create(newMovie);
         
    }
    
    @Test(expected = AirtableException.class)
    public void createMovieWithCreatedTimeTest() throws AirtableException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, NoSuchFieldException{
        
        Base base = airtable.base("appe9941ff07fffcc");
        
        Table<Movie> movieTable = base.table("Movies", Movie.class);
        Movie newMovie = new Movie();       
        newMovie.setName("Neuer Film");       
        newMovie.setCreatedTime(new Date());     
        Movie test = movieTable.create(newMovie);
         
    }
    
    @Test
    public void createActorTest() throws AirtableException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, NoSuchFieldException{
        
        Base base = airtable.base("appe9941ff07fffcc");
        
        Table<Actor> actorTable = base.table("Actors", Actor.class);
        Actor newActor = new Actor();
        newActor.setName("Neuer Actor");
        Actor test = actorTable.create(newActor);
        assertEquals(test.getName(),newActor.getName());
        assertEquals(test.getId(),"rec123456789");
        
    }
    
    @Test
    public void createMovieWithAttachementTest() throws AirtableException, IllegalAccessException, NoSuchMethodException, NoSuchMethodException, InstantiationException, InvocationTargetException, NoSuchFieldException {
        
        Base base = airtable.base("appe9941ff07fffcc");
        
        Table<Movie> movieTable = base.table("Movies", Movie.class);
        Movie newMovie = new Movie();
        
        newMovie.setName("Neuer Film");
        List<Attachment> photos = new ArrayList<Attachment>();
        Attachment photo1 = new Attachment();
        Attachment photo2 = new Attachment();
        photo1.setUrl("https://www.example.imgae.file1.de");
        photo2.setUrl("https://www.example.imgae.file2.de");
        photos.add(photo1);      
        photos.add(photo2);
        
        newMovie.setPhotos(photos);

        Movie test = movieTable.create(newMovie);
        
        assertEquals("https://www.example.imgae.file1.de", test.getPhotos().get(0).getUrl());
        assertEquals("https://www.example.imgae.file2.de", test.getPhotos().get(1).getUrl());
        
        assertNotNull(test.getPhotos().get(0).getId());
        assertNotNull(test.getPhotos().get(1).getId());
        
        
    }
    
    @Test
    public void createMovieTest() throws AirtableException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, NoSuchFieldException{
        
        Base base = airtable.base("appe9941ff07fffcc");
        
        Table<Movie> movieTable = base.table("Movies", Movie.class);
        Movie newMovie = new Movie();
        
        newMovie.setName("Neuer Film");
        newMovie.setDescription("Irgendwas");
        List<String> director = new ArrayList<>();
        director.add("recfaf64fe0db19a9");
        newMovie.setDirector(director);
        List<String> actors = new ArrayList<>();
        actors.add("recc8841a14245b0b");
        actors.add("rec514228ed76ced1");
        newMovie.setActors(actors);
        List<String> genre = new ArrayList<>();
        genre.add("Drama");
        newMovie.setGenre(genre);
        
        Movie test = movieTable.create(newMovie);
        
        assertEquals(newMovie.getName(),test.getName());
        assertEquals(newMovie.getActors(),test.getActors());
        assertEquals(newMovie.getGenre(),test.getGenre());
        assertEquals(newMovie.getDescription(),test.getDescription());
        assertEquals("rec987654321",test.getId());
        assertNotNull(test.getCreatedTime());
        
        
    }
    
}
