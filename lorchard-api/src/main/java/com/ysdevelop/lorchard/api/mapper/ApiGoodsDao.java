package com.ysdevelop.lorchard.api.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ysdevelop.lorchard.api.entity.GoodsVo;
import com.ysdevelop.lorchard.api.entity.PreviewImagesVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:17:35 
 *
 * @Package com.ysdevelop.lorchard.api.mapper
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public interface ApiGoodsDao {
	
	/**
	 * 获取所有商品
	 * @param queryMap
	 * @param merchantId
	 * 
	 * @return
	 */
	List<GoodsVo> list(@Param(value = "queryMap") Map<String, String> queryMap);
	
	/**
	 * 获取指定商品
	 * @param id
	 * @return
	 */
	GoodsVo getById(Long id);
	
	/**
	 * 获取所有商品图片
	 * 
	 * @return 返回轮播图集合
	 */
	List<PreviewImagesVo> listPreviewImage();
	
	/**
	 * 
	 * 通过商品id获取该商品所有图片
	 * 
	 * @param id 商品id
	 * 
	 * @return  List<PreviewImages> 轮播图集合
	 * 
	 */
	List<PreviewImagesVo> getPreviewImageById(Long id);
}
