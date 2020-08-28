/**
 * 
 */
package com.openthinks.demo.sprj.links.core.parts;

/**
 * Listener for part content, used in {@link PartDataPoolManager}
 * 
 * @author dailey.dai@openthinks.com
 *
 */
public interface PartDataListener<K, V> {

  void onWholeData(Whole<K, V> data, PartType type);

}
