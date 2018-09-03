package com.ysdevelop.lorchard.merchant.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.merchant.entity.Goods;
import com.ysdevelop.lorchard.merchant.entity.PreviewImages;
	

/**
 * @author user
 *
 */
public interface GoodsService {
	
	/**
	 * 
	 * 分页查询所有商品还有对应每个商品的图片
	 * 
	 * @param queryMap 分页
	 * 
	 * @return PageInfo<Goods> 分页的Goods
	 */
	PageInfo<Goods> list(Map<String, String> queryMap);
	
	/**
	 * 通过id获取商品的详细信息还有轮播图
	 * 
	 * @param id 商品的id
	 * 
	 * @return Goods 商品
	 */
	Goods getById(Integer id);
	
	/**
	 * 
	 * 查询所有商品类别的顶级分类
	 * 
	 * @return List<Goods> 商品列表
	 */
	List<Goods> listCategory();
	
	/**
	 * 修改商品信息
	 * 
	 * @param goods 商品
	 * 
	 */
	void update(Goods goods);
	
	/**
	 * 通过id删除商品
	 * 
	 * @param id 商品的id
	 * 
	 * */
	void deleteById(Integer id);
	
	/**
	 * 添加商品
	 * 
	 * @param goods 商品
	 * 
	 * */
	void add(Goods goods);
	
	
	/**
	 * 修改添加商品图片
	 * 
	 * @param previewImages 商品图片
	 * 
	 * */
	void previewImages(List<PreviewImages> previewImages);
}
