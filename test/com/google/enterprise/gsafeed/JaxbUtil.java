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

import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Helper to expose FeedHelper's unmarshal method for use in the
 * groups package. Using the unmarshal method directly, with no
 * validation, lets us unmarshal individual XML elements for
 * testing rather than needing to construct entire
 * gsafeed/xmlgroups documents.
 */
public class JaxbUtil {
  private static final Charset UTF_8 = Charset.forName("UTF-8");

  public static Object unmarshalGsafeedElement(String xml)
      throws JAXBException, IOException, ParserConfigurationException,
      SAXException {
    return new GsafeedHelper().unmarshal(
        new ByteArrayInputStream(xml.getBytes(UTF_8)),
        FeedHelper.Validation.FALSE);
  }

  public static Object unmarshalXmlgroupsElement(String xml)
      throws JAXBException, IOException, ParserConfigurationException,
      SAXException {
    return new XmlgroupsHelper().unmarshal(
        new ByteArrayInputStream(xml.getBytes(UTF_8)),
        FeedHelper.Validation.FALSE);
  }
}
