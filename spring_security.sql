/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : spring_security

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-11-20 21:07:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `t_permissionid` int(11) NOT NULL AUTO_INCREMENT,
  `t_permissionname` varchar(255) NOT NULL,
  `t_permission_description` varchar(255) NOT NULL,
  `t_url` varchar(255) NOT NULL,
  PRIMARY KEY (`t_permissionid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', 'permissionA', 'permissionA', '/test/permissionA');
INSERT INTO `t_permission` VALUES ('2', 'permissionB', 'permissionB', '/permissionB');
INSERT INTO `t_permission` VALUES ('3', 'permissionC', 'permissionC', '/permissionC');
INSERT INTO `t_permission` VALUES ('4', 'permissionD', 'permissionD', '/permissionE');
INSERT INTO `t_permission` VALUES ('5', 'permissionE', 'permissionE', '/permissionD');
INSERT INTO `t_permission` VALUES ('6', 'permissionF', 'permissionF', '/permissionF');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `t_roleid` int(11) NOT NULL AUTO_INCREMENT,
  `t_rolename` varchar(255) NOT NULL,
  `t_role_description` varchar(255) NOT NULL,
  PRIMARY KEY (`t_roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'guest', 'normal guest');
INSERT INTO `t_role` VALUES ('2', 'user', 'nomarl user');
INSERT INTO `t_role` VALUES ('3', 'admin', 'admin');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `t_roleid` int(11) NOT NULL,
  `t_permissionid` int(11) NOT NULL,
  PRIMARY KEY (`t_roleid`,`t_permissionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('1', '1');
INSERT INTO `t_role_permission` VALUES ('2', '1');
INSERT INTO `t_role_permission` VALUES ('2', '2');
INSERT INTO `t_role_permission` VALUES ('2', '4');
INSERT INTO `t_role_permission` VALUES ('3', '1');
INSERT INTO `t_role_permission` VALUES ('3', '2');
INSERT INTO `t_role_permission` VALUES ('3', '3');
INSERT INTO `t_role_permission` VALUES ('3', '4');
INSERT INTO `t_role_permission` VALUES ('3', '5');
INSERT INTO `t_role_permission` VALUES ('3', '6');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `t_userid` int(11) NOT NULL AUTO_INCREMENT,
  `t_loginname` varchar(255) NOT NULL,
  `t_password` varchar(255) NOT NULL,
  `t_user_description` varchar(255) NOT NULL,
  `t_roleid` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`t_userid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'zhangsan', '$2a$10$HScullo3k1aCQFT54DI7/O1WVCmu1M0Je7f4odR2ngKpUPYYL4cbq', 'student', '1');
INSERT INTO `t_user` VALUES ('2', 'lisi', '$2a$10$HScullo3k1aCQFT54DI7/O1WVCmu1M0Je7f4odR2ngKpUPYYL4cbq', 'teacher', '2');
INSERT INTO `t_user` VALUES ('3', 'admin', '$2a$10$v.dkR90NNln/wpJzZcnh7eal0kq15V7G0IxiHk9wZCa1ASMC7D.gq', 'admin', '3');
