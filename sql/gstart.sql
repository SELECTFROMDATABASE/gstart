/*
Navicat MySQL Data Transfer

Source Server         : LoacalMySql
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : gstart

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-05-08 20:04:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for g_sys_config
-- ----------------------------
DROP TABLE IF EXISTS `g_sys_config`;
CREATE TABLE `g_sys_config` (
  `mainId` int(11) NOT NULL COMMENT '系统配置ID',
  `typeId` int(11) DEFAULT NULL COMMENT '系统配置类别ID',
  `configLabel` varchar(20) DEFAULT NULL COMMENT '配置标识',
  `configValue` varchar(500) DEFAULT NULL COMMENT '配置属性',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `remark` varchar(255) DEFAULT NULL,
  `orderId` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`mainId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置';

-- ----------------------------
-- Records of g_sys_config
-- ----------------------------
INSERT INTO `g_sys_config` VALUES ('1', '1', 'SYS_COPYRIGHT', '广州市有趣科技公司', '版权', null, null);

-- ----------------------------
-- Table structure for g_sys_config_type
-- ----------------------------
DROP TABLE IF EXISTS `g_sys_config_type`;
CREATE TABLE `g_sys_config_type` (
  `mainId` int(11) NOT NULL COMMENT '系统配置ID',
  `typeName` varchar(50) DEFAULT NULL COMMENT '类别名称',
  `orderId` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`mainId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置类别';

-- ----------------------------
-- Records of g_sys_config_type
-- ----------------------------
INSERT INTO `g_sys_config_type` VALUES ('1', '系统', '100');

-- ----------------------------
-- Table structure for g_sys_department
-- ----------------------------
DROP TABLE IF EXISTS `g_sys_department`;
CREATE TABLE `g_sys_department` (
  `mainId` int(11) NOT NULL COMMENT '部门ID',
  `parentId` int(11) DEFAULT NULL COMMENT '上级部门ID',
  `departmentNo` varchar(20) DEFAULT NULL COMMENT '部门编号',
  `departmentName` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `orderId` int(11) DEFAULT NULL COMMENT '排序',
  `hasChild` int(11) DEFAULT NULL COMMENT '是否包含子节点',
  `isEnabled` int(11) DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`mainId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门';

-- ----------------------------
-- Records of g_sys_department
-- ----------------------------
INSERT INTO `g_sys_department` VALUES ('1', '0', '01', '企业', '', null, '1', '1');
INSERT INTO `g_sys_department` VALUES ('12', '0', '02', '系统开发', '', null, '1', '1');
INSERT INTO `g_sys_department` VALUES ('13', '12', '0201', '研发小组', '', null, '0', '1');
INSERT INTO `g_sys_department` VALUES ('14', '12', '0202', '产品测试', '', null, '0', '1');
INSERT INTO `g_sys_department` VALUES ('15', '1', '0101', '天普药业', '', null, '0', '1');
INSERT INTO `g_sys_department` VALUES ('21', '16', '0301', '测试小组', '', null, '0', '1');

-- ----------------------------
-- Table structure for g_sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `g_sys_login_log`;
CREATE TABLE `g_sys_login_log` (
  `mainId` varchar(50) NOT NULL COMMENT '角色菜单ID',
  `userId` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `sessionId` varchar(50) DEFAULT NULL COMMENT '会话ID',
  `loginTime` datetime DEFAULT NULL COMMENT '登录时间',
  `logoutTime` datetime DEFAULT NULL COMMENT '登出时间',
  `ip` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `userAgent` varchar(255) DEFAULT NULL COMMENT '浏览器',
  `os` varchar(10) DEFAULT NULL COMMENT '操作系统',
  `screenSize` varchar(10) DEFAULT NULL COMMENT '屏幕分辨率',
  `loginMode` int(11) DEFAULT NULL COMMENT '登录模式',
  PRIMARY KEY (`mainId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录日志';

-- ----------------------------
-- Records of g_sys_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for g_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `g_sys_menu`;
CREATE TABLE `g_sys_menu` (
  `mainId` int(11) NOT NULL COMMENT '菜单ID',
  `parentId` int(11) DEFAULT NULL COMMENT '父菜单ID',
  `menuNo` varchar(20) DEFAULT NULL COMMENT '菜单编号',
  `menuName` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menuLabel` varchar(100) DEFAULT NULL COMMENT '菜单标签',
  `url` varchar(255) DEFAULT NULL COMMENT 'URL地址',
  `icon80` varchar(255) DEFAULT NULL COMMENT '图标80',
  `icon32` varchar(255) DEFAULT NULL COMMENT '图标32',
  `icon16` varchar(255) DEFAULT NULL COMMENT '图标16',
  `isAuth` int(11) DEFAULT NULL COMMENT '要求授权',
  `target` varchar(20) DEFAULT NULL COMMENT '打开目标',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `orderId` int(11) DEFAULT NULL COMMENT '排序',
  `hasChild` int(11) DEFAULT NULL COMMENT '是否包含子节点',
  `isEnabled` int(11) DEFAULT NULL COMMENT '是否启用',
  `apptype` int(11) DEFAULT '1',
  `iconClass` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mainId`),
  KEY `FKilv2j9mh7v8d8ggwp1abrpin` (`parentId`),
  CONSTRAINT `FKilv2j9mh7v8d8ggwp1abrpin` FOREIGN KEY (`parentId`) REFERENCES `g_sys_menu` (`mainId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单';

-- ----------------------------
-- Records of g_sys_menu
-- ----------------------------
INSERT INTO `g_sys_menu` VALUES ('0', null, '0', '', null, null, null, null, null, null, null, null, null, null, null, '1', null);
INSERT INTO `g_sys_menu` VALUES ('1', '0', '01', '系统设置', 'sys', '', '', '', '', '1', '', '', '10', '1', '1', null, 'gear-b');
INSERT INTO `g_sys_menu` VALUES ('2', '1', '0101', '用户管理', 'sys.user', 'gstart/sys/user/user', '', '', '', '1', '', '', '100', '0', '1', null, null);
INSERT INTO `g_sys_menu` VALUES ('3', '1', '0102', '部门定义', '', 'gstart/sys/department/department', '', '', '', '1', '', '', '90', '0', '1', null, null);
INSERT INTO `g_sys_menu` VALUES ('4', '1', '0103', '岗位定义', '', 'gstart/sys/position/position', '', '', '', '1', '', '', '70', '0', '1', null, null);
INSERT INTO `g_sys_menu` VALUES ('5', '1', '0104', '角色定义', '', 'gstart/sys/role/role', '', '', '', '1', '', '', '66', '0', '1', null, null);
INSERT INTO `g_sys_menu` VALUES ('6', '1', '0105', '菜单定义', '', 'gstart/sys/menu/menu', '', '', '', '1', '', '', '60', '0', '1', null, null);
INSERT INTO `g_sys_menu` VALUES ('20', '1', '0106', '权限分配', '', '#', '', '', '', '1', '', '', '56', '0', '1', null, null);
INSERT INTO `g_sys_menu` VALUES ('26', '1', '0107', '系统配置', '', 'gstart/sys/config/config', '', '', '', '1', '', '', '44', '0', '1', null, null);
INSERT INTO `g_sys_menu` VALUES ('36', '1', '0108', '代码工具', '', 'gstart/code/vue_builder', '', '', '', '1', '', '', '33', '0', '1', null, null);
INSERT INTO `g_sys_menu` VALUES ('37', '0', '02', '资源管理', null, null, null, null, null, '1', null, null, '20', '1', '1', '1', 'upload');
INSERT INTO `g_sys_menu` VALUES ('38', '37', '0201', '文章管理', null, 'cms/article', null, null, null, '1', null, null, '100', '0', '1', '1', null);

-- ----------------------------
-- Table structure for g_sys_menuright
-- ----------------------------
DROP TABLE IF EXISTS `g_sys_menuright`;
CREATE TABLE `g_sys_menuright` (
  `mainId` int(11) NOT NULL COMMENT '菜单权限ID',
  `menuId` int(11) DEFAULT NULL COMMENT '菜单ID',
  `rightName` varchar(50) DEFAULT NULL COMMENT '权限名称',
  `rightLabel` varchar(20) DEFAULT NULL COMMENT '权限标识',
  PRIMARY KEY (`mainId`),
  KEY `FK_menuright_ref_menu` (`menuId`),
  CONSTRAINT `FK_menuright_ref_menu` FOREIGN KEY (`menuId`) REFERENCES `g_sys_menu` (`mainId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单权限';

-- ----------------------------
-- Records of g_sys_menuright
-- ----------------------------
INSERT INTO `g_sys_menuright` VALUES ('1', '2', '编辑', 'edit');
INSERT INTO `g_sys_menuright` VALUES ('2', '2', '删除', 'del');

-- ----------------------------
-- Table structure for g_sys_position
-- ----------------------------
DROP TABLE IF EXISTS `g_sys_position`;
CREATE TABLE `g_sys_position` (
  `mainId` int(11) NOT NULL COMMENT '岗位ID',
  `departmentId` int(11) DEFAULT NULL COMMENT '所属部门ID',
  `positionName` varchar(50) DEFAULT NULL COMMENT '岗位名称',
  `description` varchar(500) DEFAULT NULL COMMENT '岗位描述',
  `superiorPosId` int(11) DEFAULT NULL COMMENT '上级岗位ID',
  `adminGrade` int(11) DEFAULT NULL COMMENT '管理级别',
  `orderId` int(11) DEFAULT NULL COMMENT '排序',
  `isEnabled` int(11) DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`mainId`),
  KEY `FK_position_ref_dep` (`departmentId`),
  CONSTRAINT `FK_position_ref_dep` FOREIGN KEY (`departmentId`) REFERENCES `g_sys_department` (`mainId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='岗位';

-- ----------------------------
-- Records of g_sys_position
-- ----------------------------
INSERT INTO `g_sys_position` VALUES ('1', '15', '系统管理', '', null, '0', null, '1');
INSERT INTO `g_sys_position` VALUES ('2', '14', '测试员', '', null, '0', null, '1');
INSERT INTO `g_sys_position` VALUES ('3', '13', '开发者', '', null, '0', null, '1');

-- ----------------------------
-- Table structure for g_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `g_sys_role`;
CREATE TABLE `g_sys_role` (
  `mainId` int(11) NOT NULL COMMENT '角色ID',
  `parentId` int(11) DEFAULT NULL COMMENT '父角色ID',
  `roleNo` varchar(50) DEFAULT NULL COMMENT '角色编号',
  `roleName` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `orderId` int(11) DEFAULT NULL COMMENT '排序',
  `hasChild` int(11) DEFAULT NULL COMMENT '是否包含子节点',
  `isEnabled` int(11) DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`mainId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色';

-- ----------------------------
-- Records of g_sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for g_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `g_sys_role_menu`;
CREATE TABLE `g_sys_role_menu` (
  `mainId` varchar(50) NOT NULL COMMENT '角色菜单ID',
  `roleId` int(11) DEFAULT NULL COMMENT '角色ID',
  `menuId` int(11) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`mainId`),
  KEY `FK_rolemenu_ref_menu` (`menuId`),
  KEY `FK_rolemenu_ref_role` (`roleId`),
  CONSTRAINT `FK_rolemenu_ref_menu` FOREIGN KEY (`menuId`) REFERENCES `g_sys_menu` (`mainId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_rolemenu_ref_role` FOREIGN KEY (`roleId`) REFERENCES `g_sys_role` (`mainId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单';

-- ----------------------------
-- Records of g_sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for g_sys_role_menuright
-- ----------------------------
DROP TABLE IF EXISTS `g_sys_role_menuright`;
CREATE TABLE `g_sys_role_menuright` (
  `mainId` varchar(50) NOT NULL COMMENT '角色菜单权限ID',
  `roleId` int(11) DEFAULT NULL COMMENT '角色ID',
  `menurightId` int(11) DEFAULT NULL COMMENT '菜单权限ID',
  PRIMARY KEY (`mainId`),
  KEY `FK_rolehasmr_ref_mr` (`menurightId`),
  KEY `FK_rolehasmr_ref_role` (`roleId`),
  CONSTRAINT `FK_rolehasmr_ref_mr` FOREIGN KEY (`menurightId`) REFERENCES `g_sys_menuright` (`mainId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_rolehasmr_ref_role` FOREIGN KEY (`roleId`) REFERENCES `g_sys_role` (`mainId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单权限';

-- ----------------------------
-- Records of g_sys_role_menuright
-- ----------------------------

-- ----------------------------
-- Table structure for g_sys_role_position
-- ----------------------------
DROP TABLE IF EXISTS `g_sys_role_position`;
CREATE TABLE `g_sys_role_position` (
  `mainId` varchar(50) NOT NULL COMMENT '角色岗位ID',
  `roleId` int(11) DEFAULT NULL COMMENT '角色ID',
  `positionId` int(11) DEFAULT NULL COMMENT '岗位ID',
  PRIMARY KEY (`mainId`),
  KEY `FK_rolepos_ref_pos` (`positionId`),
  KEY `FK_rolepos_ref_role` (`roleId`),
  CONSTRAINT `FK_rolepos_ref_pos` FOREIGN KEY (`positionId`) REFERENCES `g_sys_position` (`mainId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_rolepos_ref_role` FOREIGN KEY (`roleId`) REFERENCES `g_sys_role` (`mainId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色岗位';

-- ----------------------------
-- Records of g_sys_role_position
-- ----------------------------

-- ----------------------------
-- Table structure for g_sys_support
-- ----------------------------
DROP TABLE IF EXISTS `g_sys_support`;
CREATE TABLE `g_sys_support` (
  `mainid` varchar(50) NOT NULL,
  `ch_href` varchar(4000) DEFAULT NULL,
  `ch_title` varchar(500) DEFAULT NULL,
  `ch_target` varchar(20) DEFAULT NULL,
  `orderby` int(11) DEFAULT NULL,
  `iseable` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`mainid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of g_sys_support
-- ----------------------------

-- ----------------------------
-- Table structure for g_sys_trace
-- ----------------------------
DROP TABLE IF EXISTS `g_sys_trace`;
CREATE TABLE `g_sys_trace` (
  `mainId` varchar(50) NOT NULL,
  `userId` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `traceTime` datetime DEFAULT NULL COMMENT '操作时间',
  `traceTableName` varchar(50) DEFAULT NULL COMMENT '操作表',
  `traceColumnName` varchar(50) DEFAULT NULL COMMENT '操作字段名',
  `traceMainId` varchar(50) DEFAULT NULL COMMENT '记录主键',
  `oldVal` text COMMENT '原值',
  `newVal` text COMMENT '新值',
  PRIMARY KEY (`mainId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of g_sys_trace
-- ----------------------------

-- ----------------------------
-- Table structure for g_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `g_sys_user`;
CREATE TABLE `g_sys_user` (
  `mainId` varchar(50) NOT NULL COMMENT '用户ID',
  `userName` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `pwdSalt` varchar(50) DEFAULT NULL COMMENT '密码盐',
  `name` varchar(100) DEFAULT NULL COMMENT '用户名称',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `positionId` int(11) DEFAULT NULL COMMENT '所在岗位ID',
  `mobile` varchar(50) DEFAULT NULL COMMENT '移动电话',
  `tel` varchar(50) DEFAULT NULL COMMENT '办公电话',
  `fax` varchar(50) DEFAULT NULL COMMENT '传真',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `imageUrl` varchar(255) DEFAULT NULL COMMENT '图片URL',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `loginCount` int(11) DEFAULT NULL COMMENT '登录次数',
  `extraPositionIds` varchar(50) DEFAULT NULL COMMENT '兼职岗位ID',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '上次登录时间',
  `lastLoginIP` varchar(100) DEFAULT NULL COMMENT '上次登录IP',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `creatorId` varchar(50) DEFAULT NULL COMMENT '创建人ID',
  `lastModifyTime` datetime DEFAULT NULL COMMENT '最后修改时间',
  `lastModifierId` varchar(50) DEFAULT NULL COMMENT '最后修改人ID',
  `isEnabled` int(11) DEFAULT '0' COMMENT '是否启用',
  PRIMARY KEY (`mainId`),
  KEY `IX_f_sys_user` (`userName`),
  KEY `FK_user_ref_position` (`positionId`),
  CONSTRAINT `FK_user_ref_position` FOREIGN KEY (`positionId`) REFERENCES `g_sys_position` (`mainId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of g_sys_user
-- ----------------------------

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('1');
