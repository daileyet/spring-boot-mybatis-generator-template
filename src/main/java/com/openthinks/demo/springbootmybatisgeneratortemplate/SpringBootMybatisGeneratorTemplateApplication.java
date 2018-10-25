package com.openthinks.demo.springbootmybatisgeneratortemplate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@MapperScan("com.openthinks.demo.springbootmybatisgeneratortemplate.core.mapper")
@SpringBootApplication
@EnableScheduling
public class SpringBootMybatisGeneratorTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisGeneratorTemplateApplication.class, args);
	}
}
