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

  /** Auth methods. */
  public enum AuthMethod {
    NONE("none"),
    HTTPBASIC("httpbasic"),
    NTLM("ntlm"),
    HTTPSSO("httpsso"),
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
      if (value.equals("none")) {
        return NONE;
      }
      if (value.equals("httpbasic")) {
        return HTTPBASIC;
      }
      if (value.equals("ntlm")) {
        return NTLM;
      }
      if (value.equals("httpsso")) {
        return HTTPSSO;
      }
      if (value.equals("negotiate")) {
        return NEGOTIATE;
      }
      throw new IllegalArgumentException(value);
    }
  };

  /** Scoring values. */
  public enum Scoring {
    CONTENT("content"),
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
      if (value.equals("content")) {
        return CONTENT;
      }
      if (value.equals("web")) {
        return WEB;
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
  public Record setAction(String value) {
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
  public Record setAction(Action value) {
    if (value == null) {
      this.action = null;
    } else {
      this.action = value.toString();
    }
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
   * @return possible object is {@link boolean}
   */
  public boolean getLock() {
    return Boolean.valueOf(lock);
  }

  /**
   * Sets the value of the lock property.
   *
   * @param value allowed object is {@link String}
   * @return this object
   */
  public Record setLock(String value) {
    this.lock = Boolean.valueOf(value).toString();
    return this;
  }

  /**
   * Sets the value of the lock property.
   *
   * @param value allowed object is {@link boolean}
   * @return this object
   */
  public Record setLock(boolean value) {
    this.lock = Boolean.toString(value);
    return this;
  }

  /**
   * Gets the value of the authmethod property.
   *
   * @return possible object is {@link AuthMethod}
   */
  public AuthMethod getAuthmethod() {
    if (authmethod == null) {
      return null;
    } else {
      return AuthMethod.fromString(authmethod);
    }
  }

  /**
   * Sets the value of the authmethod property.
   *
   * @param value allowed object is {@link String} or null
   * @return this object
   * @throws IllegalArgumentException if value isn't a valid
   * auth-method attribute value
   */
  public Record setAuthmethod(String value) {
    if (Strings.isNullOrEmpty(value)) {
      this.authmethod = null;
    } else {
      setAuthmethod(AuthMethod.fromString(value));
    }
    return this;
  }

  /**
   * Sets the value of the authmethod property.
   *
   * @param value allowed object is {@link AuthMethod} or null
   * @return this object
   */
  public Record setAuthmethod(AuthMethod value) {
    if (value == null) {
      this.authmethod = null;
    } else {
      this.authmethod = value.toString();
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
   * @return possible object is {@link boolean}
   */
  public boolean getCrawlImmediately() {
    return Boolean.valueOf(crawlImmediately);
  }

  /**
   * Sets the value of the crawlImmediately property.
   *
   * @param value allowed object is {@link String}
   * @return this object
   */
  public Record setCrawlImmediately(String value) {
    this.crawlImmediately = Boolean.valueOf(value).toString();
    return this;
  }

  /**
   * Sets the value of the crawlImmediately property.
   *
   * @param value allowed object is {@link boolean}
   * @return this object
   */
  public Record setCrawlImmediately(boolean value) {
    this.crawlImmediately = Boolean.toString(value);
    return this;
  }

  /**
   * Gets the value of the crawlOnce property.
   *
   * @return possible object is {@link boolean}
   */
  public boolean getCrawlOnce() {
    return Boolean.valueOf(crawlOnce);
  }

  /**
   * Sets the value of the crawlOnce property.
   *
   * @param value allowed object is {@link String}
   * @return this object
   */
  public Record setCrawlOnce(String value) {
    this.crawlOnce = Boolean.valueOf(value).toString();
    return this;
  }

  /**
   * Sets the value of the crawlOnce property.
   *
   * @param value allowed object is {@link boolean}
   * @return this object
   */
  public Record setCrawlOnce(boolean value) {
    this.crawlOnce = Boolean.toString(value);
    return this;
  }

  /**
   * Gets the value of the scoring property.
   *
   * @return possible object is {@link Scoring}
   */
  public Scoring getScoring() {
    if (scoring == null) {
      return null;
    } else {
      return Scoring.fromString(scoring);
    }
  }

  /**
   * Sets the value of the scoring property.
   *
   * @param value allowed object is {@link String} or null
   * @return this object
   * @throws IllegalArgumentException if value isn't a valid
   * scoring attribute value
   */
  public Record setScoring(String value) {
    if (value == null) {
      this.scoring = null;
    } else {
      setScoring(Scoring.fromString(value));
    }
    return this;
  }

  /**
   * Sets the value of the scoring property.
   *
   * @param value allowed object is {@link Scorng} or null
   * @return this object
   */
  public Record setScoring(Scoring value) {
    if (value == null) {
      this.scoring = null;
    } else {
      this.scoring = value.toString();
    }
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
