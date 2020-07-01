# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.21)
# Database: AppDataBase
# Generation Time: 2019-07-05 15:01:09 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table appInfo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `appInfo`;

CREATE TABLE `appInfo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `appsecret` varchar(110) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '秘钥',
  `appid` varchar(110) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '唯一标示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump of table appInfoUser
# ------------------------------------------------------------

DROP TABLE IF EXISTS `appInfoUser`;

CREATE TABLE `appInfoUser` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL COMMENT '用户键',
  `appInfoId` int(11) NOT NULL COMMENT 'appInfo键',
  PRIMARY KEY (`id`),
  CONSTRAINT `appinfouser_ibfk_1` FOREIGN KEY (`id`) REFERENCES `appInfo` (`id`),
  CONSTRAINT `appinfouser_ibfk_2` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump of table deviceInfo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `deviceInfo`;

CREATE TABLE `deviceInfo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `deviceModel` int(2) DEFAULT NULL COMMENT '设备系统，1代表IOS,2代表安卓',
  `deviceName` varchar(20) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '设备名字',
  `systemVersion` char(20) DEFAULT '' COMMENT '设备系统版本',
  `deviceToken` char(100) NOT NULL DEFAULT '' COMMENT '设备标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table friend
# ------------------------------------------------------------

DROP TABLE IF EXISTS `friend`;

CREATE TABLE `friend` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `hostId` int(11) unsigned NOT NULL COMMENT '发送者（外键）',
  `friendId` int(11) unsigned NOT NULL COMMENT '好友（外键）',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '1为请求，2为接受，3为拒绝',
  `roomId` varchar(110) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '加好友方式',
  PRIMARY KEY (`id`),
  KEY `hostId` (`hostId`),
  KEY `friendId` (`friendId`),
  CONSTRAINT `friend_ibfk_1` FOREIGN KEY (`hostId`) REFERENCES `user` (`id`),
  CONSTRAINT `friend_ibfk_2` FOREIGN KEY (`friendId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table friendshipPolicy
# ------------------------------------------------------------

DROP TABLE IF EXISTS `friendshipPolicy`;

CREATE TABLE `friendshipPolicy` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `friendshipPolicy` varchar(11) DEFAULT NULL COMMENT '加为好友方式设置',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table group_dynamic_property
# ------------------------------------------------------------

DROP TABLE IF EXISTS `group_dynamic_property`;

CREATE TABLE `group_dynamic_property` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `group_change_type` smallint(6) NOT NULL DEFAULT '0' COMMENT '变化类型',
  `group_change_datetime` datetime NOT NULL COMMENT '变化时间',
  `group_id` bigint(20) unsigned NOT NULL COMMENT '群组ID',
  `group_owner_uid` int(20) unsigned NOT NULL COMMENT '群主ID',
  `group_level` int(11) NOT NULL DEFAULT '0' COMMENT '等级',
  `group_parents_type` int(11) NOT NULL DEFAULT '0' COMMENT '父类型',
  `group_child_type` int(11) NOT NULL DEFAULT '0' COMMENT '子类型',
  `group_name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '名称',
  `group_announcement` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公告',
  `group_description` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `group_join_type` int(4) NOT NULL DEFAULT '0' COMMENT '加入类型',
  PRIMARY KEY (`id`),
  KEY `group_id` (`group_id`),
  KEY `group_owner_uid` (`group_owner_uid`),
  CONSTRAINT `group_dynamic_property_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `group_property` (`id`),
  CONSTRAINT `group_dynamic_property_ibfk_2` FOREIGN KEY (`group_owner_uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump of table group_member
# ------------------------------------------------------------

DROP TABLE IF EXISTS `group_member`;

CREATE TABLE `group_member` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) unsigned NOT NULL COMMENT '群组ID',
  `group_member_id` int(20) unsigned NOT NULL COMMENT '成员ID',
  `group_member_identity` smallint(6) NOT NULL COMMENT '成员身份',
  `group_member_join_datetime` datetime NOT NULL COMMENT '加入时间',
  `group_member_name` varchar(110) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '群里用户上的名字',
  PRIMARY KEY (`id`),
  KEY `group_id` (`group_id`),
  KEY `group_member_id` (`group_member_id`),
  CONSTRAINT `group_member_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `group_property` (`id`),
  CONSTRAINT `group_member_ibfk_2` FOREIGN KEY (`group_member_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump of table group_message
# ------------------------------------------------------------

DROP TABLE IF EXISTS `group_message`;

CREATE TABLE `group_message` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `group_id` bigint(11) unsigned NOT NULL COMMENT '群Id',
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消息主体',
  `chatType` int(11) unsigned NOT NULL COMMENT '消息类型',
  `sendtime` datetime NOT NULL COMMENT '发送时间',
  `sendId` int(11) unsigned NOT NULL COMMENT '发送者Id',
  `messageId` varchar(110) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '消息标识',
  PRIMARY KEY (`id`),
  KEY `group_id` (`group_id`),
  KEY `sendId` (`sendId`),
  KEY `chatType` (`chatType`),
  CONSTRAINT `group_message_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `group_property` (`id`),
  CONSTRAINT `group_message_ibfk_2` FOREIGN KEY (`sendId`) REFERENCES `user` (`id`),
  CONSTRAINT `group_message_ibfk_3` FOREIGN KEY (`chatType`) REFERENCES `messageType` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump of table group_message_state
# ------------------------------------------------------------

DROP TABLE IF EXISTS `group_message_state`;

CREATE TABLE `group_message_state` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `group_message_id` int(11) unsigned NOT NULL COMMENT '群消息id',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `state` int(11) DEFAULT NULL COMMENT '0为未读，50为已读,10为撤回',
  PRIMARY KEY (`id`),
  KEY `group_message_id` (`group_message_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `group_message_state_ibfk_1` FOREIGN KEY (`group_message_id`) REFERENCES `group_message` (`id`),
  CONSTRAINT `group_message_state_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump of table group_property
# ------------------------------------------------------------

DROP TABLE IF EXISTS `group_property`;

CREATE TABLE `group_property` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '群组ID',
  `group_creater_id` int(20) unsigned NOT NULL COMMENT '创建者ID',
  `group_create_datetime` datetime NOT NULL COMMENT '创建时间',
  `group_statu` int(1) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `group_creater_id` (`group_creater_id`),
  CONSTRAINT `group_property_ibfk_1` FOREIGN KEY (`group_creater_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump of table group_property_change
