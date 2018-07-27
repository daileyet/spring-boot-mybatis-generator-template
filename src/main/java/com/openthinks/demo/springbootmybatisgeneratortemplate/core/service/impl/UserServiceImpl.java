package com.openthinks.demo.springbootmybatisgeneratortemplate.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openthinks.demo.springbootmybatisgeneratortemplate.core.mapper.UserMapper;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.User;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.UserExample;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	
	@Override
	public boolean saveUser(User record) {
		if(record.getId()!=null) {
			return userMapper.updateByPrimaryKeySelective(record)>0;
		}
		return userMapper.insertSelective(record)>0;
	}
	
	@Override
	public List<User> selectAll() {
		return userMapper.selectByExample(new UserExample());
	}

}
