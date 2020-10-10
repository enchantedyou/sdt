/*
Navicat MySQL Data Transfer

Source Server         : ins-98b0wg4i
Source Server Version : 80020
Source Host           : 106.52.66.244:3306
Source Database       : sdt

Target Server Type    : MYSQL
Target Server Version : 80020
File Encoding         : 65001

Date: 2020-09-28 13:28:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sdb_batch_execution
-- ----------------------------
DROP TABLE IF EXISTS `sdb_batch_execution`;
CREATE TABLE `sdb_batch_execution` (
  `trxn_seq` varchar(32) NOT NULL,
  `tran_flow_id` varchar(20) NOT NULL,
  `batch_run_no` varchar(50) NOT NULL,
  `busi_org_id` varchar(3) NOT NULL,
  `dayend_manage_date` varchar(8) DEFAULT NULL,
  `tran_state` varchar(10) DEFAULT NULL,
  `tran_group_id` varchar(10) DEFAULT NULL,
  `data_create_user` varchar(50) DEFAULT NULL,
  `data_create_time` varchar(32) DEFAULT NULL,
  `data_update_user` varchar(50) DEFAULT NULL,
  `data_update_time` varchar(32) DEFAULT NULL,
  `data_version` int DEFAULT '0',
  PRIMARY KEY (`busi_org_id`,`tran_flow_id`,`batch_run_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sdb_batch_execution
-- ----------------------------

-- ----------------------------
-- Table structure for sdb_batch_sub_execution
-- ----------------------------
DROP TABLE IF EXISTS `sdb_batch_sub_execution`;
CREATE TABLE `sdb_batch_sub_execution` (
  `trxn_seq` varchar(32) NOT NULL,
  `system_code` varchar(20) NOT NULL,
  `flow_step_id` varchar(20) NOT NULL,
  `trxn_date` varchar(8) DEFAULT NULL,
  `tran_state` varchar(10) DEFAULT NULL,
  `error_message` text,
  `error_stack` text,
  `tran_group_id` varchar(10) DEFAULT NULL,
  `data_create_user` varchar(50) DEFAULT NULL,
  `data_create_time` varchar(32) DEFAULT NULL,
  `data_update_user` varchar(50) DEFAULT NULL,
  `data_update_time` varchar(32) DEFAULT NULL,
  `data_version` int DEFAULT '0',
  PRIMARY KEY (`trxn_seq`,`system_code`,`flow_step_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sdb_batch_sub_execution
-- ----------------------------

-- ----------------------------
-- Table structure for sdb_user
-- ----------------------------
DROP TABLE IF EXISTS `sdb_user`;
CREATE TABLE `sdb_user` (
  `user_acct` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_pwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `login_ip` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `login_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `lock_ind` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_permission` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `data_create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `data_create_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `data_update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `data_update_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `data_version` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_acct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sdb_user
-- ----------------------------
INSERT INTO `sdb_user` VALUES ('admin', '712fd505af8c1c919c85036dc52c3138', '0:0:0:0:0:0:0:1', '2020-09-25 17:32:51.570', 'N', 'NORMAL', null, '2019-07-22 14:27:20.217', 'admin', '2020-09-25 17:32:51.645', '323');

-- ----------------------------
-- Table structure for sdp_batch_date
-- ----------------------------
DROP TABLE IF EXISTS `sdp_batch_date`;
CREATE TABLE `sdp_batch_date` (
  `busi_org_id` varchar(10) NOT NULL,
  `trxn_date` varchar(8) NOT NULL,
  `data_create_user` varchar(50) DEFAULT NULL,
  `data_create_time` varchar(32) DEFAULT NULL,
  `data_update_user` varchar(50) DEFAULT NULL,
  `data_update_time` varchar(32) DEFAULT NULL,
  `data_version` int DEFAULT '0',
  PRIMARY KEY (`busi_org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sdp_batch_date
-- ----------------------------
INSERT INTO `sdp_batch_date` VALUES ('025', '20191107', null, null, null, null, '0');

-- ----------------------------
-- Table structure for sdp_batch_flow
-- ----------------------------
DROP TABLE IF EXISTS `sdp_batch_flow`;
CREATE TABLE `sdp_batch_flow` (
  `system_code` varchar(20) NOT NULL,
  `sub_system_code` varchar(20) NOT NULL,
  `flow_group` varchar(20) NOT NULL,
  `datasource_id` varchar(50) NOT NULL,
  `flow_desc` varchar(100) NOT NULL,
  `flow_order` int NOT NULL,
  `is_enabled` tinyint(1) NOT NULL DEFAULT '0',
  `data_create_user` varchar(50) DEFAULT NULL,
  `data_create_time` varchar(32) DEFAULT NULL,
  `data_update_user` varchar(50) DEFAULT NULL,
  `data_update_time` varchar(32) DEFAULT NULL,
  `data_version` int DEFAULT '0',
  PRIMARY KEY (`system_code`,`sub_system_code`,`flow_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sdp_batch_flow
-- ----------------------------
INSERT INTO `sdp_batch_flow` VALUES ('102', '1022', 'default', 'ln_dev', 'Development environment end-of-day batch process', '1', '1', null, null, null, null, '0');
INSERT INTO `sdp_batch_flow` VALUES ('204', '2041', 'default', 'cl_dev', 'Development environment end-of-day batch process', '2', '0', null, null, null, null, '0');

-- ----------------------------
-- Table structure for sdp_batch_step
-- ----------------------------
DROP TABLE IF EXISTS `sdp_batch_step`;
CREATE TABLE `sdp_batch_step` (
  `flow_step_id` varchar(20) NOT NULL,
  `flow_step_group` varchar(20) NOT NULL,
  `flow_step_name` varchar(100) NOT NULL,
  `is_enabled` tinyint(1) NOT NULL DEFAULT '0',
  `data_create_user` varchar(50) DEFAULT NULL,
  `data_create_time` varchar(32) DEFAULT NULL,
  `data_update_user` varchar(50) DEFAULT NULL,
  `data_update_time` varchar(32) DEFAULT NULL,
  `data_version` int DEFAULT '0',
  PRIMARY KEY (`flow_step_id`,`flow_step_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sdp_batch_step
-- ----------------------------
INSERT INTO `sdp_batch_step` VALUES ('Clear', '999', 'Date switch after - Clear', '1', null, null, null, null, '0');
INSERT INTO `sdp_batch_step` VALUES ('Finish', '1000', 'Date switch after - Finish', '1', null, null, null, null, '0');
INSERT INTO `sdp_batch_step` VALUES ('General', '600', 'Date switch after - General Level', '1', null, null, null, null, '0');
INSERT INTO `sdp_batch_step` VALUES ('High', '100', 'Date switch after - High Level', '1', null, null, null, null, '0');
INSERT INTO `sdp_batch_step` VALUES ('Low', '900', 'Date switch after - Low Level', '1', null, null, null, null, '0');
INSERT INTO `sdp_batch_step` VALUES ('Middle', '300', 'Date switch after - Middle Level', '1', null, null, null, null, '0');
INSERT INTO `sdp_batch_step` VALUES ('Switch', '10', 'Date switch', '1', null, null, null, null, '0');

-- ----------------------------
-- Table structure for sdp_business_parameter
-- ----------------------------
DROP TABLE IF EXISTS `sdp_business_parameter`;
CREATE TABLE `sdp_business_parameter` (
  `main_key` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sub_key` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parm_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parm_value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parm_remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `data_create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `data_create_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `data_update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `data_update_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `data_version` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`main_key`,`sub_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sdp_business_parameter
-- ----------------------------

-- ----------------------------
-- Table structure for sdp_datasource
-- ----------------------------
DROP TABLE IF EXISTS `sdp_datasource`;
CREATE TABLE `sdp_datasource` (
  `datasource_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `datasource_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tlsql_ind` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `platform_table_prefix` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `datasource_desc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `datasource_driver` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `datasource_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `datasource_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `datasource_pwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `data_create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `data_create_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `data_update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `data_update_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `data_version` int NOT NULL DEFAULT '0',
  `is_enabled` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`datasource_id`,`datasource_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sdp_datasource
-- ----------------------------
INSERT INTO `sdp_datasource` VALUES ('cbs_master', 'MYSQL', 'N', 'MS', '3.1主干集中式核心', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://10.22.62.2:3307/cd_icore?serverTimezone=Asia/Shanghai', 'vlog', 'vlog', '####', '####', 'admin', '2020-08-07 15:01:25.678', '1', '1');
INSERT INTO `sdp_datasource` VALUES ('cl_dev', 'MYSQL', 'N', 'MS', '额度dev', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://10.22.63.206:3306/dev_cl?serverTimezone=Asia/Shanghai', 'dev_cl', 'dev_cl', '####', '####', 'admin', '2020-08-07 15:01:57.619', '1', '1');
INSERT INTO `sdp_datasource` VALUES ('cl_dev_old', 'MYSQL', 'N', 'MS', '额度老版dev', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://10.22.12.37:3306/dev_cl?serverTimezone=Asia/Shanghai', 'dev_cl', 'dev_cl', '####', '####', 'admin', '2020-08-07 15:01:57.619', '1', '1');
INSERT INTO `sdp_datasource` VALUES ('cm_dev', 'MYSQL', 'N', 'MS', '公共dev', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://10.22.63.206:3306/dev_cm?serverTimezone=Asia/Shanghai', 'dev_cm', 'dev_cm', '####', '####', 'admin', '2020-08-07 15:01:57.619', '1', '1');
INSERT INTO `sdp_datasource` VALUES ('ct_dev', 'MYSQL', 'N', 'MS', '内管dev', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://10.22.63.206:3306/dev_ct?serverTimezone=Asia/Shanghai', 'dev_ct', 'dev_ct', '####', '####', 'admin', '2020-08-07 15:01:57.619', '1', '1');
INSERT INTO `sdp_datasource` VALUES ('dp_dev', 'MYSQL', 'N', 'MS', '存款dev', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://10.22.63.206:3306/dev_dp?serverTimezone=Asia/Shanghai', 'dev_dp', 'dev_dp', '####', '####', 'admin', '2020-08-07 15:01:46.655', '1', '1');
INSERT INTO `sdp_datasource` VALUES ('ds_dev', 'MYSQL', 'N', 'MS', '决策dev', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://10.22.63.206:3306/dev_ds?serverTimezone=Asia/Shanghai', 'dev_ds', 'dev_ds', '####', '####', 'admin', '2020-08-07 15:01:57.619', '1', '1');
INSERT INTO `sdp_datasource` VALUES ('ln_dev', 'MYSQL', 'N', 'MS', '贷款dev', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://10.22.63.206:3306/dev_ln?serverTimezone=Asia/Shanghai', 'dev_ln', 'dev_ln', '####', '####', 'admin', '2020-08-07 15:01:56.171', '1', '1');
INSERT INTO `sdp_datasource` VALUES ('ln_dev_old', 'MYSQL', 'N', 'MS', '贷款老版dev', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://10.22.12.37:3306/dev_ln?serverTimezone=Asia/Shanghai', 'dev_ln', 'dev_ln', '####', '####', 'admin', '2020-08-07 15:01:56.171', '1', '1');
INSERT INTO `sdp_datasource` VALUES ('ln_fat2', 'MYSQL', 'N', 'MS', '贷款fat2', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://10.22.63.1:3306/poc_ln?serverTimezone=Asia/Shanghai', 'vlog', 'vlog', '####', '####', 'admin', '2020-08-07 15:01:56.171', '1', '1');
INSERT INTO `sdp_datasource` VALUES ('sump_fat2', 'MYSQL', 'N', 'MS', 'sump fat2', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://10.22.63.1:3306/poc_sump?serverTimezone=Asia/Shanghai', 'vlog', 'vlog', '####', '####', 'admin', '2020-08-07 15:01:53.635', '1', '1');
INSERT INTO `sdp_datasource` VALUES ('ver_cl', 'MYSQL', 'N', 'MS', '额度稽核', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://106.52.66.244:3306/ver_cl?serverTimezone=Asia/Shanghai', 'root', 'ssy7779..', '####', '####', 'admin', '2020-09-22 11:10:37.044', '2', '0');
INSERT INTO `sdp_datasource` VALUES ('ver_ln', 'MYSQL', 'N', 'MS', '贷款稽核', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://106.52.66.244:3306/ver_ln?serverTimezone=Asia/Shanghai', 'root', 'ssy7779..', '####', '####', 'admin', '2020-09-22 11:10:51.754', '2', '0');
INSERT INTO `sdp_datasource` VALUES ('yf_dit', 'MYSQL', 'N', 'MS', '研发2.0 dit', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://10.22.60.59:3306/yf_cbs?serverTimezone=Asia/Shanghai', 'yfcbs', 'yfcbs', '####', '####', 'admin', '2020-08-07 15:01:51.823', '1', '1');

-- ----------------------------
-- Table structure for sdp_dict_priority
-- ----------------------------
DROP TABLE IF EXISTS `sdp_dict_priority`;
CREATE TABLE `sdp_dict_priority` (
  `dict_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dict_priority` int NOT NULL,
  `is_enabled` tinyint(1) NOT NULL DEFAULT '1',
  `group_id` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '*',
  `data_create_user` varchar(50) DEFAULT NULL,
  `data_create_time` varchar(32) DEFAULT NULL,
  `data_update_user` varchar(50) DEFAULT NULL,
  `data_update_time` varchar(32) DEFAULT NULL,
  `data_version` int DEFAULT '0',
  PRIMARY KEY (`dict_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sdp_dict_priority
-- ----------------------------
INSERT INTO `sdp_dict_priority` VALUES ('ClBaseDict', '6', '1', 'cl', null, null, null, null, '0');
INSERT INTO `sdp_dict_priority` VALUES ('ClSysDict', '7', '1', 'cl', null, null, null, null, '0');
INSERT INTO `sdp_dict_priority` VALUES ('CoServDict', '9', '1', 'ln', null, null, null, null, '0');
INSERT INTO `sdp_dict_priority` VALUES ('LnBaseDict', '6', '1', 'ln', null, null, null, null, '0');
INSERT INTO `sdp_dict_priority` VALUES ('LnIobusDict', '8', '1', 'ln', null, null, null, null, '0');
INSERT INTO `sdp_dict_priority` VALUES ('LnServDict', '9', '1', 'ln', null, null, null, null, '0');
INSERT INTO `sdp_dict_priority` VALUES ('LnSysDict', '7', '1', 'ln', null, null, null, null, '0');
INSERT INTO `sdp_dict_priority` VALUES ('MsDict', '1', '1', '*', null, null, null, null, '0');
INSERT INTO `sdp_dict_priority` VALUES ('PfOtherServDict', '9', '1', 'pf', null, null, null, null, '0');
INSERT INTO `sdp_dict_priority` VALUES ('PfServDict', '9', '1', 'pf', null, null, null, null, '0');
INSERT INTO `sdp_dict_priority` VALUES ('SysDict', '2', '1', '*', null, null, null, null, '0');

-- ----------------------------
-- Table structure for sdp_drop_list
-- ----------------------------
DROP TABLE IF EXISTS `sdp_drop_list`;
CREATE TABLE `sdp_drop_list` (
  `drop_list_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `drop_list_value` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `drop_list_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `data_sort` int NOT NULL,
  `enable_ind` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `data_create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `data_create_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `data_update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `data_update_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `data_version` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`drop_list_type`,`drop_list_value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sdp_drop_list
-- ----------------------------

-- ----------------------------
-- Table structure for sdp_enum_priority
-- ----------------------------
DROP TABLE IF EXISTS `sdp_enum_priority`;
CREATE TABLE `sdp_enum_priority` (
  `enum_type` varchar(50) NOT NULL,
  `enum_priority` int NOT NULL,
  `is_enabled` tinyint(1) NOT NULL DEFAULT '1',
  `group_id` varchar(5) NOT NULL DEFAULT '*',
  `data_create_user` varchar(50) DEFAULT NULL,
  `data_create_time` varchar(32) DEFAULT NULL,
  `data_update_user` varchar(50) DEFAULT NULL,
  `data_update_time` varchar(32) DEFAULT NULL,
  `data_version` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`enum_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sdp_enum_priority
-- ----------------------------
INSERT INTO `sdp_enum_priority` VALUES ('ClBaseEnumType', '3', '1', 'cl', null, null, null, null, '0');
INSERT INTO `sdp_enum_priority` VALUES ('ClSysEnumType', '4', '1', 'cl', null, null, null, null, '0');
INSERT INTO `sdp_enum_priority` VALUES ('CoServEnumType', '6', '1', 'ln', null, null, null, null, '0');
INSERT INTO `sdp_enum_priority` VALUES ('EnumType', '2', '1', '*', null, null, null, null, '0');
INSERT INTO `sdp_enum_priority` VALUES ('LnBaseEnumType', '3', '1', 'ln', null, null, null, null, '0');
INSERT INTO `sdp_enum_priority` VALUES ('LnIobusEnumType', '5', '1', 'ln', null, null, null, null, '0');
INSERT INTO `sdp_enum_priority` VALUES ('LnServEnumType', '6', '1', 'ln', null, null, null, null, '0');
INSERT INTO `sdp_enum_priority` VALUES ('LnSysEnumType', '4', '1', 'ln', null, null, null, null, '0');
INSERT INTO `sdp_enum_priority` VALUES ('MsEnumType', '1', '1', '*', null, null, null, null, '0');
INSERT INTO `sdp_enum_priority` VALUES ('PfOtherServEnumType', '6', '1', 'pf', null, null, null, null, '0');
INSERT INTO `sdp_enum_priority` VALUES ('PfServEnumType', '6', '1', 'pf', null, null, null, null, '0');

-- ----------------------------
-- Table structure for sdp_module_mapping
-- ----------------------------
DROP TABLE IF EXISTS `sdp_module_mapping`;
CREATE TABLE `sdp_module_mapping` (
  `module_id` varchar(2) NOT NULL,
  `module_desc` varchar(50) NOT NULL,
  `service_code_prefix` varchar(2) NOT NULL,
  `system_code` varchar(10) NOT NULL,
  `sub_system_code` varchar(10) NOT NULL,
  `gitlab_search_url` varchar(1024) DEFAULT NULL,
  `data_create_user` varchar(50) DEFAULT NULL,
  `data_create_time` varchar(32) DEFAULT NULL,
  `data_update_user` varchar(50) DEFAULT NULL,
  `data_update_time` varchar(32) DEFAULT NULL,
  `data_version` int DEFAULT '0',
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sdp_module_mapping
-- ----------------------------
INSERT INTO `sdp_module_mapping` VALUES ('cf', 'Customer', '55', '201', '2011', null, null, null, null, null, '0');
INSERT INTO `sdp_module_mapping` VALUES ('cl', 'Credit Limit', '59', '204', '2041', 'http://e-git.yfb.sunline.cn/icore3.0/base/cl-base/-/merge_requests?scope=all&utf8=%E2%9C%93&state=merged&search=;http://e-git.yfb.sunline.cn/icore3.0/iobus/cl-iobus/-/merge_requests?scope=all&utf8=%E2%9C%93&state=merged&search=;http://e-git.yfb.sunline.cn/icore3.0/busi/cl-busi/-/merge_requests?scope=all&utf8=%E2%9C%93&state=merged&search=', null, null, null, null, '0');
INSERT INTO `sdp_module_mapping` VALUES ('cm', 'Common', '35', '103', '1031', null, null, null, null, null, '0');
INSERT INTO `sdp_module_mapping` VALUES ('ct', 'Counter', '21', '301', '3011', 'http://e-git.yfb.sunline.cn/icore3.0/busi/sump-vue/-/merge_requests?scope=all&utf8=%E2%9C%93&state=merged&search=;http://e-git.yfb.sunline.cn/icore3.0/busi/ct-dependency-dbscripts/-/merge_requests?scope=all&utf8=%E2%9C%93&state=merged&search=', null, null, null, null, '0');
INSERT INTO `sdp_module_mapping` VALUES ('dp', 'Deposit', '31', '100', '1001', null, null, null, null, null, '0');
INSERT INTO `sdp_module_mapping` VALUES ('ds', 'Decision', '58', '306', '3061', null, null, null, null, null, '0');
INSERT INTO `sdp_module_mapping` VALUES ('ln', 'Loan', '32', '102', '1021', 'http://e-git.yfb.sunline.cn/icore3.0/base/ln-base/-/merge_requests?scope=all&utf8=%E2%9C%93&state=merged&search=;http://e-git.yfb.sunline.cn/icore3.0/iobus/ln-iobus/-/merge_requests?scope=all&utf8=%E2%9C%93&state=merged&search=;http://e-git.yfb.sunline.cn/icore3.0/busi/ln-busi/-/merge_requests?scope=all&utf8=%E2%9C%93&state=merged&search=', null, null, null, null, '0');
INSERT INTO `sdp_module_mapping` VALUES ('us', 'User', '54', '201', '2011', null, null, null, null, null, '0');

-- ----------------------------
-- Table structure for sdp_sequence_builder
-- ----------------------------
DROP TABLE IF EXISTS `sdp_sequence_builder`;
CREATE TABLE `sdp_sequence_builder` (
  `seq_code` varchar(50) NOT NULL,
  `seq_desc` varchar(100) DEFAULT NULL,
  `init_value` bigint NOT NULL,
  `current_value` bigint DEFAULT NULL,
  `cache_size` bigint NOT NULL,
  `seq_length` int NOT NULL,
  `data_create_user` varchar(50) DEFAULT NULL,
  `data_create_time` varchar(32) DEFAULT NULL,
  `data_update_user` varchar(50) DEFAULT NULL,
  `data_update_time` varchar(32) DEFAULT NULL,
  `data_version` int DEFAULT '0',
  PRIMARY KEY (`seq_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sdp_sequence_builder
-- ----------------------------
INSERT INTO `sdp_sequence_builder` VALUES ('TRXN_SEQ', 'transaction sequence', '0', '157', '50', '13', null, null, 'S####', '2020-09-25 17:32:50.755', '161');

-- ----------------------------
-- Table structure for sdp_system_parameter
-- ----------------------------
DROP TABLE IF EXISTS `sdp_system_parameter`;
CREATE TABLE `sdp_system_parameter` (
  `main_key` varchar(32) NOT NULL,
  `sub_key` varchar(32) NOT NULL,
  `parm_desc` varchar(200) NOT NULL,
  `parm_value` varchar(200) NOT NULL,
  `parm_remark` varchar(500) NOT NULL,
  `data_create_user` varchar(50) DEFAULT NULL,
  `data_create_time` varchar(32) DEFAULT NULL,
  `data_update_user` varchar(50) DEFAULT NULL,
  `data_update_time` varchar(32) DEFAULT NULL,
  `data_version` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`main_key`,`sub_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sdp_system_parameter
-- ----------------------------
INSERT INTO `sdp_system_parameter` VALUES ('AES_ENC_KEY', '*', 'AES加密秘钥', '4SE8yJDIfh8sojM2', '', '####', '####', '####', '####', '0');
INSERT INTO `sdp_system_parameter` VALUES ('AES_RAND_KEY', '*', 'AES随机秘钥', 'I6eSLGY6dy6PMTp6', '', '####', '####', '####', '####', '0');
INSERT INTO `sdp_system_parameter` VALUES ('DEFAULT_TELLER', '*', '默认用户', 'S####', '', '####', '####', '####', '####', '0');
INSERT INTO `sdp_system_parameter` VALUES ('TIMEOUT_MILLIS', '*', '超时毫秒数', '30000', '', '####', '####', '####', '####', '0');

-- ----------------------------
-- Table structure for sds_packet
-- ----------------------------
DROP TABLE IF EXISTS `sds_packet`;
CREATE TABLE `sds_packet` (
  `trxn_seq` varchar(64) NOT NULL,
  `trxn_date` varchar(8) DEFAULT NULL,
  `trxn_desc` varchar(100) DEFAULT NULL,
  `request` varchar(4096) DEFAULT NULL,
  `response` text,
  `begin_time` varchar(32) DEFAULT NULL,
  `end_time` varchar(32) DEFAULT NULL,
  `used_time` varchar(32) DEFAULT NULL,
  `error_text` varchar(1000) DEFAULT NULL,
  `error_stack` text,
  `host_ip` varchar(32) DEFAULT NULL,
  `data_create_user` varchar(50) DEFAULT NULL,
  `data_create_time` varchar(32) DEFAULT NULL,
  `data_update_user` varchar(50) DEFAULT NULL,
  `data_update_time` varchar(32) DEFAULT NULL,
  `data_version` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`trxn_seq`),
  KEY `sds_packet_idx1` (`trxn_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sds_packet
-- ----------------------------
INSERT INTO `sds_packet` VALUES ('2020092114080000000000002', '20200921', 'login', '{\"userPwd\":\"712fd505af8c1c919c85036dc52c3138\",\"datasourceId\":\"\",\"userAcct\":\"admin\"}', null, '2020-09-21 14:08:39.629', '2020-09-21 14:08:39.738', '109', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 14:08:39.739', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092114090000000000005', '20200921', 'search dictionary list', '{\"key\":\"loan_no\"}', null, '2020-09-21 14:09:20.943', '2020-09-21 14:09:21.010', '67', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 14:09:21.010', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092114090000000000006', '20200921', 'search dictionary list', '{\"key\":\"loan_no\"}', null, '2020-09-21 14:09:29.429', '2020-09-21 14:09:29.480', '51', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 14:09:29.480', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092115100000000000009', '20200921', 'login', '{\"userPwd\":\"712fd505af8c1c919c85036dc52c3138\",\"datasourceId\":\"\",\"userAcct\":\"admin\"}', null, '2020-09-21 15:10:25.945', '2020-09-21 15:10:26.075', '130', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 15:10:26.075', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092115120000000000012', '20200921', 'search dictionary list', '{\"key\":\"rule_type\"}', null, '2020-09-21 15:12:59.755', '2020-09-21 15:12:59.810', '55', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 15:12:59.810', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092116340000000000015', '20200921', 'login', '{\"userPwd\":\"712fd505af8c1c919c85036dc52c3138\",\"datasourceId\":\"\",\"userAcct\":\"admin\"}', null, '2020-09-21 16:34:32.007', '2020-09-21 16:34:32.111', '104', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 16:34:32.111', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092116340000000000018', '20200921', 'search dictionary list', '{\"key\":\"accrual_income_bal\"}', null, '2020-09-21 16:34:34.522', '2020-09-21 16:34:34.581', '59', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 16:34:34.581', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092116350000000000019', '20200921', 'search dictionary list', '{\"key\":\"old_interest_bal\"}', null, '2020-09-21 16:35:21.143', '2020-09-21 16:35:21.201', '58', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 16:35:21.201', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092116380000000000020', '20200921', 'search dictionary list', '{\"key\":\"accrual_income_bal\"}', null, '2020-09-21 16:38:43.463', '2020-09-21 16:38:43.511', '48', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 16:38:43.511', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092116580000000000023', '20200921', 'login', '{\"userPwd\":\"712fd505af8c1c919c85036dc52c3138\",\"datasourceId\":\"\",\"userAcct\":\"admin\"}', null, '2020-09-21 16:58:01.327', '2020-09-21 16:58:01.429', '102', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 16:58:01.429', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092116580000000000027', '20200921', 'read gitlab merge diffs', '{\"jiraId\":\"PICORE3-4938\",\"moduleId\":\"ln\"}', null, '2020-09-21 16:58:42.591', '2020-09-21 16:58:44.178', '1587', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 16:58:44.178', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092116580000000000028', '20200921', 'read gitlab merge diffs', '{\"jiraId\":\"PICORE3-4938\",\"moduleId\":\"ct\"}', null, '2020-09-21 16:58:51.389', '2020-09-21 16:58:52.803', '1414', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 16:58:52.803', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092117320000000000029', '20200921', 'login', null, null, '2020-09-21 17:32:51.207', '2020-09-21 17:32:51.208', '1', '[1001]Login is invalid, please log in again', 'com.ssy.api.exception.SdtServError.E0009(SdtServError.java:65)\r\ncom.ssy.api.plugins.AuthorityInterceptor.preHandle(AuthorityInterceptor.java:31)\r\norg.springframework.web.servlet.HandlerExecutionChain.applyPreHandle(HandlerExecutionChain.java:141)\r\norg.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1035)\r\norg.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:943)\r\norg.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\r\norg.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909)\r\njavax.servlet.http.HttpServlet.service(HttpServlet.java:660)\r\norg.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\r\njavax.servlet.http.HttpServlet.service(HttpServlet.java:741)\r\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\ncom.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\r\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\norg.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\norg.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\norg.springframework.boot.actuate.metrics.web.servlet.WebMvcMetricsFilter.doFilterInternal(WebMvcMetricsFilter.java:93)\r\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\norg.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\norg.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\norg.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\norg.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\norg.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202)\r\norg.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\norg.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:541)\r\norg.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)\r\norg.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\norg.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\norg.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\norg.apache.coyote.http11.Http11Processor.service(Http11Processor.java:373)\r\norg.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\r\norg.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:868)\r\norg.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1590)\r\norg.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\njava.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\r\njava.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)\r\norg.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\njava.lang.Thread.run(Thread.java:748)', '0:0:0:0:0:0:0:1', 'S####', '2020-09-21 17:32:51.233', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092118200000000000031', '20200921', 'login', '{\"userPwd\":\"712fd505af8c1c919c85036dc52c3138\",\"datasourceId\":\"\",\"userAcct\":\"admin\"}', null, '2020-09-21 18:20:40.705', '2020-09-21 18:20:40.839', '134', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 18:20:40.840', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092118210000000000035', '20200921', 'login', '{\"userPwd\":\"712fd505af8c1c919c85036dc52c3138\",\"datasourceId\":\"\",\"userAcct\":\"admin\"}', null, '2020-09-21 18:21:06.699', '2020-09-21 18:21:06.793', '94', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 18:21:06.794', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092118260000000000050', '20200921', 'query data source list', '{}', null, '2020-09-21 18:26:54.767', '2020-09-21 18:26:55.115', '348', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 18:26:55.115', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092118270000000000051', '20200921', 'query data source list', '{}', null, '2020-09-21 18:27:07.118', '2020-09-21 18:27:07.204', '86', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 18:27:07.205', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092118270000000000052', '20200921', 'query data source list', '{}', null, '2020-09-21 18:27:08.363', '2020-09-21 18:27:08.451', '88', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 18:27:08.452', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092118270000000000053', '20200921', 'query data source list', '{}', null, '2020-09-21 18:27:10.083', '2020-09-21 18:27:10.168', '85', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 18:27:10.168', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092118270000000000054', '20200921', 'query data source list', '{}', null, '2020-09-21 18:27:11.571', '2020-09-21 18:27:11.658', '87', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 18:27:11.659', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092118270000000000055', '20200921', 'query data source list', '{}', null, '2020-09-21 18:27:13.423', '2020-09-21 18:27:13.508', '85', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 18:27:13.508', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092118270000000000056', '20200921', 'query data source list', '{}', null, '2020-09-21 18:27:14.825', '2020-09-21 18:27:14.922', '97', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 18:27:14.922', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092118270000000000057', '20200921', 'query data source list', '{}', null, '2020-09-21 18:27:16.280', '2020-09-21 18:27:16.366', '86', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 18:27:16.366', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092118270000000000058', '20200921', 'query data source list', '{}', null, '2020-09-21 18:27:17.678', '2020-09-21 18:27:17.765', '87', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 18:27:17.765', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092118280000000000061', '20200921', 'login', '{\"userPwd\":\"712fd505af8c1c919c85036dc52c3138\",\"datasourceId\":\"\",\"userAcct\":\"admin\"}', null, '2020-09-21 18:28:49.584', '2020-09-21 18:28:49.687', '103', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 18:28:49.688', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092118440000000000064', '20200921', 'query data source list', '{}', null, '2020-09-21 18:44:56.466', '2020-09-21 18:44:56.596', '130', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-21 18:44:56.596', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092211070000000000068', '20200922', 'login', '{\"userPwd\":\"712fd505af8c1c919c85036dc52c3138\",\"datasourceId\":\"\",\"userAcct\":\"admin\"}', null, '2020-09-22 11:07:34.907', '2020-09-22 11:07:35.079', '172', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 11:07:35.080', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092211100000000000071', '20200922', 'query data source list', '{}', null, '2020-09-22 11:10:33.450', '2020-09-22 11:10:33.910', '460', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 11:10:33.910', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092211100000000000072', '20200922', 'edit data source', '{\"datasourceUser\":\"HZuBmo+EzOS/i804dQbyfQ==\",\"dataUpdateUser\":\"admin\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"dataVersion\":\"1\",\"dataCreateUser\":\"####\",\"dataCreateTime\":\"####\",\"datasourcePwd\":\"837ab35e9506ac0e80875114fd90f296\",\"datasourceType\":\"MYSQL\",\"platformTablePrefix\":\"MS\",\"isEnabled\":\"true\",\"datasourceId\":\"ver_cl\",\"tlsqlInd\":\"N\",\"dataOperateType\":\"D\",\"dataUpdateTime\":\"2020-08-07 15:01:59.490\",\"datasourceUrl\":\"jdbc:mysql://106.52.66.244:3306/ver_cl?serverTimezone=Asia/Shanghai\",\"datasourceDesc\":\"额度稽核\"}', null, '2020-09-22 11:10:36.952', '2020-09-22 11:10:37.078', '126', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 11:10:37.079', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092211100000000000073', '20200922', 'query data source list', '{}', null, '2020-09-22 11:10:41.720', '2020-09-22 11:10:42.059', '339', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 11:10:42.059', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092211100000000000074', '20200922', 'query data source list', '{}', null, '2020-09-22 11:10:47.604', '2020-09-22 11:10:47.720', '116', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 11:10:47.721', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092211100000000000075', '20200922', 'edit data source', '{\"datasourceUser\":\"HZuBmo+EzOS/i804dQbyfQ==\",\"dataUpdateUser\":\"admin\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"dataVersion\":\"1\",\"dataCreateUser\":\"####\",\"dataCreateTime\":\"####\",\"datasourcePwd\":\"837ab35e9506ac0e80875114fd90f296\",\"datasourceType\":\"MYSQL\",\"platformTablePrefix\":\"MS\",\"isEnabled\":\"true\",\"datasourceId\":\"ver_ln\",\"tlsqlInd\":\"N\",\"dataOperateType\":\"D\",\"dataUpdateTime\":\"2020-08-07 15:01:48.574\",\"datasourceUrl\":\"jdbc:mysql://106.52.66.244:3306/ver_ln?serverTimezone=Asia/Shanghai\",\"datasourceDesc\":\"贷款稽核\"}', null, '2020-09-22 11:10:51.623', '2020-09-22 11:10:51.786', '163', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 11:10:51.787', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092211100000000000076', '20200922', 'query data source list', '{}', null, '2020-09-22 11:10:58.521', '2020-09-22 11:10:58.658', '137', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 11:10:58.659', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092213150000000000080', '20200922', 'query data source list', '{}', null, '2020-09-22 13:15:11.836', '2020-09-22 13:15:12.227', '391', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 13:15:12.227', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092214420000000000083', '20200922', 'read gitlab merge diffs', '{\"jiraId\":\"PICORE3-3827\",\"moduleId\":\"ln\"}', null, '2020-09-22 14:42:12.574', '2020-09-22 14:42:12.685', '111', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 14:42:12.685', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092214420000000000089', '20200922', 'read gitlab merge diffs', '{\"jiraId\":\"PICORE3-3827\",\"moduleId\":\"ln\"}', null, '2020-09-22 14:42:32.304', '2020-09-22 14:42:32.422', '118', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 14:42:32.423', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092214430000000000092', '20200922', 'build PTE json', '{\"pteModule\":\"v_inputDataForm_btn\",\"flowtranId\":\"ln6101\",\"listName\":\"\"}', null, '2020-09-22 14:43:06.069', '2020-09-22 14:43:06.564', '495', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 14:43:06.564', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092214430000000000094', '20200922', 'build field set statement', '{\"targetEntityId\":\"ClaPartnerBasic\",\"sourceVarName\":\"attrInfo\",\"targetVarName\":\"claPartnerBasicNew\",\"sourceEntityId\":\"ClPartnerListMntIn\"}', null, '2020-09-22 14:43:20.187', '2020-09-22 14:43:20.272', '85', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 14:43:20.273', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092216350000000000114', '20200922', 'query data source list', '{}', null, '2020-09-22 16:35:00.454', '2020-09-22 16:35:00.560', '106', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 16:35:00.561', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092217070000000000121', '20200922', 'database unlock', '{\"datasourceId\":\"ln_fat2\"}', null, '2020-09-22 17:07:56.855', '2020-09-22 17:07:57.004', '149', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 17:07:57.004', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092217080000000000123', '20200922', 'database unlock', '{\"datasourceId\":\"ln_fat2\"}', null, '2020-09-22 17:08:34.373', '2020-09-22 17:08:34.428', '55', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 17:08:34.428', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092217130000000000127', '20200922', 'login', '{\"userPwd\":\"712fd505af8c1c919c85036dc52c3138\",\"datasourceId\":\"\",\"userAcct\":\"admin\"}', null, '2020-09-22 17:13:49.052', '2020-09-22 17:13:49.157', '105', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 17:13:49.157', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092217130000000000131', '20200922', 'database unlock', '{\"datasourceId\":\"ln_fat2\"}', null, '2020-09-22 17:13:59.024', '2020-09-22 17:13:59.091', '67', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 17:13:59.092', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092217140000000000132', '20200922', 'database unlock', '{\"datasourceId\":\"ln_fat2\"}', null, '2020-09-22 17:14:00.318', '2020-09-22 17:14:00.370', '52', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 17:14:00.370', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092217140000000000133', '20200922', 'database unlock', '{\"datasourceId\":\"ln_fat2\"}', null, '2020-09-22 17:14:01.002', '2020-09-22 17:14:01.056', '54', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 17:14:01.057', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092217140000000000134', '20200922', 'database unlock', '{\"datasourceId\":\"ln_fat2\"}', null, '2020-09-22 17:14:01.479', '2020-09-22 17:14:01.530', '51', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 17:14:01.530', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092217140000000000135', '20200922', 'database unlock', '{\"datasourceId\":\"ln_fat2\"}', null, '2020-09-22 17:14:01.982', '2020-09-22 17:14:02.037', '55', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 17:14:02.037', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092217140000000000136', '20200922', 'database unlock', '{\"datasourceId\":\"ln_fat2\"}', null, '2020-09-22 17:14:02.339', '2020-09-22 17:14:02.391', '52', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 17:14:02.392', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092217140000000000137', '20200922', 'database unlock', '{\"datasourceId\":\"ln_fat2\"}', null, '2020-09-22 17:14:02.680', '2020-09-22 17:14:02.957', '277', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 17:14:02.957', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092217140000000000138', '20200922', 'database unlock', '{\"datasourceId\":\"ln_fat2\"}', null, '2020-09-22 17:14:03.151', '2020-09-22 17:14:03.198', '47', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 17:14:03.198', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092217160000000000142', '20200922', 'query data source list', '{}', null, '2020-09-22 17:16:29.072', '2020-09-22 17:16:29.206', '134', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 17:16:29.206', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092217180000000000143', '20200922', 'query data source list', '{}', null, '2020-09-22 17:18:22.573', '2020-09-22 17:18:22.676', '103', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 17:18:22.677', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092217210000000000145', '20200922', 'query data source list', '{}', null, '2020-09-22 17:21:22.443', '2020-09-22 17:21:22.556', '113', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 17:21:22.556', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092217210000000000146', '20200922', 'query data source list', '{}', null, '2020-09-22 17:21:23.582', '2020-09-22 17:21:23.679', '97', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-22 17:21:23.680', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092411240000000000148', '20200924', 'login', '{\"userPwd\":\"712fd505af8c1c919c85036dc52c3138\",\"datasourceId\":\"\",\"userAcct\":\"admin\"}', null, '2020-09-24 11:24:03.135', '2020-09-24 11:24:03.236', '101', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-24 11:24:03.237', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092411240000000000152', '20200924', 'read nexus lastest iobus version', '{\"repositoryType\":\"odc-test\"}', null, '2020-09-24 11:24:11.565', '2020-09-24 11:24:12.460', '895', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-24 11:24:12.461', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092411330000000000155', '20200924', 'read gitlab merge diffs', '{\"jiraId\":\"PICORE3-4971\",\"moduleId\":\"ln\"}', null, '2020-09-24 11:33:12.347', '2020-09-24 11:33:14.378', '2031', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-24 11:33:14.378', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092411360000000000156', '20200924', 'read gitlab merge diffs', '{\"jiraId\":\"PICORE3-4962\",\"moduleId\":\"ln\"}', null, '2020-09-24 11:36:52.911', '2020-09-24 11:36:54.311', '1400', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-24 11:36:54.311', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092413360000000000157', '20200924', 'read gitlab merge diffs', '{\"jiraId\":\"PICORE3-4962\",\"moduleId\":\"ln\"}', null, '2020-09-24 13:36:50.045', '2020-09-24 13:36:50.160', '115', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-24 13:36:50.161', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092514192858163343050', '20200925', 'login', '{\"userPwd\":\"712fd505af8c1c919c85036dc52c3138\",\"datasourceId\":\"ln_dev\",\"userAcct\":\"admin\"}', '{\"loginIp\":\"127.0.0.1\",\"loginTime\":\"2020-09-25 14:19:28.581\"}', '2020-09-25 14:19:28.581', '2020-09-25 14:19:28.889', '308', '处理成功', null, '127.0.0.1', 'admin', '2020-09-25 14:19:28.892', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092514195479627517282', '20200925', 'read gitlab merge diffs', '{\"jiraId\":\"PICORE3-4995\",\"moduleId\":\"ln\"}', '\"ln-base/src/main/java/cn/sunline/icore/ln/base/transaction/LnLoanBalanceBook.java\\r\\nln-base/src/main/resources/tables/TabLnCore.tables.xml\\r\\nln-dbscripts/main/ddl/LN_table_script.sql\\r\\nln-dbscripts/upgrade/3.2.1.5-BETA/ddl/PICORE3-4995.sql\\r\\nln-serv/src/test/java/cn/sunline/icore/ln/test/transfer/TransferTest.java\\r\\nln-serv/src/test/java/cn/sunline/icore/ln/util/LnTestPublic.java\\r\\n\"', '2020-09-25 14:19:54.796', '2020-09-25 14:19:58.996', '4200', '处理成功', null, '127.0.0.1', 'admin', '2020-09-25 14:19:58.997', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092515012408331304740', '20200925', 'login', '{\"userPwd\":\"712fd505af8c1c919c85036dc52c3138\",\"datasourceId\":\"ln_dev\",\"userAcct\":\"admin\"}', '{\"loginIp\":\"127.0.0.1\",\"loginTime\":\"2020-09-25 15:01:24.083\"}', '2020-09-25 15:01:24.083', '2020-09-25 15:01:24.531', '448', '处理成功', null, '127.0.0.1', 'admin', '2020-09-25 15:01:24.534', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092515013248403457857', '20200925', 'search dictionary list', '{\"key\":\"E_BALCODE\"}', '{\"endRow\":0,\"firstPage\":0,\"hasNextPage\":false,\"hasPreviousPage\":false,\"isFirstPage\":true,\"isLastPage\":true,\"lastPage\":0,\"list\":[],\"navigateFirstPage\":0,\"navigateLastPage\":0,\"navigatePages\":8,\"navigatepageNums\":[],\"nextPage\":0,\"pageNum\":1,\"pageSize\":10,\"pages\":0,\"prePage\":0,\"size\":0,\"startRow\":0,\"total\":0}', '2020-09-25 15:01:32.484', '2020-09-25 15:01:32.761', '277', '处理成功', null, '127.0.0.1', 'admin', '2020-09-25 15:01:32.774', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092515015030741268650', '20200925', 'query data source list', '{}', '{\"endRow\":9,\"firstPage\":1,\"hasNextPage\":false,\"hasPreviousPage\":false,\"isFirstPage\":true,\"isLastPage\":true,\"lastPage\":1,\"list\":[{\"dataCreateTime\":\"####\",\"dataCreateUser\":\"####\",\"dataUpdateTime\":\"2020-08-07 15:01:25.678\",\"dataUpdateUser\":\"admin\",\"dataVersion\":1,\"datasourceDesc\":\"3.1主干集中式核心\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"datasourceId\":\"cbs_master\",\"datasourcePwd\":\"vlog\",\"datasourceType\":\"MYSQL\",\"datasourceUrl\":\"jdbc:mysql://10.22.62.2:3307/cd_icore?serverTimezone=Asia/Shanghai\",\"datasourceUser\":\"vlog\",\"isEnabled\":true,\"platformTablePrefix\":\"MS\",\"tlsqlInd\":\"N\"},{\"dataCreateTime\":\"####\",\"dataCreateUser\":\"####\",\"dataUpdateTime\":\"2020-08-07 15:01:57.619\",\"dataUpdateUser\":\"admin\",\"dataVersion\":1,\"datasourceDesc\":\"额度dev\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"datasourceId\":\"cl_dev\",\"datasourcePwd\":\"dev_cl\",\"datasourceType\":\"MYSQL\",\"datasourceUrl\":\"jdbc:mysql://10.22.63.206:3306/dev_cl?serverTimezone=Asia/Shanghai\",\"datasourceUser\":\"dev_cl\",\"isEnabled\":true,\"platformTablePrefix\":\"MS\",\"tlsqlInd\":\"N\"},{\"dataCreateTime\":\"####\",\"dataCreateUser\":\"####\",\"dataUpdateTime\":\"2020-08-07 15:01:57.619\",\"dataUpdateUser\":\"admin\",\"dataVersion\":1,\"datasourceDesc\":\"公共dev\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"datasourceId\":\"cm_dev\",\"datasourcePwd\":\"dev_cm\",\"datasourceType\":\"MYSQL\",\"datasourceUrl\":\"jdbc:mysql://10.22.63.206:3306/dev_cm?serverTimezone=Asia/Shanghai\",\"datasourceUser\":\"dev_cm\",\"isEnabled\":true,\"platformTablePrefix\":\"MS\",\"tlsqlInd\":\"N\"},{\"dataCreateTime\":\"####\",\"dataCreateUser\":\"####\",\"dataUpdateTime\":\"2020-08-07 15:01:57.619\",\"dataUpdateUser\":\"admin\",\"dataVersion\":1,\"datasourceDesc\":\"内管dev\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"datasourceId\":\"ct_dev\",\"datasourcePwd\":\"dev_ct\",\"datasourceType\":\"MYSQL\",\"datasourceUrl\":\"jdbc:mysql://10.22.63.206:3306/dev_ct?serverTimezone=Asia/Shanghai\",\"datasourceUser\":\"dev_ct\",\"isEnabled\":true,\"platformTablePrefix\":\"MS\",\"tlsqlInd\":\"N\"},{\"dataCreateTime\":\"####\",\"dataCreateUser\":\"####\",\"dataUpdateTime\":\"2020-08-07 15:01:46.655\",\"dataUpdateUser\":\"admin\",\"dataVersion\":1,\"datasourceDesc\":\"存款dev\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"datasourceId\":\"dp_dev\",\"datasourcePwd\":\"dev_dp\",\"datasourceType\":\"MYSQL\",\"datasourceUrl\":\"jdbc:mysql://10.22.63.206:3306/dev_dp?serverTimezone=Asia/Shanghai\",\"datasourceUser\":\"dev_dp\",\"isEnabled\":true,\"platformTablePrefix\":\"MS\",\"tlsqlInd\":\"N\"},{\"dataCreateTime\":\"####\",\"dataCreateUser\":\"####\",\"dataUpdateTime\":\"2020-08-07 15:01:57.619\",\"dataUpdateUser\":\"admin\",\"dataVersion\":1,\"datasourceDesc\":\"决策dev\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"datasourceId\":\"ds_dev\",\"datasourcePwd\":\"dev_ds\",\"datasourceType\":\"MYSQL\",\"datasourceUrl\":\"jdbc:mysql://10.22.63.206:3306/dev_ds?serverTimezone=Asia/Shanghai\",\"datasourceUser\":\"dev_ds\",\"isEnabled\":true,\"platformTablePrefix\":\"MS\",\"tlsqlInd\":\"N\"},{\"dataCreateTime\":\"####\",\"dataCreateUser\":\"####\",\"dataUpdateTime\":\"2020-08-07 15:01:56.171\",\"dataUpdateUser\":\"admin\",\"dataVersion\":1,\"datasourceDesc\":\"贷款dev\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"datasourceId\":\"ln_dev\",\"datasourcePwd\":\"dev_ln\",\"datasourceType\":\"MYSQL\",\"datasourceUrl\":\"jdbc:mysql://10.22.63.206:3306/dev_ln?serverTimezone=Asia/Shanghai\",\"datasourceUser\":\"dev_ln\",\"isEnabled\":true,\"platformTablePrefix\":\"MS\",\"tlsqlInd\":\"N\"},{\"dataCreateTime\":\"####\",\"dataCreateUser\":\"####\",\"dataUpdateTime\":\"2020-08-07 15:01:56.171\",\"dataUpdateUser\":\"admin\",\"dataVersion\":1,\"datasourceDesc\":\"贷款fat2\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"datasourceId\":\"ln_fat2\",\"datasourcePwd\":\"vlog\",\"datasourceType\":\"MYSQL\",\"datasourceUrl\":\"jdbc:mysql://10.22.63.1:3306/poc_ln?serverTimezone=Asia/Shanghai\",\"datasourceUser\":\"vlog\",\"isEnabled\":true,\"platformTablePrefix\":\"MS\",\"tlsqlInd\":\"N\"},{\"dataCreateTime\":\"####\",\"dataCreateUser\":\"####\",\"dataUpdateTime\":\"2020-08-07 15:01:53.635\",\"dataUpdateUser\":\"admin\",\"dataVersion\":1,\"datasourceDesc\":\"sump fat2\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"datasourceId\":\"sump_fat2\",\"datasourcePwd\":\"vlog\",\"datasourceType\":\"MYSQL\",\"datasourceUrl\":\"jdbc:mysql://10.22.63.1:3306/poc_sump?serverTimezone=Asia/Shanghai\",\"datasourceUser\":\"vlog\",\"isEnabled\":true,\"platformTablePrefix\":\"MS\",\"tlsqlInd\":\"N\"},{\"dataCreateTime\":\"####\",\"dataCreateUser\":\"####\",\"dataUpdateTime\":\"2020-08-07 15:01:51.823\",\"dataUpdateUser\":\"admin\",\"dataVersion\":1,\"datasourceDesc\":\"研发2.0 dit\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"datasourceId\":\"yf_dit\",\"datasourcePwd\":\"yfcbs\",\"datasourceType\":\"MYSQL\",\"datasourceUrl\":\"jdbc:mysql://10.22.60.59:3306/yf_cbs?serverTimezone=Asia/Shanghai\",\"datasourceUser\":\"yfcbs\",\"isEnabled\":true,\"platformTablePrefix\":\"MS\",\"tlsqlInd\":\"N\"}],\"navigateFirstPage\":1,\"navigateLastPage\":1,\"navigatePages\":8,\"navigatepageNums\":[1],\"nextPage\":0,\"pageNum\":1,\"pageSize\":10,\"pages\":1,\"prePage\":0,\"size\":10,\"startRow\":0,\"total\":10}', '2020-09-25 15:01:50.308', '2020-09-25 15:01:50.480', '172', '处理成功', null, '127.0.0.1', 'admin', '2020-09-25 15:01:50.483', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092515024848255724842', '20200925', 'query data source list', '{}', '{\"endRow\":9,\"firstPage\":1,\"hasNextPage\":false,\"hasPreviousPage\":false,\"isFirstPage\":true,\"isLastPage\":true,\"lastPage\":1,\"list\":[{\"dataCreateTime\":\"####\",\"dataCreateUser\":\"####\",\"dataUpdateTime\":\"2020-08-07 15:01:25.678\",\"dataUpdateUser\":\"admin\",\"dataVersion\":1,\"datasourceDesc\":\"3.1主干集中式核心\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"datasourceId\":\"cbs_master\",\"datasourcePwd\":\"vlog\",\"datasourceType\":\"MYSQL\",\"datasourceUrl\":\"jdbc:mysql://10.22.62.2:3307/cd_icore?serverTimezone=Asia/Shanghai\",\"datasourceUser\":\"vlog\",\"isEnabled\":true,\"platformTablePrefix\":\"MS\",\"tlsqlInd\":\"N\"},{\"dataCreateTime\":\"####\",\"dataCreateUser\":\"####\",\"dataUpdateTime\":\"2020-08-07 15:01:57.619\",\"dataUpdateUser\":\"admin\",\"dataVersion\":1,\"datasourceDesc\":\"额度dev\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"datasourceId\":\"cl_dev\",\"datasourcePwd\":\"dev_cl\",\"datasourceType\":\"MYSQL\",\"datasourceUrl\":\"jdbc:mysql://10.22.63.206:3306/dev_cl?serverTimezone=Asia/Shanghai\",\"datasourceUser\":\"dev_cl\",\"isEnabled\":true,\"platformTablePrefix\":\"MS\",\"tlsqlInd\":\"N\"},{\"dataCreateTime\":\"####\",\"dataCreateUser\":\"####\",\"dataUpdateTime\":\"2020-08-07 15:01:57.619\",\"dataUpdateUser\":\"admin\",\"dataVersion\":1,\"datasourceDesc\":\"公共dev\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"datasourceId\":\"cm_dev\",\"datasourcePwd\":\"dev_cm\",\"datasourceType\":\"MYSQL\",\"datasourceUrl\":\"jdbc:mysql://10.22.63.206:3306/dev_cm?serverTimezone=Asia/Shanghai\",\"datasourceUser\":\"dev_cm\",\"isEnabled\":true,\"platformTablePrefix\":\"MS\",\"tlsqlInd\":\"N\"},{\"dataCreateTime\":\"####\",\"dataCreateUser\":\"####\",\"dataUpdateTime\":\"2020-08-07 15:01:57.619\",\"dataUpdateUser\":\"admin\",\"dataVersion\":1,\"datasourceDesc\":\"内管dev\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"datasourceId\":\"ct_dev\",\"datasourcePwd\":\"dev_ct\",\"datasourceType\":\"MYSQL\",\"datasourceUrl\":\"jdbc:mysql://10.22.63.206:3306/dev_ct?serverTimezone=Asia/Shanghai\",\"datasourceUser\":\"dev_ct\",\"isEnabled\":true,\"platformTablePrefix\":\"MS\",\"tlsqlInd\":\"N\"},{\"dataCreateTime\":\"####\",\"dataCreateUser\":\"####\",\"dataUpdateTime\":\"2020-08-07 15:01:46.655\",\"dataUpdateUser\":\"admin\",\"dataVersion\":1,\"datasourceDesc\":\"存款dev\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"datasourceId\":\"dp_dev\",\"datasourcePwd\":\"dev_dp\",\"datasourceType\":\"MYSQL\",\"datasourceUrl\":\"jdbc:mysql://10.22.63.206:3306/dev_dp?serverTimezone=Asia/Shanghai\",\"datasourceUser\":\"dev_dp\",\"isEnabled\":true,\"platformTablePrefix\":\"MS\",\"tlsqlInd\":\"N\"},{\"dataCreateTime\":\"####\",\"dataCreateUser\":\"####\",\"dataUpdateTime\":\"2020-08-07 15:01:57.619\",\"dataUpdateUser\":\"admin\",\"dataVersion\":1,\"datasourceDesc\":\"决策dev\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"datasourceId\":\"ds_dev\",\"datasourcePwd\":\"dev_ds\",\"datasourceType\":\"MYSQL\",\"datasourceUrl\":\"jdbc:mysql://10.22.63.206:3306/dev_ds?serverTimezone=Asia/Shanghai\",\"datasourceUser\":\"dev_ds\",\"isEnabled\":true,\"platformTablePrefix\":\"MS\",\"tlsqlInd\":\"N\"},{\"dataCreateTime\":\"####\",\"dataCreateUser\":\"####\",\"dataUpdateTime\":\"2020-08-07 15:01:56.171\",\"dataUpdateUser\":\"admin\",\"dataVersion\":1,\"datasourceDesc\":\"贷款dev\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"datasourceId\":\"ln_dev\",\"datasourcePwd\":\"dev_ln\",\"datasourceType\":\"MYSQL\",\"datasourceUrl\":\"jdbc:mysql://10.22.63.206:3306/dev_ln?serverTimezone=Asia/Shanghai\",\"datasourceUser\":\"dev_ln\",\"isEnabled\":true,\"platformTablePrefix\":\"MS\",\"tlsqlInd\":\"N\"},{\"dataCreateTime\":\"####\",\"dataCreateUser\":\"####\",\"dataUpdateTime\":\"2020-08-07 15:01:56.171\",\"dataUpdateUser\":\"admin\",\"dataVersion\":1,\"datasourceDesc\":\"贷款fat2\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"datasourceId\":\"ln_fat2\",\"datasourcePwd\":\"vlog\",\"datasourceType\":\"MYSQL\",\"datasourceUrl\":\"jdbc:mysql://10.22.63.1:3306/poc_ln?serverTimezone=Asia/Shanghai\",\"datasourceUser\":\"vlog\",\"isEnabled\":true,\"platformTablePrefix\":\"MS\",\"tlsqlInd\":\"N\"},{\"dataCreateTime\":\"####\",\"dataCreateUser\":\"####\",\"dataUpdateTime\":\"2020-08-07 15:01:53.635\",\"dataUpdateUser\":\"admin\",\"dataVersion\":1,\"datasourceDesc\":\"sump fat2\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"datasourceId\":\"sump_fat2\",\"datasourcePwd\":\"vlog\",\"datasourceType\":\"MYSQL\",\"datasourceUrl\":\"jdbc:mysql://10.22.63.1:3306/poc_sump?serverTimezone=Asia/Shanghai\",\"datasourceUser\":\"vlog\",\"isEnabled\":true,\"platformTablePrefix\":\"MS\",\"tlsqlInd\":\"N\"},{\"dataCreateTime\":\"####\",\"dataCreateUser\":\"####\",\"dataUpdateTime\":\"2020-08-07 15:01:51.823\",\"dataUpdateUser\":\"admin\",\"dataVersion\":1,\"datasourceDesc\":\"研发2.0 dit\",\"datasourceDriver\":\"com.mysql.cj.jdbc.Driver\",\"datasourceId\":\"yf_dit\",\"datasourcePwd\":\"yfcbs\",\"datasourceType\":\"MYSQL\",\"datasourceUrl\":\"jdbc:mysql://10.22.60.59:3306/yf_cbs?serverTimezone=Asia/Shanghai\",\"datasourceUser\":\"yfcbs\",\"isEnabled\":true,\"platformTablePrefix\":\"MS\",\"tlsqlInd\":\"N\"}],\"navigateFirstPage\":1,\"navigateLastPage\":1,\"navigatePages\":8,\"navigatepageNums\":[1],\"nextPage\":0,\"pageNum\":1,\"pageSize\":10,\"pages\":1,\"prePage\":0,\"size\":10,\"startRow\":0,\"total\":10}', '2020-09-25 15:02:48.482', '2020-09-25 15:02:48.828', '346', '处理成功', null, '127.0.0.1', 'admin', '2020-09-25 15:02:48.829', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092517320000000000159', '20200925', 'login', '{\"userPwd\":\"712fd505af8c1c919c85036dc52c3138\",\"datasourceId\":\"\",\"userAcct\":\"admin\"}', null, '2020-09-25 17:32:51.570', '2020-09-25 17:32:51.675', '105', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-25 17:32:51.676', null, null, '0');
INSERT INTO `sds_packet` VALUES ('2020092517330000000000163', '20200925', 'read gitlab merge diffs', '{\"jiraId\":\"PICORE3-4980\",\"moduleId\":\"cl\"}', null, '2020-09-25 17:33:06.290', '2020-09-25 17:33:08.083', '1793', '处理成功', null, '0:0:0:0:0:0:0:1', 'admin', '2020-09-25 17:33:08.084', null, null, '0');
