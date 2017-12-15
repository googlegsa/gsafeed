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
 * Test Group.
 */
public class GroupTest {
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testGroup() throws Exception {
    String expected =
        "<group action='add' feedrank='1' pagerank='1'></group>";
    Group group = new Group()
        .setAction(Group.Action.ADD)
        .setFeedrank("1").setPagerank("1");
    Diff diff = DiffBuilder
        .compare(expected)
        .withTest(group)
        .checkForSimilar()
        .build();
    assertFalse(diff.toString(), diff.hasDifferences());
  }

  @Test
  public void testGetActionAdd() throws Exception {
    Group group = unmarshal("<group action='add'/>");
    assertEquals(Group.Action.ADD, group.getAction());
  }

  @Test
  public void testGetActionDelete() throws Exception {
    Group group = unmarshal("<group action='delete'/>");
    assertEquals(Group.Action.DELETE, group.getAction());
  }

  @Test
  public void testGetActionNotSet() throws Exception {
    Group group = unmarshal("<group/>");
    assertEquals(null, group.getAction());
  }

  @Test
  public void testGetActionInvalid() throws Exception {
    Group group = unmarshal("<group action='foo'/>");
    assertEquals(null, group.getAction());
  }

  @Test
  public void testSetActionAdd() {
    String expected = "<group action='add'/>";
    Group group = new Group().setAction(Group.Action.ADD);
    assertNoDiffs(expected, group);
  }

  @Test
  public void testSetActionDelete() {
    String expected = "<group action='delete'/>";
    Group group = new Group().setAction(Group.Action.DELETE);
    assertNoDiffs(expected, group);
  }

  @Test
  public void testSetActionNull() {
    String expected = "<group/>";
    Group group = new Group().setAction(null);
    assertNoDiffs(expected, group);
  }

  @Test
  public void testActionFromString() {
    for (Group.Action value : Group.Action.values()) {
      assertEquals(value, Group.Action.fromString(value.toString()));
    }
  }

  @Test
  public void testActionFromStringInvalid() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    Group.Action.fromString("foo");
  }

  @Test
  public void testActionFromStringNull() {
    assertEquals(null, Group.Action.fromString(null));
  }

  private void assertNoDiffs(String expected, Object actual) {
    Diff diff = DiffBuilder.compare(expected).withTest(actual)
        .checkForSimilar().build();
    assertFalse(diff.toString(), diff.hasDifferences());
  }

  private Group unmarshal(String value) throws Exception {
    return (Group) JaxbUtil.unmarshalGsafeedElement(value);
  }
}