# ------------------------------------------------------------

DROP TABLE IF EXISTS `group_property_change`;

CREATE TABLE `group_property_change` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '递增ID',
  `group_id` bigint(20) unsigned NOT NULL COMMENT '群组ID',
  `change_type` int(4) NOT NULL COMMENT '变化类型（1 ，2， 3）',
  `group_create_datetime` datetime NOT NULL COMMENT '创建时间',
  `group_change_datetime` datetime NOT NULL COMMENT '变化时间',
  `group_statu` int(20) NOT NULL COMMENT '状态',
  `group_creater_id` int(20) unsigned NOT NULL COMMENT '创建者ID',
  PRIMARY KEY (`id`),
  KEY `group_creater_id` (`group_creater_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `group_property_change_ibfk_1` FOREIGN KEY (`group_creater_id`) REFERENCES `user` (`id`),
  CONSTRAINT `group_property_change_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `group_property` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump of table message
# ------------------------------------------------------------

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` int(11) unsigned NOT NULL COMMENT '发出信息者（外键）',
  `friendId` int(11) unsigned NOT NULL COMMENT '收到信息者（外键）',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '信息',
  `chatType` int(11) unsigned NOT NULL COMMENT '信息类型（外键）',
  `sendtime` datetime NOT NULL COMMENT '发送时间',
  `extend` text COMMENT '扩展字段',
  `roomId` varchar(110) DEFAULT NULL,
  `messageId` varchar(110) DEFAULT NULL COMMENT '消息标识',
  PRIMARY KEY (`id`),
  KEY `fromUserId` (`userId`),
  KEY `toUserId` (`friendId`),
  KEY `messageTypeId` (`chatType`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`friendId`) REFERENCES `user` (`id`),
  CONSTRAINT `message_ibfk_3` FOREIGN KEY (`chatType`) REFERENCES `messageType` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table message_state
# ------------------------------------------------------------

DROP TABLE IF EXISTS `message_state`;

CREATE TABLE `message_state` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `message_id` int(11) unsigned DEFAULT NULL COMMENT '消息外键',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户表外键',
  `state` int(11) DEFAULT NULL COMMENT '0为未读，50为已读,10为撤回',
  `roomId` varchar(110) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '房间号',
  PRIMARY KEY (`id`),
  KEY `message_id` (`message_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `message_state_ibfk_1` FOREIGN KEY (`message_id`) REFERENCES `message` (`id`),
  CONSTRAINT `message_state_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump of table messageType
# ------------------------------------------------------------

DROP TABLE IF EXISTS `messageType`;

CREATE TABLE `messageType` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mt_id` int(11) DEFAULT NULL,
  `message_type` varchar(50) DEFAULT NULL COMMENT '消息类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table serviceInfo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `serviceInfo`;

CREATE TABLE `serviceInfo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serverIP` char(15) NOT NULL DEFAULT '' COMMENT '服务器IP地址',
  `serverPort` int(11) NOT NULL COMMENT '服务器socket端口号',
  `socketLiveTime` int(11) NOT NULL COMMENT '心脏包',
  `sslEnable` tinyint(1) NOT NULL COMMENT '是否需要ssl',
  `privateKey` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table socketInfo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `socketInfo`;

CREATE TABLE `socketInfo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(11) unsigned NOT NULL COMMENT '用户id',
  `offLineTime` datetime NOT NULL COMMENT '断开时间戳',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `socketinfo_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `sex` char(11) DEFAULT '' COMMENT '性别',
  `birthday` date DEFAULT '1970-01-01' COMMENT '生日',
  `phoneNum` char(11) DEFAULT '' COMMENT '电话号码',
  `username` char(24) DEFAULT '' COMMENT '用户名',
  `password` char(100) DEFAULT '' COMMENT '密码',
  `icon` char(100) DEFAULT '' COMMENT '头像',
  `token` char(100) DEFAULT '' COMMENT '标识',
  `loginModel` char(11) DEFAULT '' COMMENT '登录方式',
  `location` char(100) DEFAULT '' COMMENT '位置',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table userDevice
# ------------------------------------------------------------

DROP TABLE IF EXISTS `userDevice`;

CREATE TABLE `userDevice` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(11) unsigned NOT NULL COMMENT '用户Id',
  `deviceId` int(11) unsigned NOT NULL COMMENT '设备信息表Id',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `deviceId` (`deviceId`),
  CONSTRAINT `userdevice_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  CONSTRAINT `userdevice_ibfk_2` FOREIGN KEY (`deviceId`) REFERENCES `deviceInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table userRemarks
# ------------------------------------------------------------

DROP TABLE IF EXISTS `userRemarks`;

CREATE TABLE `userRemarks` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL COMMENT '备注人的id',
  `username` varchar(110) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '备注人的名字',
  `formUserId` int(11) DEFAULT NULL COMMENT '在备注人下',
  `state` int(11) DEFAULT '0' COMMENT '0是未设置，1是已经设置',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
