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
    "principal",
    "members"
})
@XmlRootElement(name = "membership")
public class Membership {

  @XmlElement(required = true)
  protected Principal principal;
  @XmlElement(required = true)
  protected Members members;

  /**
   * Gets the value of the principal property.
   *
   * @return
   *     possible object is
   *     {@link Principal }
   *
   */
  public Principal getPrincipal() {
    return principal;
  }

  /**
   * Sets the value of the principal property.
   *
   * @param value
   *     allowed object is
   *     {@link Principal }
   *
   */
  public void setPrincipal(Principal value) {
    this.principal = value;
  }

  /**
   * Gets the value of the members property.
   *
   * @return
   *     possible object is
   *     {@link Members }
   *
   */
  public Members getMembers() {
    return members;
  }

  /**
   * Sets the value of the members property.
   *
   * @param value
   *     allowed object is
   *     {@link Members }
   *
   */
  public void setMembers(Members value) {
    this.members = value;
  }
}
