package com.openthinks.demo.sprj.links.core.parts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import com.openthinks.libs.utilities.DateUtils;

/**
 * Internal wrapper for all part content
 * 
 * @author dailey.dai@openthinks.com
 *
 * @param <K> the key of this whole content
 * @param <V> the type of part content
 */
class PartDatas<K, V> {
  private final LinkedList<E<V>> data;

  final K refKey;

  PartDatas(final K refKey) {
    this.refKey = refKey;
    data = new LinkedList<>();
  }

  List<V> values() {
    List<V> ret = new ArrayList<>();
    data.forEach((e) -> {
      ret.add(e.v);
    });
    return Collections.unmodifiableList(ret);
  }

  boolean isEmpty() {
    return data.isEmpty();
  }

  long firstModifyTime() {
    if (data.isEmpty())
      return -1L;
    return data.getFirst().modifyTime;
  }

  long lastModifyTime() {
    if (data.isEmpty())
      return -1L;
    return data.getLast().modifyTime;
  }

  synchronized void append(V value) {
    data.add(new E<V>(value));
  }

  static class E<V> {
    V v;
    final Long modifyTime;

    public E(V v) {
      super();
      this.v = v;
      this.modifyTime = DateUtils.currentTimeMillis();
    }
  }
}
