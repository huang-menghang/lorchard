package com.ysdevelop.lorchard.api.mapper;

import java.util.List;

import com.ysdevelop.lorchard.api.entity.GoodsCategoryVo;

/**
 * 
 * @author USER
 *
 */
public interface ApiGoodsCategoryDao {
	
	/**
	 * 获取所有顶级分类
	 * @return
	 */
	List<GoodsCategoryVo> listParent();
	
}
