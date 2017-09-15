/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;

import com.sybit.airtable.exception.AirtableException;
import com.sybit.airtable.movies.Actor;
import com.sybit.airtable.movies.Movie;
import com.sybit.airtable.mock.WireMockBaseTest;
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
                
        Table<Movie> movieTable = base.table("Movies", Movie.class);
        Movie newMovie = new Movie();       
        newMovie.setName("Neuer Film");       
        newMovie.setId("1");     
        Movie test = movieTable.create(newMovie);
         
    }
    
    @Test(expected = AirtableException.class)
    public void createMovieWithCreatedTimeTest() throws AirtableException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, NoSuchFieldException{
                
        Table<Movie> movieTable = base.table("Movies", Movie.class);
        Movie newMovie = new Movie();       
        newMovie.setName("Neuer Film");       
        newMovie.setCreatedTime(new Date());     
        Movie test = movieTable.create(newMovie);
         
    }
    
    @Test
    public void createActorTest() throws AirtableException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, NoSuchFieldException{
                
        Table<Actor> actorTable = base.table("Actors", Actor.class);
        Actor newActor = new Actor();
        newActor.setName("Neuer Actor");
        Actor test = actorTable.create(newActor);
        assertEquals(test.getName(),newActor.getName());
        assertNotNull(test.getId());
                
    }
    
    @Test
    public void createMovieWithAttachementTest() throws AirtableException, IllegalAccessException, NoSuchMethodException, NoSuchMethodException, InstantiationException, InvocationTargetException, NoSuchFieldException {
                
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
                
        Table<Movie> movieTable = base.table("Movies", Movie.class);
        Movie newMovie = new Movie();
        
        newMovie.setName("Neuer Film");
        newMovie.setDescription("Irgendwas");
        List<String> director = new ArrayList<>();
        director.add("recPxOZblV8yJU4mY");
        newMovie.setDirector(director);
        List<String> actors = new ArrayList<>();
        actors.add("recEtUIW6FWtbEDKz");
        actors.add("recInYFZ1DQpeCuSz");
        newMovie.setActors(actors);
        List<String> genre = new ArrayList<>();
        genre.add("Drama");
        newMovie.setGenre(genre);
        
        Movie test = movieTable.create(newMovie);
        
        assertEquals(newMovie.getName(),test.getName());
        assertEquals(newMovie.getActors(),test.getActors());
        assertEquals(newMovie.getGenre(),test.getGenre());
        assertEquals(newMovie.getDescription(),test.getDescription());
        assertNotNull(test.getId());
        assertNotNull(test.getCreatedTime());
        
        
    }
    
}
