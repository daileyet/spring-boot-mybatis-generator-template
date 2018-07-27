package com.openthinks.demo.springbootmybatisgeneratortemplate.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * ClassName: AppConfig </br>
 */
// @PropertySource("classpath:conf/components.properties")
@PropertySource("classpath:application.properties")
@Component
public class AppConfig {

	@Value("${app.version}")
	private String appVersion;
	//////////////////////////////////////////
	@Value("${spring.jackson.date-format}")
	private String dateFormatPattern;

	public String getAppVersion() {
		return appVersion;
	}
	
	public String getDateFormatPattern() {
		return dateFormatPattern;
	}

}
