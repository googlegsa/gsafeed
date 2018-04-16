// Copyright 2018 Google Inc. All Rights Reserved.
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

package com.google.enterprise.gsafeed.groups;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.google.enterprise.gsafeed.JaxbUtil;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;

/**
 * Test Membership.
 */
public class MembershipTest {
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testMembership() throws Exception {
    String expected =
        "<membership source='others'>"
        + "<principal scope='GROUP' namespace='Default'"
        + " case-sensitivity-type='EVERYTHING_CASE_SENSITIVE'"
        + " >abc/group1</principal>"
        + "<members/>"
        + "</membership>";

    Principal group = new Principal()
        .setScope(Principal.Scope.GROUP)
        .setNamespace("Default")
        .setCaseSensitivityType(
            Principal.CaseSensitivityType.EVERYTHING_CASE_SENSITIVE)
        .setvalue("abc/group1");
    Membership membership = new Membership()
        .setSource("others")
        .setPrincipal(group)
        .setMembers(new Members());

    assertNoDiffs(expected, membership);
  }

  @Test
  public void testGetSource() throws Exception {
    Membership membership = unmarshal("<membership source='default'/>");
    assertEquals("default", membership.getSource());
  }

  @Test
  public void testSetSource() {
    String expected = "<membership source='default'/>";
    Membership membership = new Membership().setSource("default");
    assertNoDiffs(expected, membership);
  }

  @Test
  public void testSetSourceNull() {
    String expected = "<membership/>";
    Membership membership = new Membership().setSource(null);
    assertNoDiffs(expected, membership);
  }

  private void assertNoDiffs(String expected, Object actual) {
    Diff diff = DiffBuilder.compare(expected).withTest(actual)
        .checkForSimilar().build();
    assertFalse(diff.toString(), diff.hasDifferences());
  }

  private Membership unmarshal(String value) throws Exception {
    return (Membership) JaxbUtil.unmarshalXmlgroupsElement(value);
  }
}
