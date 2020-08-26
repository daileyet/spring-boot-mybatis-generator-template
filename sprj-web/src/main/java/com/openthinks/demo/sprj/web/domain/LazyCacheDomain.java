package com.openthinks.demo.sprj.web.domain;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 懒加载并缓存
 * 
 * @author dailey.dai@openthinks.com
 *
 */
public interface LazyCacheDomain {

	/**
	 * 重置当前缓存成员,一般置为 null
	 */
	public default void reset() {
		final Object _this = this;
		Field[] fields = this.getClass().getDeclaredFields();
		Arrays.asList(fields).parallelStream().filter(e -> e.getDeclaredAnnotation(Cache.class) != null).forEach(e -> {
			try {
				e.setAccessible(true);
				e.set(_this, null);
			} catch (SecurityException | IllegalArgumentException | IllegalAccessException e1) {
				//TODO LOG
				e1.printStackTrace();
			}
		});
	}

	/**
	 * 
	 * lazy tag for get method
	 */
	@Target({ ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public @interface Lazy {

	}

	/**
	 * 
	 * Cache tag for get method
	 */
	@Target({ ElementType.METHOD, ElementType.FIELD })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public @interface Cache {

	}
}
