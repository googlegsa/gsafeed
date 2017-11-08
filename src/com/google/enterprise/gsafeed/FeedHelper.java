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

import com.google.enterprise.gsafeed.groups.Xmlgroups;

import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URL;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

/**
 * Helper for reading and creating GSA feed files.
 */
public class FeedHelper<T> {
  public static final String PUBLIC_ID = "-//Google//DTD GSA Feeds//EN";

  public static FeedHelper<Gsafeed> getGsafeedHelper() throws JAXBException {
    return new FeedHelper<Gsafeed>("gsafeed", "/gsafeed.dtd", Gsafeed.class);
  }

  public static FeedHelper<Xmlgroups> getXmlgroupsHelper()
      throws JAXBException {
    return new FeedHelper<Xmlgroups>(
        "xmlgroups", "/groupsfeed.dtd", Xmlgroups.class);
  }

  private final String rootElement;
  private final JAXBContext jaxbContext;
  private final EntityResolver entityResolver;
  private ErrorHandler errorHandler;

  private FeedHelper(final String rootElement, final String dtdPath,
      final Class<T> c) throws JAXBException {
    this.rootElement = rootElement;
    this.jaxbContext = JAXBContext.newInstance(c.getPackage().getName());
    this.entityResolver = new EntityResolver() {
        @Override
        public InputSource resolveEntity(String publicId,
            String systemId) throws SAXException, IOException {
          if (publicId != null && publicId.equals(PUBLIC_ID)) {
            return new InputSource(c.getResource(dtdPath).openStream());
          }
          return new InputSource(new StringReader(""));
        }
      };
    this.errorHandler = new ErrorHandler() {
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
      };
  }

  public void setErrorHandler(ErrorHandler errorHandler) {
    this.errorHandler = errorHandler;
  }

  /**
   * Use the DTD to check for errors in the feed being read.
   */
  public T unmarshalWithDtd(URL url) throws JAXBException,
      IOException, ParserConfigurationException, SAXException {
    return unmarshalWithDtd(url.openStream());
  }

  /**
   * Use the DTD to check for errors in the feed being read.
   */
  public T unmarshalWithDtd(InputStream inputStream)
      throws JAXBException, IOException, ParserConfigurationException,
      SAXException {
    return unmarshal(inputStream, /* validating */ true);
  }

  /**
   * Avoid reading the DTD. No validation will happen.
   */
  public T unmarshalWithoutDtd(URL url) throws JAXBException,
      IOException, ParserConfigurationException, SAXException {
    return unmarshalWithoutDtd(url.openStream());
  }

  /**
   * Avoid reading the DTD. No validation will happen.
   */
  public T unmarshalWithoutDtd(InputStream inputStream)
      throws JAXBException, IOException, ParserConfigurationException,
      SAXException {
    return unmarshal(inputStream, /* validating */ false);
  }

  /**
   * Write the feed to the given stream.
   */
  public void marshal(T feed, OutputStream out)
      throws IOException, JAXBException {
    Marshaller marshaller = jaxbContext.createMarshaller();
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
        + "<!DOCTYPE " + rootElement
        + " PUBLIC \"" + PUBLIC_ID + "\" \"\">");
    marshaller.marshal(feed, out);
  }

  @SuppressWarnings("unchecked")
  private T unmarshal(InputStream inputStream, boolean validating)
      throws JAXBException, IOException, ParserConfigurationException,
      SAXException {
    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    saxParserFactory.setValidating(validating);
    saxParserFactory.setXIncludeAware(false);
    saxParserFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
    saxParserFactory.setFeature(
        "http://xml.org/sax/features/external-parameter-entities", false);
    saxParserFactory.setFeature(
        "http://xml.org/sax/features/external-general-entities", false);
    saxParserFactory.setFeature(
        "http://apache.org/xml/features/nonvalidating/load-external-dtd",
        validating);
    XMLReader xmlReader = saxParserFactory.newSAXParser().getXMLReader();
    xmlReader.setEntityResolver(entityResolver);
    xmlReader.setErrorHandler(errorHandler);
    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    UnmarshallerHandler unmarshallerHandler =
        unmarshaller.getUnmarshallerHandler();
    xmlReader.setContentHandler(unmarshallerHandler);
    xmlReader.parse(new InputSource(inputStream));
    return (T) unmarshallerHandler.getResult();
  }
}