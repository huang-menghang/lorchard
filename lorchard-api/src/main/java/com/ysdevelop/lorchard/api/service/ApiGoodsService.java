package com.ysdevelop.lorchard.api.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.api.entity.GoodsVo;
	
/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:20:07 
 *
 * @Package com.ysdevelop.lorchard.api.service
 *
 * @Description: TODO
 *
 * @version V1.0
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
	
	
	List<GoodsVo> spellingGroupList(List<Long> goodsIds);
}
