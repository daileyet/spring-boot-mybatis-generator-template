/**
 * 
 */
package com.openthinks.demo.sprj.web.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @author dailey.dai@openthinks.com
 *
 */
public final class SessionUser<T> extends User {
	private static final long serialVersionUID = 4679787663363969766L;

	private final T payload;

	public SessionUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
			final T payload) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.payload = payload;
	}

	public SessionUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
			final T payload) {
		super(username, password, authorities);
		this.payload = payload;
	}

	public T getPayload() {
		return payload;
	}
}
