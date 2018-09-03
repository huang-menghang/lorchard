package com.ysdevelop.lorchard.merchant.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ysdevelop.lorchard.merchant.entity.Goods;
import com.ysdevelop.lorchard.merchant.entity.PreviewImages;

/**
 * @author user
 *
 */
public interface GoodsDao {
	
	/**
	 * 
	 * 获取所有商品
	 * 
	 * @param queryMap
	 * 
	 * @return List<Goods>返回商品列表
	 */
	List<Goods> list(@Param(value = "queryMap") Map<String, String> queryMap);
	
	/**
	 * 获取所有商品图片
	 * 
	 * @return 返回轮播图集合
	 */
	List<PreviewImages> listPreviewImage();
	
	
	
	/**
	 * 
	 * 通过商品id获取商品详细信息
	 * 
	 * @param id 商品id
	 * 
	 * @return Goods 商品
	 */
	Goods getById(Integer id);
	
	/**
	 * 
	 * 获取所有顶级分类
	 * 
	 * @return List<Goods> 商品分类的id和类别名
	 */
	List<Goods> listCategory();
	
	/**
	 * 修改商品
	 * 
	 * @param goods 商品
	 * 
	 * @return Integer 返回一个值如果等于0,则修改商品失败
	 */
	Integer update(Goods goods);
	
	/**
	 * 通过id,将商品status设为1,即下架状态
	 * 
	 * @param id 商品的id
	 * 
	 * @return Integer 返回一个值如果等于0,则下架商品失败
	 * 
	 */
	Integer deleteById(Integer id);
	
	/**
	 * 
	 * 添加商品
	 * 
	 * @param goods 商品
	 * 
	 * @return Integer 返回一个值如果等于0,则添加商品失败
	 * */
	Integer add(Goods goods);
	
	/**
	 * 
	 * 添加商品尺寸
	 * 
	 * @param goods 商品
	 * 
	 * @return Integer 返回一个值如果等于0,则添加商品尺寸失败
	 */
	Integer addSpecifications(Goods goods);
	
	/**
	 * 
	 * 通过商品id获取该商品所有图片
	 * 
	 * @param id 商品id
	 * 
	 * @return  List<PreviewImages> 轮播图集合
	 * 
	 */
	List<PreviewImages> getPreviewImageById(Integer id);
	
	/**
	 * 
	 * 通过商品id删除该商品的所有图片
	 * 
	 * @param goodsId 商品id
	 * 
	 * @return Integer 返回一个值如果等于0,则删除商品图片失败
	 */
	Integer deletePreviewImagesByGoodsId(Long goodsId);
	
	/**
	 * 
	 * 通过图片数组添加多个商品图片
	 * 
	 * @param previewImages 轮播图数组
	 * 
	 * @return Integer 返回一个值如果等于0,则添加商品图片失败
	 * 
	 */
	Integer addPreviewImagesByGoodsId(@Param("previewImages") List<PreviewImages> previewImages);
}
