package com.openthinks.demo.sprj.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import com.openthinks.demo.sprj.core.util.ConfigDesc;

/**
 * ClassName: AppConfig </br>
 */
@PropertySource("classpath:application.properties")
@Component
public class AppConfig {
  public static final String DEFAULT_STATIC_URL = "#SAME";
  //////////////////////////////////////////
  // WEB APP
  @Value("${app.version}")
  private String appVersion;
  @Value("${app.static.url:" + DEFAULT_STATIC_URL + "}")
  private String staticUrl;
  @Value("${app.date-format}")
  private String appDatePattern;
  @Value("${app.date-format-short}")
  private String appDateShortPattern;
  @ConfigDesc("邮件服务器的Host地址")
  @Value("${app.email.smtp.host}")
  private String emailSMTPHost;
  @ConfigDesc("邮件服务器的端口号")
  @Value("${app.email.smtp.port}")
  private String emailSMTPPort;
  @ConfigDesc("发邮件是否开启SSL")
  @Value("${app.email.smtp.ssl:false}")
  private Boolean emailSMTPSSL;
  @ConfigDesc("登陆邮件服务器的账号")
  @Value("${app.email.smtp.account}")
  private String emailSMTPAccount;
  @ConfigDesc("登陆邮件服务器的密码")
  @Value("${app.email.smtp.pass}")
  private String emailSMTPPass;
  @Value("${app.admin.contacts}")
  private String adminContacts;
  //////////////////////////////////////////
  // CONNECTOR

  /////////////////////////////////////////
  @Value("${spring.jackson.date-format}")
  private String dateFormatPattern;

  public String getAppVersion() {
    return appVersion;
  }

  public String getJSONDateFormatPattern() {
    return dateFormatPattern;
  }

  public String getStaticUrl() {
    return staticUrl;
  }

  public String getAppDatePattern() {
    return appDatePattern;
  }

  public String getAppDateShortPattern() {
    return appDateShortPattern;
  }

  public String getEmailSMTPHost() {
    return emailSMTPHost;
  }

  public String getEmailSMTPAccount() {
    return emailSMTPAccount;
  }

  public String getEmailSMTPPass() {
    return emailSMTPPass;
  }

  public boolean getEmailSMTPSSL() {
    return emailSMTPSSL;
  }

  public String getEmailSMTPPort() {
    return emailSMTPPort;
  }

  public String getAdminContacts() {
    return adminContacts;
  }

  @Override
  public String toString() {
    return "AppConfig [appVersion=" + appVersion + ", staticUrl=" + staticUrl + ", appDatePattern="
        + appDatePattern + ", appDateShortPattern=" + appDateShortPattern + ", emailSMTPHost="
        + emailSMTPHost + ", emailSMTPPort=" + emailSMTPPort + ", emailSMTPSSL=" + emailSMTPSSL
        + ", emailSMTPAccount=" + emailSMTPAccount + ", emailSMTPPass=" + emailSMTPPass
        + ", adminContacts=" + adminContacts + ", dateFormatPattern=" + dateFormatPattern + "]";
  }
}
