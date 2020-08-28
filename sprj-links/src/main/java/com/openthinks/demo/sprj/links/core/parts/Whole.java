package com.openthinks.demo.sprj.links.core.parts;

import java.util.List;

/**
 * Whole message content which include all part content
 * 
 * @author dailey.dai@openthinks.com
 *
 * @param <K> the key of this whole content
 * @param <V> the type of part content
 */
public final class Whole<K, V> {
  private final List<V> values;
  private final long firstTime;
  private final long lastTime;
  private final K keyRef;

  Whole(final PartDatas<K, V> partDatas) {
    this(partDatas.values(), partDatas.refKey, partDatas.firstModifyTime(),
        partDatas.lastModifyTime());
  }

  public Whole(List<V> vs, K keyRef, long firstTime, long lastTime) {
    super();
    this.values = vs;
    this.keyRef = keyRef;
    this.firstTime = firstTime;
    this.lastTime = lastTime;
  }

  public List<V> getValues() {
    return values;
  }

  /**
   * get first part received time
   * 
   * @return long
   */
  public long getFirstTime() {
    return firstTime;
  }

  /**
   * get last part received time
   * 
   * @return long
   */
  public long getLastTime() {
    return lastTime;
  }

  public K getKeyRef() {
    return keyRef;
  }

  @Override
  public String toString() {
    return "Whole [values=" + values + ", firstTime=" + firstTime + ", lastTime=" + lastTime
        + ", keyRef=" + keyRef + "]";
  }

}
