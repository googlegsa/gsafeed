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

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;

/**
 * Test Content.
 */
public class ContentTest {
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
}
