/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : shangdihuayu

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-12-06 13:50:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for album
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `album_id` char(5) NOT NULL,
  `album_name` varchar(50) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`album_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of album
-- ----------------------------

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `file_id` varchar(50) NOT NULL,
  `module` varchar(20) NOT NULL,
  `album_id` varchar(50) DEFAULT NULL COMMENT '专辑id',
  `voice_id` varchar(50) DEFAULT NULL,
  `type` char(255) DEFAULT NULL COMMENT '0 图片 1音频',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(50) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `user_pwd` varchar(50) NOT NULL,
  `gender` char(1) DEFAULT NULL,
  `tel` char(11) DEFAULT NULL,
  `weichat` varchar(20) DEFAULT NULL,
  `qq` varchar(20) DEFAULT NULL,
  `last_login_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `state` char(255) DEFAULT NULL COMMENT '0未在线 1在线',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('root', 'root', '123456', '1', '', null, null, null, null);

-- ----------------------------
-- Table structure for voice
-- ----------------------------
DROP TABLE IF EXISTS `voice`;
CREATE TABLE `voice` (
  `voice_id` varchar(50) NOT NULL,
  `voice_name` varchar(50) NOT NULL,
  `create_date` date DEFAULT NULL,
  `album_id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `play_count` int(8) DEFAULT NULL,
  PRIMARY KEY (`voice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of voice
-- ----------------------------
