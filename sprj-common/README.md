# sprj-common

## mybatis generate

Configure mybatis generator plugin used database connection info as below:

`pom.xml`

```xml
<profile>
	<id>dev</id>
	<activation>
		<activeByDefault>true</activeByDefault>
	</activation>
	<properties>
		<!-- only used for mybatis generator -->
		<jdbc.driverClassName>com.mysql.jdbc.Driver</jdbc.driverClassName>
		<jdbc.url>jdbc:mysql://172.17.0.6:3306/test_spring_boot?characterEncoding=utf8</jdbc.url>
		<jdbc.username>tester</jdbc.username>
		<jdbc.password>test123</jdbc.password>
	</properties>
</profile>			
``` 

```shell
# generate mapper,model

mvn generate-sources -Dmybatis.generate.skip=false
``` 