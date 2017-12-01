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
import static org.junit.Assert.assertFalse;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;
import java.nio.charset.Charset;

/**
 * Test Record.
 */
public class RecordTest {
  private static final Charset UTF_8 = Charset.forName("UTF-8");

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testRecord() throws Exception {
    String expected =
        "<record url='http://example.com'"
        + " displayurl='http://example.com' action='add'"
        + " mimetype='text/plain'"
        + " last-modified='Tue, 6 Nov 2007 12:45:26 GMT'"
        + " lock='false' authmethod='none' feedrank='1'"
        + " pagerank='1' crawl-immediately='false'"
        + " crawl-once='false' scoring='content'></record>";
    Record record = new Record()
        .setUrl("http://example.com")
        .setDisplayurl("http://example.com")
        .setAction("add")
        .setMimetype("text/plain")
        .setLastModified("Tue, 6 Nov 2007 12:45:26 GMT")
        .setLock("false")
        .setAuthmethod("none")
        .setFeedrank("1")
        .setPagerank("1")
        .setCrawlImmediately("false")
        .setCrawlOnce("false")
        .setScoring("content");
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
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    Record record = unmarshal("<record action='foo'/>");
    record.getAction();
  }

  @Test
  public void testSetActionAdd() {
    String expected = "<record action='add'/>";
    Record record1 = new Record().setAction("add");
    Record record2 = new Record().setAction(Record.Action.ADD);
    assertNoDiffs(expected, record1);
    assertNoDiffs(expected, record2);
  }

  @Test
  public void testSetActionDelete() {
    String expected = "<record action='delete'/>";
    Record record1 = new Record().setAction("delete");
    Record record2 = new Record().setAction(Record.Action.DELETE);
    assertNoDiffs(expected, record1);
    assertNoDiffs(expected, record2);
  }

  @Test
  public void testSetActionNull() {
    String expected = "<record/>";
    Record record1 = new Record().setAction((String) null);
    Record record2 = new Record().setAction((Record.Action) null);
    assertNoDiffs(expected, record1);
    assertNoDiffs(expected, record2);
  }

