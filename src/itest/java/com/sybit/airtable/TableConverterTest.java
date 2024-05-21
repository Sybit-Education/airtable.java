/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;


import com.sybit.airtable.exception.AirtableException;
import com.sybit.airtable.movies.Movie;
import com.sybit.airtable.mock.WireMockBaseTest;
import com.sybit.airtable.vo.Attachment;
import com.sybit.airtable.vo.Thumbnail;
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

        assertEquals(2, movie.getPhotos().size());

        Attachment photo1 = movie.getPhotos().get(0);
        assertNotNull(photo1);
        Attachment photo2 = movie.getPhotos().get(0);
        assertNotNull(photo2);

        assertEquals("attk3WY5B28GVcFGU",photo1.getId());
        assertEquals("https://dl.airtable.com/9UhUUeAtSym1PzBdA0q0_AlPacinoandMarlonBrando.jpg",photo1.getUrl());
        assertEquals("AlPacinoandMarlonBrando.jpg",photo1.getFilename());
        assertEquals(35698, photo1.getSize(),0);
        assertEquals("image/jpeg",photo1.getType());
        assertEquals(2, photo1.getThumbnails().size());
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
        assertEquals("https://dl.airtable.com/rlQ8MyQ4RuqN7rT03ALq_small_The%20Godfather%20poster.jpg",thumb.getUrl());
        assertEquals(36.0, thumb.getHeight(), 0);
        assertEquals(24.0, thumb.getWidth(), 0);
    }

}
