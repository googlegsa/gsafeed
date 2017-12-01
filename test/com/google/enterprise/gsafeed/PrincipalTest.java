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
 * Test Principal.
 */
public class PrincipalTest {
  private static final Charset UTF_8 = Charset.forName("UTF-8");

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testPrincipal() throws Exception {
    String expected =
        "<principal scope='user'"
        + " access='permit' namespace='Default'"
        + " case-sensitivity-type='everything-case-sensitive'"
        + " principal-type='unqualified'>"
        + "user@example.com</principal>";
    Principal principal = new Principal()
        .setScope("user")
        .setAccess("permit")
        .setNamespace("Default")
        .setCaseSensitivityType("everything-case-sensitive")
        .setPrincipalType("unqualified")
        .setvalue("user@example.com");
    Diff diff = DiffBuilder
        .compare(expected)
        .withTest(principal)
        .checkForSimilar()
        .build();
    assertFalse(diff.toString(), diff.hasDifferences());
  }

  @Test
  public void testGetScopeUser() throws Exception {
    Principal principal = unmarshal("<principal scope='user'/>");
    assertEquals(Principal.Scope.USER, principal.getScope());
  }

  @Test
  public void testGetScopeGroup() throws Exception {
    Principal principal = unmarshal("<principal scope='group'/>");
    assertEquals(Principal.Scope.GROUP, principal.getScope());
  }

  @Test
  public void testGetScopeUnset() throws Exception {
    thrown.expect(NullPointerException.class);
    Principal principal = unmarshal("<principal/>");
    principal.getScope();
  }

  @Test
  public void testGetScopeInvalid() throws Exception {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    Principal principal = unmarshal("<principal scope='foo'/>");
    principal.getScope();
  }

  @Test
  public void testSetScopeUser() {
    String expected = "<principal scope='user'/>";
    Principal principal1 = new Principal().setScope("user");
    Principal principal2 = new Principal().setScope(Principal.Scope.USER);
    assertNoDiffs(expected, principal1);
    assertNoDiffs(expected, principal2);
  }

  @Test
  public void testSetScopeGroup() {
    String expected = "<principal scope='group'/>";
    Principal principal1 = new Principal().setScope("group");
    Principal principal2 = new Principal().setScope(Principal.Scope.GROUP);
    assertNoDiffs(expected, principal1);
    assertNoDiffs(expected, principal2);
  }

  @Test
  public void testSetScopeNullString() {
    thrown.expect(NullPointerException.class);
    new Principal().setScope((String) null);
  }

  @Test
  public void testSetScopeNullEnum() {
    thrown.expect(NullPointerException.class);
    new Principal().setScope((Principal.Scope) null);
  }

