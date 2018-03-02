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

package com.google.enterprise.gsafeed;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Converts between XML string representation of dates in RFC 822
 * format and java.util.Date objects during marshal/unmarshal.
 */
class DateAdapter extends XmlAdapter<String, Date> {
  public static final String FORMAT = "EEE, dd MMM yyyy HH:mm:ss z";

  // Definition copied from GsaFeedFileMaker in adaptor library;
  // format string extracted for access by other code if desired.
  private static final ThreadLocal<DateFormat> rfc822Format =
      new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
          DateFormat df = new SimpleDateFormat(FORMAT, Locale.ENGLISH);
          df.setTimeZone(TimeZone.getTimeZone("GMT"));
          return df;
        }
      };

  @Override
  public String marshal(Date value) {
    if (value == null) {
      return null;
    }
    return rfc822Format.get().format(value);
  }

  @Override
  public Date unmarshal(String value) throws ParseException {
    if (value == null) {
      return null;
    }
    return rfc822Format.get().parse(value);
  }
}
