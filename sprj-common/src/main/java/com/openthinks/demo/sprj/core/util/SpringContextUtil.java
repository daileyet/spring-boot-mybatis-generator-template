/**
 * 
 */
package com.openthinks.demo.sprj.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import net.sourceforge.springframework.SpringContext;

/**
 * @author dailey.dai@openthinks.com
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext(org
	 * .springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringContextUtil.applicationContext == null) {
			SpringContextUtil.applicationContext = applicationContext;
			new SpringContext().setApplicationContext(applicationContext);
		}
	}

	public final static ApplicationContext getContext() {
		return applicationContext;
	}

	public final static <T> T service(Class<T> serviceClazz) {
		return getContext().getBean(serviceClazz);
	}
}
