package com.tableau.airtable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class AirtableJDBCTest {

    @Test
    public void getConnection() throws SQLException {
        DriverManager.registerDriver (new com.tableau.airtable.AirtableJDBC());
        Properties props = new Properties();
        props.put("key", System.getenv("AIRTABLE_API_KEY"));
        props.put("base", "appOQS45OT1iBFN9l");
        DriverManager.getConnection("airtable:https://api.airtable.com/v0", props);
    }
}
