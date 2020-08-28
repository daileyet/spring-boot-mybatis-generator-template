/**
 * 
 */
package com.openthinks.demo.sprj.links.core.msg;

import java.io.Serializable;

/**
 * @author dailey.dai@openthinks.com
 *
 */
public interface Message {
  
  public Serializable id();

  public Object payload();

}
