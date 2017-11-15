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

package com.google.enterprise.gsafeed.groups;

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
        "<principal scope='USER'"
        + " namespace='Default'"
        + " case-sensitivity-type='EVERYTHING_CASE_SENSITIVE'"
        + " principal-type='unqualified'>user@example.com</principal>";
    Principal principal = new Principal()
        .setScope("USER")
        .setNamespace("Default")
        .setCaseSensitivityType("EVERYTHING_CASE_SENSITIVE")
        .setPrincipalType("unqualified")
        .setvalue("user@example.com");
    Diff diff = DiffBuilder
        .compare(expected)
        .withTest(principal)
        .checkForSimilar()
        .build();
    assertFalse(diff.toString(), diff.hasDifferences());
  }
}