  @Test
  public void testSetScopeInvalid() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    new Principal().setScope("foo");
  }

  @Test
  public void testGetAccessPermit() throws Exception {
    Principal principal = unmarshal("<principal access='permit'/>");
    assertEquals(Principal.Access.PERMIT, principal.getAccess());
  }

  @Test
  public void testGetAccessDeny() throws Exception {
    Principal principal = unmarshal("<principal access='deny'/>");
    assertEquals(Principal.Access.DENY, principal.getAccess());
  }

  @Test
  public void testGetAccessUnset() throws Exception {
    thrown.expect(NullPointerException.class);
    Principal principal = unmarshal("<principal/>");
    principal.getAccess();
  }

  @Test
  public void testGetAccessInvalid() throws Exception {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    Principal principal = unmarshal("<principal access='foo'/>");
    principal.getAccess();
  }

  @Test
  public void testSetAccessPermit() {
    String expected = "<principal access='permit'/>";
    Principal principal1 = new Principal().setAccess("permit");
    Principal principal2 = new Principal().setAccess(Principal.Access.PERMIT);
    assertNoDiffs(expected, principal1);
    assertNoDiffs(expected, principal2);
  }

  @Test
  public void testSetAccessDeny() {
    String expected = "<principal access='deny'/>";
    Principal principal1 = new Principal().setAccess("deny");
    Principal principal2 = new Principal().setAccess(Principal.Access.DENY);
    assertNoDiffs(expected, principal1);
    assertNoDiffs(expected, principal2);
  }

  @Test
  public void testSetAccessNullString() {
    thrown.expect(NullPointerException.class);
    new Principal().setAccess((String) null);
  }

  @Test
  public void testSetAccessNullEnum() {
    thrown.expect(NullPointerException.class);
    new Principal().setAccess((Principal.Access) null);
  }

  @Test
  public void testSetAccessInvalid() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    new Principal().setAccess("foo");
  }

  @Test
  public void testGetCaseSensitivityTypeSensitive() throws Exception {
    Principal principal = unmarshal(
        "<principal case-sensitivity-type='everything-case-sensitive'/>");
    assertEquals(Principal.CaseSensitivityType.EVERYTHING_CASE_SENSITIVE,
        principal.getCaseSensitivityType());
  }
  @Test
  public void testGetCaseSensitivityTypeInsensitive() throws Exception {
    Principal principal = unmarshal(
        "<principal case-sensitivity-type='everything-case-insensitive'/>");
    assertEquals(Principal.CaseSensitivityType.EVERYTHING_CASE_INSENSITIVE,
        principal.getCaseSensitivityType());
  }

  @Test
  public void testGetCaseSensitivityTypeNotSet() throws Exception {
    Principal principal = unmarshal("<principal/>");
    assertEquals(null, principal.getCaseSensitivityType());
  }

  @Test
  public void testGetCaseSensitivityTypeInvalid() throws Exception {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    Principal principal = unmarshal("<principal case-sensitivity-type='foo'/>");
    principal.getCaseSensitivityType();
  }

  @Test
  public void testSetCaseSensitivityTypeSensitive() {
    String expected =
        "<principal case-sensitivity-type='everything-case-sensitive'/>";
    Principal principal1 =
        new Principal().setCaseSensitivityType("everything-case-sensitive");
    Principal principal2 =
        new Principal().setCaseSensitivityType(
            Principal.CaseSensitivityType.EVERYTHING_CASE_SENSITIVE);
    assertNoDiffs(expected, principal1);
    assertNoDiffs(expected, principal2);
  }

  @Test
  public void testSetCaseSensitivityTypeInsensitive() {
    String expected =
        "<principal case-sensitivity-type='everything-case-insensitive'/>";
    Principal principal1 =
        new Principal().setCaseSensitivityType("everything-case-insensitive");
    Principal principal2 =
        new Principal().setCaseSensitivityType(
            Principal.CaseSensitivityType.EVERYTHING_CASE_INSENSITIVE);
    assertNoDiffs(expected, principal1);
    assertNoDiffs(expected, principal2);
  }

  @Test
  public void testSetCaseSensitivityTypeNull() {
    String expected = "<principal/>";
    Principal principal1 =
        new Principal().setCaseSensitivityType((String) null);
    Principal principal2 =
        new Principal().setCaseSensitivityType(
            (Principal.CaseSensitivityType) null);
    assertNoDiffs(expected, principal1);
    assertNoDiffs(expected, principal2);
  }

  @Test
  public void testSetCaseSensitivityTypeInvalid() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    new Principal().setCaseSensitivityType("foo");
  }

  @Test
  public void testGetPrincipalTypeUnqualified() throws Exception {
    Principal principal =
        unmarshal("<principal principal-type='unqualified'/>");
    assertEquals(
        Principal.PrincipalType.UNQUALIFIED, principal.getPrincipalType());
  }

  @Test
  public void testGetPrincipalTypeNotSet() throws Exception {
    Principal principal = unmarshal("<principal/>");
    assertEquals(null, principal.getPrincipalType());
  }

  @Test
  public void testGetPrincipalTypeInvalid() throws Exception {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    Principal principal = unmarshal("<principal principal-type='foo'/>");
    principal.getPrincipalType();
  }

  @Test
  public void testSetPrincipalTypeUnqualified() {
    String expected = "<principal principal-type='unqualified'/>";
    Principal principal1 = new Principal().setPrincipalType("unqualified");
    Principal principal2 =
        new Principal().setPrincipalType(Principal.PrincipalType.UNQUALIFIED);
    assertNoDiffs(expected, principal1);
    assertNoDiffs(expected, principal2);
  }

  @Test
  public void testSetPrincipalTypeNull() {
    String expected = "<principal/>";
    Principal principal1 = new Principal().setPrincipalType((String) null);
    Principal principal2 =
        new Principal().setPrincipalType((Principal.PrincipalType) null);
    assertNoDiffs(expected, principal1);
    assertNoDiffs(expected, principal2);
  }

  @Test
  public void testSetPrincipalTypeInvalid() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    new Principal().setPrincipalType("foo");
  }

  private void assertNoDiffs(String expected, Object actual) {
    Diff diff = DiffBuilder.compare(expected).withTest(actual)
        .checkForSimilar().build();
    assertFalse(diff.toString(), diff.hasDifferences());
  }

  private Principal unmarshal(String value) throws Exception {
    return (Principal) JaxbUtil.unmarshalGsafeed(value);
  }
}
