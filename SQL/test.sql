/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50711
Source Host           : 127.0.0.1:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2018-10-15 10:47:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `updateDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('8a02126c6661c0a8016661c0ccff0000', '张三', '23', '男', '2018-10-11 14:11:57');
INSERT INTO `user` VALUES ('8a02126c6661c0a8016661c0e7820001', '李四', '22', '男', '2018-10-11 14:12:04');
INSERT INTO `user` VALUES ('8a02126c6661c0a8016661c118980002', '王五', '19', '女', '2018-10-11 14:12:16');
INSERT INTO `user` VALUES ('8a02126c6661c0a8016661c1419f0003', '赵六', '24', '女', '2018-10-11 14:13:10');
INSERT INTO `user` VALUES ('8a02126c6661d851016661d869ab0000', '赵六', '22', '女', '2018-10-11 14:37:44');
INSERT INTO `user` VALUES ('8a02126c6661d851016661d88a130001', '赵六', '22', '男', '2018-10-11 14:37:53');
INSERT INTO `user` VALUES ('8a02126c66622b430166622b99fd0000', '����', '23', '��', '2018-10-11 16:08:36');
INSERT INTO `user` VALUES ('8a02126c66622b430166622f5df10001', '张三', '54', '男', '2018-10-11 16:12:43');
