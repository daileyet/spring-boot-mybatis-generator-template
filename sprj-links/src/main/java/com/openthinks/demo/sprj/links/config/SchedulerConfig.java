package com.openthinks.demo.sprj.links.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.openthinks.demo.sprj.core.AppConfig;

/**
 * ClassName: SchedulerConfig </br>
 */
@Configuration
public class SchedulerConfig implements SchedulingConfigurer {
  @Autowired
  AppConfig appConfig;

  @Bean
  public TaskScheduler taskScheduler() {
    ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    threadPoolTaskScheduler.setPoolSize(appConfig.getConnSchedulerThreadCount());
    threadPoolTaskScheduler.setThreadNamePrefix("scheduled-task-pool-");
    threadPoolTaskScheduler.initialize();
    return threadPoolTaskScheduler;
  }

  /**
   * @see org.springframework.scheduling.annotation.SchedulingConfigurer#configureTasks(org.springframework.scheduling.config.ScheduledTaskRegistrar)
   */
  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    taskRegistrar.setTaskScheduler(taskScheduler());
  }

}
