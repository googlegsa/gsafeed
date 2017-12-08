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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "principal"
})
@XmlRootElement(name = "acl")
public class Acl {

  @XmlAttribute(name = "url")
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  protected String url;
  @XmlAttribute(name = "inheritance-type")
  protected InheritanceType inheritanceType;
  @XmlAttribute(name = "inherit-from")
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  protected String inheritFrom;
  protected List<Principal> principal;

  /** Inheritance types. */
  @XmlEnum(String.class)
  public static enum InheritanceType {
    @XmlEnumValue("child-overrides")
    CHILD_OVERRIDES("child-overrides"),
    @XmlEnumValue("parent-overrides")
    PARENT_OVERRIDES("parent-overrides"),
    @XmlEnumValue("and-both-permit")
    AND_BOTH_PERMIT("and-both-permit"),
    @XmlEnumValue("leaf-node")
    LEAF_NODE("leaf-node");

    private String xmlValue;

    private InheritanceType(String xmlValue) {
      this.xmlValue = xmlValue;
    }

    @Override
    public String toString() {
      return xmlValue;
    }

    public static InheritanceType fromString(String value) {
      if (value == null) {
        return null;
      }
      for (InheritanceType inheritanceType : InheritanceType.values()) {
        if (inheritanceType.xmlValue.equals(value)) {
          return inheritanceType;
        }
      }
      throw new IllegalArgumentException(value);
    }
  }

  /**
   * Gets the value of the url property.
   *
   * @return possible object is {@link String}
   */
  public String getUrl() {
    return url;
  }

  /**
   * Sets the value of the url property.
   *
   * @param value allowed object is {@link String}
   * @return this object
   */
  public Acl setUrl(String value) {
    this.url = value;
    return this;
  }

  /**
   * Gets the value of the inheritanceType property.
   *
   * @return possible object is {@link InheritanceType}
   */
  public InheritanceType getInheritanceType() {
    return inheritanceType;
  }

  /**
   * Sets the value of the inheritanceType property.
   *
   * @param value allowed object is {@link InheritanceType} or null
   * @return this object
   */
  public Acl setInheritanceType(InheritanceType value) {
    this.inheritanceType = value;
    return this;
  }

  /**
   * Gets the value of the inheritFrom property.
   *
   * @return possible object is {@link String}
   */
  public String getInheritFrom() {
    return inheritFrom;
  }

  /**
   * Sets the value of the inheritFrom property.
   *
   * @param value allowed object is {@link String}
   * @return this object
   */
  public Acl setInheritFrom(String value) {
    this.inheritFrom = value;
    return this;
  }

  /**
   * Gets the value of the principal property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.  This
   * is why there is not a <CODE>set</CODE> method for the
   * principal property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getPrincipal().add(newItem);
   * </pre>
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link Principal}
   *
   * @return the Principal objects
   */
  public List<Principal> getPrincipal() {
    if (principal == null) {
      principal = new ArrayList<Principal>();
    }
    return this.principal;
  }
}
