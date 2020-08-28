/**
 * 
 */
package com.openthinks.demo.sprj.links.core;

import com.openthinks.demo.sprj.links.core.parts.PartDataListener;
import com.openthinks.demo.sprj.links.core.parts.PartDataPoolManager;
import com.openthinks.demo.sprj.links.core.parts.PartType;
import com.openthinks.demo.sprj.links.core.parts.PartTypeIdentity;
import com.openthinks.demo.sprj.links.core.parts.SimplePartDataPoolManager;
import com.openthinks.demo.sprj.links.core.parts.Whole;

/**
 * special {@link PartDataPoolManager} which part content format is JSON string
 * 
 * @author dailey.dai@openthinks.com
 *
 */
public abstract class JSONPartPoolManagerPoxy<K> implements PartDataPoolManager<K, String> {
  private final PartDataListener<K, String> LISTENER = (data, type) -> {
    processWholeData(data, type);
  };
  private final PartDataPoolManager<K, String> poolManager =
      new SimplePartDataPoolManager<>(IDENTITY, LISTENER);

  @Override
  public void setListener(PartDataListener<K, String> listener) {
    poolManager.setListener(listener);
  }

  @Override
  public boolean put(K key, String value) {
    return poolManager.put(key, value);
  }

  @Override
  public void remove(K key) {
    poolManager.remove(key);
  }

  /**
   * action when generated whole message data
   * 
   * @param data complete whole received data
   * @param type {@link PartType}
   */
  protected abstract void processWholeData(Whole<K, String> data, PartType type);

  /**
   * {@link PartTypeIdentity} for JSON format content
   */
  protected static final PartTypeIdentity<String> IDENTITY = (content) -> {
    if (content == null)
      return PartType.NONE;
    int startIndex = content.indexOf("{"), endIndex = content.indexOf("}");
    if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
      return PartType.WHOLE;
    } else if (startIndex != -1 && endIndex == -1) {
      return PartType.FIRST;
    } else if (startIndex == -1 && endIndex != -1) {
      return PartType.LAST;
    } else if (startIndex == -1 && endIndex == -1) {
      return PartType.MIDDLE;
    }
    return PartType.NONE;
  };
}
