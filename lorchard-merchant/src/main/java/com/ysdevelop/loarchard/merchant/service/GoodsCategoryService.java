package com.ysdevelop.loarchard.merchant.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.loarchard.merchant.entity.GoodsCategory;

public interface GoodsCategoryService {
	PageInfo<GoodsCategory> list(Map<String, String> queryMap);

	List<GoodsCategory> listParent();

	void add(GoodsCategory category);

	GoodsCategory getById(Integer id);

	void update(GoodsCategory category);

	void deleteById(Integer id);
}
