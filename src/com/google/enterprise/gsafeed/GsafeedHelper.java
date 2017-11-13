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

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Helper for reading and creating GSA feed files.
 */
public class GsafeedHelper extends FeedHelper {

  public GsafeedHelper() throws JAXBException {
    super(Gsafeed.class, "gsafeed", "/gsafeed.dtd");
  }

  public void setErrorHandler(ErrorHandler errorHandler) {
    this.errorHandler = errorHandler;
  }

  /**
   * Use the DTD to check for errors in the feed being read.
   */
  public Gsafeed unmarshalWithDtd(URL url) throws JAXBException,
      IOException, ParserConfigurationException, SAXException {
    return unmarshalWithDtd(url.openStream());
  }

  /**
   * Use the DTD to check for errors in the feed being read.
   */
  public Gsafeed unmarshalWithDtd(InputStream inputStream)
      throws JAXBException, IOException, ParserConfigurationException,
      SAXException {
    return (Gsafeed) unmarshal(inputStream, Validation.TRUE);
  }

  /**
   * Avoid reading the DTD. No validation will happen.
   */
  public Gsafeed unmarshalWithoutDtd(URL url) throws JAXBException,
      IOException, ParserConfigurationException, SAXException {
    return unmarshalWithoutDtd(url.openStream());
  }

  /**
   * Avoid reading the DTD. No validation will happen.
   */
  public Gsafeed unmarshalWithoutDtd(InputStream inputStream)
      throws JAXBException, IOException, ParserConfigurationException,
      SAXException {
    return (Gsafeed) unmarshal(inputStream, Validation.FALSE);
  }

  /**
   * Write the feed to the given stream.
   */
  public void marshal(Gsafeed feed, OutputStream out)
      throws IOException, JAXBException {
    super.marshal(feed, out);
  }
}
