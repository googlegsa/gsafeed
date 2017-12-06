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

import org.junit.Test;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;

/**
 * Test Metadata.
 */
public class MetadataTest {
  @Test
  public void testMetadata() throws Exception {
    String expected =
        "<metadata overwrite-acls='true'></metadata>";
    Metadata metadata = new Metadata()
        .setOverwriteAcls(true);
    Diff diff = DiffBuilder
        .compare(expected)
        .withTest(metadata)
        .checkForSimilar()
        .build();
    assertFalse(diff.toString(), diff.hasDifferences());
  }

  @Test
  public void testGetOverwriteAclsTrue() throws Exception {
    Metadata metadata = unmarshal("<metadata overwrite-acls='true'/>");
    assertEquals(true, metadata.getOverwriteAcls());
  }

  @Test
  public void testGetOverwriteAclsFalse() throws Exception {
    Metadata metadata = unmarshal("<metadata overwrite-acls='false'/>");
    assertEquals(false, metadata.getOverwriteAcls());
  }

  @Test
  public void testGetOverwriteAclsUnset() throws Exception {
    Metadata metadata = unmarshal("<metadata/>");
    assertEquals(null, metadata.getOverwriteAcls());
  }

  @Test
  public void setOverwriteAclsTrue() {
    String expected = "<metadata overwrite-acls='true'/>";
    Metadata metadata = new Metadata().setOverwriteAcls(true);
    assertNoDiffs(expected, metadata);
  }

  @Test
  public void setOverwriteAclsFalse() {
    String expected = "<metadata overwrite-acls='false'/>";
    Metadata metadata = new Metadata().setOverwriteAcls(false);
    assertNoDiffs(expected, metadata);
  }

  @Test
  public void setOverwriteAclsNull() {
    String expected = "<metadata/>";
    Metadata metadata1 = new Metadata().setOverwriteAcls(null);
    assertNoDiffs(expected, metadata1);
  }

  private void assertNoDiffs(String expected, Object actual) {
    Diff diff = DiffBuilder.compare(expected).withTest(actual)
        .checkForSimilar().build();
    assertFalse(diff.toString(), diff.hasDifferences());
  }

  private Metadata unmarshal(String value) throws Exception {
    return (Metadata) JaxbUtil.unmarshalGsafeed(value);
  }
}
