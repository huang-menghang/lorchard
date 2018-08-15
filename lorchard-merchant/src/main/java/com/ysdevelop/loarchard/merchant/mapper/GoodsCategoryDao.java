package com.ysdevelop.loarchard.merchant.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ysdevelop.loarchard.merchant.entity.GoodsCategory;

public interface GoodsCategoryDao {

	List<GoodsCategory> list(@Param(value="queryMap")Map<String,String> queryMap);
	
}
