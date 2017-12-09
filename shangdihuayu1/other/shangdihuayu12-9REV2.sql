/*
Navicat MySQL Data Transfer

Source Server         : DB_INCHRIST
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : shangdihuayu

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-12-09 20:23:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `album`
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `album_id` char(50) NOT NULL,
  `album_name` varchar(50) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `parent_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`album_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of album
-- ----------------------------
INSERT INTO `album` VALUES ('dujinglingxiu', '读经灵修', '2017-12-08 10:20:42', '手机打开了就放空手机的反馈', null);
INSERT INTO `album` VALUES ('hahaha', '哈哈哈', '2017-12-09 19:06:22', '', 'junzundejingbai');
INSERT INTO `album` VALUES ('jianzhengfenxiang', '见证分享', '2017-12-30 12:28:32', null, null);
INSERT INTO `album` VALUES ('junzundejingbai', '君尊的敬拜', '2017-12-29 11:01:14', '上看到了房间昆仑山解放', null);
INSERT INTO `album` VALUES ('shandidehuochai', '上帝的火柴', '2017-12-29 12:26:54', null, null);
INSERT INTO `album` VALUES ('sibuzhenzhengchuan', '司布真正传', '2017-12-09 19:08:14', '斯蒂芬斯蒂芬斯蒂芬斯蒂芬', 'tebiejiemu');
INSERT INTO `album` VALUES ('tebiejiemu', '特别节目', '2017-12-23 12:27:54', null, null);
INSERT INTO `album` VALUES ('yandushengjing', '研读圣经', '2017-12-09 12:26:33', null, null);

-- ----------------------------
-- Table structure for `file`
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `file_id` varchar(50) NOT NULL,
  `module` varchar(50) NOT NULL,
  `album_id` varchar(50) DEFAULT NULL COMMENT '专辑id',
  `voice_id` varchar(50) DEFAULT NULL,
  `type` char(255) DEFAULT NULL COMMENT '0 图片 1音频',
  `file_name` varchar(50) DEFAULT NULL,
  `file_extension` varchar(10) DEFAULT NULL,
  `file_size` varchar(255) DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL,
  `file_content_type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('02d4118d-dbfd-11e7-b06d-c45444f5ce98', 'shangdihuayu', '哈利路亚', 'piantouyinle.mp3', 'image', '9851590207558333.jpg', '11', '11', '2017-12-08 17:49:01', '11');
INSERT INTO `file` VALUES ('02d535b3-dbfd-11e7-b06d-c45444f5ce98', 'shangdihuayu', '哈利路亚', 'piantouyinle.mp3', 'multiMedia', '片头音乐.mp3', '11', '11', '2017-12-08 17:49:01', '11');
INSERT INTO `file` VALUES ('0a516f7d-dc9c-11e7-9c21-c45444f5ce98', '君尊的敬拜', 'junzundejingbai', 'yedibaihegegetadeai.mp3', 'image', '9851590207558333.jpg', '11', '11', '2017-12-09 12:47:20', '11');
INSERT INTO `file` VALUES ('0a560272-dc9c-11e7-9c21-c45444f5ce98', '君尊的敬拜', 'junzundejingbai', 'yedibaihegegetadeai.mp3', 'multiMedia', '野地百合 - 各各他的爱.mp3', '11', '11', '2017-12-09 12:47:20', '11');
INSERT INTO `file` VALUES ('3fe3d1ec-dc96-11e7-9c21-c45444f5ce98', 'junzundejingbai', 'junzundejingbai', 'liuruoyingwozhisheiguanzhemingtian.mp3', 'image', '858656148704588183.jpg', '11', '11', '2017-12-09 12:05:53', '11');
INSERT INTO `file` VALUES ('3fe4c93b-dc96-11e7-9c21-c45444f5ce98', 'junzundejingbai', 'junzundejingbai', 'liuruoyingwozhisheiguanzhemingtian.mp3', 'multiMedia', '刘若英 - 我知谁管着明天.mp3', '11', '11', '2017-12-09 12:05:53', '11');
INSERT INTO `file` VALUES ('41b3be73-dcd1-11e7-9c21-c45444f5ce98', '特别节目/司布真正传', 'sibuzhenzhengchuan', '11', 'image', '858656148704588183.jpg', '11', '11', '2017-12-09 19:08:14', '11');
INSERT INTO `file` VALUES ('42592b28-dc93-11e7-9c21-c45444f5ce98', 'shangdihuayu', 'junzundejingbai', 'huangyunlingzhongxingminanjingdeduihua.mp3', 'image', '716317944752518787.jpg', '11', '11', '2017-12-09 11:44:28', '11');
INSERT INTO `file` VALUES ('425a54cd-dc93-11e7-9c21-c45444f5ce98', 'shangdihuayu', 'junzundejingbai', 'huangyunlingzhongxingminanjingdeduihua.mp3', 'multiMedia', '黄韵玲、钟兴民 - 安静的对话.mp3', '11', '11', '2017-12-09 11:44:28', '11');
INSERT INTO `file` VALUES ('44173e8b-dcdb-11e7-9c21-c45444f5ce98', '特别节目/司布真正传', 'sibuzhenzhengchuan', 'diyiridairensheng.mp3', 'image', '858656148704588183.jpg', '11', '11', '2017-12-09 20:19:53', '11');
INSERT INTO `file` VALUES ('44188eaa-dcdb-11e7-9c21-c45444f5ce98', '特别节目/司布真正传', 'sibuzhenzhengchuan', 'diyiridairensheng.mp3', 'multiMedia', '第一日带人声.mp3', '11', '11', '2017-12-09 20:19:53', '11');
INSERT INTO `file` VALUES ('8d9ab239-dbf6-11e7-b06d-c45444f5ce98', 'shangdihuayu', '我是所属专辑', 'shimingnew.mp3', 'image', '19783340281952597.jpg', '11', '11', '2017-12-08 17:02:48', '11');
INSERT INTO `file` VALUES ('8d9bf29c-dbf6-11e7-b06d-c45444f5ce98', 'shangdihuayu', '我是所属专辑', 'shimingnew.mp3', 'multiMedia', '使命new.mp3', '11', '11', '2017-12-08 17:02:48', '11');
INSERT INTO `file` VALUES ('90eb3bd6-dcb7-11e7-9c21-c45444f5ce98', '见证分享', 'jianzhengfenxiang', 'liuxuanyuhuidaonishenbian.mp3', 'image', 'TIM截图20170901185325.png', '11', '11', '2017-12-09 16:04:20', '11');
INSERT INTO `file` VALUES ('90ed024b-dcb7-11e7-9c21-c45444f5ce98', '见证分享', 'jianzhengfenxiang', 'liuxuanyuhuidaonishenbian.mp3', 'multiMedia', '刘炫宇 - 回到你身边.mp3', '11', '11', '2017-12-09 16:04:20', '11');
INSERT INTO `file` VALUES ('b9eb24d7-dc97-11e7-9c21-c45444f5ce98', 'junzundejingbai', 'junzundejingbai', '32630yubei.mp3', 'image', '716317944752518787.jpg', '11', '11', '2017-12-09 12:16:27', '11');
INSERT INTO `file` VALUES ('b9ee8854-dc97-11e7-9c21-c45444f5ce98', 'junzundejingbai', 'junzundejingbai', '32630yubei.mp3', 'multiMedia', '32630_预备.mp3', '11', '11', '2017-12-09 12:16:27', '11');
INSERT INTO `file` VALUES ('e8151ef1-dc99-11e7-9c21-c45444f5ce98', '', 'shandidehuochai', 'wuqixianyidingshini.mp3', 'image', '315109880449753061.jpg', '11', '11', '2017-12-09 12:32:04', '11');
INSERT INTO `file` VALUES ('e81681a6-dc99-11e7-9c21-c45444f5ce98', '', 'shandidehuochai', 'wuqixianyidingshini.mp3', 'multiMedia', '巫启贤 - 一定是你.mp3', '11', '11', '2017-12-09 12:32:04', '11');
INSERT INTO `file` VALUES ('feda04fd-dcd0-11e7-9c21-c45444f5ce98', '君尊的敬拜/哈哈哈', 'hahaha', '11', 'image', '716317944752518787.jpg', '11', '11', '2017-12-09 19:06:22', '11');

-- ----------------------------
-- Table structure for `user`
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
INSERT INTO `user` VALUES ('anna', '安娜', '123456', '0', null, null, null, '2017-12-08 19:45:53', null);
INSERT INTO `user` VALUES ('caizhi', '才智', '123456', '1', null, null, null, '2017-12-08 19:45:48', null);
INSERT INTO `user` VALUES ('dabao', '大宝', '123456', '1', null, null, null, null, null);
INSERT INTO `user` VALUES ('daoyou', '道友', '123456', '1', '123', '134234', '234234', '2017-12-08 19:45:59', '1');
INSERT INTO `user` VALUES ('jiachang', '家昌', '123456', '1', null, null, null, '2017-12-08 19:46:18', null);
INSERT INTO `user` VALUES ('mashuang', '小爽', '123456', '0', '234234', '234234', '23423423', '2017-12-08 19:46:02', '0');
INSERT INTO `user` VALUES ('root', 'root', '123456', '1', '', null, null, null, null);
INSERT INTO `user` VALUES ('wenwang', '文望', '123456', '1', null, null, null, '2017-12-08 19:39:42', null);
INSERT INTO `user` VALUES ('xiaozhen', '小珍', '123456', '0', '23423423', 'w42342342', '34234', '2017-12-08 19:37:50', null);

-- ----------------------------
-- Table structure for `voice`
-- ----------------------------
DROP TABLE IF EXISTS `voice`;
CREATE TABLE `voice` (
  `voice_id` varchar(50) NOT NULL,
  `voice_name` varchar(50) NOT NULL,
  `create_date` date DEFAULT NULL,
  `album_id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `play_count` int(8) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`voice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of voice
-- ----------------------------
INSERT INTO `voice` VALUES ('32630yubei.mp3', '32630_预备.mp3', '2017-12-09', 'junzundejingbai', 'dabao', '0', '哈利路亚赞美主！~~~');
INSERT INTO `voice` VALUES ('diyiridairensheng.mp3', '第一日带人声.mp3', '2017-12-09', 'sibuzhenzhengchuan', 'xiaozhen', '0', '古典风格的');
INSERT INTO `voice` VALUES ('huangyunlingzhongxingminanjingdeduihua.mp3', '黄韵玲、钟兴民 - 安静的对话.mp3', '2017-12-09', 'junzundejingbai', 'anna', '0', '哈利路亚哈哈哈哈哈');
INSERT INTO `voice` VALUES ('liuruoyingwozhisheiguanzhemingtian.mp3', '刘若英 - 我知谁管着明天.mp3', '2017-12-09', 'junzundejingbai', 'daoyou', '0', '腐蚀毒粉上帝');
INSERT INTO `voice` VALUES ('liuxuanyuhuidaonishenbian.mp3', '刘炫宇 - 回到你身边.mp3', '2017-12-09', 'jianzhengfenxiang', 'dabao', '0', 'gdfg ');
INSERT INTO `voice` VALUES ('piantouyinle.mp3', '片头音乐.mp3', '2017-12-08', '哈利路亚', '雅歌妹妹', '0', '适当放松的方式');
INSERT INTO `voice` VALUES ('shimingnew.mp3', '使命new.mp3', '2017-12-08', '我是所属专辑', '小珍', '0', 'fsddfsdfsdf');
INSERT INTO `voice` VALUES ('wuqixianyidingshini.mp3', '巫启贤 - 一定是你.mp3', '2017-12-09', 'shandidehuochai', 'caizhi', '0', '大苏打');
INSERT INTO `voice` VALUES ('yedibaihegegetadeai.mp3', '野地百合 - 各各他的爱.mp3', '2017-12-09', 'junzundejingbai', 'daoyou', '0', '');
