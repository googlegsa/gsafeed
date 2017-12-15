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
  protected Action action;
  @XmlAttribute(name = "mimetype", required = true)
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  protected String mimetype;
  @XmlAttribute(name = "last-modified")
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  protected String lastModified;
  @XmlAttribute(name = "lock")
  protected Boolean lock;
  @XmlAttribute(name = "authmethod")
  protected AuthMethod authmethod;
  @XmlAttribute(name = "feedrank")
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  protected String feedrank;
  @XmlAttribute(name = "pagerank")
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  protected String pagerank;
  @XmlAttribute(name = "crawl-immediately")
  protected Boolean crawlImmediately;
  @XmlAttribute(name = "crawl-once")
  protected Boolean crawlOnce;
  @XmlAttribute(name = "scoring")
  protected Scoring scoring;
  protected Acl acl;
  protected List<Metadata> metadata;
  protected List<Content> content;

  /** Action types. */
  @XmlType(name = "record-action")
  @XmlEnum(String.class)
  public static enum Action {
    @XmlEnumValue("add")
    ADD("add"),
    @XmlEnumValue("delete")
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
      if (value == null) {
        return null;
      }
      for (Action action : Action.values()) {
        if (action.xmlValue.equals(value)) {
          return action;
        }
      }
      throw new IllegalArgumentException(value);
    }
  }

  /** Auth methods. */
  @XmlEnum(String.class)
  public enum AuthMethod {
    @XmlEnumValue("none")
    NONE("none"),
    @XmlEnumValue("httpbasic")
    HTTPBASIC("httpbasic"),
    @XmlEnumValue("ntlm")
    NTLM("ntlm"),
    @XmlEnumValue("httpsso")
    HTTPSSO("httpsso"),
    @XmlEnumValue("negotiate")
    NEGOTIATE("negotiate");

    private String xmlValue;

    private AuthMethod(String xmlValue) {
      this.xmlValue = xmlValue;
    }

    @Override
    public String toString() {
      return xmlValue;
    }

    public static AuthMethod fromString(String value) {
      if (value == null) {
        return null;
      }
      for (AuthMethod authmethod : AuthMethod.values()) {
        if (authmethod.xmlValue.equals(value)) {
          return authmethod;
        }
      }
      throw new IllegalArgumentException(value);
    }
  };

  /** Scoring values. */
  @XmlEnum(String.class)
  public enum Scoring {
    @XmlEnumValue("content")
    CONTENT("content"),
    @XmlEnumValue("web")
    WEB("web");

    private String xmlValue;

    private Scoring(String xmlValue) {
      this.xmlValue = xmlValue;
    }

    @Override
    public String toString() {
      return xmlValue;
    }

    public static Scoring fromString(String value) {
      if (value == null) {
        return null;
      }
      for (Scoring scoring : Scoring.values()) {
        if (scoring.xmlValue.equals(value)) {
          return scoring;
        }
      }
      throw new IllegalArgumentException(value);
    }
  };

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
  public Record setUrl(String value) {
    this.url = value;
    return this;
  }

  /**
   * Gets the value of the displayurl property.
   *
   * @return possible object is {@link String}
   */
  public String getDisplayurl() {
    return displayurl;
  }

  /**
   * Sets the value of the displayurl property.
   *
   * @param value allowed object is {@link String}
   * @return this object
   */
  public Record setDisplayurl(String value) {
    this.displayurl = value;
    return this;
  }

  /**
   * Gets the value of the action property.
   *
   * @return possible object is {@link Action}
   */
  public Action getAction() {
    return action;
  }

  /**
   * Sets the value of the action property.
   *
   * @param value allowed object is {@link Action} or null
   * @return this object
   */
  public Record setAction(Action value) {
    this.action = value;
    return this;
  }

  /**
   * Gets the value of the mimetype property.
   *
   * @return possible object is {@link String}
   */
  public String getMimetype() {
    return mimetype;
  }

  /**
   * Sets the value of the mimetype property.
   *
   * @param value allowed object is {@link String}
   * @return this object
   */
  public Record setMimetype(String value) {
    this.mimetype = value;
    return this;
  }

  /**
   * Gets the value of the lastModified property.
   *
   * @return possible object is {@link String}
   */
  public String getLastModified() {
    return lastModified;
  }

  /**
   * Sets the value of the lastModified property.
   *
   * @param value allowed object is {@link String}
   * @return this object
   */
  public Record setLastModified(String value) {
    this.lastModified = value;
    return this;
  }

  /**
   * Gets the value of the lock property.
   *
   * @return possible object is {@link Boolean}
   */
  public Boolean getLock() {
    return lock;
  }

  /**
   * Sets the value of the lock property.
   *
   * @param value allowed object is {@link Boolean}
   * @return this object
   */
  public Record setLock(Boolean value) {
    this.lock = value;
    return this;
  }

  /**
   * Gets the value of the authmethod property.
   *
   * @return possible object is {@link AuthMethod}
   */
  public AuthMethod getAuthmethod() {
    return authmethod;
  }

  /**
   * Sets the value of the authmethod property.
   *
   * @param value allowed object is {@link AuthMethod} or null
   * @return this object
   */
  public Record setAuthmethod(AuthMethod value) {
    this.authmethod = value;
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
  public Record setFeedrank(String value) {
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
  public Record setPagerank(String value) {
    this.pagerank = value;
    return this;
  }

  /**
   * Gets the value of the crawlImmediately property.
   *
   * @return possible object is {@link Boolean}
   */
  public Boolean getCrawlImmediately() {
    return crawlImmediately;
  }

  /**
   * Sets the value of the crawlImmediately property.
   *
   * @param value allowed object is {@link Boolean} or null
   * @return this object
   */
  public Record setCrawlImmediately(Boolean value) {
    this.crawlImmediately = value;
    return this;
  }

  /**
   * Gets the value of the crawlOnce property.
   *
   * @return possible object is {@link Boolean}
   */
  public Boolean getCrawlOnce() {
    return crawlOnce;
  }

  /**
   * Sets the value of the crawlOnce property.
   *
   * @param value allowed object is {@link Boolean} or null
   * @return this object
   */
  public Record setCrawlOnce(Boolean value) {
    this.crawlOnce = value;
    return this;
  }

  /**
   * Gets the value of the scoring property.
   *
   * @return possible object is {@link Scoring}
   */
  public Scoring getScoring() {
    return scoring;
  }

  /**
   * Sets the value of the scoring property.
   *
   * @param value allowed object is {@link Scoring} or null
   * @return this object
   */
  public Record setScoring(Scoring value) {
    this.scoring = value;
    return this;
  }

  /**
   * Gets the value of the acl property.
   *
   * @return possible object is {@link Acl}
   */
  public Acl getAcl() {
    return acl;
  }

  /**
   * Sets the value of the acl property.
   *
   * @param value allowed object is {@link Acl}
   * @return this object
   */
  public Record setAcl(Acl value) {
    this.acl = value;
    return this;
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
   * {@link Metadata}
   *
   * @return the Metadata objects
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
   * {@link Content}
   *
   * @return the Content objects
   */
  public List<Content> getContent() {
    if (content == null) {
      content = new ArrayList<Content>();
    }
    return this.content;
  }
}
