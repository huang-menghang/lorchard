package com.ysdevelop.lorchard.api.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ysdevelop.lorchard.api.entity.GoodsVo;
import com.ysdevelop.lorchard.api.entity.OrderItemVo;
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
	 * 通过order查询轮播图
	 * @param orderItems
	 * @return
	 */
	List<PreviewImagesVo> listPreviewImageByOrder(List<OrderItemVo> orderItems);
	
	/**
	 * 通过good查询轮播图
	 * @param orderItems
	 * @return
	 */
	List<PreviewImagesVo> listPreviewImageByGood(List<GoodsVo> goods);
	
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

	List<GoodsVo> spellingGroupList(List<Long> goodsIds);
}
