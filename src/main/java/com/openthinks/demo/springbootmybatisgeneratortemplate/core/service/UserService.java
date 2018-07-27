/**
 * 
 */
package com.openthinks.demo.springbootmybatisgeneratortemplate.core.service;

import java.util.List;

import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.User;

/**
 * @author dailey.yet@outlook.com
 *
 */
public interface UserService {

	public boolean saveUser(User record);

	public List<User> selectAll();
	
}
