/**
 * 
 */
package com.openthinks.demo.sprj.links.core.parts;

/**
 * 
 * The strategy which how to judge {@link PartType} for given message content
 * 
 * @author dailey.dai@openthinks.com
 * @param <V> type of message content
 */
public interface PartTypeIdentity<V> {

  /**
   * judge the given content is belong which {@link PartType}
   * 
   * @param content message content
   * @return {@link PartType}
   */
  public PartType judge(V content);
}
