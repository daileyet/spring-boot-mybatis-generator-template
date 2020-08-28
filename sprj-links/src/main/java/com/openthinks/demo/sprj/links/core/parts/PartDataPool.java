/**
 * 
 */
package com.openthinks.demo.sprj.links.core.parts;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Part content container
 * 
 * @author dailey.dai@openthinks.com
 * 
 * @param <K> the key of this whole content
 * @param <V> the type of part content
 */
public final class PartDataPool<K, V> {

  protected final Map<K, PartDatas<K, V>> dataMap = new ConcurrentHashMap<>();

  /**
   * put complete content
   * 
   * @param key
   * @param value
   * @return {@link Whole}
   */
  public Whole<K, V> wholePut(K key, V value) {
    PartDatas<K, V> partDatas = new PartDatas<K, V>(key);
    partDatas.append(value);
    dataMap.put(key, partDatas);
    return new Whole<K, V>(partDatas);
  }

  /**
   * put first part content
   * 
   * @param key
   * @param value
   */
  public void firstPut(K key, V value) {
    PartDatas<K, V> partDatas = new PartDatas<K, V>(key);
    partDatas.append(value);
    dataMap.put(key, partDatas);
  }

  /**
   * put middle part content
   * 
   * @param key
   * @param value
   * @return success or not
   */
  public boolean middlePut(K key, V value) {
    PartDatas<K, V> partDatas = dataMap.get(key);
    if (partDatas == null || partDatas.isEmpty()) {
      return false;
    }
    partDatas.append(value);
    dataMap.put(key, partDatas);
    return true;
  }

  /**
   * put last part content
   * 
   * @param key
   * @param value
   * @return optional of complete content
   */
  public Optional<Whole<K, V>> lastPut(K key, V value) {
    PartDatas<K, V> partDatas = dataMap.get(key);
    if (partDatas == null || partDatas.isEmpty()) {
      return Optional.empty();
    }
    partDatas.append(value);
    return Optional.of(new Whole<K, V>(partDatas));
  }

  public boolean exist(K key) {
    return dataMap.containsKey(key);
  }

  public Optional<Whole<K, V>> find(K key) {
    PartDatas<K, V> partDatas = dataMap.get(key);
    if (partDatas == null) {
      return Optional.empty();
    }
    return Optional.of(new Whole<K, V>(partDatas));
  }

  public void remove(K key) {
    dataMap.remove(key);
  }


}
