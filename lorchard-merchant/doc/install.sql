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

DROP TABLE IF EXISTS `t_lorchard_mall_goods_category`;
CREATE TABLE `t_lorchard_mall_goods_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `merchantId`int(11) NOT NULL DEFAULT '0' COMMENT '商家Id',
  `name` varchar(16) NOT NULL COMMENT '分类名称',
  `description` varchar(256) NOT NULL COMMENT '描述',
  `imagePath` varchar(256) NOT NULL COMMENT '分类地址',
  `index` int(8) NOT NULL DEFAULT '1' COMMENT '分类索引',
  `level` int(2) NOT NULL DEFAULT '1' COMMENT '等级',
  `parentId` int(8) NOT NULL DEFAULT '0' COMMENT '上级Id',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创健日期',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

INSERT INTO `lorchard-mall`.`t_lorchard_mall_goods_category` (`id`, `merchantId`, `name`, `description`, `imagePath`, `index`, `level`, `parentId`, `createTime`, `updateTime`) VALUES ('1', '1', '热带水果', '很好吃', NULL, '1', '1', '0', '2018-08-15 15:07:51', NULL);
INSERT INTO `lorchard-mall`.`t_lorchard_mall_goods_category` (`id`, `merchantId`, `name`, `description`, `imagePath`, `index`, `level`, `parentId`, `createTime`, `updateTime`) VALUES ('2', '1', '时令鲜蔬', '很好吃', NULL, '1', '1', '0', '2018-08-15 17:48:10', NULL);
INSERT INTO `lorchard-mall`.`t_lorchard_mall_goods_category` (`id`, `merchantId`, `name`, `description`, `imagePath`, `index`, `level`, `parentId`, `createTime`, `updateTime`) VALUES ('3', '1', '肉禽蛋类', '很好吃', NULL, '1', '1', '0', '2018-08-15 17:48:47', NULL);
INSERT INTO `lorchard-mall`.`t_lorchard_mall_goods_category` (`id`, `merchantId`, `name`, `description`, `imagePath`, `index`, `level`, `parentId`, `createTime`, `updateTime`) VALUES ('4', '1', '乳品速食', '不好吃', NULL, '1', '1', '0', '2018-08-15 17:49:19', NULL);
INSERT INTO `lorchard-mall`.`t_lorchard_mall_goods_category` (`id`, `merchantId`, `name`, `description`, `imagePath`, `index`, `level`, `parentId`, `createTime`, `updateTime`) VALUES ('5', '1', '苹果', '好吃', NULL, '1', '2', '2', '2018-08-15 17:50:08', NULL);
INSERT INTO `lorchard-mall`.`t_lorchard_mall_goods_category` (`id`, `merchantId`, `name`, `description`, `imagePath`, `index`, `level`, `parentId`, `createTime`, `updateTime`) VALUES ('6', '1', '椰子', '好吃', NULL, '1', '2', '1', '2018-08-15 17:50:51', NULL);
INSERT INTO `lorchard-mall`.`t_lorchard_mall_goods_category` (`id`, `merchantId`, `name`, `description`, `imagePath`, `index`, `level`, `parentId`, `createTime`, `updateTime`) VALUES ('7', '1', '鸡蛋', '还好吃', NULL, '1', '2', '3', '2018-08-15 17:51:31', NULL);
INSERT INTO `lorchard-mall`.`t_lorchard_mall_goods_category` (`id`, `merchantId`, `name`, `description`, `imagePath`, `index`, `level`, `parentId`, `createTime`, `updateTime`) VALUES ('8', '1', '牛奶', '还好吃', NULL, '1', '2', '4', '2018-08-15 17:54:53', NULL);
INSERT INTO `lorchard-mall`.`t_lorchard_mall_goods_category` (`id`, `merchantId`, `name`, `description`, `imagePath`, `index`, `level`, `parentId`, `createTime`, `updateTime`) VALUES ('9', '0', '有机蔬菜', '非常好吃', 'http://pdjllrqfe.bkt.clouddn.com/FgIjFGutsBvt3VkaIGc_9Tox_mS0', '0', '1', '0', '2018-08-16 18:23:40', NULL);

-- 分类树处理
CREATE DEFINER=`root`@`%` PROCEDURE `showCategoryTreeNodes`(IN rootid INT)
BEGIN
 DECLARE Level int ;
 drop TABLE IF EXISTS tmpLst;
 CREATE TABLE tmpLst (
 id int,
 nLevel int,
 sCort varchar(8000)
 );
 Set Level=0 ;
 INSERT into tmpLst SELECT id,Level,ID FROM t_lorchard_mall_goods_category WHERE parentId=rootid;
 WHILE ROW_COUNT()>0 DO
 SET Level=Level+1 ;
 INSERT into tmpLst
  SELECT A.ID,Level,concat(B.sCort,A.ID) FROM t_lorchard_mall_goods_category A,tmpLst B
  WHERE A.parentId=B.ID AND B.nLevel=Level-1 ;
 END WHILE;
END
