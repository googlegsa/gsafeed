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

package com.google.enterprise.gsafeed.examples;

import com.google.enterprise.gsafeed.Group;
import com.google.enterprise.gsafeed.Gsafeed;
import com.google.enterprise.gsafeed.GsafeedHelper;
import com.google.enterprise.gsafeed.Record;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;

/**
 * Examples of marshalling and unmarshalling.
 */
public class MarshalUnmarshal {

  public static void main(String... args) throws Exception {
    GsafeedHelper helper = new GsafeedHelper();

    // Read a feed file.
    URL feedUrl = MarshalUnmarshal.class.getResource(args[0]);
    Gsafeed feed = helper.unmarshalWithDtd(feedUrl);

    // List the records in the feed.
    for (Group group : feed.getGroup()) {
      for (Object aclOrRecord : group.getAclOrRecord()) {
        if (aclOrRecord instanceof Record) {
          Record record = (Record) aclOrRecord;
          System.out.println("Found record for " + record.getUrl());
        }
      }
    }

    // Marshal that feed object back to XML.
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    helper.marshal(feed, out);
    System.out.println();
    System.out.println(out.toString("UTF-8"));

    // Read the marshalled feed to check that it's still a valid
    // gsafeed.
    helper.unmarshalWithDtd(new ByteArrayInputStream(out.toByteArray()));
  }
}
