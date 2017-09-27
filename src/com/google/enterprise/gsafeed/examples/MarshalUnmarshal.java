package com.google.enterprise.gsafeed.examples;

import static java.nio.charset.StandardCharsets.UTF_8;

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
    // Read a feed file.
    URL feedUrl = MarshalUnmarshal.class.getResource(args[0]);
    Gsafeed feed = GsafeedHelper.unmarshalWithDtd(feedUrl);

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
    GsafeedHelper.marshal(feed, out);
    System.out.println();
    System.out.println(out.toString(UTF_8.name()));

    // Read the marshalled feed to check that it's still a valid
    // gsafeed.
    GsafeedHelper.unmarshalWithDtd(
        new ByteArrayInputStream(out.toByteArray()));
  }
}