  @Test
  public void testSetActionInvalid() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    new Record().setAction("foo");
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
    assertEquals(false, record.getLock());
  }

  @Test
  public void setLockTrue() {
    String expected = "<record lock='true'/>";
    Record record1 = new Record().setLock("true");
    Record record2 = new Record().setLock(true);
    assertNoDiffs(expected, record1);
    assertNoDiffs(expected, record2);
  }

  @Test
  public void setLockFalse() {
    String expected = "<record lock='false'/>";
    Record record1 = new Record().setLock("false");
    Record record2 = new Record().setLock(false);
    assertNoDiffs(expected, record1);
    assertNoDiffs(expected, record2);
  }

  @Test
  public void setLockNull() {
    String expected = "<record lock='false'/>";
    Record record1 = new Record().setLock(null);
    assertNoDiffs(expected, record1);
  }

  @Test
  public void setLockInvalid() {
    String expected = "<record lock='false'/>";
    Record record1 = new Record().setLock("foo");
    assertNoDiffs(expected, record1);
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
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    Record record = unmarshal("<record authmethod='foo'/>");
    record.getAuthmethod();
  }

  @Test
  public void setAuthmethodNone() {
    String expected = "<record authmethod='none'/>";
    Record record1 = new Record().setAuthmethod("none");
    Record record2 = new Record().setAuthmethod(Record.AuthMethod.NONE);
    assertNoDiffs(expected, record1);
    assertNoDiffs(expected, record2);
  }

  @Test
  public void setAuthmethodHttpbasic() {
    String expected = "<record authmethod='httpbasic'/>";
    Record record1 = new Record().setAuthmethod("httpbasic");
    Record record2 = new Record().setAuthmethod(Record.AuthMethod.HTTPBASIC);
    assertNoDiffs(expected, record1);
    assertNoDiffs(expected, record2);
  }

  @Test
  public void setAuthmethodNtlm() {
    String expected = "<record authmethod='ntlm'/>";
    Record record1 = new Record().setAuthmethod("ntlm");
    Record record2 = new Record().setAuthmethod(Record.AuthMethod.NTLM);
    assertNoDiffs(expected, record1);
    assertNoDiffs(expected, record2);
  }

  @Test
  public void setAuthmethodHttpsso() {
    String expected = "<record authmethod='httpsso'/>";
    Record record1 = new Record().setAuthmethod("httpsso");
    Record record2 = new Record().setAuthmethod(Record.AuthMethod.HTTPSSO);
    assertNoDiffs(expected, record1);
    assertNoDiffs(expected, record2);
  }

  @Test
  public void setAuthmethodNegotiate() {
    String expected = "<record authmethod='negotiate'/>";
    Record record1 = new Record().setAuthmethod("negotiate");
    Record record2 = new Record().setAuthmethod(Record.AuthMethod.NEGOTIATE);
    assertNoDiffs(expected, record1);
    assertNoDiffs(expected, record2);
  }

  @Test
  public void testSetAuthmethodNull() {
    String expected = "<record/>";
    Record record1 = new Record().setAuthmethod((String) null);
    Record record2 = new Record().setAuthmethod((Record.AuthMethod) null);
    assertNoDiffs(expected, record1);
    assertNoDiffs(expected, record2);
  }

  @Test
  public void testSetAuthmethodInvalid() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    new Record().setAuthmethod("foo");
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
    assertEquals(false, record.getCrawlImmediately());
  }

  @Test
  public void setCrawlImmediatelyTrue() {
    String expected = "<record crawl-immediately='true'/>";
    Record record1 = new Record().setCrawlImmediately("true");
    Record record2 = new Record().setCrawlImmediately(true);
    assertNoDiffs(expected, record1);
    assertNoDiffs(expected, record2);
  }

  @Test
  public void setCrawlImmediatelyFalse() {
    String expected = "<record crawl-immediately='false'/>";
    Record record1 = new Record().setCrawlImmediately("false");
    Record record2 = new Record().setCrawlImmediately(false);
    assertNoDiffs(expected, record1);
    assertNoDiffs(expected, record2);
  }

  @Test
  public void setCrawlImmediatelyNull() {
    String expected = "<record crawl-immediately='false'/>";
    Record record1 = new Record().setCrawlImmediately(null);
    assertNoDiffs(expected, record1);
  }

  @Test
  public void setCrawlImmediatelyInvalid() {
    String expected = "<record crawl-immediately='false'/>";
    Record record1 = new Record().setCrawlImmediately("foo");
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
    assertEquals(false, record.getCrawlOnce());
  }

  @Test
  public void setCrawlOnceTrue() {
    String expected = "<record crawl-once='true'/>";
    Record record1 = new Record().setCrawlOnce("true");
    Record record2 = new Record().setCrawlOnce(true);
    assertNoDiffs(expected, record1);
    assertNoDiffs(expected, record2);
  }

  @Test
  public void setCrawlOnceFalse() {
    String expected = "<record crawl-once='false'/>";
    Record record1 = new Record().setCrawlOnce("false");
    Record record2 = new Record().setCrawlOnce(false);
    assertNoDiffs(expected, record1);
    assertNoDiffs(expected, record2);
  }

  @Test
  public void setCrawlOnceNull() {
    String expected = "<record crawl-once='false'/>";
    Record record1 = new Record().setCrawlOnce(null);
    assertNoDiffs(expected, record1);
  }

  @Test
  public void setCrawlOnceInvalid() {
    String expected = "<record crawl-once='false'/>";
    Record record1 = new Record().setCrawlOnce("foo");
    assertNoDiffs(expected, record1);
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
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    Record record = unmarshal("<record scoring='foo'/>");
    record.getScoring();
  }

  @Test
  public void testSetScoringContent() {
    String expected = "<record scoring='content'/>";
    Record record1 = new Record().setScoring("content");
    Record record2 = new Record().setScoring(Record.Scoring.CONTENT);
    assertNoDiffs(expected, record1);
    assertNoDiffs(expected, record2);
  }

  @Test
  public void testSetScoringWeb() {
    String expected = "<record scoring='web'/>";
    Record record1 = new Record().setScoring("web");
    Record record2 = new Record().setScoring(Record.Scoring.WEB);
    assertNoDiffs(expected, record1);
    assertNoDiffs(expected, record2);
  }

  @Test
  public void testSetScoringNull() {
    String expected = "<record/>";
    Record record1 = new Record().setScoring((String) null);
    Record record2 = new Record().setScoring((Record.Scoring) null);
    assertNoDiffs(expected, record1);
    assertNoDiffs(expected, record2);
  }

  @Test
  public void testSetScoringInvalid() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    new Record().setScoring("foo");
  }

  private void assertNoDiffs(String expected, Object actual) {
    Diff diff = DiffBuilder.compare(expected).withTest(actual)
        .checkForSimilar().build();
    assertFalse(diff.toString(), diff.hasDifferences());
  }

  private Record unmarshal(String value) throws Exception {
    return (Record) JaxbUtil.unmarshalGsafeed(value);
  }
}
