/**
 * 
 */
package com.openthinks.demo.sprj.web.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author dailey.dai@openthinks.com
 *
 */
@Component
public class TestJob {
  private static final Logger LOGGER = LoggerFactory.getLogger(TestJob.class);

  @Scheduled(initialDelay = 60000, fixedDelay = 60000)
  public void jobExecute() {
    LOGGER.warn("JOB {} has been executed.", this.getClass().getName());
  }
}
