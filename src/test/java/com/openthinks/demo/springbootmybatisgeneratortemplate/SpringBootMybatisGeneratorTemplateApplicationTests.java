package com.openthinks.demo.springbootmybatisgeneratortemplate;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.openthinks.demo.springbootmybatisgeneratortemplate.core.model.User;
import com.openthinks.demo.springbootmybatisgeneratortemplate.core.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringBootMybatisGeneratorTemplateApplication.class)
public class SpringBootMybatisGeneratorTemplateApplicationTests {

	@Autowired
	UserService userService;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testCreateUser() {
		User user = new User.Builder().name("test").birthday(new Date()).address("China, Suzhou").build();
		boolean isSuccess = userService.saveUser(user);
		Assert.assertEquals(true, isSuccess);
	}

}
