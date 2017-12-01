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
 * Test Content.
 */
public class ContentTest {
  private static final Charset UTF_8 = Charset.forName("UTF-8");

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testContent() throws Exception {
    String expected =
        "<content encoding='base64binary'>This is the content.</content>";
    Content content = new Content()
        .setEncoding("base64binary")
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
    assertEquals(Content.Encoding.BASE_64_BINARY, content.getEncoding());
  }

  @Test
  public void testGetEncodingBase64Compressed() throws Exception {
    Content content = unmarshal("<content encoding='base64compressed'/>");
    assertEquals(Content.Encoding.BASE_64_COMPRESSED, content.getEncoding());
  }

  @Test
  public void testGetEncodingNotSet() throws Exception {
    Content content = unmarshal("<content>This is the content.</content>");
    assertEquals(null, content.getEncoding());
  }

  @Test
  public void testGetEncodingInvalid() throws Exception {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    Content content = unmarshal("<content encoding='foo'/>");
    content.getEncoding();
  }

  @Test
  public void testSetEncodingBase64Binary() {
    String expected = "<content encoding='base64binary'/>";
    Content content1 = new Content().setEncoding("base64binary");
    Content content2 =
        new Content().setEncoding(Content.Encoding.BASE_64_BINARY);
    assertNoDiffs(expected, content1);
    assertNoDiffs(expected, content2);
  }

  @Test
  public void testSetEncodingBase64Compressed() {
    String expected = "<content encoding='base64compressed'/>";
    Content content1 = new Content().setEncoding("base64compressed");
    Content content2 =
        new Content().setEncoding(Content.Encoding.BASE_64_COMPRESSED);
    assertNoDiffs(expected, content1);
    assertNoDiffs(expected, content2);
  }

  @Test
  public void testSetEncodingNull() {
    String expected = "<content/>";
    Content content1 = new Content().setEncoding((String) null);
    Content content2 =
        new Content().setEncoding((Content.Encoding) null);
    assertNoDiffs(expected, content1);
    assertNoDiffs(expected, content2);
  }

  @Test
  public void testSetEncodingInvalid() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    new Content().setEncoding("foo");
  }

  private void assertNoDiffs(String expected, Object actual) {
    Diff diff = DiffBuilder.compare(expected).withTest(actual)
        .checkForSimilar().build();
    assertFalse(diff.toString(), diff.hasDifferences());
  }

  private Content unmarshal(String value) throws Exception {
    return (Content) JaxbUtil.unmarshalGsafeed(value);
  }
}
