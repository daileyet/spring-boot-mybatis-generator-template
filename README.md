# Template Server Project

## sprj-common

Server common project include 
* mybatis generator plugin
* mysql connector
* app common configuration
* utilties
* sample db schema
* sharding-sphere jdbc

## sprj-links

Server backend project which will handle client TCP/UDP connection, it will include
* spring-boot start
* netty support
* support TCP channel and JSON message received base on netty
* include `sprj-common`

## sprj-web

Server web protal which serve HTTP request
* spring-boot start
* tomcat or undertow
* use thymeleaf, spring-security
* include `sprj-common`