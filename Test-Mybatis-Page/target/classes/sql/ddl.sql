DROP DATABASE IF EXISTS mybatis_page;
CREATE DATABASE mybatis_page DEFAULT CHARACTER SET utf8;
USE mybatis_page;

##创建用户表
CREATE TABLE user (
   id   INT AUTO_INCREMENT PRIMARY KEY,
   username VARCHAR(30),
   password  VARCHAR(32)

)ENGINE=INNODB;