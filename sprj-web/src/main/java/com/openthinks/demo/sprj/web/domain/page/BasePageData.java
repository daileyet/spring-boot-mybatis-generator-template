/**
 * 
 */
package com.openthinks.demo.sprj.web.domain.page;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.openthinks.demo.sprj.web.domain.LazyCacheDomain;

/**
 * Basic Page Data
 * 
 * @author dailey.dai@openthinks.com
 *
 */
public abstract class BasePageData implements LazyCacheDomain {

  protected final Map<String, Object> additionMap = new ConcurrentHashMap<>();

  public Map<String, Object> getAdditionMap() {
    return additionMap;
  }

  public void putAddition(String key, Object value) {
    additionMap.put(key, value);
  }

  public void clearAddition() {
    additionMap.clear();
  }

}
