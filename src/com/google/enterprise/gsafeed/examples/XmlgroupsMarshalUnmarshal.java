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

import com.google.enterprise.gsafeed.XmlgroupsHelper;
import com.google.enterprise.gsafeed.groups.Membership;
import com.google.enterprise.gsafeed.groups.Principal;
import com.google.enterprise.gsafeed.groups.Xmlgroups;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;

/**
 * Examples of marshalling and unmarshalling.
 */
public class XmlgroupsMarshalUnmarshal {

  public static void main(String... args) throws Exception {
    XmlgroupsHelper helper = new XmlgroupsHelper();

    // Read a feed file.
    URL feedUrl = MarshalUnmarshal.class.getResource(args[0]);
    // Examples in the feed developer's guide, as well as groups
    // feeds downloaded from the GSA, don't include the doctype,
    // so skip the validation here.
    Xmlgroups feed = helper.unmarshalWithoutDtd(feedUrl);

    // List the groups and their members.
    for (Membership membership : feed.getMembership()) {
      System.out.println(membership.getPrincipal().getvalue());
      for (Principal principal : membership.getMembers().getPrincipal()) {
        System.out.println("  " + principal.getvalue());
      }
    }

    // Marshal that feed object back to XML.
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    helper.marshal(feed, out);
    System.out.println();
    System.out.println(out.toString("UTF-8"));

    // Read the marshalled feed to check that it's still a valid
    // groups feed. Groups feed construction in the adaptor
    // library code adds a doctype, so check this using the dtd.
    helper.unmarshalWithDtd(new ByteArrayInputStream(out.toByteArray()));
  }
}
