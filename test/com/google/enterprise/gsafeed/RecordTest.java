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

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;

/**
 * Test Record.
 */
public class RecordTest {
  @ClassRule
  public static final ConsoleLogging logging = new ConsoleLogging(Level.OFF);

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testRecord() throws Exception {
    String expected =
        "<record url='http://example.com'"
        + " displayurl='http://example.com' action='add'"
        + " mimetype='text/plain'"
        + " last-modified='Tue, 06 Nov 2007 12:45:26 GMT'"
        + " lock='false' authmethod='none' feedrank='1'"
        + " pagerank='1' crawl-immediately='false'"
        + " crawl-once='false' scoring='content'></record>";
    Record record = new Record()
        .setUrl("http://example.com")
        .setDisplayurl("http://example.com")
        .setAction(Record.Action.ADD)
        .setMimetype("text/plain")
        .setLastModified(
            new SimpleDateFormat(DateAdapter.FORMAT, Locale.ENGLISH)
            .parse("Tue, 06 Nov 2007 12:45:26 GMT"))
        .setLock(false)
        .setAuthmethod(Record.AuthMethod.NONE)
        .setFeedrank("1")
        .setPagerank("1")
        .setCrawlImmediately(false)
        .setCrawlOnce(false)
        .setScoring(Record.Scoring.CONTENT);
    Diff diff = DiffBuilder
        .compare(expected)
        .withTest(record)
        .checkForSimilar()
        .build();
    assertFalse(diff.toString(), diff.hasDifferences());
  }

  @Test
  public void testGetActionAdd() throws Exception {
    Record record = unmarshal("<record action='add'/>");
    assertEquals(Record.Action.ADD, record.getAction());
  }

  @Test
  public void testGetActionDelete() throws Exception {
    Record record = unmarshal("<record action='delete'/>");
    assertEquals(Record.Action.DELETE, record.getAction());
  }

  @Test
  public void testGetActionNotSet() throws Exception {
    Record record = unmarshal("<record/>");
    assertEquals(null, record.getAction());
  }

  @Test
  public void testGetActionInvalid() throws Exception {
    Record record = unmarshal("<record action='foo'/>");
    assertEquals(null, record.getAction());
  }

  @Test
  public void testSetActionAdd() {
    String expected = "<record action='add'/>";
    Record record = new Record().setAction(Record.Action.ADD);
    assertNoDiffs(expected, record);
  }

  @Test
  public void testSetActionDelete() {
    String expected = "<record action='delete'/>";
    Record record = new Record().setAction(Record.Action.DELETE);
    assertNoDiffs(expected, record);
  }

  @Test
  public void testSetActionNull() {
    String expected = "<record/>";
    Record record = new Record().setAction(null);
    assertNoDiffs(expected, record);
  }

  public void testActionFromString() {
    for (Record.Action value : Record.Action.values()) {
      assertEquals(value, Record.Action.fromString(value.toString()));
    }
  }

