DROP TABLE IF EXISTS `t_lorchard_mall_merchant`;
CREATE TABLE `t_smurfs_mall_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商家id',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `name` varbinary(50) NOT NULL COMMENT '姓名',
  `registerIp` varchar(64) DEFAULT NULL COMMENT '注册ip',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创健日期',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_lorchard_mall_member`;
CREATE TABLE `t_smurfs_mall_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `merchantId`int(11) NOT NULL DEFAULT '0' COMMENT '商家Id',
  `gender` tinyint(1) NOT NULL DEFAULT '0' COMMENT '会员性别',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `nickname` varbinary(50) NOT NULL COMMENT '昵称',
  `openid` varchar(128) NOT NULL COMMENT 'openid',
  `registerIp` varchar(64) DEFAULT NULL COMMENT '注册ip',
  `avatar` varchar(256) NOT NULL COMMENT '头像路径',
  `language` varchar(16) NOT NULL COMMENT '语言',
  `country` varchar(16) NOT NULL COMMENT '国家',
  `province` varchar(16) NOT NULL COMMENT '省市',
  `city` varchar(32) DEFAULT NULL COMMENT '城市',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创健日期',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;