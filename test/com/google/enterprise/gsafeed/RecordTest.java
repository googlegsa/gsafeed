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
 * Test Record.
 */
public class RecordTest {
  @Test
  public void testRecord() throws Exception {
    String expected =
        "<record url='http://example.com'"
        + " displayurl='http://example.com' action='add'"
        + " mimetype='text/plain'"
        + " last-modified='Tue, 6 Nov 2007 12:45:26 GMT'"
        + " lock='false' authmethod='none' feedrank='1'"
        + " pagerank='1' crawl-immediately='false'"
        + " crawl-once='false' scoring='content'></record>";
    Record record = new Record()
        .setUrl("http://example.com")
        .setDisplayurl("http://example.com")
        .setAction("add")
        .setMimetype("text/plain")
        .setLastModified("Tue, 6 Nov 2007 12:45:26 GMT")
        .setLock("false")
        .setAuthmethod("none")
        .setFeedrank("1")
        .setPagerank("1")
        .setCrawlImmediately("false")
        .setCrawlOnce("false")
        .setScoring("content");
    Diff diff = DiffBuilder
        .compare(expected)
        .withTest(record)
        .checkForSimilar()
        .build();
    assertFalse(diff.toString(), diff.hasDifferences());
  }
}
