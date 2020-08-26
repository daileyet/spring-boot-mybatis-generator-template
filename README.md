# spring-boot-mybatis-generator-template
Springboot with mybatis and mybatis generator
https://start.spring.io/

## Features
1.	Spring boot web, json support
2.  Spring boot mybatis
3.  Mybatis generator plugins
4.  JSON converter and property configurable 
5.	add ant build for shell script and install zip

## Convert jar to war

### pom.xml
```xml
<!-- <packaging>jar</packaging> -->
	<packaging>war</packaging>
```

```xml
	<!-- set scope provided-->
	<!-- package war -->
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-tomcat</artifactId>
		<scope>provided</scope>
	</dependency>
```

### SpringBootServletInitializer
```java
public class SpringBootMybatisGeneratorTemplateApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisGeneratorTemplateApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootMybatisGeneratorTemplateApplication.class);
	}
}


```
