package com.ysdevelop.lorchard.api.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.api.entity.GoodsVo;
	
/**
 * 
 * @author USER
 *
 */
public interface ApiGoodsService {
	
	/**
	 * 获取商品集合
	 * @param queryMap
	 * @return
	 */
	PageInfo<GoodsVo> list(Map<String, String> queryMap);
	
	/**
	 * 获取指定商品
	 * @param id
	 * @return
	 */
	GoodsVo getById(Long id);
}
