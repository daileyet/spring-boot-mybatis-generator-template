/**
 * 
 */
package com.openthinks.demo.sprj.links.core.parts;

import com.openthinks.libs.utilities.Result;

/**
 * Default implementation of {@link PartDataPoolManager}
 * 
 * @author dailey.dai@openthinks.com
 *
 */
public final class SimplePartDataPoolManager<K, V> implements PartDataPoolManager<K, V> {
  private final PartTypeIdentity<V> typeIdentity;
  private PartDataListener<K, V> listener;
  private final PartDataPool<K, V> pool = new PartDataPool<>();
  private final PartDataListenerHolder listenrHolder = new PartDataListenerHolder();

  public SimplePartDataPoolManager(PartTypeIdentity<V> typeIdentity) {
    super();
    this.typeIdentity = typeIdentity;
  }

  public SimplePartDataPoolManager(PartTypeIdentity<V> typeIdentity,
      PartDataListener<K, V> listener) {
    super();
    this.typeIdentity = typeIdentity;
    setListener(listener);
  }

  @Override
  public void setListener(PartDataListener<K, V> listener) {
    this.listener = listener;
  }

  @Override
  public boolean put(K key, V value) {
    PartType type = typeIdentity.judge(value);
    Result<Boolean> result = new Result<>(false);
    switch (type) {
      case FIRST:
        pool.firstPut(key, value);
        result.set(true);
        break;
      case MIDDLE:
        result.set(pool.middlePut(key, value));
        break;
      case LAST:
        pool.lastPut(key, value).ifPresent(w -> {
          result.set(true);
          listenrHolder.onWholeData(w, PartType.LAST);
        });
        break;
      case WHOLE:
        result.set(true);
        listenrHolder.onWholeData(pool.wholePut(key, value), PartType.WHOLE);
        break;
      default:
        break;
    }
    return result.get();
  }

  @Override
  public void remove(K key) {
    pool.remove(key);
  }

  class PartDataListenerHolder implements PartDataListener<K, V> {

    @Override
    public void onWholeData(Whole<K, V> data, PartType type) {
      if (listener != null)
        listener.onWholeData(data, type);
    }
  }

}
