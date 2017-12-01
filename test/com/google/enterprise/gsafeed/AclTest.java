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
 * Test Acl.
 */
public class AclTest {
  private static final Charset UTF_8 = Charset.forName("UTF-8");

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testAcl() throws Exception {
    String expected =
        "<acl url='http://example.com'"
        + " inheritance-type='leaf-node'"
        + " inherit-from='12345'></acl>";
    Acl acl = new Acl()
        .setUrl("http://example.com")
        .setInheritanceType(Acl.InheritanceType.LEAF_NODE)
        .setInheritFrom("12345");
    Diff diff = DiffBuilder
        .compare(expected)
        .withTest(acl)
        .checkForSimilar()
        .build();
    assertFalse(diff.toString(), diff.hasDifferences());
  }

  @Test
  public void testGetInheritanceTypeChildOverrides() throws Exception {
    Acl acl = unmarshal("<acl inheritance-type='child-overrides'/>");
    assertEquals(Acl.InheritanceType.CHILD_OVERRIDES, acl.getInheritanceType());
  }

  @Test
  public void testGetInheritanceTypeParentOverrides() throws Exception {
    Acl acl = unmarshal("<acl inheritance-type='parent-overrides'/>");
    assertEquals(Acl.InheritanceType.PARENT_OVERRIDES, acl.getInheritanceType());
  }

  @Test
  public void testGetInheritanceTypeAndBothPermit() throws Exception {
    Acl acl = unmarshal("<acl inheritance-type='and-both-permit'/>");
    assertEquals(Acl.InheritanceType.AND_BOTH_PERMIT, acl.getInheritanceType());
  }

  @Test
  public void testGetInheritanceTypeLeafNode() throws Exception {
    Acl acl = unmarshal("<acl inheritance-type='leaf-node'/>");
    assertEquals(Acl.InheritanceType.LEAF_NODE, acl.getInheritanceType());
  }

  @Test
  public void testGetInheritanceTypeNotSet() throws Exception {
    Acl acl = unmarshal("<acl/>");
    assertEquals(null, acl.getInheritanceType());
  }

  @Test
  public void testGetInheritanceTypeInvalid() throws Exception {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    Acl acl = unmarshal("<acl inheritance-type='foo'/>");
    acl.getInheritanceType();
  }

  @Test
  public void testSetInheritanceTypeChildOverrides() {
    String expected = "<acl inheritance-type='child-overrides'/>";
    Acl acl1 = new Acl().setInheritanceType("child-overrides");
    Acl acl2 =
        new Acl().setInheritanceType(Acl.InheritanceType.CHILD_OVERRIDES);
    assertNoDiffs(expected, acl1);
    assertNoDiffs(expected, acl2);
  }

  @Test
  public void testSetInheritanceTypeParentOverrides() {
    String expected = "<acl inheritance-type='parent-overrides'/>";
    Acl acl1 = new Acl().setInheritanceType("parent-overrides");
    Acl acl2 =
        new Acl().setInheritanceType(Acl.InheritanceType.PARENT_OVERRIDES);
    assertNoDiffs(expected, acl1);
    assertNoDiffs(expected, acl2);
  }

  @Test
  public void testSetInheritanceTypeAndBothPermit() {
    String expected = "<acl inheritance-type='and-both-permit'/>";
    Acl acl1 = new Acl().setInheritanceType("and-both-permit");
    Acl acl2 =
        new Acl().setInheritanceType(Acl.InheritanceType.AND_BOTH_PERMIT);
    assertNoDiffs(expected, acl1);
    assertNoDiffs(expected, acl2);
  }

  @Test
  public void testSetInheritanceTypeLeafNode() {
    String expected = "<acl inheritance-type='leaf-node'/>";
    Acl acl1 = new Acl().setInheritanceType("leaf-node");
    Acl acl2 = new Acl().setInheritanceType(Acl.InheritanceType.LEAF_NODE);
    assertNoDiffs(expected, acl1);
    assertNoDiffs(expected, acl2);
  }

  @Test
  public void testSetInheritanceTypeNull() {
    String expected = "<acl/>";
    Acl acl1 = new Acl().setInheritanceType((String) null);
    Acl acl2 = new Acl().setInheritanceType((Acl.InheritanceType) null);
    assertNoDiffs(expected, acl1);
    assertNoDiffs(expected, acl2);
  }

  @Test
  public void testSetInheritanceTypeInvalid() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    new Acl().setInheritanceType("foo");
  }

  private void assertNoDiffs(String expected, Object actual) {
    Diff diff = DiffBuilder.compare(expected).withTest(actual)
        .checkForSimilar().build();
    assertFalse(diff.toString(), diff.hasDifferences());
  }

  private Acl unmarshal(String value) throws Exception {
    return (Acl) JaxbUtil.unmarshalGsafeed(value);
  }
}
