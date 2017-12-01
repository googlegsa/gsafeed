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
import java.util.ArrayList;
import java.util.List;
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
@XmlType(name = "", propOrder = {
    "principal"
})
@XmlRootElement(name = "acl")
public class Acl {

  @XmlAttribute(name = "url")
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  protected String url;
  @XmlAttribute(name = "inheritance-type")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String inheritanceType;
  @XmlAttribute(name = "inherit-from")
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  protected String inheritFrom;
  protected List<Principal> principal;

  /** Inheritance types. */
  public static enum InheritanceType {
    CHILD_OVERRIDES("child-overrides"),
    PARENT_OVERRIDES("parent-overrides"),
    AND_BOTH_PERMIT("and-both-permit"),
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
      if (value.equals("child-overrides")) {
        return CHILD_OVERRIDES;
      }
      if (value.equals("parent-overrides")) {
          return PARENT_OVERRIDES;
      }
      if (value.equals("and-both-permit")) {
        return AND_BOTH_PERMIT;
      }
      if (value.equals("leaf-node")) {
        return LEAF_NODE;
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
    if (inheritanceType == null) {
      return null;
    } else {
      return InheritanceType.fromString(inheritanceType);
    }
  }

  /**
   * Sets the value of the inheritanceType property.
   *
   * @param value allowed object is {@link String} or null
   * @return this object
   * @throws IllegalArgumentException if value isn't a valid
   * inheritance-type attribute value
   */
  public Acl setInheritanceType(String value) {
    if (Strings.isNullOrEmpty(value)) {
      this.inheritanceType = null;
    } else {
      setInheritanceType(InheritanceType.fromString(value));
    }
    return this;
  }

  /**
   * Sets the value of the inheritanceType property.
   *
   * @param value allowed object is {@link InheritanceType} or null
   * @return this object
   */
  public Acl setInheritanceType(InheritanceType value) {
    if (value == null) {
      this.inheritanceType = null;
    } else {
      this.inheritanceType = value.toString();
    }
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
