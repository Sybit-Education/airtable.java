package com.sybit.airtable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.sybit.airtable.exception.AirtableException;
import com.sybit.airtable.movies.ActorSerializedNames;
import com.sybit.airtable.movies.Movie;
import com.sybit.airtable.mock.WireMockBaseTest;
import org.apache.http.client.HttpResponseException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.any;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by kobisuissa on 18/04/17.
 */
public class TableSelectJacksonOMTest extends WireMockBaseTest {

    @Before
    public void setup() throws AirtableException {
        airtable.configure(new ObjectMapper() {

            final com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

            @Override
            public <T> T readValue(final String value, final Class<T> valueType) {
                try {
                    return objectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public String writeValue(final Object value) {
                try {
                    return objectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        airtable.setEndpointUrl("http://localhost:8080/v0");

        //set 404 as default
        stubFor(any(anyUrl())
            .atPriority(10)
            .willReturn(aResponse()
                .withStatus(404)
                .withBody("{\"error\":{\"type\":\"NOT_FOUND\",\"message\":\"Not found\"}}")));

    }

    @Test
    public void testSelectTable() throws AirtableException, HttpResponseException {

        Base base = airtable.base("appe9941ff07fffcc");

        List<Movie> retval = base.table("Movies", Movie.class).select();
        assertNotNull(retval);
        assertEquals(10, retval.size());
        Movie mov = retval.get(0);
        assertEquals("Sister Act", mov.getName());
    }

    @Test
    public void testSelectTableMaxRecords() throws AirtableException, HttpResponseException {

        Base base = airtable.base("appe9941ff07fffcc");

        List<Movie> retval = base.table("Movies", Movie.class).select(2);
        assertNotNull(retval);
        assertEquals(2, retval.size());
        Movie mov = retval.get(0);
        assertEquals("Sister Act", mov.getName());
    }

    @Test
    public void testSelectTableSorted() throws AirtableException, HttpResponseException {

        Base base = airtable.base("appe9941ff07fffcc");
        Table table = base.table("Movies", Movie.class);

        List<Movie> retval = table.select(new Sort("Name", Sort.Direction.asc));
        assertNotNull(retval);
        assertEquals(10, retval.size());
        Movie mov = retval.get(0);
        assertEquals("Billy Madison", mov.getName());

        retval = table.select(new Sort("Name", Sort.Direction.desc));
        assertNotNull(retval);
        assertEquals(10, retval.size());
        mov = retval.get(0);
        assertEquals("You've got Mail", mov.getName());

    }

    @Test
    public void testSelectTableView() throws AirtableException, HttpResponseException {

        Base base = airtable.base("appe9941ff07fffcc");

        List<Movie> retval = base.table("Movies", Movie.class).select("Main View");
        assertNotNull(retval);
        assertEquals(10, retval.size());
        Movie mov = retval.get(0);
        assertEquals("The Godfather", mov.getName());
    }

    @Test(expected = AirtableException.class)
    public void testSelectNonExistingTable() throws AirtableException, HttpResponseException {

        Base base = airtable.base("appe9941ff07fffcc");

        List<Movie> retval = base.table("NotExists", Movie.class).select();
        assertNotNull(retval);
    }

    @Test
    public void testSelectWithSerializedNames() throws AirtableException, HttpResponseException {

        Base base = airtable.base("appe9941ff07fffcc");

        List<ActorSerializedNames> retval = base.table("SerializedNames", ActorSerializedNames.class).select();
        assertNotNull(retval);
        assertEquals("Marlon Brando", retval.get(0).getName());
    }

}
