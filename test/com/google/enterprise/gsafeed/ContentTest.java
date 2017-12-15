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

/**
 * Test Content.
 */
public class ContentTest {
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testContent() throws Exception {
    String expected =
        "<content encoding='base64binary'>This is the content.</content>";
    Content content = new Content()
        .setEncoding(Content.Encoding.BASE64_BINARY)
        .setvalue("This is the content.");
    Diff diff = DiffBuilder
        .compare(expected)
        .withTest(content)
        .checkForSimilar()
        .build();
    assertFalse(diff.toString(), diff.hasDifferences());
  }

  @Test
  public void testGetEncodingBase64Binary() throws Exception {
    Content content = unmarshal("<content encoding='base64binary'/>");
    assertEquals(Content.Encoding.BASE64_BINARY, content.getEncoding());
  }

  @Test
  public void testGetEncodingBase64Compressed() throws Exception {
    Content content = unmarshal("<content encoding='base64compressed'/>");
    assertEquals(Content.Encoding.BASE64_COMPRESSED, content.getEncoding());
  }

  @Test
  public void testGetEncodingNotSet() throws Exception {
    Content content = unmarshal("<content/>");
    assertEquals(null, content.getEncoding());
  }

  @Test
  public void testGetEncodingInvalid() throws Exception {
    Content content = unmarshal("<content encoding='foo'/>");
    assertEquals(null, content.getEncoding());
  }

  @Test
  public void testSetEncodingBase64Binary() {
    String expected = "<content encoding='base64binary'/>";
    Content content =
        new Content().setEncoding(Content.Encoding.BASE64_BINARY);
    assertNoDiffs(expected, content);
  }

  @Test
  public void testSetEncodingBase64Compressed() {
    String expected = "<content encoding='base64compressed'/>";
    Content content =
        new Content().setEncoding(Content.Encoding.BASE64_COMPRESSED);
    assertNoDiffs(expected, content);
  }

  @Test
  public void testSetEncodingNull() {
    String expected = "<content/>";
    Content content = new Content().setEncoding(null);
    assertNoDiffs(expected, content);
  }

  @Test
  public void testEncodingFromString() {
    for (Content.Encoding value : Content.Encoding.values()) {
      assertEquals(value, Content.Encoding.fromString(value.toString()));
    }
  }

  @Test
  public void testEncodingFromStringInvalid() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    Content.Encoding.fromString("foo");
  }

  @Test
  public void testEncodingFromStringNull() {
    assertEquals(null, Content.Encoding.fromString(null));
  }

  private void assertNoDiffs(String expected, Object actual) {
    Diff diff = DiffBuilder.compare(expected).withTest(actual)
        .checkForSimilar().build();
    assertFalse(diff.toString(), diff.hasDifferences());
  }

  private Content unmarshal(String value) throws Exception {
    return (Content) JaxbUtil.unmarshalGsafeedElement(value);
  }
}
