CREATE SCHEMA IF NOT EXISTS `test_spring_boot` DEFAULT CHARACTER SET utf8 ;
use `test_spring_boot`;

CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` bigint(20) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `user_pass` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';


CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` bigint(20) NOT NULL,
  `role_name` varchar(45) NOT NULL,
  `role_desc` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name_UNIQUE` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限表';


CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_idx` (`user_id`),
  KEY `fk_role_idx` (`role_id`),
  CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DELETE FROM sys_role WHERE id >=1 and id<=4;
-- INSERT ROLES
insert into sys_role(id,role_name,role_desc,create_time)
select 1,'ROLE_ADMIN','administrator',NOW()
UNION
select 2,'ROLE_CONTRIBUTOR','log configuer',NOW()
UNION
select 3,'ROLE_VIEWER','viewer',NOW()
UNION
select 4,'ROLE_ANONYMOUS','anyone',NOW();

DELETE FROM sys_user WHERE id=1;
insert sys_user(id,user_name,user_pass,create_time) values(1,'admin','$2a$10$e0TKuiJs2Mrtd64gYrk0luahxwknB8jvB8RrP/qIPLJqXF0bBGYXi',NOW());

DELETE FROM sys_user_role WHERE user_id=1;
insert into sys_user_role(id,user_id,role_id)
select UUID_SHORT(),1,1
UNION
select UUID_SHORT(),1,2
UNION
select UUID_SHORT(),1,3