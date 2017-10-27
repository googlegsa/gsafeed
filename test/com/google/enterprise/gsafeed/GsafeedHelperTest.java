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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.xml.sax.SAXParseException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Test GsafeedHelper.
 */
public class GsafeedHelperTest {
  @ClassRule
  public static TemporaryFolder testFolder = new TemporaryFolder();

  private static File privateFile;
  private static File evilDtd;
  private static String validFeed;
  private static String invalidFeed;
  private static String entityExpansionFeed;
  private static String externalEntityFeed;
  private static String externalParameterEntityFeed;

  @BeforeClass
  public static void setUpClass() throws IOException {
    privateFile = testFolder.newFile("gsafeedhelpertest-private-file.txt");
    writeFile(privateFile, "This file is private!!!");
    evilDtd = testFolder.newFile("gsafeedhelpertest-evil-dtd.dtd");
    writeFile(evilDtd,
        "<!ENTITY % file SYSTEM '" + privateFile.toURI() + "'>\n"
        + "<!ENTITY % start '<![CDATA['>\n"
        + "<!ENTITY % end ']]>'>\n"
        + "<!ENTITY % all \"<!ENTITY fileContents '%start;%file;%end;'>\">\n");

    validFeed =
        "<?xml version='1.0' encoding='utf-8'?>"
        + "<!DOCTYPE gsafeed PUBLIC '-//Google//DTD GSA Feeds//EN' ''>"
        + "<gsafeed>"
        + "  <header>"
        + "    <datasource>sample</datasource>"
        + "    <feedtype>full</feedtype>"
        + "  </header>"
        + "  <group>"
        + "    <record url='http://www.example.com/hello01'"
        + "      mimetype='text/plain'"
        + "      last-modified='Tue, 6 Nov 2007 12:45:26 GMT'>"
        + "      <content>This is hello01</content>"
        + "    </record>"
        + "  </group>"
        + "</gsafeed>";

    invalidFeed =
        "<?xml version='1.0' encoding='utf-8'?>"
        + "<!DOCTYPE gsafeed PUBLIC '-//Google//DTD GSA Feeds//EN' ''>"
        + "<gsafeed>"
        + "  <invalid-element/>"                                      // <----
        + "  <header>"
        + "    <datasource>sample</datasource>"
        + "    <feedtype>full</feedtype>"
        + "  </header>"
        + "  <group>"
        + "    <record url='http://www.example.com/hello01'"
        + "      mimetype='text/plain'"
        + "      last-modified='Tue, 6 Nov 2007 12:45:26 GMT'>"
        + "      <content>This is hello01</content>"
        + "    </record>"
        + "  </group>"
        + "</gsafeed>";

    // "Billion laughs" attack
    entityExpansionFeed =
        "<?xml version='1.0' encoding='utf-8'?>"
        + "<!DOCTYPE gsafeed PUBLIC '-//Google//DTD GSA Feeds//EN' '' [ "
        + "<!ENTITY a '1234567890' > "                                // <----
        + "<!ENTITY b '&a;&a;&a;&a;&a;&a;&a;&a;&a;&a;' > "
        + "<!ENTITY c '&b;&b;&b;&b;&b;&b;&b;&b;&b;&b;' > "
        + "<!ENTITY d '&c;&c;&c;&c;&c;&c;&c;&c;&c;&c;' > "
        + "<!ENTITY e '&d;&d;&d;&d;&d;&d;&d;&d;&d;&d;' > "
        + "<!ENTITY f '&e;&e;&e;&e;&e;&e;&e;&e;&e;&e;' > "
        + "<!ENTITY g '&f;&f;&f;&f;&f;&f;&f;&f;&f;&f;' > "
        + "<!ENTITY h '&g;&g;&g;&g;&g;&g;&g;&g;&g;&g;' > "
        + "<!ENTITY i '&h;&h;&h;&h;&h;&h;&h;&h;&h;&h;' > "
        + "<!ENTITY j '&i;&i;&i;&i;&i;&i;&i;&i;&i;&i;' > "
        + "]>"
        + "<gsafeed>"
        + "  <header>"
        + "    <datasource>sample</datasource>"
        + "    <feedtype>full</feedtype>"
        + "  </header>"
        + "  <group>"
        + "    <record url='http://www.example.com/hello01' "
        + "      mimetype='text/plain'"
        + "      last-modified='Tue, 6 Nov 2007 12:45:26 GMT'>"
        + "      <content> &j; </content>"                            // <----
        + "    </record>"
        + "  </group>"
        + "</gsafeed>";

    externalEntityFeed =
        "<?xml version='1.0' encoding='utf-8'?>"
        + "<!DOCTYPE gsafeed PUBLIC '-//Google//DTD GSA Feeds//EN' '' [ "
        + "<!ENTITY xxe SYSTEM '" + privateFile.toURI() + "'>"       // <----
        + "]>"
        + "<gsafeed>"
        + "  <header>"
        + "    <datasource>sample</datasource>"
        + "    <feedtype>full</feedtype>"
        + "  </header>"
        + "  <group>"
        + "    <record url='http://www.example.com/hello01' "
        + "      mimetype='text/plain'"
        + "      last-modified='Tue, 6 Nov 2007 12:45:26 GMT'>"
        + "      <content> &xxe; </content>"                          // <----
        + "    </record>"
        + "  </group>"
        + "</gsafeed>";

    externalParameterEntityFeed =
        "<?xml version='1.0' encoding='utf-8'?>"
        + "<!DOCTYPE gsafeed PUBLIC '-//Google//DTD GSA Feeds//EN' '' [ "
        + "<!ENTITY % dtd SYSTEM "
        + "'" + evilDtd.toURI() + "'>"                               // <----
        + "%dtd;"
        + "%all;"
        + "]>"
        + "<gsafeed>"
        + "  <header>"
        + "    <datasource>sample</datasource>"
        + "    <feedtype>full</feedtype>"
        + "  </header>"
        + "  <group>"
        + "    <record url='http://www.example.com/hello01'"
        + "      mimetype='text/plain'"
        + "      last-modified='Tue, 6 Nov 2007 12:45:26 GMT'>"
        + "      <content> &fileContents; </content>"                 // <----
        + "    </record>"
        + "  </group>"
        + "</gsafeed>";
  }

