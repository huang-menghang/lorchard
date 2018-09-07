package com.ysdevelop.lorchard.api.service;

import java.util.List;

import com.ysdevelop.lorchard.api.entity.GoodsCategoryVo;

/**
 * 
 * @author USER
 *
 */
public interface ApiGoodsCategoryService {
	
	/**
	 * 获取顶级分类
	 * @return
	 */
	List<GoodsCategoryVo> listParent();
}
