/*
 Navicat Premium Data Transfer

 Source Server         : mysql5.6
 Source Server Type    : MySQL
 Source Server Version : 50620
 Source Host           : localhost:3306
 Source Schema         : park_db

 Target Server Type    : MySQL
 Target Server Version : 50620
 File Encoding         : 65001

 Date: 05/04/2021 23:46:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('a', 'a');

-- ----------------------------
-- Table structure for t_car
-- ----------------------------
DROP TABLE IF EXISTS `t_car`;
CREATE TABLE `t_car`  (
  `carId` int(11) NOT NULL AUTO_INCREMENT COMMENT '车辆id',
  `carNo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车牌',
  `carModelObj` int(11) NOT NULL COMMENT '车型',
  `pinpai` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌',
  `carPhoto` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车辆照片',
  `youxing` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '油型',
  `haoyouliang` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '耗油量',
  `chexianriqi` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车险日期',
  `zonglicheng` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '总里程',
  `carDesc` varchar(8000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆详情',
  `userObj` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属用户',
  `addTime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登记时间',
  PRIMARY KEY (`carId`) USING BTREE,
  INDEX `carModelObj`(`carModelObj`) USING BTREE,
  INDEX `userObj`(`userObj`) USING BTREE,
  CONSTRAINT `t_car_ibfk_1` FOREIGN KEY (`carModelObj`) REFERENCES `t_carmodel` (`modelId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_car_ibfk_2` FOREIGN KEY (`userObj`) REFERENCES `t_userinfo` (`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_car
-- ----------------------------
INSERT INTO `t_car` VALUES (1, '川A-2012', 2, '长安', 'upload/938084ec-6fb7-4abd-bc02-e6784d14c9db.jpg', '90号汽油', '5L/100公里', '2021-04-05', '9000公里', '<p>精品汽车</p>', '18888888888', '2021-04-05 00:32:27');
INSERT INTO `t_car` VALUES (2, '川A-2234', 3, '福田', 'upload/ad7ade0e-ae20-4872-9285-ec4b61c90ba9.jpg', '93号汽油', '8L/100公里', '2021-04-05', '8200公里', '<p>好汽车</p>', 'user1', '2021-04-05 15:07:51');

-- ----------------------------
-- Table structure for t_carmodel
-- ----------------------------
DROP TABLE IF EXISTS `t_carmodel`;
CREATE TABLE `t_carmodel`  (
  `modelId` int(11) NOT NULL AUTO_INCREMENT COMMENT '车型id',
  `modelName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车型名称',
  PRIMARY KEY (`modelId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_carmodel
-- ----------------------------
INSERT INTO `t_carmodel` VALUES (1, '小轿车');
INSERT INTO `t_carmodel` VALUES (2, '小货车');
INSERT INTO `t_carmodel` VALUES (3, '大货车');

-- ----------------------------
-- Table structure for t_leaveword
-- ----------------------------
DROP TABLE IF EXISTS `t_leaveword`;
CREATE TABLE `t_leaveword`  (
  `leaveWordId` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言id',
  `leaveTitle` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '留言标题',
  `leaveContent` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '留言内容',
  `userObj` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '留言人',
  `leaveTime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '留言时间',
  `replyContent` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理回复',
  `replyTime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY (`leaveWordId`) USING BTREE,
  INDEX `userObj`(`userObj`) USING BTREE,
  CONSTRAINT `t_leaveword_ibfk_1` FOREIGN KEY (`userObj`) REFERENCES `t_userinfo` (`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_leaveword
-- ----------------------------
INSERT INTO `t_leaveword` VALUES (1, '111', '222', 'user1', '2021-04-05 00:33:33', '333', '2021-04-05 00:33:38');
INSERT INTO `t_leaveword` VALUES (2, 'aaaa', 'bbbb', '18888888888', '2021-04-05 15:31:56', '--', '--');

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice`  (
  `noticeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '公告id',
  `title` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `content` varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告内容',
  `publishDate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`noticeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES (1, 'aaaaaa', 'bbbb', '2021-04-05 00:33:47');
INSERT INTO `t_notice` VALUES (2, 'ddd', 'gafasfa', '2021-04-05 15:38:34');
INSERT INTO `t_notice` VALUES (3, 'gafas', 'afafa', '2021-04-05 15:38:46');
INSERT INTO `t_notice` VALUES (4, '标题标题标题标题标题标题', '内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容', '2021-04-05 15:39:16');

-- ----------------------------
-- Table structure for t_park
-- ----------------------------
DROP TABLE IF EXISTS `t_park`;
CREATE TABLE `t_park`  (
  `parkId` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `carObj` int(11) NOT NULL COMMENT '车辆信息',
  `userObj` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '停车用户',
  `spaceObj` int(11) NOT NULL COMMENT '停入车位',
  `startTime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '停车开始时间',
  `endTime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '停车离开时间',
  `price` float NOT NULL COMMENT '车位价格',
  `timeSpan` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '停车时长',
  `parkMoney` float NOT NULL COMMENT '停车费用',
  `parkMemo` varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '停车备注',
  PRIMARY KEY (`parkId`) USING BTREE,
  INDEX `carObj`(`carObj`) USING BTREE,
  INDEX `userObj`(`userObj`) USING BTREE,
  INDEX `spaceObj`(`spaceObj`) USING BTREE,
  CONSTRAINT `t_park_ibfk_1` FOREIGN KEY (`carObj`) REFERENCES `t_car` (`carId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_park_ibfk_2` FOREIGN KEY (`userObj`) REFERENCES `t_userinfo` (`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_park_ibfk_3` FOREIGN KEY (`spaceObj`) REFERENCES `t_spaceinfo` (`spaceId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_park
-- ----------------------------
INSERT INTO `t_park` VALUES (1, 1, '18888888888', 1, '2021-04-05 00:32:40', '2021-04-05 03:32:44', 15, '3小时', 45, '11111');
INSERT INTO `t_park` VALUES (2, 1, '18888888888', 2, '2021-04-05 19:36:41', '2021-04-05 20:07:26', 12, '0.5小时', 6, '测试');
INSERT INTO `t_park` VALUES (3, 2, 'user1', 1, '2021-04-05 07:12:33', '2021-04-05 20:12:47', 15, '13.0小时', 195, '冲冲冲');
INSERT INTO `t_park` VALUES (4, 1, '18888888888', 1, '2021-04-05 17:58:11', '--', 15, '--', 0, 'hahahhah哈哈');

-- ----------------------------
-- Table structure for t_region
-- ----------------------------
DROP TABLE IF EXISTS `t_region`;
CREATE TABLE `t_region`  (
  `regionId` int(11) NOT NULL AUTO_INCREMENT COMMENT '区域id',
  `regionFloor` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所在楼层',
  `regionName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '区域名称',
  `regionDesc` varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域说明',
  PRIMARY KEY (`regionId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_region
-- ----------------------------
INSERT INTO `t_region` VALUES (1, '负1楼', 'A区', 'bbb');
INSERT INTO `t_region` VALUES (2, '负1楼', 'B区', '车位很多');

-- ----------------------------
-- Table structure for t_spaceinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_spaceinfo`;
CREATE TABLE `t_spaceinfo`  (
  `spaceId` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `regionObj` int(11) NOT NULL COMMENT '所在区域',
  `spaceNo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车位名称',
  `spacePhoto` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车位照片',
  `spacePrice` float NOT NULL COMMENT '车位价格',
  `spaceState` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车位状态',
  `spaceDesc` varchar(8000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车位描述',
  PRIMARY KEY (`spaceId`) USING BTREE,
  INDEX `regionObj`(`regionObj`) USING BTREE,
  CONSTRAINT `t_spaceinfo_ibfk_1` FOREIGN KEY (`regionObj`) REFERENCES `t_region` (`regionId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_spaceinfo
-- ----------------------------
INSERT INTO `t_spaceinfo` VALUES (1, 1, 'A-203', 'upload/98a7b3bf-045c-45ac-9c12-d82475a2353c.jpg', 15, '占用中', '<p>1111</p>');
INSERT INTO `t_spaceinfo` VALUES (2, 1, 'A-204', 'upload/510a9049-4462-4436-add1-89cd720f1421.jpg', 12, '空闲中', '<p>测试<br/></p>');
INSERT INTO `t_spaceinfo` VALUES (3, 2, 'B-101', 'upload/2e7cbdc5-3395-4011-bb1b-1930de0c1c31.jpg', 10, '空闲中', '<p>很便宜啊</p>');
INSERT INTO `t_spaceinfo` VALUES (4, 2, 'B-102', 'upload/e3206a0f-ec9b-4896-98e9-2eb6340587ea.jpg', 9, '空闲中', '<p>最便宜的，位置也丰富</p>');

-- ----------------------------
-- Table structure for t_spaceorder
-- ----------------------------
DROP TABLE IF EXISTS `t_spaceorder`;
CREATE TABLE `t_spaceorder`  (
  `orderId` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `spaceObj` int(11) NOT NULL COMMENT '预约车位',
  `userObj` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '预约用户',
  `startTime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预约开始时间',
  `endTime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预约结束时间',
  `orderMoney` float NOT NULL COMMENT '预计费用',
  `shenHeState` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '审核状态',
  `orderMemo` varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预约备注',
  `orderTime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下单时间',
  PRIMARY KEY (`orderId`) USING BTREE,
  INDEX `spaceObj`(`spaceObj`) USING BTREE,
  INDEX `userObj`(`userObj`) USING BTREE,
  CONSTRAINT `t_spaceorder_ibfk_1` FOREIGN KEY (`spaceObj`) REFERENCES `t_spaceinfo` (`spaceId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_spaceorder_ibfk_2` FOREIGN KEY (`userObj`) REFERENCES `t_userinfo` (`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_spaceorder
-- ----------------------------
INSERT INTO `t_spaceorder` VALUES (2, 2, '18888888888', '2021-04-05 13:01:00', '2021-04-05 15:01:00', 24, '已审核', 'test测试', '2021-04-05 13:01:39');

-- ----------------------------
-- Table structure for t_userinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_userinfo`;
CREATE TABLE `t_userinfo`  (
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'user_name',
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `gender` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `birthDate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生日期',
  `userPhoto` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户照片',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `address` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭地址',
  `regTime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册时间',
  `openid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_userinfo
-- ----------------------------
INSERT INTO `t_userinfo` VALUES ('18888888888', '123456', '张曦文', '男', '2020-12-21', 'images/avatar/avatar22.png', '18888888888', 'test@126.com', '四川成都', '2021-04-05 00:55:57', '--');
INSERT INTO `t_userinfo` VALUES ('user1', '123', '王晓光', '男', '2021-04-05', 'upload/bfb7aa84-c04a-4d21-81ef-920ea353e199.jpg', '13510834234', 'xiaotao@163.com', '成都红星路', '2021-04-05 00:30:41', NULL);

SET FOREIGN_KEY_CHECKS = 1;
