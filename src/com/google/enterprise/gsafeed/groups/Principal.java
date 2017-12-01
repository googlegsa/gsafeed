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

package com.google.enterprise.gsafeed.groups;

import com.google.common.base.Strings;
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

  /** Scopes. */
  // In this case, the enum names match the XML
  // values. Keep the enum implementation the same as all the
  // others for consistency.
  public static enum Scope {
    USER("USER"),
    GROUP("GROUP");

    private String xmlValue;

    private Scope(String xmlValue) {
      this.xmlValue = xmlValue;
    }

    @Override
    public String toString() {
      return xmlValue;
    }

    public static Scope fromString(String value) {
      if (value.equals("USER")) {
        return USER;
      }
      if (value.equals("GROUP")) {
        return GROUP;
      }
      throw new IllegalArgumentException(value);
    }
  }

  /** Case sensitivity. */
  // In this case, the enum names match the XML
  // values. Keep the enum implementation the same as all the
  // others for consistency.
  public static enum CaseSensitivityType {
    EVERYTHING_CASE_SENSITIVE("EVERYTHING_CASE_SENSITIVE"),
    EVERYTHING_CASE_INSENSITIVE("EVERYTHING_CASE_INSENSITIVE");

    private String xmlValue;

    private CaseSensitivityType(String xmlValue) {
      this.xmlValue = xmlValue;
    }

    @Override
    public String toString() {
      return xmlValue;
    }

    public static CaseSensitivityType fromString(String value) {
      if (value.equals("EVERYTHING_CASE_SENSITIVE")) {
        return EVERYTHING_CASE_SENSITIVE;
      }
      if (value.equals("EVERYTHING_CASE_INSENSITIVE")) {
        return EVERYTHING_CASE_INSENSITIVE;
      }
      throw new IllegalArgumentException(value);
    }
  }

  /** Principal type. */
  public static enum PrincipalType {
    UNQUALIFIED("unqualified");

    private String xmlValue;

    private PrincipalType(String xmlValue) {
      this.xmlValue = xmlValue;
    }

    @Override
    public String toString() {
      return xmlValue;
    }

    public static PrincipalType fromString(String value) {
      if (value.equals("unqualified")) {
        return UNQUALIFIED;
      }
      throw new IllegalArgumentException(value);
    }
  }

  /**
   * Gets the value of the scope property.
   *
   * @return possible object is {@link Scope}
   */
  public Scope getScope() {
    return Scope.fromString(scope);
  }

  /**
   * Sets the value of the scope property.
   *
   * @param value allowed object is {@link String}
   * @return this object
   * @throws IllegalArgumentException if value isn't a valid
   * scope attribute value
   */
  public Principal setScope(String value) {
    // scope is required, so don't accept null
    return setScope(Scope.fromString(value));
  }

  /**
   * Sets the value of the scope property.
   *
   * @param value allowed object is {@link Scope}
   * @return this object
   */
  public Principal setScope(Scope value) {
    // scope is required, so don't accept null
    this.scope = value.toString();
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
   * @return possible object is {@link CaseSensitivityType}
   */
  public CaseSensitivityType getCaseSensitivityType() {
    if (caseSensitivityType == null) {
      return null;
    } else {
      return CaseSensitivityType.fromString(caseSensitivityType);
    }
  }

  /**
   * Sets the value of the caseSensitivityType property.
   *
   * @param value allowed object is {@link String} or null
   * @return this object
   * @throws IllegalArgumentException if value isn't a valid
   * case-sensitivity-type attribute value
   */
  public Principal setCaseSensitivityType(String value) {
    if (Strings.isNullOrEmpty(value)) {
      this.caseSensitivityType = null;
    } else {
      setCaseSensitivityType(CaseSensitivityType.fromString(value));
    }
    return this;
  }

  /**
   * Sets the value of the caseSensitivityType property.
   *
   * @param value allowed object is {@link setCaseSensitivityType} or null
   * @return this object
   */
  public Principal setCaseSensitivityType(CaseSensitivityType value) {
    if (value == null) {
      this.caseSensitivityType = null;
    } else {
      this.caseSensitivityType = value.toString();
    }
    return this;
  }

  /**
   * Gets the value of the principalType property.
   *
   * @return possible object is {@link PrincipalType}
   */
  public PrincipalType getPrincipalType() {
    if (principalType == null) {
      return null;
    }
    return PrincipalType.fromString(this.principalType);
  }

  /**
   * Sets the value of the principalType property.
   *
   * @param value allowed object is {@link String} or null
   * @return this object
   * @throws IllegalArgumentException if value isn't a valid
   * principal-type attribute value
   */
  public Principal setPrincipalType(String value) {
    if (Strings.isNullOrEmpty(value)) {
      this.principalType = null;
    } else {
      setPrincipalType(PrincipalType.fromString(value));
    }
    return this;
  }

  /**
   * Sets the value of the principalType property.
   *
   * @param value allowed object is {@link PrincipalType} or null
   * @return this object
   */
  public Principal setPrincipalType(PrincipalType value) {
    if (value == null) {
      this.principalType = null;
    } else {
      this.principalType = value.toString();
    }
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
