/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : permission

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 02/12/2020 18:59:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl`;
CREATE TABLE `sys_acl`  (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `surrogate_id` bigint(22) NOT NULL COMMENT '权限id唯一主键',
  `number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '权限编码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '权限名',
  `acl_module_id` bigint(22) NOT NULL COMMENT '权限模块id',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '请求的url',
  `type` int(2) NOT NULL DEFAULT 1 COMMENT '1:菜单权限, 2按钮, 3其他',
  `status` int(2) DEFAULT NULL COMMENT '状态, 0正常, 冻结',
  `seq` int(2) DEFAULT NULL COMMENT '排序',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作人',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作ip',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_surrogate_key`(`surrogate_id`) USING BTREE COMMENT '唯一键'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_acl_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl_module`;
CREATE TABLE `sys_acl_module`  (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `surrogate_id` bigint(22) NOT NULL COMMENT '权限模块id,唯一主键',
  `number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '权限模块编码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '权限模块名称',
  `parent_id` bigint(22) NOT NULL COMMENT '父surrogate_id',
  `level` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '权限模块层级',
  `seq` int(2) NOT NULL COMMENT '顺序',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '0正常, 1冻结',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作人',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作ip',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_surrogate_key`(`surrogate_id`) USING BTREE COMMENT '唯一键'
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `surrogate_id` bigint(22) NOT NULL COMMENT '唯一主键',
  `number` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '部门编号',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `parent_id` bigint(22) NOT NULL COMMENT '父id',
  `level` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '部门层级, 0. / 0.1, 0.2',
  `seq` int(2) DEFAULT NULL COMMENT '排序, 部门咋当前层级目录下的顺序',
  `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作人',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作ip',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_surrogate_key`(`surrogate_id`) USING BTREE COMMENT '唯一主键'
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `surrogate_id` bigint(22) NOT NULL COMMENT '数据字典id唯一主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '数据字典名称',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `deleted` int(1) DEFAULT NULL COMMENT '删除状态, 0正常, 1删除',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '操作人',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作ip',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_surrogate_id`(`surrogate_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail`  (
  `surrogate_id` bigint(22) NOT NULL COMMENT '数据字典id唯一主键',
  `parent_id` bigint(22) NOT NULL COMMENT '数据字典主表id',
  `number` int(2) NOT NULL COMMENT '明细编号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '数据字典明细名称',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`surrogate_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `surrogate_id` bigint(22) NOT NULL COMMENT '日志id,唯一主键',
  `type` int(2) NOT NULL COMMENT '1部门，2用户，3权限模块, 4权限， 5角色, 6角色用户关系, 7角色权限关系',
  `target_id` bigint(22) NOT NULL COMMENT '各个模块的主键id, 涉及到关联关系的操作存放的是角色id',
  `old_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '更新前的值',
  `new_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '更新后的值',
  `status` int(2) DEFAULT NULL COMMENT '状态, 当前是否复原过, 0没有, 1复原过',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作人',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作ip',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_surrogate_key`(`surrogate_id`) USING BTREE COMMENT '唯一id'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `surrogate_id` bigint(22) NOT NULL COMMENT '角色id唯一主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
  `type` int(2) NOT NULL COMMENT '角色类型, 1超级管理员, 2管理员, 3.普通角色',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `deleted` int(1) DEFAULT NULL COMMENT '删除状态, 0正常, 1删除',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '操作人',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作ip',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_surrogate_key`(`surrogate_id`) USING BTREE COMMENT '唯一键'
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_acl`;
CREATE TABLE `sys_role_acl`  (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `surrogate_id` bigint(22) NOT NULL COMMENT '角色-权限id唯一主键',
  `role_id` bigint(22) DEFAULT NULL COMMENT '角色id',
  `acl_id` bigint(22) DEFAULT NULL COMMENT '权限id',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作人',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作ip',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_surrogate_key`(`surrogate_id`) USING BTREE COMMENT '唯一键'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `surrogate_id` bigint(22) NOT NULL COMMENT '角色-用户id唯一主键',
  `role_id` bigint(22) NOT NULL COMMENT '角色id',
  `user_id` bigint(22) NOT NULL COMMENT '用户id',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作ip',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_surrogate_key`(`surrogate_id`) USING BTREE COMMENT '唯一键'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `surrogate_id` bigint(22) NOT NULL COMMENT '唯一主键',
  `number` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工编号',
  `login_account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '登录账号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工姓名',
  `telephone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '员工电话',
  `mail` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `dept_id` bigint(22) DEFAULT NULL COMMENT '用户所在部门id',
  `status` int(1) DEFAULT NULL COMMENT '状态, 0正常, 1冻结',
  `deleted` int(1) DEFAULT NULL COMMENT '0正常, 1删除',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作人',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作ip',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_surrogate_key`(`surrogate_id`) USING BTREE COMMENT '唯一键'
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