  @Test
  public void testActionFromStringInvalid() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    Record.Action.fromString("foo");
  }

  @Test
  public void testActionFromStringNull() {
    assertEquals(null, Record.Action.fromString(null));
  }

  @Test
  public void testGetLockTrue() throws Exception {
    Record record = unmarshal("<record lock='true'/>");
    assertEquals(true, record.getLock());
  }

  @Test
  public void testGetLockFalse() throws Exception {
    Record record = unmarshal("<record lock='false'/>");
    assertEquals(false, record.getLock());
  }

  @Test
  public void testGetLockUnset() throws Exception {
    Record record = unmarshal("<record/>");
    assertEquals(null, record.getLock());
  }

  @Test
  public void setLockTrue() {
    String expected = "<record lock='true'/>";
    Record record = new Record().setLock(true);
    assertNoDiffs(expected, record);
  }

  @Test
  public void setLockFalse() {
    String expected = "<record lock='false'/>";
    Record record = new Record().setLock(false);
    assertNoDiffs(expected, record);
  }

  @Test
  public void setLockNull() {
    String expected = "<record/>";
    Record record = new Record().setLock(null);
    assertNoDiffs(expected, record);
  }

  @Test
  public void testGetAuthmethodNone() throws Exception {
    Record record = unmarshal("<record authmethod='none'/>");
    assertEquals(Record.AuthMethod.NONE, record.getAuthmethod());
  }

  @Test
  public void testGetAuthmethodHttpbasic() throws Exception {
    Record record = unmarshal("<record authmethod='httpbasic'/>");
    assertEquals(Record.AuthMethod.HTTPBASIC, record.getAuthmethod());
  }

  @Test
  public void testGetAuthmethodNtlm() throws Exception {
    Record record = unmarshal("<record authmethod='ntlm'/>");
    assertEquals(Record.AuthMethod.NTLM, record.getAuthmethod());
  }

  @Test
  public void testGetAuthmethodHttpsso() throws Exception {
    Record record = unmarshal("<record authmethod='httpsso'/>");
    assertEquals(Record.AuthMethod.HTTPSSO, record.getAuthmethod());
  }

  @Test
  public void testGetAuthmethodNegotiate() throws Exception {
    Record record = unmarshal("<record authmethod='negotiate'/>");
    assertEquals(Record.AuthMethod.NEGOTIATE, record.getAuthmethod());
  }

  @Test
  public void testGetAuthmethodNotSet() throws Exception {
    Record record = unmarshal("<record/>");
    assertEquals(null, record.getAuthmethod());
  }

  @Test
  public void testGetAuthmethodInvalid() throws Exception {
    Record record = unmarshal("<record authmethod='foo'/>");
    assertEquals(null, record.getAuthmethod());
  }

  @Test
  public void setAuthmethodNone() {
    String expected = "<record authmethod='none'/>";
    Record record = new Record().setAuthmethod(Record.AuthMethod.NONE);
    assertNoDiffs(expected, record);
  }

  @Test
  public void setAuthmethodHttpbasic() {
    String expected = "<record authmethod='httpbasic'/>";
    Record record = new Record().setAuthmethod(Record.AuthMethod.HTTPBASIC);
    assertNoDiffs(expected, record);
  }

  @Test
  public void setAuthmethodNtlm() {
    String expected = "<record authmethod='ntlm'/>";
    Record record = new Record().setAuthmethod(Record.AuthMethod.NTLM);
    assertNoDiffs(expected, record);
  }

  @Test
  public void setAuthmethodHttpsso() {
    String expected = "<record authmethod='httpsso'/>";
    Record record = new Record().setAuthmethod(Record.AuthMethod.HTTPSSO);
    assertNoDiffs(expected, record);
  }

  @Test
  public void setAuthmethodNegotiate() {
    String expected = "<record authmethod='negotiate'/>";
    Record record = new Record().setAuthmethod(Record.AuthMethod.NEGOTIATE);
    assertNoDiffs(expected, record);
  }

  @Test
  public void testSetAuthmethodNull() {
    String expected = "<record/>";
    Record record = new Record().setAuthmethod(null);
    assertNoDiffs(expected, record);
  }

  public void testAuthMethodFromString() {
    for (Record.AuthMethod value : Record.AuthMethod.values()) {
      assertEquals(value, Record.AuthMethod.fromString(value.toString()));
    }
  }

  @Test
  public void testAuthMethodFromStringInvalid() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    Record.AuthMethod.fromString("foo");
  }

  @Test
  public void testAuthMethodFromStringNull() {
    assertEquals(null, Record.AuthMethod.fromString(null));
  }

  @Test
  public void testGetCrawlImmediatelyTrue() throws Exception {
    Record record = unmarshal("<record crawl-immediately='true'/>");
    assertEquals(true, record.getCrawlImmediately());
  }

  @Test
  public void testGetCrawlImmediatelyFalse() throws Exception {
    Record record = unmarshal("<record crawl-immediately='false'/>");
    assertEquals(false, record.getCrawlImmediately());
  }

  @Test
  public void testGetCrawlImmediatelyUnset() throws Exception {
    Record record = unmarshal("<record/>");
    assertEquals(null, record.getCrawlImmediately());
  }

  @Test
  public void setCrawlImmediatelyTrue() {
    String expected = "<record crawl-immediately='true'/>";
    Record record = new Record().setCrawlImmediately(true);
    assertNoDiffs(expected, record);
  }

  @Test
  public void setCrawlImmediatelyFalse() {
    String expected = "<record crawl-immediately='false'/>";
    Record record = new Record().setCrawlImmediately(false);
    assertNoDiffs(expected, record);
  }

  @Test
  public void setCrawlImmediatelyNull() {
    String expected = "<record/>";
    Record record1 = new Record().setCrawlImmediately(null);
    assertNoDiffs(expected, record1);
  }

  @Test
  public void testGetCrawlOnceTrue() throws Exception {
    Record record = unmarshal("<record crawl-once='true'/>");
    assertEquals(true, record.getCrawlOnce());
  }

  @Test
  public void testGetCrawlOnceFalse() throws Exception {
    Record record = unmarshal("<record crawl-once='false'/>");
    assertEquals(false, record.getCrawlOnce());
  }

  @Test
  public void testGetCrawlOnceUnset() throws Exception {
    Record record = unmarshal("<record/>");
    assertEquals(null, record.getCrawlOnce());
  }

  @Test
  public void setCrawlOnceTrue() {
    String expected = "<record crawl-once='true'/>";
    Record record = new Record().setCrawlOnce(true);
    assertNoDiffs(expected, record);
  }

  @Test
  public void setCrawlOnceFalse() {
    String expected = "<record crawl-once='false'/>";
    Record record = new Record().setCrawlOnce(false);
    assertNoDiffs(expected, record);
  }

  @Test
  public void setCrawlOnceNull() {
    String expected = "<record/>";
    Record record = new Record().setCrawlOnce(null);
    assertNoDiffs(expected, record);
  }

  @Test
  public void testGetScoringContent() throws Exception {
    Record record = unmarshal("<record scoring='content'/>");
    assertEquals(Record.Scoring.CONTENT, record.getScoring());
  }

  @Test
  public void testGetScoringWeb() throws Exception {
    Record record = unmarshal("<record scoring='web'/>");
    assertEquals(Record.Scoring.WEB, record.getScoring());
  }

  @Test
  public void testGetScoringNotSet() throws Exception {
    Record record = unmarshal("<record/>");
    assertEquals(null, record.getScoring());
  }

  @Test
  public void testGetScoringInvalid() throws Exception {
    Record record = unmarshal("<record scoring='foo'/>");
    assertEquals(null, record.getScoring());
  }

  @Test
  public void testSetScoringContent() {
    String expected = "<record scoring='content'/>";
    Record record = new Record().setScoring(Record.Scoring.CONTENT);
    assertNoDiffs(expected, record);
  }

  @Test
  public void testSetScoringWeb() {
    String expected = "<record scoring='web'/>";
    Record record = new Record().setScoring(Record.Scoring.WEB);
    assertNoDiffs(expected, record);
  }

  @Test
  public void testSetScoringNull() {
    String expected = "<record/>";
    Record record = new Record().setScoring(null);
    assertNoDiffs(expected, record);
  }

  public void testScoringFromString() {
    for (Record.Scoring value : Record.Scoring.values()) {
      assertEquals(value, Record.Scoring.fromString(value.toString()));
    }
  }

  @Test
  public void testScoringFromStringInvalid() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    Record.Scoring.fromString("foo");
  }

  @Test
  public void testScoringFromStringNull() {
    assertEquals(null, Record.Scoring.fromString(null));
  }

  @Test
  public void testActionValidation() throws Exception {
    String xml =
        "<?xml version='1.0' encoding='utf-8'?>"
        + "<!DOCTYPE gsafeed PUBLIC '-//Google//DTD GSA Feeds//EN' ''>"
        + "<gsafeed>"
        + "  <header>"
        + "    <datasource>sample</datasource>"
        + "    <feedtype>full</feedtype>"
        + "  </header>"
        + "  <group>"
        + "   <record url='url' mimetype='mimetype' action='foo'/>"
        + "  </group>"
        + "</gsafeed>";

    thrown.expect(org.xml.sax.SAXParseException.class);
    thrown.expectMessage("Attribute \"action\" with value \"foo\" must"
        + " have a value from the list \"add delete \".");
    new GsafeedHelper().unmarshalWithDtd(xml);
  }

  @Test
  public void testLockValidation() throws Exception {
    String xml =
        "<?xml version='1.0' encoding='utf-8'?>"
        + "<!DOCTYPE gsafeed PUBLIC '-//Google//DTD GSA Feeds//EN' ''>"
        + "<gsafeed>"
        + "  <header>"
        + "    <datasource>sample</datasource>"
        + "    <feedtype>full</feedtype>"
        + "  </header>"
        + "  <group>"
        + "   <record url='url' mimetype='mimetype' lock='foo'/>"
        + "  </group>"
        + "</gsafeed>";

    thrown.expect(org.xml.sax.SAXParseException.class);
    thrown.expectMessage("Attribute \"lock\" with value \"foo\" must"
        + " have a value from the list \"true false \".");
    new GsafeedHelper().unmarshalWithDtd(xml);
  }

  @Test
  public void testLastModifiedMarshal() throws Exception {
    String expected =
        "<record last-modified='Tue, 06 Nov 2007 12:45:26 GMT'/>";
    Record record = new Record()
        .setLastModified(
            new SimpleDateFormat(DateAdapter.FORMAT, Locale.ENGLISH)
            .parse("Tue, 06 Nov 2007 12:45:26 GMT"));
    assertNoDiffs(expected, record);
  }

  @Test
  public void testLastModifiedUnmarshal() throws Exception {
    GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"),
      Locale.ENGLISH);
    cal.set(Calendar.YEAR, 2007);
    cal.set(Calendar.MONTH, Calendar.NOVEMBER);
    cal.set(Calendar.DATE, 6);
    cal.set(Calendar.HOUR_OF_DAY, 12);
    cal.set(Calendar.MINUTE, 45);
    cal.set(Calendar.SECOND, 26);
    cal.set(Calendar.MILLISECOND, 0);
    Record record = unmarshal(
        "<record last-modified='Tue, 06 Nov 2007 12:45:26 GMT'/>");
    assertEquals(cal.getTime(), record.getLastModified());
  }

  @Test
  public void testLastModifiedDifferentFormatWithoutValidation()
      throws Exception {
    Record record = unmarshal("<record last-modified='May 3, 2011'/>");
    assertEquals(null, record.getLastModified());
  }

  @Test
  public void testLastModifiedDifferentFormatWithValidation()
      throws Exception {
    String feed =
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
        + "      last-modified='Nov 6, 2007'>"
        + "      <content>This is hello01</content>"
        + "    </record>"
        + "  </group>"
        + "</gsafeed>";

    // The actual exception class is coming back as
    // com.sun.istack.internal.SAXParseException2.class, so check
    // using the cause for a more readable test.
    thrown.expectCause(isA(javax.xml.bind.UnmarshalException.class));
    thrown.expectMessage("Unparseable date: \"Nov 6, 2007\"");
    new GsafeedHelper().unmarshalWithDtd(feed);
  }

  // This is an even more straightforward date format test, using
  // the adapter directly.
  @Test
  public void testDateAdapterMarshal() throws Exception {
    GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"),
      Locale.ENGLISH);
    cal.set(Calendar.YEAR, 2007);
    cal.set(Calendar.MONTH, Calendar.NOVEMBER);
    cal.set(Calendar.DATE, 6);
    cal.set(Calendar.HOUR_OF_DAY, 12);
    cal.set(Calendar.MINUTE, 45);
    cal.set(Calendar.SECOND, 26);
    cal.set(Calendar.MILLISECOND, 0);

    DateAdapter adapter = new DateAdapter();
    assertEquals(
        "Tue, 06 Nov 2007 12:45:26 GMT", adapter.marshal(cal.getTime()));
  }

  private void assertNoDiffs(String expected, Object actual) {
    Diff diff = DiffBuilder.compare(expected).withTest(actual)
        .checkForSimilar().build();
    assertFalse(diff.toString(), diff.hasDifferences());
  }

  private Record unmarshal(String value) throws Exception {
    return (Record) JaxbUtil.unmarshalGsafeedElement(value);
  }
}
