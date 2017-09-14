/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.sybit.airtable.Airtable;
import com.sybit.airtable.exception.AirtableException;
import org.junit.Before;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.recording.SnapshotRecordResult;
import com.sybit.airtable.Base;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.After;

/**
 * Base Class to test using WireMock.
 *
 * Config files for the requests are stored at
 * 'src/test/resources/__files' and 'src/test/resources/mappings'.
 */
public class WireMockBaseTest {

    private static class WiremockProp {

        private static boolean recording;

        private static boolean cleanDirectorys;

        private static String targetUrl;

        private static String proxyBase;

        private static int proxyPort;

        private static int serverPort;

        /**
         * @return the recording
         */
        public static boolean isRecording() {
            return recording;
        }

        /**
         * @param aRecording the recording to set
         */
        public static void setRecording(boolean aRecording) {
            recording = aRecording;
        }

        /**
         * @return the cleanDirectorys
         */
        public static boolean isCleanDirectorys() {
            return cleanDirectorys;
        }

        /**
         * @param aCleanDirectorys the cleanDirectorys to set
         */
        public static void setCleanDirectorys(boolean aCleanDirectorys) {
            cleanDirectorys = aCleanDirectorys;
        }

        /**
         * @return the targetUrl
         */
        public static String getTargetUrl() {
            return targetUrl;
        }

        /**
         * @param aTargetUrl the targetUrl to set
         */
        public static void setTargetUrl(String aTargetUrl) {
            targetUrl = aTargetUrl;
        }

        /**
         * @return the proxyBase
         */
        public static String getProxyBase() {
            return proxyBase;
        }

        /**
         * @param aProxyBase the proxyBase to set
         */
        public static void setProxyBase(String aProxyBase) {
            proxyBase = aProxyBase;
        }

        /**
         * @return the proxyPort
         */
        public static int getProxyPort() {
            return proxyPort;
        }

        /**
         * @param aProxyPort the proxyPort to set
         */
        public static void setProxyPort(int aProxyPort) {
            proxyPort = aProxyPort;
        }

        /**
         * @return the serverPort
         */
        public static int getServerPort() {
            return serverPort;
        }

        /**
         * @param aServerPort the serverPort to set
         */
        public static void setServerPort(int aServerPort) {
            serverPort = aServerPort;
        }
    };

    private static WireMockServer wireMockServer;
    private static WiremockProp prop;
    
    protected static Airtable airtable = new Airtable();
    protected static Base base;

    @Before
    public void setUp() throws AirtableException {
        
        airtable.configure();
        airtable.setProxy("127.0.0.1");
        airtable.setEndpointUrl("http://localhost:8080");
        base = airtable.base("appTtHA5PfJnVfjdu");
        
        WiremockProp.setRecording(false);
        WiremockProp.setCleanDirectorys(false);
        WiremockProp.setProxyBase("192.168.1.254");
        WiremockProp.setProxyPort(8080);
        WiremockProp.setServerPort(8080);
        WiremockProp.setTargetUrl("https://api.airtable.com/v0");

        if (WiremockProp.getProxyBase() != null && WiremockProp.getProxyPort() != 0) {
            wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(WiremockProp.getServerPort()).proxyVia(WiremockProp.getProxyBase(), WiremockProp.getProxyPort()));
        } else {
            wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(WiremockProp.getServerPort()));
        }

        //start the Wiremock-Server
        startServer();

        //check if record 
        if (WiremockProp.isRecording()) {
            //check if cleanDirectorys
            if (WiremockProp.isCleanDirectorys()) {
                cleanExistingRecords();
                startRecording();
            } else {
                startRecording();
            }
        }
    }

    @After
    public void tearDown() {

        if (prop.isRecording()) {
            stopRecording();
        }

        stopServer();
    }

    public static void startRecording() {

        wireMockServer.startRecording(recordSpec()
                .forTarget(prop.getTargetUrl())
                .captureHeader("Accept")
                .captureHeader("Content-Type", true)
                .extractBinaryBodiesOver(0)
                .extractTextBodiesOver(0)
                .makeStubsPersistent(true)
                .transformers("modify-response-header")
                .transformerParameters(Parameters.one("headerValue", "123"))
                .matchRequestBodyWithEqualToJson(false, true));
    }

    public static void stopRecording() {

        SnapshotRecordResult recordedMappings = wireMockServer.stopRecording();
    }

    public static void startServer() {

        wireMockServer.start();
    }

    public static void stopServer() {

        wireMockServer.stop();
    }

    public static void cleanExistingRecords() {

        File mappings = new File("src/test/resources/mappings");
        File bodyFiles = new File("src/test/resources/__files");

        try {
            FileUtils.cleanDirectory(mappings);
            FileUtils.cleanDirectory(bodyFiles);
        } catch (IOException ex) {
            System.out.println("Exception deleting Files: " + ex);
        }
    }

}

