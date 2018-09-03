package com.ysdevelop.lorchard.merchant.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.merchant.entity.Goods;
import com.ysdevelop.lorchard.merchant.entity.PreviewImages;
import com.ysdevelop.lorchard.merchant.mapper.GoodsDao;
import com.ysdevelop.lorchard.merchant.service.GoodsService;
import com.ysdevelop.lorchard.common.exception.WebServiceException;
import com.ysdevelop.lorchard.common.result.CodeMsg;
import com.ysdevelop.lorchard.common.utils.Constant;

/**
 * @author user
 *
 */
@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsDao goodsDao;
	
	private Long goodsId;

	/**
	 * 分页查询所有商品还有对应每个商品的图片
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public PageInfo<Goods> list(Map<String, String> queryMap) {
		if (queryMap == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		// 获取分页条件的
		String pageSize = queryMap.get("limit");
		String pageNum = queryMap.get("page");
		if (pageSize == null || pageNum == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		Integer integerPageSize = Integer.parseInt(pageSize);
		Integer integerPageNum = Integer.parseInt(pageNum);
		// 调用存储过程实现树形分类
		
		PageHelper.startPage(integerPageNum, integerPageSize, Boolean.TRUE);
		List<Goods> goods = goodsDao.list(queryMap);
		
		//查询所有图片,并将他们放入对应的商品
		List<PreviewImages> listPreviewImage = goodsDao.listPreviewImage();
		for (Goods good : goods) {
			List<PreviewImages> transitionPreviewImage=new ArrayList<>();
			for (PreviewImages previewImages : listPreviewImage) {
				if (good.getId() == previewImages.getGoodsId()) {
					transitionPreviewImage.add(previewImages);
				}
			}
			good.setPreviewImages(transitionPreviewImage);
		}
		
		PageInfo<Goods> pageInfo = new PageInfo<>(goods);
		return pageInfo;
	}

	/**
	 * 通过id获取商品的详细信息还有轮播图
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Goods getById(Integer id) {
		if (id == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		Goods goods = goodsDao.getById(id);
		List<PreviewImages> previewImageById = goodsDao.getPreviewImageById(id);
		goods.setPreviewImages(previewImageById);
		return goods;
	}

	/**
	 * 查询所有商品类别的顶级分类
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<Goods> listCategory() {
		return goodsDao.listCategory();
	}

	/**
	 * 修改商品信息
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(Goods goods) {
		// 判断goods是否为空
		if (goods == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}

		// 判断正常价是否小于折后价
		if (goods.getOriginalPrice() < goods.getMinPrice()) {
			throw new WebServiceException(CodeMsg.ORIGINALPRIVE_LESS_THAN_MINPRICE);
		}

		Integer changeCount = goodsDao.update(goods);

		if (changeCount == Constant.DEFALULT_ZERO) {
			throw new WebServiceException(CodeMsg.CATEGORY_UPDATE_ERROR);
		}
		goodsId=goods.getId();
	}

	/**
	 * 通过id下架商品
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteById(Integer id) {
		if (id == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}

		Integer changeCount = goodsDao.deleteById(id);
		if (changeCount == Constant.DEFALULT_ZERO) {
			throw new WebServiceException(CodeMsg.DELETE_ERROR);
		}
	}

	/**
	 * 
	 * 添加商品
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void add(Goods goods) {
		if (goods == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}

		// 判断正常价是否小于折后价
		if (goods.getOriginalPrice() < goods.getMinPrice()) {
			throw new WebServiceException(CodeMsg.ORIGINALPRIVE_LESS_THAN_MINPRICE);
		}

		goods.setMerchantId(1L);
		Integer changeCount = goodsDao.add(goods);
		Integer changeCount2 = goodsDao.addSpecifications(goods);
		if (changeCount == Constant.DEFALULT_ZERO || changeCount2 == Constant.DEFALULT_ZERO) {
			throw new WebServiceException(CodeMsg.DELETE_ERROR);
		}
		goodsId=goods.getId();
	}
	
	/**
	 * 修改添加商品图片
	 * */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void previewImages(List<PreviewImages> previewImages) {
		for (PreviewImages previewImage : previewImages) {
			if(previewImage.getGoodsId()==null||previewImage.getGoodsId().equals("")) {
				previewImage.setGoodsId(goodsId);
			}
		}
		goodsDao.deletePreviewImagesByGoodsId(goodsId);
		goodsDao.addPreviewImagesByGoodsId(previewImages);
	}

}
