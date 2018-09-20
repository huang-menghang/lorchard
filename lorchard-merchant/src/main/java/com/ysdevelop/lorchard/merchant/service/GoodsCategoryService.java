package com.ysdevelop.lorchard.merchant.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.merchant.entity.GoodsCategory;

public interface GoodsCategoryService {
	PageInfo<GoodsCategory> list(Map<String, String> queryMap);

	List<GoodsCategory> listParent(Long merchantId);

	void add(GoodsCategory category);

	GoodsCategory getById(Integer id);

	void update(GoodsCategory category);

	void deleteById(Integer id);
}
