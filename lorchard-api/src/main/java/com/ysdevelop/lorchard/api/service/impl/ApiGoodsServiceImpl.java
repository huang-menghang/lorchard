package com.ysdevelop.lorchard.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.api.entity.GoodsVo;
import com.ysdevelop.lorchard.api.entity.PreviewImagesVo;
import com.ysdevelop.lorchard.api.mapper.ApiGoodsDao;
import com.ysdevelop.lorchard.api.service.ApiGoodsService;
import com.ysdevelop.lorchard.common.exception.WebServiceException;
import com.ysdevelop.lorchard.common.result.CodeMsg;
import com.ysdevelop.lorchard.common.utils.Constant;

/**
 * 
 * @author USER
 *
 */
@Service
public class ApiGoodsServiceImpl implements ApiGoodsService {

	@Autowired
	private ApiGoodsDao apiGoodsDao;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public PageInfo<GoodsVo> list(Map<String, String> queryMap) {
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
		apiGoodsDao.callTreeProcedure(Constant.DEFALULT_ZERO);
		PageHelper.startPage(integerPageNum, integerPageSize, Boolean.TRUE);
		List<GoodsVo> goods = apiGoodsDao.list(queryMap, Constant.DEFALULT_ONE);
		// 查询所有图片,并将他们放入对应的商品
		List<PreviewImagesVo> listPreviewImage = apiGoodsDao.listPreviewImage();
		for (GoodsVo good : goods) {
			List<PreviewImagesVo> transitionPreviewImage = new ArrayList<>();
			for (PreviewImagesVo previewImages : listPreviewImage) {
				if (good.getId() == previewImages.getGoodsId()) {
					transitionPreviewImage.add(previewImages);
				}
			}
			good.setPreviewImages(transitionPreviewImage);
		}
		PageInfo<GoodsVo> pageInfo = new PageInfo<>(goods);
		return pageInfo;
	}

	@Override
	public GoodsVo getById(Long id) {
		GoodsVo goodsVo = apiGoodsDao.getById(id);
		List<PreviewImagesVo> previewImages = apiGoodsDao.getPreviewImageById(id);
		goodsVo.setPreviewImages(previewImages);
		return goodsVo;
		
	}

}
