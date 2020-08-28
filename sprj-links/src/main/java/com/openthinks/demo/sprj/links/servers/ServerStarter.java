/**
 * 
 */
package com.openthinks.demo.sprj.links.servers;

/**
 * Server starter interface
 * 
 * @author dailey.dai@openthinks.com
 *
 */
public interface ServerStarter {

  public void start() throws Exception;

  public void stop() throws Exception;

  public boolean isRunning();

  /**
   * show enable or disable; if disable then current instance will not call {@link #start()}
   * 
   * @return true or false
   */
  public boolean isActive();
}
