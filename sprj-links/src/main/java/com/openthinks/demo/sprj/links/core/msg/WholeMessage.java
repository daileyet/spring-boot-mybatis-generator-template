/**
 * 
 */
package com.openthinks.demo.sprj.links.core.msg;

import java.io.Serializable;

import com.openthinks.demo.sprj.links.core.parts.Whole;

/**
 * @author dailey.dai@openthinks.com
 *
 */
public abstract class WholeMessage<K, V> implements Message {
  protected Whole<K, V> payload;

  public WholeMessage(Whole<K, V> data) {
    this.payload = data;
  }

  @Override
  public Serializable id() {
    return String.format("%s_%d", getKeyAsString(), payload.getFirstTime());
  }

  public abstract String getKeyAsString();

  @Override
  public Whole<K, V> payload() {
    return payload;
  }

  @Override
  public String toString() {
    return "WholeMessage [payload=" + payload + ", id()=" + id() + "]";
  }

}
