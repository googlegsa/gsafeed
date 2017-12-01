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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
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
    "aclOrRecord"
})
@XmlRootElement(name = "group")
public class Group {

  @XmlAttribute(name = "action")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String action;
  @XmlAttribute(name = "feedrank")
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  protected String feedrank;
  @XmlAttribute(name = "pagerank")
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  protected String pagerank;
  @XmlElements({
    @XmlElement(name = "acl", type = Acl.class),
    @XmlElement(name = "record", type = Record.class)
  })
  protected List<Object> aclOrRecord;

  /** Action types. */
  public static enum Action {
    ADD("add"),
    DELETE("delete");

    private String xmlValue;

    private Action(String xmlValue) {
      this.xmlValue = xmlValue;
    }

    @Override
    public String toString() {
      return xmlValue;
    }

    public static Action fromString(String value) {
      if (value.equals("add")) {
        return ADD;
      }
      if (value.equals("delete")) {
          return DELETE;
      }
      throw new IllegalArgumentException(value);
    }
  }

  /**
   * Gets the value of the action property.
   *
   * @return possible object is {@link Action}
   */
  public Action getAction() {
    if (action == null) {
      return null;
    } else {
      return Action.fromString(action);
    }
  }

  /**
   * Sets the value of the action property.
   *
   * @param value allowed object is {@link String} or null
   * @return this object
   * @throws IllegalArgumentException if value isn't a valid
   * action attribute value
   */
  public Group setAction(String value) {
    if (value == null) {
      this.action = null;
    } else {
      setAction(Action.fromString(value));
    }
    return this;
  }

  /**
   * Sets the value of the action property.
   *
   * @param value allowed object is {@link Action} or null
   * @return this object
   */
  public Group setAction(Action value) {
    if (value == null) {
      this.action = null;
    } else {
      this.action = value.toString();
    }
    return this;
  }

  /**
   * Gets the value of the feedrank property.
   *
   * @return possible object is {@link String}
   */
  public String getFeedrank() {
    return feedrank;
  }

  /**
   * Sets the value of the feedrank property.
   *
   * @param value allowed object is {@link String}
   * @return this object
   */
  public Group setFeedrank(String value) {
    this.feedrank = value;
    return this;
  }

  /**
   * Gets the value of the pagerank property.
   *
   * @return possible object is {@link String}
   */
  public String getPagerank() {
    return pagerank;
  }

  /**
   * Sets the value of the pagerank property.
   *
   * @param value allowed object is {@link String}
   * @return this object
   */
  public Group setPagerank(String value) {
    this.pagerank = value;
    return this;
  }

  /**
   * Gets the value of the aclOrRecord property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.  This
   * is why there is not a <CODE>set</CODE> method for the
   * aclOrRecord property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getAclOrRecord().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link Acl}
   * {@link Record}
   *
   * @return the Acl and Record objects
   */
  public List<Object> getAclOrRecord() {
    if (aclOrRecord == null) {
      aclOrRecord = new ArrayList<Object>();
    }
    return this.aclOrRecord;
  }
}
