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
    
    @Test
    public void createActorTest() throws AirtableException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException{
        
        Base base = airtable.base("appe9941ff07fffcc");
        
        Table<Actor> actorTable = base.table("Actors", Actor.class);
        Actor newActor = new Actor();
        newActor.setName("Neuer Actor");
        newActor.setId("10");
        Actor test = actorTable.create(newActor);
        assertEquals(test.getName(),newActor.getName());
        assertEquals(test.getId(),"rec123456789");
        
    }
    
    @Test
    public void createMovieTest() throws AirtableException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException{
        
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
        //Date d = new Date();
        //newMovie.setCreatedTime(d);
        
        Movie test = movieTable.create(newMovie);
        
        assertEquals(newMovie.getName(),test.getName());
        assertEquals(newMovie.getActors(),test.getActors());
        assertEquals(newMovie.getGenre(),test.getGenre());
        assertEquals(newMovie.getDescription(),test.getDescription());
        assertEquals("rec987654321",test.getId());
        assertNotNull(test.getCreatedTime());
        
        
    }
    
}
