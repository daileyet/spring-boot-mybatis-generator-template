package com.openthinks.demo.springbootmybatisgeneratortemplate.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * ClassName: SchedulerConfig </br>
 */
@Configuration
public class SchedulerConfig implements SchedulingConfigurer {
  private final int POOL_SIZE = 10;

  /**
   * @see org.springframework.scheduling.annotation.SchedulingConfigurer#configureTasks(org.springframework.scheduling.config.ScheduledTaskRegistrar)
   */
  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
    threadPoolTaskScheduler.setThreadNamePrefix("scheduled-task-pool-");
    threadPoolTaskScheduler.initialize();
    taskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
  }

}
