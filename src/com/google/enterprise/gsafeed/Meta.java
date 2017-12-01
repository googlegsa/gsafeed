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

import com.google.common.base.Strings;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "meta")
public class Meta {

  @XmlAttribute(name = "encoding")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String encoding;
  @XmlAttribute(name = "name", required = true)
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  protected String name;
  @XmlAttribute(name = "content", required = true)
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  protected String content;

  /** Encoding types. */
  public static enum Encoding {
    BASE_64_BINARY("base64binary");

    private String xmlValue;

    private Encoding(String xmlValue) {
      this.xmlValue = xmlValue;
    }

    @Override
    public String toString() {
      return xmlValue;
    }

    public static Encoding fromString(String value) {
      if (value.equals("base64binary")) {
        return BASE_64_BINARY;
      }
      throw new IllegalArgumentException(value);
    }
  }

  /**
   * Gets the value of the encoding property.
   *
   * @return possible object is {@link Encoding}
   */
  public Encoding getEncoding() {
    if (encoding == null) {
      return null;
    }
    return Encoding.fromString(encoding);
  }

  /**
   * Sets the value of the encoding property.
   *
   * @param value allowed object is {@link String} or null
   * @return this object
   * @throws IllegalArgumentException if value isn't a valid
   * encoding attribute value
   */
  public Meta setEncoding(String value) {
    if (Strings.isNullOrEmpty(value)) {
      this.encoding = null;
    } else {
      setEncoding(Encoding.fromString(value));
    }
    return this;
  }

  /**
   * Sets the value of the encoding property.
   *
   * @param value allowed object is {@link Encoding} or null
   * @return this object
   */
  public Meta setEncoding(Encoding value) {
    if (value == null) {
      this.encoding = null;
    } else {
      this.encoding = value.toString();
    }
    return this;
  }

  /**
   * Gets the value of the name property.
   *
   * @return possible object is {@link String}
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the value of the name property.
   *
   * @param value allowed object is {@link String}
   * @return this object
   */
  public Meta setName(String value) {
    this.name = value;
    return this;
  }

  /**
   * Gets the value of the content property.
   *
   * @return possible object is {@link String}
   */
  public String getContent() {
    return content;
  }

  /**
   * Sets the value of the content property.
   *
   * @param value allowed object is {@link String}
   * @return this object
   */
  public Meta setContent(String value) {
    this.content = value;
    return this;
  }
}
