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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "value"
})
@XmlRootElement(name = "content")
public class Content {

  @XmlAttribute(name = "encoding")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String encoding;
  @XmlValue
  protected String value;

  /**
   * Gets the value of the encoding property.
   *
   * @return possible object is {@link String}
   */
  public String getEncoding() {
    return encoding;
  }

  /**
   * Sets the value of the encoding property.
   *
   * @param value allowed object is {@link String}
   */
  public void setEncoding(String value) {
    this.encoding = value;
  }

  /**
   * Gets the value of the value property.
   *
   * @return possible object is {@link String}
   */
  public String getvalue() {
    return value;
  }

  /**
   * Sets the value of the value property.
   *
   * @param value allowed object is {@link String}
   */
  public void setvalue(String value) {
    this.value = value;
  }
}
