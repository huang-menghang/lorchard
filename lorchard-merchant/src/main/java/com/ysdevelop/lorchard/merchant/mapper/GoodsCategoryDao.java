package com.ysdevelop.lorchard.merchant.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ysdevelop.lorchard.merchant.entity.GoodsCategory;

public interface GoodsCategoryDao {

	List<GoodsCategory> list(@Param(value = "queryMap") Map<String, String> queryMap);

	void callTreeProcedure(Integer rootId);

	List<GoodsCategory> listParent();

	Integer add(GoodsCategory category);

	GoodsCategory getById(Integer id);

	Integer update(GoodsCategory category);

	Integer deleteById(Integer id);
}
