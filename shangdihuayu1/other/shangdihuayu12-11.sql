/*
Navicat MySQL Data Transfer

Source Server         : DB_INCHRIST
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : shangdihuayu

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-12-11 07:07:34
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
INSERT INTO `album` VALUES ('daweibaosenxilie', '大卫鲍森系列', '2017-12-10 23:15:31', null, 'yandushengjing');
INSERT INTO `album` VALUES ('shangdidehuochai', '上帝的火柴', '2017-12-10 23:15:31', null, null);
INSERT INTO `album` VALUES ('yanduchuaijiji', '研读出埃及记', '2017-12-10 23:15:31', null, 'yandushengjing');

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
INSERT INTO `file` VALUES ('69164f0c-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '6fenbieweishengguiwo', 'multiMedia', '6分别为圣归我.mp3', '11', '11', '2017-12-10 23:11:34', '11');
INSERT INTO `file` VALUES ('81ac45ff-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '6fenbieweishengguiwo', 'multiMedia', '6分别为圣归我.mp3', '11', '11', '2017-12-10 23:12:15', '11');
INSERT INTO `file` VALUES ('81afe24a-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '5yehehuazhiai', 'multiMedia', '5耶和华之爱.mp3', '11', '11', '2017-12-10 23:12:15', '11');
INSERT INTO `file` VALUES ('81b25def-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '4moxiyufalaojiaoshou', 'multiMedia', '4摩西与法老交手.mp3', '11', '11', '2017-12-10 23:12:15', '11');
INSERT INTO `file` VALUES ('81b52719-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '3moxiyushenxianghui', 'multiMedia', '3摩西与神相会.mp3', '11', '11', '2017-12-10 23:12:15', '11');
INSERT INTO `file` VALUES ('81b8c925-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '2moxiyujiaren', 'multiMedia', '2摩西与家人.mp3', '11', '11', '2017-12-10 23:12:15', '11');
INSERT INTO `file` VALUES ('81bc475e-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '20yehehuarongguangchongman', 'multiMedia', '20耶和华荣光充满.mp3', '11', '11', '2017-12-10 23:12:15', '11');
INSERT INTO `file` VALUES ('81beeaf5-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '1qiaoheibanlazhishidian', 'multiMedia', '1敲黑板啦,知识点.mp3', '11', '11', '2017-12-10 23:12:15', '11');
INSERT INTO `file` VALUES ('81c191ff-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '19youlianminendiandeshen', 'multiMedia', '19有怜悯恩典的神.mp3', '11', '11', '2017-12-10 23:12:15', '11');
INSERT INTO `file` VALUES ('81c406e7-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '18zongrongyufangsi', 'multiMedia', '18纵容与放肆.mp3', '11', '11', '2017-12-10 23:12:15', '11');
INSERT INTO `file` VALUES ('81c6ecdf-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '17zaineiliyunixianghui', 'multiMedia', '17在那里与你相会.mp3', '11', '11', '2017-12-10 23:12:15', '11');
INSERT INTO `file` VALUES ('81c9f153-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '16tamenkanjianshen', 'multiMedia', '16他们看见神.mp3', '11', '11', '2017-12-10 23:12:15', '11');
INSERT INTO `file` VALUES ('81cce9bc-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '15congtianshangshuohua', 'multiMedia', '15从天上说话.mp3', '11', '11', '2017-12-10 23:12:15', '11');
INSERT INTO `file` VALUES ('81cfd81f-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '14shenfenfudehua', 'multiMedia', '14神吩咐的话.mp3', '11', '11', '2017-12-10 23:12:15', '11');
INSERT INTO `file` VALUES ('81d255b0-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '13jisiguoshengjiemin', 'multiMedia', '13祭司国,圣洁民.mp3', '11', '11', '2017-12-10 23:12:15', '11');
INSERT INTO `file` VALUES ('81d528d8-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '12duzibanlibule', 'multiMedia', '12独自办理不了.mp3', '11', '11', '2017-12-10 23:12:15', '11');
INSERT INTO `file` VALUES ('81d7daf3-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '11yehehuanixi', 'multiMedia', '11耶和华尼西.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('81dae5e1-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '10zheishishenmeni', 'multiMedia', '10这是什么呢.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('81dd9aaa-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '9yehehualafa', 'multiMedia', '9耶和华拉法.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('81e0333c-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '7chengyuanyufumie', 'multiMedia', '7城垣与覆灭.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('81e34e62-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '8honghaizhige', 'multiMedia', '8红海之歌.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('81e6839f-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijiyueseyuyesu', 'multiMedia', '研读创世记：约瑟与耶稣.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('81e9cd6f-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijiyukexuedezhongdie', 'multiMedia', '研读创世记：与科学的重叠.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('81ecc8f8-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijiyabolahanyisayage2', 'multiMedia', '研读创世记：亚伯拉罕、以撒、雅各2.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('81efa63f-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijiyabolahanyisayage', 'multiMedia', '研读创世记：亚伯拉罕、以撒、雅各.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('81f26ddf-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijitashishengjingdejichu', 'multiMedia', '研读创世记：它是圣经的基础.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('81f500fa-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijishizhong', 'multiMedia', '研读创世记：始终.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('81f77324-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijishengwuyujinhua', 'multiMedia', '研读创世记：生物与进化.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('81fa209c-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijishangdidezhexue', 'multiMedia', '研读创世记：上帝的哲学.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('81fcb013-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijisanzhongguanxi', 'multiMedia', '研读创世记：三种关系.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('81ff14e5-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijipingxinxinduchuangshiji', 'multiMedia', '研读创世记：凭信心读创世记.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('82011e3a-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijinuoyafangzhou', 'multiMedia', '研读创世记：挪亚方舟.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('8203b376-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijicongyidianyuandaobabieta', 'multiMedia', '研读创世记：从伊甸园到巴别塔.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('8205ca1a-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', '2016.1.9yandushipian3', 'multiMedia', '2016.1.9研读诗篇3.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('82081879-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', '2016.1.7yandushipian2', 'multiMedia', '2016.1.7研读诗篇2.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('820a4226-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', '2016.1.5yandushipian1', 'multiMedia', '2016.1.5研读诗篇1.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('820d5888-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', '2016.1.11yandushipian4', 'multiMedia', '2016.1.11研读诗篇4.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('821079ff-ddbc-11e7-b6a7-c45444f5ce98', '上帝的火柴', 'shangdidehuochai', 'yeying', 'multiMedia', '夜莺.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('821416b5-ddbc-11e7-b6a7-c45444f5ce98', '上帝的火柴', 'shangdidehuochai', 'muzhiguniang', 'multiMedia', '拇指姑娘.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('8216fbe7-ddbc-11e7-b6a7-c45444f5ce98', '上帝的火柴', 'shangdidehuochai', 'huangdidexinzhuang', 'multiMedia', '皇帝的新装.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('8219ae8e-ddbc-11e7-b6a7-c45444f5ce98', '上帝的火柴', 'shangdidehuochai', 'haidenu:er', 'multiMedia', '海的女儿.mp3', '11', '11', '2017-12-10 23:12:16', '11');
INSERT INTO `file` VALUES ('f62a2e5b-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '6fenbieweishengguiwo', 'multiMedia', '6分别为圣归我.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f62cb644-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '5yehehuazhiai', 'multiMedia', '5耶和华之爱.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f62f671a-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '4moxiyufalaojiaoshou', 'multiMedia', '4摩西与法老交手.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f63166f9-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '3moxiyushenxianghui', 'multiMedia', '3摩西与神相会.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f633df4b-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '2moxiyujiaren', 'multiMedia', '2摩西与家人.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f6365cf5-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '20yehehuarongguangchongman', 'multiMedia', '20耶和华荣光充满.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f638b73a-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '1qiaoheibanlazhishidian', 'multiMedia', '1敲黑板啦,知识点.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f63be707-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '19youlianminendiandeshen', 'multiMedia', '19有怜悯恩典的神.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f63ec7ed-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '18zongrongyufangsi', 'multiMedia', '18纵容与放肆.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f641df10-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '17zaineiliyunixianghui', 'multiMedia', '17在那里与你相会.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f645a4bb-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '16tamenkanjianshen', 'multiMedia', '16他们看见神.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f648ccd6-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '15congtianshangshuohua', 'multiMedia', '15从天上说话.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f64c091d-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '14shenfenfudehua', 'multiMedia', '14神吩咐的话.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f64f1d9b-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '13jisiguoshengjiemin', 'multiMedia', '13祭司国,圣洁民.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f6526643-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '12duzibanlibule', 'multiMedia', '12独自办理不了.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f6559d38-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '11yehehuanixi', 'multiMedia', '11耶和华尼西.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f658ada2-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '10zheishishenmeni', 'multiMedia', '10这是什么呢.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f65ba087-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '9yehehualafa', 'multiMedia', '9耶和华拉法.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f65e2786-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '7chengyuanyufumie', 'multiMedia', '7城垣与覆灭.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f660cd0a-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '8honghaizhige', 'multiMedia', '8红海之歌.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f6638002-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijiyueseyuyesu', 'multiMedia', '研读创世记：约瑟与耶稣.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f6665441-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijiyukexuedezhongdie', 'multiMedia', '研读创世记：与科学的重叠.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f6687839-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijiyabolahanyisayage2', 'multiMedia', '研读创世记：亚伯拉罕、以撒、雅各2.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f66ac1b4-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijiyabolahanyisayage', 'multiMedia', '研读创世记：亚伯拉罕、以撒、雅各.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f66cf5ea-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijitashishengjingdejichu', 'multiMedia', '研读创世记：它是圣经的基础.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f66f34bf-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijishizhong', 'multiMedia', '研读创世记：始终.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f671d1ec-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijishengwuyujinhua', 'multiMedia', '研读创世记：生物与进化.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f67518a8-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijishangdidezhexue', 'multiMedia', '研读创世记：上帝的哲学.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f678690f-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijisanzhongguanxi', 'multiMedia', '研读创世记：三种关系.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f67b399e-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijipingxinxinduchuangshiji', 'multiMedia', '研读创世记：凭信心读创世记.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f67e4f37-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijinuoyafangzhou', 'multiMedia', '研读创世记：挪亚方舟.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f680ff8a-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', 'yanduchuangshijicongyidianyuandaobabieta', 'multiMedia', '研读创世记：从伊甸园到巴别塔.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f6843c71-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', '2016.1.9yandushipian3', 'multiMedia', '2016.1.9研读诗篇3.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f68724bc-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', '2016.1.7yandushipian2', 'multiMedia', '2016.1.7研读诗篇2.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f68a03b6-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', '2016.1.5yandushipian1', 'multiMedia', '2016.1.5研读诗篇1.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f68cf3c0-ddbc-11e7-b6a7-c45444f5ce98', '研读圣经/大卫鲍森系列', 'daweibaosenxilie', '2016.1.11yandushipian4', 'multiMedia', '2016.1.11研读诗篇4.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f68f510d-ddbc-11e7-b6a7-c45444f5ce98', '上帝的火柴', 'shangdidehuochai', 'yeying', 'multiMedia', '夜莺.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f6915667-ddbc-11e7-b6a7-c45444f5ce98', '上帝的火柴', 'shangdidehuochai', 'muzhiguniang', 'multiMedia', '拇指姑娘.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f693a038-ddbc-11e7-b6a7-c45444f5ce98', '上帝的火柴', 'shangdidehuochai', 'huangdidexinzhuang', 'multiMedia', '皇帝的新装.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('f695ba21-ddbc-11e7-b6a7-c45444f5ce98', '上帝的火柴', 'shangdidehuochai', 'haidenu:er', 'multiMedia', '海的女儿.mp3', '11', '11', '2017-12-10 23:15:31', '11');
INSERT INTO `file` VALUES ('fcadadb1-ddbb-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '6fenbieweishengguiwo', 'multiMedia', '6分别为圣归我.mp3', '11', '11', '2017-12-10 23:08:32', '11');
INSERT INTO `file` VALUES ('fcb06cb0-ddbb-11e7-b6a7-c45444f5ce98', '研读圣经/研读出埃及记', 'yanduchuaijiji', '5yehehuazhiai', 'multiMedia', '5耶和华之爱.mp3', '11', '11', '2017-12-10 23:08:32', '11');

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
INSERT INTO `user` VALUES ('liyage', '雅歌', '123456', '1', '123', null, null, '2017-12-20 07:06:08', null);
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
INSERT INTO `voice` VALUES ('10zheishishenmeni', '10这是什么呢.mp3', '2017-12-10', 'yanduchuaijiji', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('11yehehuanixi', '11耶和华尼西.mp3', '2017-12-10', 'yanduchuaijiji', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('12duzibanlibule', '12独自办理不了.mp3', '2017-12-10', 'yanduchuaijiji', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('13jisiguoshengjiemin', '13祭司国,圣洁民.mp3', '2017-12-10', 'yanduchuaijiji', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('14shenfenfudehua', '14神吩咐的话.mp3', '2017-12-10', 'yanduchuaijiji', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('15congtianshangshuohua', '15从天上说话.mp3', '2017-12-10', 'yanduchuaijiji', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('16tamenkanjianshen', '16他们看见神.mp3', '2017-12-10', 'yanduchuaijiji', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('17zaineiliyunixianghui', '17在那里与你相会.mp3', '2017-12-10', 'yanduchuaijiji', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('18zongrongyufangsi', '18纵容与放肆.mp3', '2017-12-10', 'yanduchuaijiji', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('19youlianminendiandeshen', '19有怜悯恩典的神.mp3', '2017-12-10', 'yanduchuaijiji', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('1qiaoheibanlazhishidian', '1敲黑板啦,知识点.mp3', '2017-12-10', 'yanduchuaijiji', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('2016.1.11yandushipian4', '2016.1.11研读诗篇4.mp3', '2017-12-10', 'daweibaosenxilie', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('2016.1.5yandushipian1', '2016.1.5研读诗篇1.mp3', '2017-12-10', 'daweibaosenxilie', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('2016.1.7yandushipian2', '2016.1.7研读诗篇2.mp3', '2017-12-10', 'daweibaosenxilie', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('2016.1.9yandushipian3', '2016.1.9研读诗篇3.mp3', '2017-12-10', 'daweibaosenxilie', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('20yehehuarongguangchongman', '20耶和华荣光充满.mp3', '2017-12-10', 'yanduchuaijiji', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('2moxiyujiaren', '2摩西与家人.mp3', '2017-12-10', 'yanduchuaijiji', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('3moxiyushenxianghui', '3摩西与神相会.mp3', '2017-12-10', 'yanduchuaijiji', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('4moxiyufalaojiaoshou', '4摩西与法老交手.mp3', '2017-12-10', 'yanduchuaijiji', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('5yehehuazhiai', '5耶和华之爱.mp3', '2017-12-10', 'yanduchuaijiji', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('6fenbieweishengguiwo', '6分别为圣归我.mp3', '2017-12-10', 'yanduchuaijiji', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('7chengyuanyufumie', '7城垣与覆灭.mp3', '2017-12-10', 'yanduchuaijiji', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('8honghaizhige', '8红海之歌.mp3', '2017-12-10', 'yanduchuaijiji', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('9yehehualafa', '9耶和华拉法.mp3', '2017-12-10', 'yanduchuaijiji', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('haidenu:er', '海的女儿.mp3', '2017-12-10', 'shangdidehuochai', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('huangdidexinzhuang', '皇帝的新装.mp3', '2017-12-10', 'shangdidehuochai', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('muzhiguniang', '拇指姑娘.mp3', '2017-12-10', 'shangdidehuochai', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('yanduchuangshijicongyidianyuandaobabieta', '研读创世记：从伊甸园到巴别塔.mp3', '2017-12-10', 'daweibaosenxilie', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('yanduchuangshijinuoyafangzhou', '研读创世记：挪亚方舟.mp3', '2017-12-10', 'daweibaosenxilie', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('yanduchuangshijipingxinxinduchuangshiji', '研读创世记：凭信心读创世记.mp3', '2017-12-10', 'daweibaosenxilie', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('yanduchuangshijisanzhongguanxi', '研读创世记：三种关系.mp3', '2017-12-10', 'daweibaosenxilie', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('yanduchuangshijishangdidezhexue', '研读创世记：上帝的哲学.mp3', '2017-12-10', 'daweibaosenxilie', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('yanduchuangshijishengwuyujinhua', '研读创世记：生物与进化.mp3', '2017-12-10', 'daweibaosenxilie', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('yanduchuangshijishizhong', '研读创世记：始终.mp3', '2017-12-10', 'daweibaosenxilie', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('yanduchuangshijitashishengjingdejichu', '研读创世记：它是圣经的基础.mp3', '2017-12-10', 'daweibaosenxilie', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('yanduchuangshijiyabolahanyisayage', '研读创世记：亚伯拉罕、以撒、雅各.mp3', '2017-12-10', 'daweibaosenxilie', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('yanduchuangshijiyabolahanyisayage2', '研读创世记：亚伯拉罕、以撒、雅各2.mp3', '2017-12-10', 'daweibaosenxilie', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('yanduchuangshijiyueseyuyesu', '研读创世记：约瑟与耶稣.mp3', '2017-12-10', 'daweibaosenxilie', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('yanduchuangshijiyukexuedezhongdie', '研读创世记：与科学的重叠.mp3', '2017-12-10', 'daweibaosenxilie', 'liyage', '0', null);
INSERT INTO `voice` VALUES ('yeying', '夜莺.mp3', '2017-12-10', 'shangdidehuochai', 'liyage', '0', null);
