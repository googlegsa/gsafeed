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
 * Test Meta.
 */
public class MetaTest {
  private static final Charset UTF_8 = Charset.forName("UTF-8");

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testMeta() throws Exception {
    String expected =
        "<meta encoding='base64binary'"
        + " name='meta-name'"
        + " content='meta-content'></meta>";
    Meta meta = new Meta()
        .setEncoding("base64binary")
        .setName("meta-name")
        .setContent("meta-content");
    Diff diff = DiffBuilder
        .compare(expected)
        .withTest(meta)
        .checkForSimilar()
        .build();
    assertFalse(diff.toString(), diff.hasDifferences());
  }

  @Test
  public void testGetEncodingBase64Binary() throws Exception {
    Meta meta = unmarshal("<meta encoding='base64binary'/>");
    assertEquals(Meta.Encoding.BASE_64_BINARY, meta.getEncoding());
  }

  @Test
  public void testGetEncodingNotSet() throws Exception {
    Meta meta = unmarshal("<meta/>");
    assertEquals(null, meta.getEncoding());
  }

  @Test
  public void testGetEncodingInvalid() throws Exception {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    Meta meta = unmarshal("<meta encoding='foo'/>");
    meta.getEncoding();
  }

  @Test
  public void testSetEncodingBase64Binary() {
    String expected = "<meta encoding='base64binary'/>";
    Meta meta1 = new Meta().setEncoding("base64binary");
    Meta meta2 = new Meta().setEncoding(Meta.Encoding.BASE_64_BINARY);
    assertNoDiffs(expected, meta1);
    assertNoDiffs(expected, meta2);
  }

  @Test
  public void testSetEncodingNull() {
    String expected = "<meta/>";
    Meta meta1 = new Meta().setEncoding((String) null);
    Meta meta2 = new Meta().setEncoding((Meta.Encoding) null);
    assertNoDiffs(expected, meta1);
    assertNoDiffs(expected, meta2);
  }

  @Test
  public void testSetEncodingInvalid() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    new Meta().setEncoding("foo");
  }

  private void assertNoDiffs(String expected, Object actual) {
    Diff diff = DiffBuilder.compare(expected).withTest(actual)
        .checkForSimilar().build();
    assertFalse(diff.toString(), diff.hasDifferences());
  }

  private Meta unmarshal(String value) throws Exception {
    return (Meta) JaxbUtil.unmarshalGsafeed(value);
  }
}
