/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : 127.0.0.1:3306
 Source Schema         : vueadmin

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 31/03/2022 18:52:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for plat_menu
-- ----------------------------
DROP TABLE IF EXISTS `plat_menu`;
CREATE TABLE `plat_menu`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '菜单主键',
  `parentId` int(11) NOT NULL COMMENT '父级菜单主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '组件名称',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '项目中组件地址（主菜单默认Layout，子菜单默认前缀@/views，后面自定义',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图标',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '路由地址',
  `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '默认重定向',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '显示标题',
  `hidden` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '默认隐藏',
  `needCache` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否需要缓存',
  `props` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '是否传参数',
  `notNeedAuth` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '是否忽略权限',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '备注',
  `isUse` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 1 COMMENT '是否启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12003 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of plat_menu
-- ----------------------------
INSERT INTO `plat_menu` VALUES (10000, 0, 'System', 'Layout', 'el-icon-setting', '/system', '/system/user', '系统管理', 0, 1, 0, 0, '', 1);
INSERT INTO `plat_menu` VALUES (11000, 10000, 'User', '/System/User/index', 'el-icon-user-solid', '/user', NULL, '用户管理', 0, 1, 0, 0, '', 1);
INSERT INTO `plat_menu` VALUES (11001, 10000, 'UserDetail', '/System/User/Detail/index', NULL, '/user/detail', NULL, '用户管理-详情', 1, 1, 1, 0, '', 1);
INSERT INTO `plat_menu` VALUES (11002, 10000, 'UserEdit', '/System/User/Edit/index', NULL, '/user/edit/', NULL, '用户管理-编辑', 1, 1, 1, 0, '', 1);
INSERT INTO `plat_menu` VALUES (12000, 10000, 'Role', '/System/Role/index', 'el-icon-user', '/role', NULL, '角色管理', 0, 1, 0, 0, '', 1);
INSERT INTO `plat_menu` VALUES (12001, 10000, 'RoleDetail', '/System/Role/Detail/index', NULL, '/role/detail/:id', NULL, '角色管理-详情', 1, 1, 1, 0, '', 1);
INSERT INTO `plat_menu` VALUES (12002, 10000, 'RoleEdit', '/System/Role/Edit/index', NULL, '/role/edit/:id', NULL, '角色管理-编辑', 1, 1, 1, 0, '', 1);

-- ----------------------------
-- Table structure for plat_user
-- ----------------------------
DROP TABLE IF EXISTS `plat_user`;
CREATE TABLE `plat_user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户表ID',
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `loginName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '登录名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '登录密码（md5）加密',
  `role` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `isUse` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 1 COMMENT '是否启用',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户头像',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '地址',
  `tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮箱',
  `createTime` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of plat_user
-- ----------------------------
INSERT INTO `plat_user` VALUES (1, '超级管理员', 'xxs', '202cb962ac59075b964b07152d234b70', 1, 1, 'https://img0.baidu.com/it/u=3233551726,336273710&fm=26&fmt=auto&gp=0.jpg', NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
