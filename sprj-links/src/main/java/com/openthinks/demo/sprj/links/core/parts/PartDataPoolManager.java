package com.openthinks.demo.sprj.links.core.parts;


/**
 * {@link PartDataPool} management interface
 * 
 * @author dailey.dai@openthinks.com
 *
 * @param <K> the key of this whole content
 * @param <V> the type of part content
 */
public interface PartDataPoolManager<K, V> {

  /**
   * set {@link PartDataListener}
   * 
   * @param listener
   */
  void setListener(PartDataListener<K, V> listener);

  /**
   * keep part content and will auto make decision to call different put method in
   * {@link PartDataPool}
   * 
   * @param key
   * @param value
   * @return success or not
   */
  boolean put(K key, V value);

  /**
   * remove keep part content
   * 
   * @param key
   */
  void remove(K key);

}
