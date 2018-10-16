package com.openthinks.demo.springbootmybatisgeneratortemplate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.openthinks.demo.springbootmybatisgeneratortemplate.core.service.SystemUserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringBootMybatisGeneratorTemplateApplication.class)
public class SpringBootMybatisGeneratorTemplateApplicationTests {

	@Autowired
	SystemUserService userService;

	@Test
	public void testCreateUser() {
	}

}
