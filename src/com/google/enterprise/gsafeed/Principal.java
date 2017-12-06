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
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
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
  protected Scope scope;
  @XmlAttribute(name = "access", required = true)
  protected Access access;
  @XmlAttribute(name = "namespace")
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  protected String namespace;
  @XmlAttribute(name = "case-sensitivity-type")
  protected CaseSensitivityType caseSensitivityType;
  @XmlAttribute(name = "principal-type")
  protected PrincipalType principalType;
  @XmlValue
  protected String value;

  /** Scopes. */
  @XmlEnum(String.class)
  public static enum Scope {
    @XmlEnumValue("user")
    USER("user"),
    @XmlEnumValue("group")
    GROUP("group");

    private String xmlValue;

    private Scope(String xmlValue) {
      this.xmlValue = xmlValue;
    }

    @Override
    public String toString() {
      return xmlValue;
    }

    public static Scope fromString(String value) {
      for (Scope scope : Scope.values()) {
        if (scope.xmlValue.equals(value)) {
          return scope;
        }
      }
      throw new IllegalArgumentException(value);
    }
  }

  /** Access values. */
  @XmlEnum(String.class)
  public static enum Access {
    @XmlEnumValue("permit")
    PERMIT("permit"),
    @XmlEnumValue("deny")
    DENY("deny");

    private String xmlValue;

    private Access(String xmlValue) {
      this.xmlValue = xmlValue;
    }

    @Override
    public String toString() {
      return xmlValue;
    }

    public static Access fromString(String value) {
      for (Access access : Access.values()) {
        if (access.xmlValue.equals(value)) {
          return access;
        }
      }
      throw new IllegalArgumentException(value);
    }
  }

  /** Case sensitivity. */
  @XmlEnum(String.class)
  public static enum CaseSensitivityType {
    @XmlEnumValue("everything-case-sensitive")
    EVERYTHING_CASE_SENSITIVE("everything-case-sensitive"),
    @XmlEnumValue("everything-case-insensitive")
    EVERYTHING_CASE_INSENSITIVE("everything-case-insensitive");

    private String xmlValue;

    private CaseSensitivityType(String xmlValue) {
      this.xmlValue = xmlValue;
    }

    @Override
    public String toString() {
      return xmlValue;
    }

    public static CaseSensitivityType fromString(String value) {
      for (CaseSensitivityType type : CaseSensitivityType.values()) {
        if (type.xmlValue.equals(value)) {
          return type;
        }
      }
      throw new IllegalArgumentException(value);
    }
  }

  /** Principal type. */
  @XmlEnum(String.class)
  public static enum PrincipalType {
    @XmlEnumValue("unqualified")
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
      for (PrincipalType type : PrincipalType.values()) {
        if (type.xmlValue.equals(value)) {
          return type;
        }
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
    return scope;
  }

  /**
   * Sets the value of the scope property.
   *
   * @param value allowed object is {@link Scope}
   * @return this object
   */
  public Principal setScope(Scope value) {
    if (value == null) {
      throw new IllegalArgumentException("null");
    }
    this.scope = value;
    return this;
  }

  /**
   * Gets the value of the access property.
   *
   * @return possible object is {@link String}
   */
  public Access getAccess() {
    return access;
  }

  /**
   * Sets the value of the access property.
   *
   * @param value allowed object is {@link Access}
   * @return this object
   */
  public Principal setAccess(Access value) {
    if (value == null) {
      throw new IllegalArgumentException("null");
    }
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
   * @return possible object is {@link CaseSensitivityType}
   */
  public CaseSensitivityType getCaseSensitivityType() {
    return caseSensitivityType;
  }

  /**
   * Sets the value of the caseSensitivityType property.
   *
   * @param value allowed object is {@link CaseSensitivityType} or null
   * @return this object
   */
  public Principal setCaseSensitivityType(CaseSensitivityType value) {
    this.caseSensitivityType = value;
    return this;
  }

  /**
   * Gets the value of the principalType property.
   *
   * @return possible object is {@link PrincipalType}
   */
  public PrincipalType getPrincipalType() {
    return principalType;
  }

  /**
   * Sets the value of the principalType property.
   *
   * @param value allowed object is {@link PrincipalType} or null
   * @return this object
   */
  public Principal setPrincipalType(PrincipalType value) {
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
