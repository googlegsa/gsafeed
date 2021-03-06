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

import com.google.enterprise.gsafeed.GsaFeedFileSender;
import com.google.enterprise.gsafeed.Gsafeed;
import com.google.enterprise.gsafeed.GsafeedHelper;
import com.google.enterprise.gsafeed.IOHelper;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Example of sending a feed to the GSA.
 */
public class SendGsaFeed {
  public static final Charset UTF_8 = Charset.forName("UTF-8");

  public static void main(String... args) throws Exception {
    if (args.length != 2) {
      System.out.println("Usage: " + SendGsaFeed.class.getName()
          + " <feed file> <GSA hostname>");
      return;
    }
    String feedFile = args[0];
    String gsaHost = args[1];

    System.out.println("Sending feed file " + feedFile
        + " to GSA at " + gsaHost);

    // Read the feed file to get the datasource and feedtype.
    URL feedUrl = SendGsaFeed.class.getResource(feedFile);
    String xml = IOHelper.readInputStreamToString(feedUrl.openStream(), UTF_8);
    Gsafeed feed = new GsafeedHelper().unmarshalWithDtd(
        new ByteArrayInputStream(xml.getBytes(UTF_8)));
    String datasource = feed.getHeader().getDatasource();
    String feedtype = feed.getHeader().getFeedtype();

    // Send the feed to the GSA.
    GsaFeedFileSender sender =
        new GsaFeedFileSender(gsaHost, /* secure */ false, UTF_8);
    sender.sendGsaFeed(datasource, feedtype, xml, /* useCompression */ false);
  }
}
