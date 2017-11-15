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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "header",
    "group"
})
@XmlRootElement(name = "gsafeed")
public class Gsafeed {

  @XmlElement(required = true)
  protected Header header;
  @XmlElement(required = true)
  protected List<Group> group;

  /**
   * Gets the value of the header property.
   *
   * @return possible object is {@link Header}
   */
  public Header getHeader() {
    return header;
  }

  /**
   * Sets the value of the header property.
   *
   * @param value allowed object is {@link Header}
   * @return this object
   */
  public Gsafeed setHeader(Header value) {
    this.header = value;
    return this;
  }

  /**
   * Gets the value of the group property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the group property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getGroup().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link Group}
   *
   * @return the Group objects
   */
  public List<Group> getGroup() {
    if (group == null) {
      group = new ArrayList<Group>();
    }
    return this.group;
  }
}
