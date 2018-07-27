package com.openthinks.demo.springbootmybatisgeneratortemplate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.openthinks.demo.springbootmybatisgeneratortemplate.core.mapper")
@SpringBootApplication
public class SpringBootMybatisGeneratorTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisGeneratorTemplateApplication.class, args);
	}
}
