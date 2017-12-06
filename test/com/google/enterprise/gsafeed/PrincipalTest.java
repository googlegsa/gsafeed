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
 * Test Principal.
 */
public class PrincipalTest {
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
        .setScope(Principal.Scope.USER)
        .setAccess(Principal.Access.PERMIT)
        .setNamespace("Default")
        .setCaseSensitivityType(
            Principal.CaseSensitivityType.EVERYTHING_CASE_SENSITIVE)
        .setPrincipalType(Principal.PrincipalType.UNQUALIFIED)
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
    Principal principal = unmarshal("<principal/>");
    assertEquals(null, principal.getScope());
  }

  @Test
  public void testGetScopeInvalid() throws Exception {
    Principal principal = unmarshal("<principal scope='foo'/>");
    assertEquals(null, principal.getScope());
  }

  @Test
  public void testSetScopeUser() {
    String expected = "<principal scope='user'/>";
    Principal principal = new Principal().setScope(Principal.Scope.USER);
    assertNoDiffs(expected, principal);
  }

  @Test
  public void testSetScopeGroup() {
    String expected = "<principal scope='group'/>";
    Principal principal = new Principal().setScope(Principal.Scope.GROUP);
    assertNoDiffs(expected, principal);
  }

  @Test
  public void testSetScopeNull() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("null");
    new Principal().setScope(null);
  }

  @Test
  public void testScopeInvalid() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    Principal.Scope.fromString("foo");
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
    Principal principal = unmarshal("<principal/>");
    assertEquals(null, principal.getAccess());
  }

  @Test
  public void testGetAccessInvalid() throws Exception {
    Principal principal = unmarshal("<principal access='foo'/>");
    assertEquals(null, principal.getAccess());
  }

  @Test
  public void testSetAccessPermit() {
    String expected = "<principal access='permit'/>";
    Principal principal = new Principal().setAccess(Principal.Access.PERMIT);
    assertNoDiffs(expected, principal);
  }

  @Test
  public void testSetAccessDeny() {
    String expected = "<principal access='deny'/>";
    Principal principal = new Principal().setAccess(Principal.Access.DENY);
    assertNoDiffs(expected, principal);
  }

  @Test
  public void testSetAccessNull() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("null");
    new Principal().setAccess(null);
  }

  @Test
  public void testAccessInvalid() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    Principal.Access.fromString("foo");
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
    Principal principal =
        unmarshal("<principal case-sensitivity-type='foo'/>");
    assertEquals(null, principal.getCaseSensitivityType());
  }

  @Test
  public void testSetCaseSensitivityTypeSensitive() {
    String expected =
        "<principal case-sensitivity-type='everything-case-sensitive'/>";
    Principal principal =
        new Principal().setCaseSensitivityType(
            Principal.CaseSensitivityType.EVERYTHING_CASE_SENSITIVE);
    assertNoDiffs(expected, principal);
  }

  @Test
  public void testSetCaseSensitivityTypeInsensitive() {
    String expected =
        "<principal case-sensitivity-type='everything-case-insensitive'/>";
    Principal principal =
        new Principal().setCaseSensitivityType(
            Principal.CaseSensitivityType.EVERYTHING_CASE_INSENSITIVE);
    assertNoDiffs(expected, principal);
  }

  @Test
  public void testSetCaseSensitivityTypeNull() {
    String expected = "<principal/>";
    Principal principal =
        new Principal().setCaseSensitivityType(null);
    assertNoDiffs(expected, principal);
  }

  @Test
  public void testCaseSensitivityTypeInvalid() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    Principal.CaseSensitivityType.fromString("foo");
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
    Principal principal = unmarshal("<principal principal-type='foo'/>");
    assertEquals(null, principal.getPrincipalType());
  }

  @Test
  public void testSetPrincipalTypeUnqualified() {
    String expected = "<principal principal-type='unqualified'/>";
    Principal principal =
        new Principal().setPrincipalType(Principal.PrincipalType.UNQUALIFIED);
    assertNoDiffs(expected, principal);
  }

  @Test
  public void testSetPrincipalTypeNull() {
    String expected = "<principal/>";
    Principal principal = new Principal().setPrincipalType(null);
    assertNoDiffs(expected, principal);
  }

  @Test
  public void testPrincipalTypeInvalid() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("foo");
    Principal.PrincipalType.fromString("foo");
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
