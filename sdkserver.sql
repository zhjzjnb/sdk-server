/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50635
 Source Host           : localhost
 Source Database       : sdkserver

 Target Server Type    : MySQL
 Target Server Version : 50635
 File Encoding         : utf-8

 Date: 01/22/2018 22:41:04 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `iuser_name` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `admin`
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES ('1', '1', 'c4ca4238a0b923820dcc509a6f75849b');
COMMIT;

-- ----------------------------
--  Table structure for `game`
-- ----------------------------
DROP TABLE IF EXISTS `game`;
CREATE TABLE `game` (
  `app_id` int(11) NOT NULL AUTO_INCREMENT,
  `app_key` varchar(255) NOT NULL,
  `app_secret` varchar(255) NOT NULL,
  `public_key` varchar(1024) NOT NULL,
  `private_key` varchar(1024) NOT NULL,
  `create_time` datetime NOT NULL,
  `game_name` varchar(255) NOT NULL,
  `pay_callback` varchar(255) NOT NULL,
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `game`
-- ----------------------------
BEGIN;
INSERT INTO `game` VALUES ('1', 'asd', '111', 'dasdasd', 'ddd', '2018-01-17 22:10:54', '你好', 'http://1.2.2/action.do');
COMMIT;

-- ----------------------------
--  Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `platform` varchar(255) NOT NULL,
  `root` smallint(6) NOT NULL,
  `system_version` varchar(255) NOT NULL,
  `system_name` varchar(255) NOT NULL,
  `device_model` varchar(255) NOT NULL,
  `device_name` varchar(255) NOT NULL,
  `register_time` datetime NOT NULL,
  `register_place` varchar(255) NOT NULL,
  PRIMARY KEY (`uid`,`user_name`),
  UNIQUE KEY `iuser` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_info`
-- ----------------------------
BEGIN;
INSERT INTO `user_info` VALUES ('4', 'username:1', 'pwd:1', '0', '0', '0', '', '', '', '0000-00-00 00:00:00', ''), ('5', '1233', '', '0', '0', '0', '', '', '', '0000-00-00 00:00:00', ''), ('6', 'username:23', 'pwd:2', 'ios', '0', '11.0.1', 'iOS', 'iPhone', '张浩杰的MacBook Pro', '2018-01-16 22:35:59', 'todo'), ('9', 'username:22', 'pwd:2', 'ios', '0', '11.0.1', 'iOS', 'iPhone', '张浩杰的MacBook Pro', '2018-01-16 22:42:28', 'todo'), ('10', 'username:2', 'pwd:2', 'ios', '0', '11.0.1', 'iOS', 'iPhone', '张浩杰的MacBook Pro', '2018-01-16 23:11:22', 'todo');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
