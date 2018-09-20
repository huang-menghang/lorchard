package com.ysdevelop.lorchard.api.mapper;

import java.util.List;

import com.ysdevelop.lorchard.api.entity.GoodsCategoryVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:17:30 
 *
 * @Package com.ysdevelop.lorchard.api.mapper
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public interface ApiGoodsCategoryDao {
	
	/**
	 * 获取所有顶级分类
	 * @param merchantId 
	 * @return
	 */
	List<GoodsCategoryVo> listParent(Long merchantId);
	
}
