CREATE SCHEMA IF NOT EXISTS jianzheng default character set utf8mb4 collate utf8mb4_general_ci;
use
    jianzheng;
set names utf8mb4;
/*****************************基础模块************************************/
/* 系统用户表 */
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          int(10) UNSIGNED                                              NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `mobile`      varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '手机号',
    `password`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '密码,管理员使用',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '姓名',
    `nickname`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '微信昵称',
    `avatar`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '头像',
    `last_time`   timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
    `last_ip`     varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '最后登录ip',
    `create_time` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `data_status` tinyint(2)                                                    NOT NULL DEFAULT 2 COMMENT '通用状态,2正常,3删除,4停用',
    `characters`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '角色',
    `motto`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注',
    `username`    varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL DEFAULT '' COMMENT '用户名',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `user_sys_role` (`characters`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 14
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户表'
  ROW_FORMAT = Dynamic;

/* 系统角色表 */
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          int(10) UNSIGNED                                               NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `role_name`   varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci  NOT NULL DEFAULT '' COMMENT '角色名称',
    `remark`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL DEFAULT '' COMMENT '备注',
    `permissions` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL DEFAULT '' COMMENT '权限id，多个以逗号分隔',
    `create_time` timestamp                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `data_status` tinyint(2)                                                     NOT NULL DEFAULT 2 COMMENT '通用状态,2正常,3删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 15
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_croatian_ci COMMENT = '角色'
  ROW_FORMAT = Dynamic;

/* 系统权限表 */
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`
(
    `id`              int(10) UNSIGNED                                               NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `parent_id`       int(10)                                                        NOT NULL DEFAULT 0 COMMENT '父权限ID，一级菜单为0',
    `permission_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci  NOT NULL DEFAULT '' COMMENT '权限名称',
    `key_name`        varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci  NOT NULL DEFAULT '' COMMENT '标识名',
    `component`       varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci  NOT NULL DEFAULT '' COMMENT '组件名',
    `permission_type` tinyint(2)                                                     NOT NULL DEFAULT 1 COMMENT '类别，1目录，2菜单，3权限',
    `permits`         varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL DEFAULT '' COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
    `url`             varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL DEFAULT '' COMMENT '权限URL，菜单用',
    `icon`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci  NOT NULL DEFAULT '' COMMENT '权限图标，菜单用',
    `sort`            smallint(4)                                                    NOT NULL DEFAULT 0 COMMENT '排序权重',
    `hidden`          tinyint(2)                                                     NOT NULL DEFAULT 1 COMMENT '是否隐藏,1显示,2隐藏',
    `data_status`     tinyint(2)                                                     NOT NULL DEFAULT 2 COMMENT '通用状态,2正常,3删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 33
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_croatian_ci COMMENT = '权限'
  ROW_FORMAT = Dynamic;

/* 系统客户表 */
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`
(
    `id`            int                                                           NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `customer`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户公司',
    `director`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '负责人',
    `add_time`      timestamp                                                     NOT NULL COMMENT '添加时间',
    `update_time`   timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `last_operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '最后操作人',
    `exist`         int                                                           NOT NULL COMMENT '该条是否存在，1存在，2已删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `customer` (`customer`) USING BTREE,
    INDEX `last_operator_customer` (`last_operator`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 19
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

/* 系统订单表 */
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`
(
    `id`             int                                                           NOT NULL AUTO_INCREMENT,
    `customer`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `start_time`     timestamp                                                     NOT NULL,
    `estimated_time` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `complete_time`  timestamp                                                     NULL DEFAULT NULL,
    `cost`           double                                                        NOT NULL,
    `status`         int                                                           NOT NULL,
    `producer`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `update_time`    timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_operator`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `exist`          int                                                           NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `customer` (`customer`) USING BTREE,
    INDEX `last_operator_order` (`last_operator`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

INSERT INTO `sys_permission`
VALUES (1, 0, '用户管理', 'system:user', 'user', 2, 'user:list,user:add,user:update,user:delete', 'user ', '', 0, 1, 2);
INSERT INTO `sys_permission`
VALUES (2, 0, '角色管理', 'system:sysRole', 'sysRole', 2, 'sysRole:list,sysRole:add,sysRole:update,sysRole:delete',
        'sysRole', '', 0, 1, 2);
INSERT INTO `sys_permission`
VALUES (3, 0, '客户管理', 'system:customere', 'customer', 2, 'customer:list,customer:add,customer:update,customer:delete',
        'customer', '', 0, 1, 2);
INSERT INTO `sys_permission`
VALUES (4, 0, '订单管理', 'system:order', 'order', 2, 'order:list,order:add,order:update,order:delete', 'order', '', 0, 1,
        2);
INSERT INTO `sys_permission`
VALUES (5, 1, '用户添加', 'system:user:add', 'user:add', 3, '', '/user/add', '', 1, 1, 2);
INSERT INTO `sys_permission`
VALUES (6, 1, '用户删除', 'system:user:delete', 'user:delete', 3, '', '/user/delete', '', 2, 1, 2);
INSERT INTO `sys_permission`
VALUES (7, 1, '用户修改', 'system:user:update', 'user:update', 3, '', '/user/update', '', 3, 1, 2);
INSERT INTO `sys_permission`
VALUES (8, 1, '用户列表', 'system:user:list', 'user:list', 3, '', '/user/list', '', 4, 1, 2);
INSERT INTO `sys_permission`
VALUES (9, 2, '角色添加', 'system:sysRole:add', 'sysRole:add', 3, '', '/sysRole/add', '', 1, 1, 2);
INSERT INTO `sys_permission`
VALUES (10, 2, '角色删除', 'system:sysRole:delete', 'sysRole:delete', 3, '', '/sysRole/delete', '', 2, 1, 2);
INSERT INTO `sys_permission`
VALUES (11, 2, '角色修改', 'system:sysRole:update', 'sysRole:update', 3, '', '/sysRole/update', '', 3, 1, 2);
INSERT INTO `sys_permission`
VALUES (12, 2, '角色列表', 'system:sysRole:list', 'sysRole:list', 3, '', '/sysRole/list', '', 4, 1, 2);
INSERT INTO `sys_permission`
VALUES (13, 3, '客户添加', 'system:customer:add', 'customer:add', 3, '', '/customer/add', '', 1, 1, 2);
INSERT INTO `sys_permission`
VALUES (14, 3, '客户删除', 'system:customer:delete', 'customer:delete', 3, '', '/customer/delete', '', 2, 1, 2);
INSERT INTO `sys_permission`
VALUES (15, 3, '客户修改', 'system:customer:update', 'customer:update', 3, '', '/customer/update', '', 3, 1, 2);
INSERT INTO `sys_permission`
VALUES (17, 3, '客户列表', 'system:customer:list', 'customer:list', 3, '', '/customer/list', '', 4, 1, 2);
INSERT INTO `sys_permission`
VALUES (18, 1, '修改用户密码', 'system:user:updatePwd', 'user:updatePwd', 3, '', '/user/updatePwd', '', 5, 1, 2);
INSERT INTO `sys_permission`
VALUES (19, 0, '权限管理', 'system:permission', 'permission', 2,
        'permission:list,permission:add,permission:update,permission:delete', 'permission', '', 0, 1, 2);
INSERT INTO `sys_permission`
VALUES (21, 19, '权限添加', 'system:permission:add', 'permission:add', 3, '', '/permission/add', '', 1, 1, 2);
INSERT INTO `sys_permission`
VALUES (22, 19, '权限删除', 'system:permission:delete', 'permission:delete', 3, '', '/permission/delete', '', 2, 1, 2);
INSERT INTO `sys_permission`
VALUES (23, 19, '权限修改', 'system:permission:update', 'permission:update', 3, '', '/permission/update', '', 3, 1, 2);
INSERT INTO `sys_permission`
VALUES (24, 19, '权限列表', 'system:permission:list', 'permission:list', 3, '', '/permission/list', '', 4, 1, 2);
INSERT INTO `sys_permission`
VALUES (25, 4, '订单添加', 'system:order:add', 'order:add', 3, '', '/order/add', '', 1, 1, 2);
INSERT INTO `sys_permission`
VALUES (26, 4, '订单删除', 'system:order:delete', 'order:delete', 3, '', '/order/delete', '', 2, 1, 2);
INSERT INTO `sys_permission`
VALUES (27, 4, '订单修改', 'system:order:update', 'order:update', 3, '', '/order/update', '', 3, 1, 2);
INSERT INTO `sys_permission`
VALUES (28, 4, '订单列表', 'system:order:list', 'order:list', 3, '', '/order/list', '', 4, 1, 2);
INSERT INTO `sys_permission`
VALUES (29, 0, '我的资料', 'system:myData', 'myData', 2, 'myData:list,myData:update', 'myData', '', 0, 1, 2);
INSERT INTO `sys_permission`
VALUES (30, 29, '资料修改', 'system:myData:update', 'myData:update', 3, '', '/myData/update', '', 3, 1, 2);
INSERT INTO `sys_permission`
VALUES (31, 29, '资料列表', 'system:myData:list', 'myData:list', 3, '', '/myData/list', '', 4, 1, 2);
INSERT INTO `sys_permission`
VALUES (32, 1, '用户停用', 'system.user.stop', 'user:stop', 3, '', '/user/stop', '', 6, 1, 2);


INSERT INTO `sys_role`
VALUES (1, '管理员', '管理员管理员', '1,5,6,7,8,18,2,9,10,11,12,3,13,14,15,17,4,25,26,27,28,19,21,22,23,24,29,30,31',
        '2021-06-29 14:51:49', 2);
INSERT INTO `sys_role`
VALUES (2, '老板', '老板老板', '28,31,17', '2021-07-01 11:08:54', 2);
INSERT INTO `sys_role`
VALUES (3, '商务', '商务商务', '28,31,17', '2021-07-01 11:09:17', 2);
INSERT INTO `sys_role`
VALUES (4, '财务', '财务财务', '28,31,17', '2021-07-01 11:09:28', 2);
INSERT INTO `sys_role`
VALUES (5, '制作', '制作制作', '28,31', '2021-07-01 15:23:41', 2);
INSERT INTO `sys_role`
VALUES (9, '超级管理员', '超级管理员超级管理员', '1,5,6,7,8,18,2,9,10,11,12,3,13,14,15,17,4,25,26,27,28,19,21,22,23,24,29,30,31,32',
        '2021-07-07 11:01:44', 2);


INSERT INTO `user`
VALUES (1, '13801380238', 'e10adc3949ba59abbe56e057f20f883e', '简正', 'jianzhengwx', '', '2021-06-29 15:25:20', '',
        '2021-06-29 15:25:20', 2, '超级管理员', '简正简正', 'jianzheng');
INSERT INTO `user`
VALUES (2, '13032382138', 'e10adc3949ba59abbe56e057f20f883e', '李四', 'lisiwx', '', '2021-06-29 17:25:50', '',
        '2021-06-29 17:25:50', 2, '管理员', '李四李四', 'lisi');
INSERT INTO `user`
VALUES (3, '13032380138', 'e10adc3949ba59abbe56e057f20f883e', '王五', 'wangwuwx', '', '2021-06-29 17:37:18', '',
        '2021-06-29 17:37:19', 2, '管理员', '王五王五', 'wangwu');
INSERT INTO `user`
VALUES (4, '15801380238', 'e10adc3949ba59abbe56e057f20f883e', '张三', 'zhangsanwx', '', '2021-06-29 17:49:50', '',
        '2021-06-29 17:49:50', 2, '老板', '张三张三', 'zhangsan');
INSERT INTO `user`
VALUES (5, '13801380438', 'e10adc3949ba59abbe56e057f20f883e', '杜牧', 'dumuwx', '', '2021-06-30 10:37:45', '',
        '2021-06-30 10:37:45', 2, '制作', '杜牧杜牧', 'dumu');
INSERT INTO `user`
VALUES (6, '13807380238', 'e10adc3949ba59abbe56e057f20f883e', '朱穆', 'zhumuwx', '', '2021-06-30 10:40:51', '',
        '2021-06-30 10:40:51', 3, '商务', '朱穆朱穆', 'zhumu');
INSERT INTO `user`
VALUES (7, '13801380938', 'fcea920f7412b5da7be0cf42b8c93759', '王勃', 'wangbowx', '', '2021-06-30 11:24:49', '',
        '2021-06-30 11:24:49', 3, '财务', '王勃王勃', 'wangbo');

INSERT INTO `customer`
VALUES (3, '京东', '刘东强', '2021-06-29 17:35:28', '2021-07-09 09:28:32', '13032380138', 1);
INSERT INTO `customer`
VALUES (4, '恒大集团', '许家印', '2021-06-30 12:54:26', '2021-07-09 15:36:35', '13032380138', 1);
INSERT INTO `customer`
VALUES (5, '简正科技', '肖裕', '2021-06-30 13:09:24', '2021-06-30 17:20:10', '13801380238', 1);
INSERT INTO `customer`
VALUES (6, '万科企业股份有限公司', '郁亮', '2021-06-30 13:19:35', '2021-07-09 15:37:48', '13032380138', 1);
INSERT INTO `customer`
VALUES (11, '新浪', '王志东', '2021-06-30 13:59:22', '2021-07-02 15:19:18', '13801380238', 1);
INSERT INTO `customer`
VALUES (12, '搜狐', '张朝阳', '2021-06-30 13:59:36', '2021-07-02 15:19:18', '13801380238', 1);
INSERT INTO `customer`
VALUES (13, '携程', '梁建章', '2021-06-30 13:59:47', '2021-07-02 15:19:18', '13801380238', 1);
INSERT INTO `customer`
VALUES (14, '腾讯', '马化腾', '2021-06-30 14:00:07', '2021-07-02 15:19:18', '13801380238', 1);
INSERT INTO `customer`
VALUES (15, '百度', '李彦宏', '2021-06-30 14:00:25', '2021-07-02 15:19:18', '13801380238', 1);
INSERT INTO `customer`
VALUES (19, '碧桂园', '杨国强', '2021-07-09 09:29:18', '2021-07-09 09:29:18', '13032380138', 1);
INSERT INTO `customer`
VALUES (20, '苏宁易购集团股份有限公司', '张近东', '2021-07-09 15:42:13', '2021-07-09 15:42:13', '13032380138', 1);

INSERT INTO `order`
VALUES (1, '京东', '2020-07-01 00:00:00', '2021-07-31 00:00:00', '2021-07-01 00:00:00', 450000, 1, '小王',
        '2021-07-09 15:03:56', '13032380138', 0);
INSERT INTO `order`
VALUES (2, '简正科技', '2021-06-30 00:00:01', '2021-07-09 00:00:00', '2021-07-02 00:00:00', 900000, 1, '小涛',
        '2021-07-09 15:42:57', '13032380138', 0);
INSERT INTO `order`
VALUES (5, '万科企业股份有限公司', '2021-07-01 00:00:00', '2021-07-02 00:00:00', '2021-07-03 00:00:00', 1700000, 1, '小郑',
        '2021-07-09 15:43:44', '13032380138', 0);
INSERT INTO `order`
VALUES (6, '简正科技', '2021-07-01 00:00:00', '2021-07-08 00:00:00', NULL, 500000, 0, '小贾', '2021-07-09 15:04:02',
        '13032380138', 0);

