package com.ysdevelop.lorchard.api.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.api.entity.GoodsCategoryVo;

/**
 * 
 * @author USER
 *
 */
public interface ApiGoodsCategoryService {
	
	/**
	 * 获取所有分类
	 * @param queryMap
	 * @return
	 */
	PageInfo<GoodsCategoryVo> list(Map<String, String> queryMap);
	
	/**
	 * 获取当前分类下子分类
	 * @param parentId
	 * @return
	 */
	List<GoodsCategoryVo> listByParentId(Long parentId);
	
	/**
	 * 获取顶级分类
	 * @return
	 */
	List<GoodsCategoryVo> listParent();
	
	/**
	 * 添加分类
	 * @param category
	 */
	void add(GoodsCategoryVo category);
	
	/**
	 * 获取分类
	 * @param id
	 * @return
	 */
	GoodsCategoryVo getById(Long id);
	
	/**
	 * 更新分类
	 * @param category
	 */
	void update(GoodsCategoryVo category);
	
	/**
	 * 删除分类
	 * @param id
	 */
	void deleteById(Long id);
}
