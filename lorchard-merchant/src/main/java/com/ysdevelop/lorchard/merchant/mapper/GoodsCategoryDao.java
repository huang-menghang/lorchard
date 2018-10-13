package com.ysdevelop.lorchard.merchant.mapper;
/**
 * @author oldHuang
 * 
 * @Package com.ysdevelop.loarchard.merchant.controller
 * 
 * */
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ysdevelop.lorchard.merchant.entity.GoodsCategory;

public interface GoodsCategoryDao {
	
	/**
	 * 获取商家的所有商品类别
	 * 
	 * @param queryMap
	 * 
	 * @return List<GoodsCategory> 类别集合
	 */
	List<GoodsCategory> list(@Param(value = "queryMap") Map<String, String> queryMap);
	
	
	/**
	 * 调用数据库函数
	 * 
	 * @param rootId 类别的顶层类别
	 */
	void callTreeProcedure(Integer rootId);
	
	/**
	 * 获取类别信息
	 * 
	 * @param merchantId 商家id
	 * 
	 * @return 类别集合
	 */
	List<GoodsCategory> listParent(Long merchantId);
	
	/**
	 * 添加类别
	 * 
	 * @param category 类别信息
	 * 
	 * @return Integer 0代表失败
	 */
	Integer add(GoodsCategory category);
	
	/**
	 * 通过id获取商品类别
	 * 
	 * @param id 类别id
	 * 
	 * @return GoodsCategory 商品类别
	 */
	GoodsCategory getById(Integer id);
	
	/**
	 * 修改类别
	 * 
	 * @param category 商品类别
	 * 
	 * @return Integer 0代表失败
	 */
	Integer update(GoodsCategory category);
	
	/**
	 * 
	 * 删除商品类别
	 * 
	 * @param id 类别id
	 * 
	 * @return Integer 0代表失败
	 */
	Integer deleteById(Integer id);
}
