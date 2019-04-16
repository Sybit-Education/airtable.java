package com.tableau.airtable;

import com.sybit.airtable.exception.AirtableException;

import java.sql.*;
import java.util.logging.Logger;
import java.util.Properties;

public class AirtableJDBC implements Driver {
    private static final String AIRTABLE_SCHEME = "airtable:";
    private static final Logger LOG = Logger.getLogger(AirtableJDBC.class.getName());


    public Logger getParentLogger() {
        return LOG;
    }

    public boolean jdbcCompliant() {
        return false;
    }

    public int getMajorVersion() {
        return 0;
    }

    public int getMinorVersion() {
        return 0;
    }

    public DriverPropertyInfo[] getPropertyInfo(String url,
                                                Properties info)
            throws SQLException {
        return new DriverPropertyInfo[]{new DriverPropertyInfo("apiKey", null),
                                        new DriverPropertyInfo("base", null)};
    }


    public boolean acceptsURL(String url)
            throws SQLException {
        return url.startsWith(AIRTABLE_SCHEME);
    }

    public AirtableJDBCConnection connect(String url,
                                      Properties info)
            throws SQLException {
        if (!acceptsURL(url)) {
            return null;
        }
        String apiEndpoint = url.substring(AIRTABLE_SCHEME.length());
        String apiKey = (String) info.get("key");
        String base = (String) info.get("base");
        try {
            return new AirtableJDBCConnection(apiEndpoint, base, apiKey);
        } catch (AirtableException e) {
            throw new SQLException(e);
        }
    }
}