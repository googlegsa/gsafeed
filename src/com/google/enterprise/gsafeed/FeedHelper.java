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
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

/**
 * Helper for reading and creating GSA feed files.
 */
class FeedHelper {
  private static final String PUBLIC_ID = "-//Google//DTD GSA Feeds//EN";

  enum Validation { FALSE, TRUE }

  private String rootElementName;
  private JAXBContext jaxbContext;
  private EntityResolver entityResolver;
  ErrorHandler errorHandler;
  ValidationEventHandler validationEventHandler;

  FeedHelper(Class<?> rootElementClass, String rootElementName,
      final String dtdPath) throws JAXBException {
    this.rootElementName = rootElementName;
    this.jaxbContext =
        JAXBContext.newInstance(rootElementClass.getPackage().getName());
    this.entityResolver = new EntityResolver() {
        @Override
        public InputSource resolveEntity(String publicId,
            String systemId) throws SAXException, IOException {
          if (publicId != null && publicId.equals(PUBLIC_ID)) {
            return new InputSource(
                FeedHelper.class.getResource(dtdPath).openStream());
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
    this.validationEventHandler = new ValidationEventHandler() {
        public boolean handleEvent(ValidationEvent event) {
          boolean shouldContinue;
          String severity;
          switch (event.getSeverity()) {
            case ValidationEvent.WARNING:
              severity = "WARNING";
              shouldContinue = true;
              break;
            case ValidationEvent.ERROR:
              severity = "ERROR";
              shouldContinue = false;
              break;
            case ValidationEvent.FATAL_ERROR:
              severity = "FATAL_ERROR";
              shouldContinue = false;
              break;
            default:
              throw new IllegalArgumentException(
                  "Unknown severity " + event.getSeverity());
          }
          // TODO(aptls): find out if we want logging, System.out, ...
          System.out.println("Validation event: "
              + severity + " " + event.getMessage());
          return shouldContinue;
        }
      };
  }

  Object unmarshal(InputStream inputStream, Validation validation)
      throws JAXBException, IOException, ParserConfigurationException,
      SAXException {
    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    saxParserFactory.setValidating(validation == Validation.TRUE);
    saxParserFactory.setXIncludeAware(false);
    saxParserFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
    saxParserFactory.setFeature(
        "http://xml.org/sax/features/external-parameter-entities", false);
    saxParserFactory.setFeature(
        "http://xml.org/sax/features/external-general-entities", false);
    saxParserFactory.setFeature(
        "http://apache.org/xml/features/nonvalidating/load-external-dtd",
        validation == Validation.TRUE);
    XMLReader xmlReader = saxParserFactory.newSAXParser().getXMLReader();
    xmlReader.setEntityResolver(entityResolver);
    xmlReader.setErrorHandler(errorHandler);
    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    if (validation == Validation.TRUE && validationEventHandler != null) {
      unmarshaller.setEventHandler(validationEventHandler);
    }
    UnmarshallerHandler unmarshallerHandler =
        unmarshaller.getUnmarshallerHandler();
    xmlReader.setContentHandler(unmarshallerHandler);
    xmlReader.parse(new InputSource(inputStream));
    return unmarshallerHandler.getResult();
  }

  /**
   * Write the feed to the given stream.
   */
  void marshal(Object feed, OutputStream out)
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
        + "<!DOCTYPE " + rootElementName
        + " PUBLIC \"" + PUBLIC_ID + "\" \"\">");
    marshaller.marshal(feed, out);
  }
}
