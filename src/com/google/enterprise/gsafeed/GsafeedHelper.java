// Copyright 2017 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.enterprise.gsafeed;

import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Helper methods for reading and creating GSA feed files.
 */
public class GsafeedHelper {

  public static Gsafeed unmarshalWithDtd(URL url) throws JAXBException,
      IOException, ParserConfigurationException, SAXException {
    return unmarshalWithDtd(url.openStream());
  }

  /**
   * Use the DTD to check for errors in the feed being read.
   * Assume the DTD is on the classpath as /gsafeed.dtd.
   */
  public static Gsafeed unmarshalWithDtd(InputStream inputStream)
      throws JAXBException, IOException, ParserConfigurationException,
      SAXException {
    JAXBContext jaxbContext =
        JAXBContext.newInstance("com.google.enterprise.gsafeed");
    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    DocumentBuilderFactory docBuilderFactory =
        DocumentBuilderFactory.newInstance();
    docBuilderFactory.setValidating(true);
    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
    docBuilder.setEntityResolver(new EntityResolver() {
        @Override
        public InputSource resolveEntity(String publicId,
            String systemId) throws SAXException, IOException {
          if (publicId.equals("-//Google//DTD GSA Feeds//EN")) {
            return new InputSource(
                GsafeedHelper.class.getResource("/gsafeed.dtd").openStream());
          }
          return null; // DTD is the only expected entity in feed files
        }
      });
    docBuilder.setErrorHandler(new ErrorHandler() {
        @Override
        public void warning(SAXParseException exception)
            throws SAXException {
          throw exception;
        }

        @Override
        public void error(SAXParseException exception)
            throws SAXException {
          throw exception;
        }

        @Override
        public void fatalError(SAXParseException exception)
            throws SAXException {
          throw exception;
        }
      });
    Document document = docBuilder.parse(inputStream);
    return (Gsafeed) unmarshaller.unmarshal(document);
  }

  /**
   * Avoid reading the DTD. No validation will happen.
   */
  public static Gsafeed unmarshalWithoutDtd(URL url) throws JAXBException,
      IOException, ParserConfigurationException, SAXException {
    JAXBContext jaxbContext =
        JAXBContext.newInstance("com.google.enterprise.gsafeed");
    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    DocumentBuilderFactory docBuilderFactory =
        DocumentBuilderFactory.newInstance();
    docBuilderFactory.setValidating(false);
    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
    docBuilder.setEntityResolver(new EntityResolver() {
        @Override
        public InputSource resolveEntity(String publicId,
            String systemId) throws SAXException, IOException {
          if (publicId.equals("-//Google//DTD GSA Feeds//EN")) {
            return new InputSource(new StringReader(""));
          }
          return null; // DTD is the only expected entity in feed files
        }
      });
    Document document = docBuilder.parse(url.openStream());
    return (Gsafeed) unmarshaller.unmarshal(document);
  }

  /**
   * Write the feed to the given stream.
   */
  public static void marshal(Gsafeed feed, OutputStream out)
      throws IOException, JAXBException {
    JAXBContext context = JAXBContext.newInstance(Gsafeed.class);
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    // By default, we get the XML declaration including
    // "standalone='yes'". This is one way to avoid that since
    // it's not part of the GSA's usual feeds.
    marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
    // TODO(aptls): check use of this property in Java 7, 8;
    // those versions might use com.sun.xml.bind.xmlHeaders.
    // Compare this with using a Transformer + JAXBSource for
    // marshalling.
    marshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders",
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
        + System.getProperty("line.separator")
        + "<!DOCTYPE gsafeed PUBLIC \"-//Google//DTD GSA Feeds//EN\" \"\">");
    marshaller.marshal(feed, out);
  }
}
