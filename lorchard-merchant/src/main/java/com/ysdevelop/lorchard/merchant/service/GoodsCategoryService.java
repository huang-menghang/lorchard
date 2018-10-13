package com.ysdevelop.lorchard.merchant.service;
/**
 * @author oldHuang
 * 
 * @Package com.ysdevelop.loarchard.merchant.controller
 * 
 * */
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.merchant.entity.GoodsCategory;

public interface GoodsCategoryService {
	/**
	 * 获取商品类别
	 * 
	 * @param queryMap
	 * 
	 * @return PageInfo<GoodsCategory> 商品类别分类
	 */
	PageInfo<GoodsCategory> list(Map<String, String> queryMap);
	
	/**
	 * 获取顶层分类
	 * 
	 * @param merchantId 商家id
	 * 
	 * @return List<GoodsCategory> 顶层分类
	 */
	List<GoodsCategory> listParent(Long merchantId);
	
	/**
	 * 添加商品类别
	 * 
	 * @param category 商品类别信息
	 */
	void add(GoodsCategory category);
	
	/**
	 * 获取类别信息
	 * 
	 * @param id 类别id
	 * 
	 * @return GoodsCategory 类别信息
	 */
	GoodsCategory getById(Integer id);
	
	/**
	 * 修改类别信息
	 * 
	 * @param category 类别信息
	 */
	void update(GoodsCategory category);
	
	/**
	 * 删除类别
	 * 
	 * @param id 类别id
	 */
	void deleteById(Integer id);
}
