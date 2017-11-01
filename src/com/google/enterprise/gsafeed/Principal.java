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
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "value"
})
@XmlRootElement(name = "principal")
public class Principal {

  @XmlAttribute(name = "scope", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String scope;
  @XmlAttribute(name = "access", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String access;
  @XmlAttribute(name = "namespace")
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  protected String namespace;
  @XmlAttribute(name = "case-sensitivity-type")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String caseSensitivityType;
  @XmlAttribute(name = "principal-type")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String principalType;
  @XmlValue
  protected String value;

  /**
   * Gets the value of the scope property.
   *
   * @return possible object is {@link String}
   */
  public String getScope() {
    return scope;
  }

  /**
   * Sets the value of the scope property.
   *
   * @param value allowed object is {@link String}
   * @return this object
   */
  public Principal setScope(String value) {
    this.scope = value;
    return this;
  }

  /**
   * Gets the value of the access property.
   *
   * @return possible object is {@link String}
   */
  public String getAccess() {
    return access;
  }

  /**
   * Sets the value of the access property.
   *
   * @param value allowed object is {@link String}
   * @return this object
   */
  public Principal setAccess(String value) {
    this.access = value;
    return this;
  }

  /**
   * Gets the value of the namespace property.
   *
   * @return possible object is {@link String}
   */
  public String getNamespace() {
    if (namespace == null) {
      return "Default";
    } else {
      return namespace;
    }
  }

  /**
   * Sets the value of the namespace property.
   *
   * @param value allowed object is {@link String}
   * @return this object
   */
  public Principal setNamespace(String value) {
    this.namespace = value;
    return this;
  }

  /**
   * Gets the value of the caseSensitivityType property.
   *
   * @return possible object is {@link String}
   */
  public String getCaseSensitivityType() {
    if (caseSensitivityType == null) {
      return "everything-case-sensitive";
    } else {
      return caseSensitivityType;
    }
  }

  /**
   * Sets the value of the caseSensitivityType property.
   *
   * @param value allowed object is {@link String}
   * @return this object
   */
  public Principal setCaseSensitivityType(String value) {
    this.caseSensitivityType = value;
    return this;
  }

  /**
   * Gets the value of the principalType property.
   *
   * @return possible object is {@link String}
   */
  public String getPrincipalType() {
    return principalType;
  }

  /**
   * Sets the value of the principalType property.
   *
   * @param value allowed object is {@link String}
   * @return this object
   */
  public Principal setPrincipalType(String value) {
    this.principalType = value;
    return this;
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
   * @return this object
   */
  public Principal setvalue(String value) {
    this.value = value;
    return this;
  }
}
