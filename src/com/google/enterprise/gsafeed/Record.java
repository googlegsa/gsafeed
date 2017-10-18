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
    "acl",
    "metadata",
    "content"
})
@XmlRootElement(name = "record")
public class Record {

  @XmlAttribute(name = "url", required = true)
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  protected String url;
  @XmlAttribute(name = "displayurl")
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  protected String displayurl;
  @XmlAttribute(name = "action")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String action;
  @XmlAttribute(name = "mimetype", required = true)
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  protected String mimetype;
  @XmlAttribute(name = "last-modified")
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  protected String lastModified;
  @XmlAttribute(name = "lock")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String lock;
  @XmlAttribute(name = "authmethod")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String authmethod;
  @XmlAttribute(name = "feedrank")
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  protected String feedrank;
  @XmlAttribute(name = "pagerank")
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  protected String pagerank;
  @XmlAttribute(name = "crawl-immediately")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String crawlImmediately;
  @XmlAttribute(name = "crawl-once")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String crawlOnce;
  @XmlAttribute(name = "scoring")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String scoring;
  protected Acl acl;
  protected List<Metadata> metadata;
  protected List<Content> content;

  /**
   * Gets the value of the url property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getUrl() {
    return url;
  }

  /**
   * Sets the value of the url property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setUrl(String value) {
    this.url = value;
  }

  /**
   * Gets the value of the displayurl property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getDisplayurl() {
    return displayurl;
  }

  /**
   * Sets the value of the displayurl property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setDisplayurl(String value) {
    this.displayurl = value;
  }

  /**
   * Gets the value of the action property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getAction() {
    return action;
  }

  /**
   * Sets the value of the action property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setAction(String value) {
    this.action = value;
  }

  /**
   * Gets the value of the mimetype property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getMimetype() {
    return mimetype;
  }

  /**
   * Sets the value of the mimetype property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setMimetype(String value) {
    this.mimetype = value;
  }

  /**
   * Gets the value of the lastModified property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getLastModified() {
    return lastModified;
  }

  /**
   * Sets the value of the lastModified property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setLastModified(String value) {
    this.lastModified = value;
  }

  /**
   * Gets the value of the lock property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getLock() {
    if (lock == null) {
      return "false";
    } else {
      return lock;
    }
  }

  /**
   * Sets the value of the lock property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setLock(String value) {
    this.lock = value;
  }

  /**
   * Gets the value of the authmethod property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getAuthmethod() {
    return authmethod;
  }

  /**
   * Sets the value of the authmethod property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setAuthmethod(String value) {
    this.authmethod = value;
  }

  /**
   * Gets the value of the feedrank property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFeedrank() {
    return feedrank;
  }

  /**
   * Sets the value of the feedrank property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFeedrank(String value) {
    this.feedrank = value;
  }

  /**
   * Gets the value of the pagerank property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getPagerank() {
    return pagerank;
  }

  /**
   * Sets the value of the pagerank property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setPagerank(String value) {
    this.pagerank = value;
  }

  /**
   * Gets the value of the crawlImmediately property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getCrawlImmediately() {
    if (crawlImmediately == null) {
      return "false";
    } else {
      return crawlImmediately;
    }
  }

  /**
   * Sets the value of the crawlImmediately property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setCrawlImmediately(String value) {
    this.crawlImmediately = value;
  }

  /**
   * Gets the value of the crawlOnce property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getCrawlOnce() {
    if (crawlOnce == null) {
      return "false";
    } else {
      return crawlOnce;
    }
  }

  /**
   * Sets the value of the crawlOnce property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setCrawlOnce(String value) {
    this.crawlOnce = value;
  }

  /**
   * Gets the value of the scoring property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getScoring() {
    return scoring;
  }

  /**
   * Sets the value of the scoring property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setScoring(String value) {
    this.scoring = value;
  }

  /**
   * Gets the value of the acl property.
   *
   * @return
   *     possible object is
   *     {@link Acl }
   *
   */
  public Acl getAcl() {
    return acl;
  }

  /**
   * Sets the value of the acl property.
   *
   * @param value
   *     allowed object is
   *     {@link Acl }
   *
   */
  public void setAcl(Acl value) {
    this.acl = value;
  }

  /**
   * Gets the value of the metadata property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.  This
   * is why there is not a <CODE>set</CODE> method for the
   * metadata property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getMetadata().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link Metadata }
   *
   *
   */
  public List<Metadata> getMetadata() {
    if (metadata == null) {
      metadata = new ArrayList<Metadata>();
    }
    return this.metadata;
  }

  /**
   * Gets the value of the content property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.  This
   * is why there is not a <CODE>set</CODE> method for the
   * content property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getContent().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link Content }
   *
   *
   */
  public List<Content> getContent() {
    if (content == null) {
      content = new ArrayList<Content>();
    }
    return this.content;
  }
}
