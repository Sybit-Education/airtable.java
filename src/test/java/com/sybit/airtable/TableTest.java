/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable;

import com.sybit.airtable.exception.AirtableException;
import com.sybit.airtable.movies.Actor;
import com.sybit.airtable.movies.ActorSerializedNames;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertNotNull;

/**
 *
 * @author fzr
 */
public class TableTest {

    private Base base;

    @Before
    public void before() throws AirtableException {

        Airtable airtable = new Airtable().configure(new Configuration("123", "https://url", null));
        this.base = new Base("base", airtable);

    }

    @Test(expected = java.lang.AssertionError.class)
    public void testTableAssertions() {
        Table table = new Table(null, null);

    }

    @Test
    public void testTable() {

        Table<Actor> table = new Table<>("table", Actor.class, this.base);
        assertNotNull(table);

    }

    @Test
    public void setParentTest() {

        Table<Actor> table = new Table<>("table", Actor.class);
        table.setParent(this.base);
        assertNotNull(table);
    }

    @Test
    public void key2properties() {
        Table<Actor> table = new Table<>("table", Actor.class);

        String actual = table.key2property("FirstName");
        Assert.assertEquals("firstName", actual);

        actual = table.key2property("First-Name");
        Assert.assertEquals("first-Name", actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void key2properties_EmptyArgument() {
        Table<Actor> table = new Table<>("table", Actor.class);
        String actual = table.key2property("");
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void key2properties_NullArgument() {
        Table<Actor> table = new Table<>("table", Actor.class);
        String actual = table.key2property(null);
        Assert.fail();
    }

    @Test
    public void testSetPropertyExistingField()
    {
        Table<ActorSerializedNames> table = new Table<>("table", ActorSerializedNames.class);
        ActorSerializedNames row = new ActorSerializedNames();

        Method setPropertyMethod = null;

        try {
            setPropertyMethod = Table.class.getDeclaredMethod("setProperty", Object.class, String.class, Object.class);

        } catch(NoSuchMethodException e) {
            Assert.fail("Could not get setProperty method.");
        }

        setPropertyMethod.setAccessible(true);


        try {
            String val = "biographyData";
            // test valid SerializedName
            setPropertyMethod.invoke(table, row, "Biography of Actor", val);

        } catch(IllegalAccessException | InvocationTargetException e) {
            Assert.fail("Failed to set serializedName property");
        }
        Assert.assertSame(row.getBiography(), "biographyData");
    }

    @Test(expected = InvocationTargetException.class)
    public void testSetPropertyNonexistentField() throws InvocationTargetException
    {
        Table<ActorSerializedNames> table = new Table<>("table", ActorSerializedNames.class);
        ActorSerializedNames row = new ActorSerializedNames();

        Method setPropertyMethod = null;

        try {
            setPropertyMethod = Table.class.getDeclaredMethod("setProperty", Object.class, String.class, Object.class);

        } catch(NoSuchMethodException e) {
            Assert.fail("Could not get setProperty method.");
        }

        setPropertyMethod.setAccessible(true);
        // test invalid field name
        try {
            String val = "failMe";
            setPropertyMethod.invoke(table,row, "schim", val);
            Assert.fail("Calling setProperty on nonexistent property 'schim' did not result in exception as expected.");
        }

        catch(IllegalAccessException e) {
            Assert.fail(String.format("%s: %s", "Failed to set serializedName property \n", e.getClass().getName()));
        }

    }

}