  private static void writeFile(File file, String content) throws IOException {
    FileWriter writer = new FileWriter(file);
    try {
      writer.write(content);
    } finally {
      writer.close();
    }
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testWithDtd() throws Exception {
    Gsafeed feed = GsafeedHelper.unmarshalWithDtd(
        new ByteArrayInputStream(validFeed.getBytes("UTF-8")));
    assertEquals("sample", feed.getHeader().getDatasource());
  }

  @Test
  public void testWithoutDtd() throws Exception {
    Gsafeed feed = GsafeedHelper.unmarshalWithoutDtd(
        new ByteArrayInputStream(validFeed.getBytes("UTF-8")));
    assertEquals("sample", feed.getHeader().getDatasource());
  }

  @Test
  public void testWithDtdInvalidDoc() throws Exception {
    thrown.expect(SAXParseException.class);
    thrown.expectMessage(
        "Element type \"invalid-element\" must be declared.");
    Gsafeed feed = GsafeedHelper.unmarshalWithDtd(
        new ByteArrayInputStream(invalidFeed.getBytes("UTF-8")));
  }

  @Test
  public void testWithoutDtdInvalidDoc() throws Exception {
    Gsafeed feed = GsafeedHelper.unmarshalWithoutDtd(
        new ByteArrayInputStream(invalidFeed.getBytes("UTF-8")));
    assertEquals("sample", feed.getHeader().getDatasource());
  }

  @Test
  public void testWithDtdExternalEntity() throws Exception {
    Gsafeed feed = GsafeedHelper.unmarshalWithDtd(
        new ByteArrayInputStream(externalEntityFeed.getBytes("UTF-8")));
    assertEquals("", getFirstRecordContent(feed));
  }

  @Test
  public void testWithoutDtdExternalEntity() throws Exception {
    Gsafeed feed = GsafeedHelper.unmarshalWithoutDtd(
        new ByteArrayInputStream(externalEntityFeed.getBytes("UTF-8")));
    assertEquals("", getFirstRecordContent(feed));
  }

  @Test
  public void testWithDtdExternalParameterEntity() throws Exception {
    thrown.expect(SAXParseException.class);
    thrown.expectMessage("The entity \"all\" was referenced, but not declared.");
    Gsafeed feed = GsafeedHelper.unmarshalWithDtd(
        new ByteArrayInputStream(
            externalParameterEntityFeed.getBytes("UTF-8")));
    assertEquals("", getFirstRecordContent(feed));
  }

  @Test
  public void testWithoutDtdExternalParameterEntity() throws Exception {
    Gsafeed feed = GsafeedHelper.unmarshalWithoutDtd(
        new ByteArrayInputStream(
            externalParameterEntityFeed.getBytes("UTF-8")));
    assertEquals("", getFirstRecordContent(feed));
  }

  @Test
  public void testWithDtdEntityExpansion() throws Exception {
    thrown.expect(SAXParseException.class);
    thrown.expectMessage("JAXP00010001: The parser has encountered more "
        + "than \"64000\" entity expansions in this document; "
        + "this is the limit imposed by the JDK.");
    Gsafeed feed = GsafeedHelper.unmarshalWithDtd(
        new ByteArrayInputStream((entityExpansionFeed.getBytes("UTF-8"))));
  }

  @Test
  public void testWithoutDtdEntityExpansion() throws Exception {
    thrown.expect(SAXParseException.class);
    thrown.expectMessage("JAXP00010001: The parser has encountered more "
        + "than \"64000\" entity expansions in this document; "
        + "this is the limit imposed by the JDK.");
    Gsafeed feed = GsafeedHelper.unmarshalWithoutDtd(
        new ByteArrayInputStream(entityExpansionFeed.getBytes("UTF-8")));
  }

  private String getFirstRecordContent(Gsafeed feed) {
    List<Group> groups = feed.getGroup();
    assertTrue("Missing groups", groups.size() != 0);
    List<Object> records = groups.get(0).getAclOrRecord();
    assertTrue("Missing records", records.size() != 0);
    Record record = (Record) records.get(0);
    List<Content> content = record.getContent();
    assertTrue("Missing content", content.size() != 0);
    return content.get(0).getvalue().trim();
  }
}
