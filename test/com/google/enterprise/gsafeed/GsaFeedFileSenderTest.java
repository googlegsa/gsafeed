// Copyright 2012 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.enterprise.gsafeed;

import static org.junit.Assert.assertEquals;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.SSLContext;

/**
 * Test cases for {@link GsaFeedFileSender}.
 */
// Modified from GsaFeedFileSenderTest in adaptor library.
public class GsaFeedFileSenderTest {

  @BeforeClass
  public static void setUpClass() {
    // Tests trigger logs; remove those messages from test output.
    Logger.getLogger(GsaFeedFileSender.class.getName()).setLevel(Level.SEVERE);
  }

  // Copied from TestHelper.java
  public static void initSSLKeystores() {
    /*
     * test-keys.jks created with: keytool -genkeypair -alias adaptor -keystore
     * test/test-keys.jks -keyalg RSA -validity 1000000 -storepass changeit
     * -dname "CN=localhost, OU=Unknown, O=Unknown, L=Unknown, ST=Unknown,
     * C=Unknown" -keypass changeit"
     */
    System.setProperty("javax.net.ssl.keyStore",
        GsaFeedFileSenderTest.class.getResource("/test-keys.jks").getPath());
    System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
    /*
     * test-cacerts.jks created with: keytool -exportcert -alias adaptor
     * -keystore test/test-keys.jks -rfc -file tmp.crt -storepass changeit
     * keytool -importcert -keystore test/test-cacerts.jks -file tmp.crt
     * -storepass changeit -noprompt -alias adaptor rm tmp.crt
     */
    System.setProperty("javax.net.ssl.trustStore",
        GsaFeedFileSenderTest.class.getResource("/test-cacerts.jks").getPath());
    System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private Charset charset = Charset.forName("UTF-8");
  private HttpServer server;
  private int port;
  private GsaFeedFileSender sender;

  @Before
  public void startup() throws IOException {
    initSSLKeystores();
    server = HttpServer.create(new InetSocketAddress(0), 0);
    port = server.getAddress().getPort();
    server.start();
    URL feedUrl = new URL("http://localhost:" + port + "/xmlfeed");
    URL groupsUrl = new URL("http://localhost:" + port + "/xmlgroups");
    sender = new GsaFeedFileSender(feedUrl, groupsUrl, charset);
  }

  @After
  public void shutdown() {
    if (server != null) {
      server.stop(0);
    }
  }

  @Test
  public void testFeedSuccess() throws Exception {
    final String payload = "<someXmlString/>";
    final String datasource = "test-DataSource_09AZaz";
    final String goldenResponse
        = "--<<\r\n"
        + "Content-Disposition: form-data; name=\"datasource\"\r\n"
        + "Content-Type: text/plain\r\n"
        + "\r\n"
        + datasource + "\r\n"
        + "--<<\r\n"
        + "Content-Disposition: form-data; name=\"feedtype\"\r\n"
        + "Content-Type: text/plain\r\n"
        + "\r\n"
        + "metadata-and-url\r\n"
        + "--<<\r\n"
        + "Content-Disposition: form-data; name=\"data\"\r\n"
        + "Content-Type: text/xml\r\n"
        + "\r\n"
        + payload + "\r\n"
        + "--<<--\r\n";

    MockHttpHandler handler
        = new MockHttpHandler(200, "Success".getBytes(charset));
    server.createContext("/xmlfeed", handler);

    sender.sendGsaFeed(datasource, "metadata-and-url", payload, false);
    assertEquals("POST", handler.getRequestMethod());
    assertEquals(URI.create("/xmlfeed"), handler.getRequestUri());
    assertEquals("multipart/form-data; boundary=<<",
        handler.getRequestHeaders().getFirst("Content-Type"));
    assertEquals(goldenResponse,
        new String(handler.getRequestBytes(), charset));
  }

  @Test
  public void testFeedHttpsSuccess() throws Exception {
    final String payload = "<someXmlString/>";
    final String datasource = "testDataSource";
    final String goldenResponse
        = "--<<\r\n"
        + "Content-Disposition: form-data; name=\"datasource\"\r\n"
        + "Content-Type: text/plain\r\n"
        + "\r\n"
        + datasource + "\r\n"
        + "--<<\r\n"
        + "Content-Disposition: form-data; name=\"feedtype\"\r\n"
        + "Content-Type: text/plain\r\n"
        + "\r\n"
        + "metadata-and-url\r\n"
        + "--<<\r\n"
        + "Content-Disposition: form-data; name=\"data\"\r\n"
        + "Content-Type: text/xml\r\n"
        + "\r\n"
        + payload + "\r\n"
        + "--<<--\r\n";

    // Unfortunately this test requires a fixed port.
    server = HttpsServer.create(new InetSocketAddress(19902), 0);
    HttpsConfigurator httpsConf
        = new HttpsConfigurator(SSLContext.getDefault());
    ((HttpsServer) server).setHttpsConfigurator(httpsConf);
    server.start();
    MockHttpHandler handler
        = new MockHttpHandler(200, "Success".getBytes(charset));
    server.createContext("/xmlfeed", handler);

    URL feedUrl = new URL("https://localhost:19902/xmlfeed");
    URL groupsUrl = new URL("https://localhost:19902/xmlgroups");
    GsaFeedFileSender secureSender = new GsaFeedFileSender(feedUrl,
        groupsUrl, charset);
    secureSender.sendGsaFeed(datasource, "metadata-and-url", payload, false);
    assertEquals("POST", handler.getRequestMethod());
    assertEquals(URI.create("/xmlfeed"), handler.getRequestUri());
    assertEquals("multipart/form-data; boundary=<<",
        handler.getRequestHeaders().getFirst("Content-Type"));
    assertEquals(goldenResponse,
        new String(handler.getRequestBytes(), charset));
  }

  @Test
  public void testFeedSuccessGzipped() throws Exception {
    final String payload = "<someXmlString/>";
    final String datasource = "testDataSource";
    final String goldenResponse
        = "--<<\r\n"
        + "Content-Disposition: form-data; name=\"datasource\"\r\n"
        + "Content-Type: text/plain\r\n"
        + "\r\n"
        + datasource + "\r\n"
        + "--<<\r\n"
        + "Content-Disposition: form-data; name=\"feedtype\"\r\n"
        + "Content-Type: text/plain\r\n"
        + "\r\n"
        + "metadata-and-url\r\n"
        + "--<<\r\n"
        + "Content-Disposition: form-data; name=\"data\"\r\n"
        + "Content-Type: text/xml\r\n"
        + "\r\n"
        + payload + "\r\n"
        + "--<<--\r\n";

    MockHttpHandler handler
        = new MockHttpHandler(200, "Success".getBytes(charset));
    server.createContext("/xmlfeed", handler);

    sender.sendGsaFeed(datasource, "metadata-and-url", payload, true);
    assertEquals("POST", handler.getRequestMethod());
    assertEquals(URI.create("/xmlfeed"), handler.getRequestUri());
    assertEquals("multipart/form-data; boundary=<<",
        handler.getRequestHeaders().getFirst("Content-Type"));
    assertEquals("gzip",
        handler.getRequestHeaders().getFirst("Content-Encoding"));
    InputStream uncompressed = new GZIPInputStream(
        new ByteArrayInputStream(handler.getRequestBytes()));
    String response = new String(
        IOHelper.readInputStreamToByteArray(uncompressed), charset);
    assertEquals(goldenResponse, response);
  }

  @Test
  public void testFeedInvalidDataSource() throws Exception {
    thrown.expect(IllegalArgumentException.class);
    sender.sendGsaFeed("bad#source", "metadata-and-url", "<payload/>", false);
  }

  @Test
  public void testFeedInvalidDataSource2() throws Exception {
    thrown.expect(IllegalArgumentException.class);
    sender.sendGsaFeed("9badsource", "metadata-and-url", "<payload/>", false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFeedInvalidUrl() throws Exception {
    new GsaFeedFileSender("badname:", false, charset);
  }

  @Test
  public void testFeedFailureWriting() throws Exception {
    server.createContext("/xmlfeed", new HttpHandler() {
      @Override
      public void handle(HttpExchange ex) throws IOException {
        throw new IOException();
      }
    });
    String longMsg = "Some random really long string\n";
    int numRepeats = 1024 * 256;
    StringBuilder sb = new StringBuilder(longMsg.length() * numRepeats);
    for (int i = 0; i < numRepeats; i++) {
      sb.append(longMsg);
    }
    // This payload has to be enough to exhaust output buffers, otherwise the
    // exception will be noticed when reading the response.
    String longPayload = sb.toString();
    sb = null;
    thrown.expect(IOException.class);
    sender.sendGsaFeed("datasource", "metadata-and-url", longPayload, false);
  }

  @Test
  public void testFeedGsaReturnedFailure() throws Exception {
    MockHttpHandler handler
        = new MockHttpHandler(200, "Some failure".getBytes(charset));
    server.createContext("/xmlfeed", handler);

    thrown.expect(IllegalStateException.class);
    sender.sendGsaFeed("datasource", "metadata-and-url", "<payload/>", false);
  }

  @Test
  public void testFeedCantReadResponse() throws Exception {
    server.createContext("/xmlfeed", new HttpHandler() {
      @Override
      public void handle(HttpExchange ex) throws IOException {
        throw new IOException();
      }
    });

    thrown.expect(IOException.class);
    sender.sendGsaFeed("datasource", "metadata-and-url", "<payload/>", false);
  }

  @Test
  public void testGroupsSuccess_Incremental() throws Exception {
    final String payload = "<someXmlString/>";
    final String groupsource = "docspot";
    final String goldenResponse
        = "--<<\r\n"
        + "Content-Disposition: form-data; name=\"groupsource\"\r\n"
        + "Content-Type: text/plain\r\n"
        + "\r\n"
        + groupsource + "\r\n"
        + "--<<\r\n"
        + "Content-Disposition: form-data; name=\"feedtype\"\r\n"
        + "Content-Type: text/plain\r\n"
        + "\r\n"
        + "incremental\r\n"
        + "--<<\r\n"
        + "Content-Disposition: form-data; name=\"data\"\r\n"
        + "Content-Type: text/xml\r\n"
        + "\r\n"
        + payload + "\r\n"
        + "--<<--\r\n";
    MockHttpHandler handler
        = new MockHttpHandler(200, "Success".getBytes(charset));
    server.createContext("/xmlgroups", handler);
    sender.sendGroups(groupsource, "incremental", payload, false);
    assertEquals("POST", handler.getRequestMethod());
    assertEquals(URI.create("/xmlgroups"), handler.getRequestUri());
    assertEquals("multipart/form-data; boundary=<<",
        handler.getRequestHeaders().getFirst("Content-Type"));
    assertEquals(goldenResponse,
        new String(handler.getRequestBytes(), charset));
  }

  @Test
  public void testGroupsSuccess_Cleanup() throws Exception {
    final String payload = "<someXmlString/>";
    final String groupsource = "docspot";
    final String goldenResponse
        = "--<<\r\n"
        + "Content-Disposition: form-data; name=\"cleanup\"\r\n"
        + "Content-Type: text/plain\r\n"
        + "\r\n"
        + groupsource + "\r\n"
        + "--<<--\r\n";
    MockHttpHandler handler
        = new MockHttpHandler(200, "Success".getBytes(charset));
    server.createContext("/xmlgroups", handler);
    sender.sendGroups(groupsource, "cleanup", payload, false);
    assertEquals("POST", handler.getRequestMethod());
    assertEquals(URI.create("/xmlgroups"), handler.getRequestUri());
    assertEquals("multipart/form-data; boundary=<<",
        handler.getRequestHeaders().getFirst("Content-Type"));
    assertEquals(goldenResponse,
        new String(handler.getRequestBytes(), charset));
  }

  @Test
  public void testGroupsInvalidGroupSource() throws Exception {
    thrown.expect(IllegalArgumentException.class);
    sender.sendGroups("bad#source", "full", "<payload/>", false);
  }

  @Test
  public void testGroupsInvalidFeedType() throws Exception {
    thrown.expect(IllegalArgumentException.class);
    sender.sendGroups("groupsource", "invalid", "<payload/>", false);
  }
}
