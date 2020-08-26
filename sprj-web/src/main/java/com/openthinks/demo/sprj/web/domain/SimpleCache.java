/**
 * 
 */
package com.openthinks.demo.sprj.web.domain;

import java.time.Duration;

import com.openthinks.libs.utilities.DateUtils;

/**
 * @author dailey.dai@openthinks.com
 *
 */
public abstract class SimpleCache<T> {
	/**
	 * payload
	 */
	protected final T payload;
	/**
	 * create time
	 */
	private final long createTime;

	public SimpleCache(T payload) {
		this(payload, DateUtils.currentTimeMillis());
	}

	public SimpleCache(T payload, long createTime) {
		super();
		this.payload = payload;
		this.createTime = createTime;
	}

	public T getPayload() {
		return payload;
	}

	/**
	 * is survival or not
	 * 
	 * @return
	 */
	public boolean isSurvival() {
		Duration temp = DateUtils.between(createTime, DateUtils.currentTimeMillis()).abs();
		return maxLiveTime().compareTo(temp) > 0;
	}

	/**
	 * max survival duration
	 * 
	 * @return
	 */
	public abstract Duration maxLiveTime();

	public static final <E> SimpleCache<E> create(E payload, final Duration duration) {
		return new SimpleCache<E>(payload) {
			@Override
			public Duration maxLiveTime() {
				return duration;
			}
		};
	}
}
