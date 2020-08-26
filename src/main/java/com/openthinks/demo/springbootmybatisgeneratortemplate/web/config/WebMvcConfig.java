package com.openthinks.demo.springbootmybatisgeneratortemplate.web.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.openthinks.demo.springbootmybatisgeneratortemplate.core.AppConfig;

/**
 * ClassName: WebMvcConfig </br>
 * Function: 请求拦截器. </br>
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  @Autowired
  AppConfig appConfig;

  /**
   * ClassName: CommonAttrInterceptor </br>
   * date: Aug 24, 2018 3:58:09 PM </br>
   */
  public final class CommonAttrInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
      request.setAttribute("appVersion", appConfig.getAppVersion());
      return HandlerInterceptor.super.preHandle(request, response, handler);
    }
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new CommonAttrInterceptor()).excludePathPatterns("/api/**", "/css/**",
        "/img/**", "/lib/**", "/js/**", "/favicon.ico");
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("forward:/index.html");
  }

}
