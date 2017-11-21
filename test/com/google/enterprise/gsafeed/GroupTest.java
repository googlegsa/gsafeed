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
 * Test Group.
 */
public class GroupTest {
  @Test
  public void testGroup() throws Exception {
    String expected =
        "<group action='add' feedrank='1' pagerank='1'></group>";
    Group group = new Group()
        .setAction("add")
        .setFeedrank("1").setPagerank("1");
    Diff diff = DiffBuilder
        .compare(expected)
        .withTest(group)
        .checkForSimilar()
        .build();
    assertFalse(diff.toString(), diff.hasDifferences());
  }
}
