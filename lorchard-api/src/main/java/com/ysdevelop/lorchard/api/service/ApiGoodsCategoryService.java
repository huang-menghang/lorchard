package com.ysdevelop.lorchard.api.service;

import java.util.List;

import com.ysdevelop.lorchard.api.entity.GoodsCategoryVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:20:02 
 *
 * @Package com.ysdevelop.lorchard.api.service
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public interface ApiGoodsCategoryService {
	
	/**
	 * 获取顶级分类
	 * @param merchantId 
	 * @return
	 */
	List<GoodsCategoryVo> listParent(Long merchantId);
}
