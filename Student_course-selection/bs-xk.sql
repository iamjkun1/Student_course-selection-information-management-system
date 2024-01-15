/*
 Navicat Premium Data Transfer

 Source Server         : jkun
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : bs-xk

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 10/12/2023 16:36:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID学号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `age` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '年龄',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `level` int(0) NULL DEFAULT 1 COMMENT '权限等级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 565 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理员信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_info
-- ----------------------------
INSERT INTO `admin_info` VALUES (1, 'admin', '123456ab', '男', '42', '12340001', 1);
INSERT INTO `admin_info` VALUES (17, 'admin54', '123456ab', '女', '45', '253215432', 1);
INSERT INTO `admin_info` VALUES (18, 'admin34', '123456ab', '男', '21', '15723343', 1);
INSERT INTO `admin_info` VALUES (43, 'admin5', '123456ab', '男', '34', '2342134', 1);
INSERT INTO `admin_info` VALUES (431, 'admin53', '123456ab', '男', '43', '5312345', 1);
INSERT INTO `admin_info` VALUES (432, 'admin7', '123456ab', '男', '34', '23421342', 1);
INSERT INTO `admin_info` VALUES (557, 'test', '123456ab', '男', '25', '122', 1);

-- ----------------------------
-- Table structure for class_info
-- ----------------------------
DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程介绍',
  `zhuanyeId` bigint(0) NULL DEFAULT NULL COMMENT '专业ID',
  `score` int(0) NULL DEFAULT NULL COMMENT '课程学分',
  `teacherId` bigint(0) NULL DEFAULT NULL COMMENT '教师ID',
  `kaiban` int(0) NULL DEFAULT 0 COMMENT '开班人数',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上课时段',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上课地点',
  `yixuan` int(0) NULL DEFAULT 0 COMMENT '已选人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '课程信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class_info
-- ----------------------------
INSERT INTO `class_info` VALUES (13, 'Java进阶', 'JavaSE--企业理解实战', 4, 2, 560, 30, '周四', '二教301', 3);
INSERT INTO `class_info` VALUES (14, 'Java基础', '学会Java基础', 6, 2, 558, 38, '周五', '一教401', 5);
INSERT INTO `class_info` VALUES (15, 'Python基础', '学会Python基础', 6, 3, 559, 34, '周一', '一教112', 5);
INSERT INTO `class_info` VALUES (16, '打灰教学', '在线打灰', 12, 2, 565, 50, '周六', '后山', 2);
INSERT INTO `class_info` VALUES (17, '环境开发与保护', '环境开发与保护', 11, 3, 563, 54, '周三', '青年广场', 1);
INSERT INTO `class_info` VALUES (18, '嵌入式开发', '硬件开发实践', 6, 3, 562, 50, '周一三四五', '实训中心506', 0);
INSERT INTO `class_info` VALUES (19, '电子商务教学', '电子商务', 7, 2, 566, 43, '周二六七节', '三教301', 1);
INSERT INTO `class_info` VALUES (20, '音乐教学', '唱，跳，rap', 10, 2, 567, 25, '周三二三节', '坤坤广场', 2);
INSERT INTO `class_info` VALUES (21, '测试工程', '运维测试', 4, 3, 560, 40, '周五六七节', '实验中心', 1);
INSERT INTO `class_info` VALUES (22, '退火算法', '陷入局部最优解', 6, 3, 560, 21, '周一上午', '实验中心', 0);

-- ----------------------------
-- Table structure for student_info
-- ----------------------------
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID学号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `age` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '年龄',
  `code` bigint(0) NULL DEFAULT NULL COMMENT '学号',
  `level` int(0) NULL DEFAULT 3 COMMENT '权限等级',
  `xueyuanId` bigint(0) NULL DEFAULT NULL COMMENT '学院ID',
  `score` int(0) NULL DEFAULT 0 COMMENT '总学分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 582 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '学生信息表\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_info
-- ----------------------------
INSERT INTO `student_info` VALUES (558, '李四', '123456ab', '男', '22', 2021241132764, 3, 3, 0);
INSERT INTO `student_info` VALUES (560, '张一', '123456ab', '男', '23', 2021241198715, 3, 2, 1);
INSERT INTO `student_info` VALUES (561, '张二', '123456ab', '男', '23', 2021241187784, 3, 2, 2);
INSERT INTO `student_info` VALUES (562, '程程', '123456ab', '女', '21', 202124153212, 3, 5, 1);
INSERT INTO `student_info` VALUES (563, '王五', '123456ab', '男', '25', 2021241532123, 3, 4, 0);
INSERT INTO `student_info` VALUES (565, '陈小小', '123456ab', '女', '20', 202124113298789, 3, 2, 6);
INSERT INTO `student_info` VALUES (566, '张晓霞', '123456ab', '女', '23', 20212411329854, 3, 5, 2);
INSERT INTO `student_info` VALUES (567, '小明', '123456ab', '男', '23', 20212411398534, 3, 4, 2);
INSERT INTO `student_info` VALUES (569, '张八', '123456ab', '男', '26', 202124198734, 3, 8, 1);
INSERT INTO `student_info` VALUES (572, '小李', '123456ab', '女', '20', 2201234344, 3, 9, 1);
INSERT INTO `student_info` VALUES (573, '张雄安', '123456ab', '男', '24', 2341343, 3, 2, 1);
INSERT INTO `student_info` VALUES (581, '李三', '123456ab', '男', '23', 20212413434, 3, 2, 0);

-- ----------------------------
-- Table structure for teacher_info
-- ----------------------------
DROP TABLE IF EXISTS `teacher_info`;
CREATE TABLE `teacher_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID学号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `age` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '年龄',
  `zhicheng` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '职称',
  `level` int(0) NULL DEFAULT 2 COMMENT '权限等级',
  `zhuanyeId` bigint(0) NULL DEFAULT NULL COMMENT '专业ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 568 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '教师信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher_info
-- ----------------------------
INSERT INTO `teacher_info` VALUES (556, '张老师', '123456ab', '男', '31', '教授', 2, NULL);
INSERT INTO `teacher_info` VALUES (558, '李老师', '123456ab', '女', '27', '教授', 2, NULL);
INSERT INTO `teacher_info` VALUES (559, '赵老师', '123456ab', '女', '27', '副教授', 2, NULL);
INSERT INTO `teacher_info` VALUES (560, '钱老师', '123456ab', '女', '25', '教授', 2, NULL);
INSERT INTO `teacher_info` VALUES (561, '王老师', '123456ab', '男', '67', '教授', 2, NULL);
INSERT INTO `teacher_info` VALUES (562, '黄老师', '123456ab', '女', '26', '副教授', 2, NULL);
INSERT INTO `teacher_info` VALUES (563, '张小老师', '123456ab', '女', '31', '教授助理', 2, NULL);
INSERT INTO `teacher_info` VALUES (565, '小明老师', '123456ab', '男', '36', '教授', 2, NULL);
INSERT INTO `teacher_info` VALUES (566, '小青老师', '123456ab', '女', '33', '副教授', 2, NULL);
INSERT INTO `teacher_info` VALUES (567, '钱小老师', '123456ab', '女', '33', '教授助理', 2, NULL);
INSERT INTO `teacher_info` VALUES (568, '黄小老师', '123456ab', '女', '31', '教授助理', 2, NULL);

-- ----------------------------
-- Table structure for xuanke_info
-- ----------------------------
DROP TABLE IF EXISTS `xuanke_info`;
CREATE TABLE `xuanke_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程介绍',
  `zhuanyeId` bigint(0) NULL DEFAULT NULL COMMENT '专业ID',
  `score` int(0) NULL DEFAULT NULL COMMENT '课程学分',
  `teacherId` bigint(0) NULL DEFAULT NULL COMMENT '教师ID',
  `kaiban` int(0) NULL DEFAULT 0 COMMENT '开班人数',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上课时段',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上课地点',
  `studentId` bigint(0) NULL DEFAULT NULL COMMENT '学生ID',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 158 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '选课信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xuanke_info
-- ----------------------------
INSERT INTO `xuanke_info` VALUES (130, 'Java进阶', 'JavaSE--企业理解实战', 4, 2, 560, 30, '周四', '二教301', 558, '已开课');
INSERT INTO `xuanke_info` VALUES (131, 'Python基础', '学会Python基础', 6, 2, 560, 34, '周一', '一教112', 558, '已开课');
INSERT INTO `xuanke_info` VALUES (143, 'Java基础', '学会Java基础', 6, 2, 558, 38, '周五', '一教401', 581, '待开课');
INSERT INTO `xuanke_info` VALUES (144, 'Python基础', '学会Python基础', 6, 2, 559, 34, '周一', '一教112', 581, '待开课');
INSERT INTO `xuanke_info` VALUES (145, '打灰教学', '在线打灰', 12, 2, 565, 50, '周六', '后山', 581, '待开课');
INSERT INTO `xuanke_info` VALUES (146, '环境开发与保护', '环境开发与保护', 11, 3, 563, 54, '周三', '青年广场', 581, '待开课');
INSERT INTO `xuanke_info` VALUES (148, '测试工程', '运维测试', 4, 2, 560, 40, '周五六七节', '实验中心', 563, '待开课');
INSERT INTO `xuanke_info` VALUES (150, 'Python基础', '学会Python基础', 6, 2, 559, 34, '周一', '一教112', 563, '待开课');
INSERT INTO `xuanke_info` VALUES (151, 'Java基础', '学会Java基础', 6, 2, 558, 38, '周五', '一教401', 563, '待开课');
INSERT INTO `xuanke_info` VALUES (152, '电子商务教学', '电子商务', 7, 2, 566, 43, '周二六七节', '三教301', 563, '待开课');
INSERT INTO `xuanke_info` VALUES (155, '音乐教学', '唱，跳，rap', 10, 2, 567, 25, '周三二三节', '坤坤广场', 558, '待开课');
INSERT INTO `xuanke_info` VALUES (156, 'Java进阶', 'JavaSE--企业理解实战', 4, 2, 560, 30, '周四', '二教301', 563, '待开课');

-- ----------------------------
-- Table structure for xueyuan_info
-- ----------------------------
DROP TABLE IF EXISTS `xueyuan_info`;
CREATE TABLE `xueyuan_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学院名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学院介绍',
  `score` int(0) NULL DEFAULT NULL COMMENT '学分限制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '学院信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xueyuan_info
-- ----------------------------
INSERT INTO `xueyuan_info` VALUES (2, '计算机学院', '纯纯理工', 12);
INSERT INTO `xueyuan_info` VALUES (3, '外国语学院', '美女多', 23);
INSERT INTO `xueyuan_info` VALUES (4, '机械学院', '生产第一线', 12);
INSERT INTO `xueyuan_info` VALUES (5, '音乐学院', '唱歌爱好者', 22);
INSERT INTO `xueyuan_info` VALUES (6, '体育学院', '强身健体', 10);
INSERT INTO `xueyuan_info` VALUES (7, '土木学院', '基建狂魔', 10);
INSERT INTO `xueyuan_info` VALUES (8, '生命学院', '生命诚可贵', 12);
INSERT INTO `xueyuan_info` VALUES (9, '环境设计学院', '保护环境', 15);
INSERT INTO `xueyuan_info` VALUES (10, '大数据学院', '大数据分析法', 34);

-- ----------------------------
-- Table structure for zhuanye_info
-- ----------------------------
DROP TABLE IF EXISTS `zhuanye_info`;
CREATE TABLE `zhuanye_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '专业名称',
  `department` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '系名',
  `xueyuanId` bigint(0) NULL DEFAULT NULL COMMENT '学院ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '专业信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zhuanye_info
-- ----------------------------
INSERT INTO `zhuanye_info` VALUES (4, '软件工程', '计算机系', 2);
INSERT INTO `zhuanye_info` VALUES (6, '计算机科学与技术', '计算机系', 2);
INSERT INTO `zhuanye_info` VALUES (7, '商务英语', '外语系', 3);
INSERT INTO `zhuanye_info` VALUES (8, '电子工程', '计算机系', 2);
INSERT INTO `zhuanye_info` VALUES (9, '机械工程', '机械系', 4);
INSERT INTO `zhuanye_info` VALUES (10, '音乐文化', '音乐系', 5);
INSERT INTO `zhuanye_info` VALUES (11, '环境工程', '环境系', 9);
INSERT INTO `zhuanye_info` VALUES (12, '土木工程', '土木系', 7);

SET FOREIGN_KEY_CHECKS = 1;
