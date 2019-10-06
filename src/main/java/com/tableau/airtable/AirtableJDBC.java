package com.tableau.airtable;

import com.sybit.airtable.exception.AirtableException;

import java.sql.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.logging.Logger;
import java.util.Properties;

public class AirtableJDBC implements Driver {
    private static final String AIRTABLE_SCHEME = "jdbc:airtable:";
    private static final Logger LOG = Logger.getLogger(AirtableJDBC.class.getName());

    static {
        try {
            java.sql.DriverManager.registerDriver(new com.tableau.airtable.AirtableJDBC());
        } catch (SQLException E) {
            throw new RuntimeException("Can't register airtable driver!");
        }
    }

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
        return new DriverPropertyInfo[]{new DriverPropertyInfo("key", null),
                                        new DriverPropertyInfo("base", null)};
    }


    public boolean acceptsURL(String url)
            throws SQLException {
        System.out.println("acceptsURL");
        return url.startsWith(AIRTABLE_SCHEME);
    }

    public AirtableJDBCConnection connect(String url,
                                      Properties info)
            throws SQLException {
        if (!acceptsURL(url)) {
            return null;
        }
        //String airtableURI = url.substring(5); // https://api.airtable.com/v0/appOQS45OT1iBFN9l/Breeding%20Plants
        String airtableUrl = url.substring(AIRTABLE_SCHEME.length());
	URL atURL;
	try {
	    atURL = new URL(airtableUrl);
	} catch (MalformedURLException e) {
	    throw new SQLException("Invalid Airtable URL " + airtableUrl, e);
	}
        String protocol = atURL.getProtocol();
	String authority = atURL.getAuthority();        
	String path = atURL.getPath();
	
	String[] tokens = path.split("/");
	if (tokens.length < 4)
	    throw new SQLException("Invalid airtable url: " + airtableUrl);
	String apiVersion = tokens[1];
        String base = tokens[2];
	String defaultTable = tokens[3];
	System.out.println(">>> pulling apiKey from info ");
        String apiKey = (String) info.get("key");
	System.out.println(">>> pulling apiKey from info " + apiKey);
	if (apiKey == null) {
	    String query = atURL.getQuery();
	    if (query != null) {
		String[] keyTokens = query.split("=");
		if (keyTokens.length < 2 && !keyTokens[0].equals("api_key"))
		    throw new SQLException("Invalid airtable key: " + keyTokens);
		System.out.println(">>> pulling apiKey from query " + keyTokens[1]);
		apiKey=keyTokens[1];
	    }
	}
	if (apiKey == null)
	    throw new SQLException("No API Key");
	String apiEndpoint = protocol + "://" + authority + "/" + apiVersion;

	System.err.println(">>> endPoint " + apiEndpoint);
	System.err.println(">>> apiKey " + apiKey);
	System.err.println(">>> base " + base);
	System.err.println(">>> defaultTable " + defaultTable);

        try {
            return new AirtableJDBCConnection(apiEndpoint, base, apiKey, defaultTable);
        } catch (AirtableException e) {
            e.printStackTrace();
            throw new SQLException("Invalid airtable parameters", e);
        }
    }
}