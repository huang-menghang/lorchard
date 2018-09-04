DROP TABLE IF EXISTS `t_lorchard_mall_merchant`;
CREATE TABLE `t_lorchard_mall_merchant` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(32) DEFAULT NULL COMMENT '登录名',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号码',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态,0是正常,1是未注册商家信息',
  `salt` varchar(32) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_lorchard_mall_member`;
CREATE TABLE `t_lorchard_mall_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `merchantId` tinyint(1) NOT NULL DEFAULT '0' COMMENT '商家Id',
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
  `birthday` varchar(50) DEFAULT NULL COMMENT '生日',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创健日期',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_lorchard_mall_goods_category`;
CREATE TABLE `t_lorchard_mall_goods_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `merchantId` int(11) NOT NULL DEFAULT '0' COMMENT '商家Id',
  `name` varchar(16) NOT NULL COMMENT '分类名称',
  `description` varchar(256) NOT NULL COMMENT '描述',
  `imagePath` varchar(256) DEFAULT NULL COMMENT '分类地址',
  `index` int(8) NOT NULL DEFAULT '1' COMMENT '分类索引',
  `level` int(2) NOT NULL DEFAULT '1' COMMENT '等级,1,2',
  `parentId` int(8) NOT NULL DEFAULT '0' COMMENT '上级Id',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0,表示未删除,1,表示删除',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创健日期',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

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
delimiter //
drop PROCEDURE IF EXISTS showCategoryTreeNodes//
CREATE PROCEDURE showCategoryTreeNodes (IN rootid INT)
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
END;
//
delimiter ;

DROP TABLE IF EXISTS `t_lorchard_mall_shop`;
CREATE TABLE `t_lorchard_mall_shop` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `merchantId` int(8) DEFAULT NULL,
  `name` varchar(32) NOT NULL COMMENT '店铺名称',
  `province` varchar(64) NOT NULL COMMENT '省',
  `city` varchar(64) NOT NULL COMMENT '市',
  `town` varchar(64) DEFAULT NULL COMMENT '区县',
  `detailAddress` varchar(255) NOT NULL COMMENT '详细地址',
  `description` varchar(255) NOT NULL COMMENT '描述',
  `operatorName` varchar(32) NOT NULL COMMENT '运营人的信息',
  `merchantQr` varchar(255) DEFAULT NULL,
  `mobile` varchar(11) NOT NULL,
  `qq` varchar(12) NOT NULL,
  `wechatNo` varchar(32) NOT NULL,
  `email` varchar(64) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
--会员日志
DROP TABLE IF EXISTS `t_lorchard_mall_shop_system_log`;
CREATE TABLE `t_lorchard_mall_shop_system_log` (
   `id` int(8) NOT NULL AUTO_INCREMENT,
   `merchantId` int(8) NOT NULL COMMENT '商家id',
   `memberId` int(8) NOT NULL COMMENT '会员id',
   `goodsId` int(8) NOT NULL COMMENT '产品id',
   `type` tinyint(1) NOT NULL DEFAULT '0',
   `description` varchar(256) NOT NULL COMMENT '日志描述',
   `createTime` timestamp,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--店铺每日流量统计
DROP TABLE IF EXISTS `t_lorchard_mall_shop_flow_stat_daily_site`;
CREATE TABLE `t_lorchard_mall_shop_flow_stat_daily_site` (
   `id` int(8) NOT NULL AUTO_INCREMENT,
   `merchantId` int(8) NOT NULL COMMENT '商家id',
   `pageView` int(8) NOT NULL  COMMENT '浏览量',
   `visitorNumber` int(8) NOT NULL COMMENT '访客数',
   `goodsView` int(8) NOT NULL  COMMENT '商品浏览量',
   `goodsAceessNumber` int(8) NOT NULL  COMMENT '商品访问数',
   `date` date NOT NULL COMMENT '日期',
   `createTime` timestamp,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--商品表
DROP TABLE IF EXISTS `t_lorchard_mall_goods`;
CREATE TABLE `t_lorchard_mall_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `merchantId` int(11) NOT NULL COMMENT '商家ID',
  `name` varchar(16) NOT NULL COMMENT '商品名称',
  `description` varchar(256) NOT NULL COMMENT '商品描述',
  `categoryId` int(11) NOT NULL COMMENT '商品类别',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0为上架,1为下架状态',
  `recommend` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0为非推荐,1为推荐商品',
  `stock` int(8) DEFAULT '0' COMMENT '商品库存',
  `sales` int(8) NOT NULL DEFAULT '0' COMMENT '商品销量',
  `createTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

--商品轮播图表
DROP TABLE IF EXISTS `t_lorchard_mall_goods_previewimage`;
CREATE TABLE `t_lorchard_mall_goods_previewimage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `previewImagePath` text NOT NULL COMMENT '轮播图路径',
  `previewImageIndex` tinyint(9) DEFAULT NULL COMMENT '轮播图索引',
  `goodsId` int(11) NOT NULL COMMENT '商品id',
  `createTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0为未删除,1为删除状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=utf8;


--商品规格表
DROP TABLE IF EXISTS `t_lorchard_mall_goods_specifications`;
CREATE TABLE `t_lorchard_mall_goods_specifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '规格id',
  `type` tinyint(1) NOT NULL COMMENT '0表示500g,1表示1份',
  `originalPrice` decimal(16,2) NOT NULL COMMENT '正常价',
  `minPrice` decimal(16,2) NOT NULL COMMENT '最低价',
  `specificationsDescription` varchar(255) NOT NULL COMMENT '规格描述',
  `goodsId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- 商店每日流量
delimiter //
drop PROCEDURE IF EXISTS shopFlowStatDaily//
CREATE PROCEDURE shopFlowStatDaily ()
BEGIN
    -- 定义一个变量
    DECLARE  mid INT(8); 
    -- 遍历数据结束标志
    DECLARE done INT DEFAULT FALSE;
    -- 定义一个游标变量
    DECLARE cur_merchant CURSOR FOR 
		SELECT  id FROM `t_lorchard_mall_merchant`;
    -- 将结束标志绑定到游标
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    -- 打开游标
    OPEN  cur_merchant;     
    -- 遍历
    read_loop: LOOP
            -- 取值 取多个字段
            FETCH  NEXT from cur_merchant INTO mid;
            IF done THEN
                LEAVE read_loop;
            END IF;
 
        -- 你自己想做的操作
    INSERT INTO `t_lorchard_mall_shop_flow_stat_daily_site` (
           `merchantId`,
	         `pageView`,
	         `visitorNumber`,
	         `goodsView`,
	         `goodsAceessNumber`,
	         `date`,
	        `createTime`
       )
     VALUES
	  (
	    	mid,IFNULL(
			    (
			  	SELECT
					count(id)
				 FROM
					 `t_lorchard_mall_shop_system_log`
				 WHERE
					 DATEDIFF(`createTime`, NOW()) =- 1
         AND `merchantId` = mid
			   ),
			 0
		  ),
		  IFNULL(
			 (
				 SELECT
					count(DISTINCT(memberId))
				 FROM
					`t_lorchard_mall_shop_system_log`
				 WHERE
					DATEDIFF(`createTime`, NOW()) =- 1
         AND `merchantId` = mid
			 ),
			 0
		  ),
		 IFNULL(
			 (
				  SELECT
					  count(id)
				  FROM
					  `t_lorchard_mall_shop_system_log`
			  	WHERE
					  type = 1
				  AND (
					  DATEDIFF(`createTime`, NOW()) =- 1
				  )
          AND `merchantId` = mid
			  ),
			 0
	  	),
		 IFNULL(
			 (
				 SELECT
					 count(DISTINCT(`goodsId`))
				 FROM
					 `t_lorchard_mall_shop_system_log`
				 WHERE
					  type = 1
				 AND (
					  DATEDIFF(`createTime`, NOW()) =- 1
				 )
         AND `merchantId` = mid
			 ),
			 0
		 ),
		   date_sub(curdate(), INTERVAL 1 DAY),
		 NOW()
	 );
    END LOOP;
    END
//
delimiter ;

