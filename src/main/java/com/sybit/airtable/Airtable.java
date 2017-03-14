package com.sybit.airtable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import org.apache.http.HttpHost;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class Airtable {

    private static final Logger LOG = Logger.getLogger( Airtable.class.getName() );
    private static final String ENDPOINT_URL = "https://api.airtable.com/v0";

    private static String  endpointUrl;
    private static String apiKey;

    /**
     * Configure, using java property vareiable 'AirtableAPI' to set API-Key.
     *
     * @return
     */
    public Airtable configure() throws AirtableException {
        String property = "AirtableApi";
        LOG.log(Level.CONFIG, "Using Java property '-D" + property + "' to get key.");
        final String airtableApi = System.getProperty(property);

        return this.configure(airtableApi);
    }

    /**
     * Configure Airtable.
     *
     * @param apiKey API-Key of Airtable.
     * @return
     */
    public Airtable configure(String apiKey) throws AirtableException {
        return configure(apiKey, ENDPOINT_URL);
    }

    /**
     *
     * @param apiKey
     * @param endpointUrl
     * @return
     */
    public Airtable configure(String apiKey, String endpointUrl) throws AirtableException {
        if(apiKey == null) {
            throw new AirtableException("apiKey was null");
        }
        if(endpointUrl == null) {
            throw new AirtableException("endpointUrl was null");
        }

        this.apiKey = apiKey;
        this.endpointUrl = endpointUrl;

        final String httpProxy = System.getenv("http_proxy");
        if(httpProxy != null) {
            LOG.log( Level.INFO, "Use Proxy: Environment variable 'http_proxy' found and used: " + httpProxy);
            Unirest.setProxy(HttpHost.create(httpProxy));
        }

        // Only one time
        Unirest.setObjectMapper(new ObjectMapper() {
            final Gson gson = new GsonBuilder().create();

            public <T> T readValue(String value, Class<T> valueType) {
                return gson.fromJson(value, valueType);
            }

            public String writeValue(Object value) {
                return gson.toJson(value);
            }
        });

        return this;
    }

    /**
     * Getting the base by given Java VM property <code>AirtableBase</code> (<code>-DAirtableBase=xyz</code>.
     * @return the base object.
     */
    public Base base() throws AirtableException {
        String property = "AirtableBase";
        LOG.log(Level.CONFIG, "Using Java property '-D" + property + "' to get key.");
        String val = System.getProperty(property);

        return base(val);
    }

    /**
     *
     * @param base
     * @return
     */
    public Base base(String base) throws AirtableException {
        if(base == null) {
            throw new AirtableException("base was null");
        }
        Base b = new Base(base);
        b.setParent(this);

        return b;
    }

    /**
     *
     * @return
     */
    public String endpointUrl() {
        return endpointUrl;
    }

    /**
     *
     * @return
     */
    public String apiKey() {
        return apiKey;
    }

}
