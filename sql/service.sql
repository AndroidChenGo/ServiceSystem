
create database service ;

Use service;

CREATE TABLE `freelancer`
(
    `id`             INT UNIQUE NOT NULL COMMENT '自由职业者工号',
    `name`           VARCHAR(15) COMMENT '自由职业者姓名',
    `username`       VARCHAR(15) COMMENT '自由职业者账户名',
    `password`       VARCHAR(20) COMMENT '自由职业者登录密码',
    `type`           VARCHAR(20) COMMENT '自由职业者服务类型',
    `phone`          VARCHAR(20) COMMENT '自由职业者电话号码',
    `email`         VARCHAR(20) COMMENT '自由职业者邮箱号码',
    `wechat`         VARCHAR(20) COMMENT '自由职业者微信号码',
    `introduction`   VARCHAR(100) COMMENT '自由职业者自我介绍',
    PRIMARY KEY (`id`)
);

CREATE TABLE `client`
(
    `id`             INT UNIQUE NOT NULL COMMENT '客户编号',
    `name`           VARCHAR(15) COMMENT '客户姓名',
    `username`       VARCHAR(15) COMMENT '客户账户名',
    `password`       VARCHAR(20) COMMENT '客户登录密码',
    `type`           VARCHAR(20) COMMENT '客户类型',
    `phone`          VARCHAR(20) COMMENT '客户电话号码',
    `email`         VARCHAR(20) COMMENT '客户邮箱号码',
    `wechat`         VARCHAR(20) COMMENT '客户微信号码',
    `introduction`   VARCHAR(100) COMMENT '客户自我介绍',
    PRIMARY KEY (`id`)
);

CREATE TABLE `admin`
(
    `id`             INT UNIQUE NOT NULL COMMENT '管理员编号',
    `name`           VARCHAR(15) COMMENT '管理员姓名',
    `username`       VARCHAR(15) NOT NULL COMMENT '管理员账户名',
    `password`       VARCHAR(20) NOT NULL COMMENT '管理员登录密码',
    `phone`          VARCHAR(20) COMMENT '管理员电话号码',
    `email`         VARCHAR(20) COMMENT '管理员邮箱号码',
    `wechat`         VARCHAR(20) COMMENT '管理员微信号码',
    PRIMARY KEY (`id`)
);

CREATE TABLE `project`
(
    `id`             INT UNIQUE NOT NULL COMMENT '项目编号',
    `name`           VARCHAR(15) COMMENT '项目名称',
    `type`           VARCHAR(20) DEFAULT "未发布" COMMENT '项目类型',
    `state`          VARCHAR(20) COMMENT '项目状态',
    `clientId`       INT COMMENT NOT NULL '发布项目的客户id',
    `content`        VARCHAR(100) COMMENT '项目详情',
    PRIMARY KEY (`id`)
);

CREATE TABLE `contract`
(
    `id`            INT UNIQUE NOT NULL COMMENT '合同编号',
    `name`          VARCHAR(20) COMMENT '合同名称',
    `content`       VARCHAR(200) COMMENT '合同内容',
    `state`         VARCHAR(20) COMMENT '合同状态',
    `clientId`      INT COMMENT NOT NULL '签订合同的客户id',
    `freelancerId`  INT COMMENT '签订合同的自由职业者id',
    `projectId`     INT COMMENT '关联的项目id',
    PRIMARY KEY (`id`)
);

CREATE TABLE `timetable`
(
    `id`            INT UNIQUE NOT NULL AUTO_INCREMENT COMMENT '时间表id',
    `freelancerId`  INT COMMENT NOT NULL '自由职业者id',
    `name`          VARCHAR(20) COMMENT '时间表名称',
    `content`       VARCHAR(200) COMMENT '工作内容',
    `startTime`    DATETIME COMMENT '工作开始时间',
    `endTime`    DATETIME COMMENT '工作结束时间',
    PRIMARY KEY (`id`)
);