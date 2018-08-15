package com.ysdevelop.loarchard.merchant.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysdevelop.loarchard.merchant.entity.GoodsCategory;
import com.ysdevelop.loarchard.merchant.mapper.GoodsCategoryDao;
import com.ysdevelop.loarchard.merchant.service.GoodsCategoryService;
import com.ysdevelop.lochard.common.exception.WebServiceException;
import com.ysdevelop.lochard.common.result.CodeMsg;
import com.ysdevelop.lochard.common.utils.Constant;

@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

	@Autowired
	private GoodsCategoryDao categoryDao;

	@Override
	public PageInfo<GoodsCategory> list(Map<String, String> queryMap) {
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
		PageHelper.startPage(integerPageNum, integerPageSize, Boolean.TRUE);
		List<GoodsCategory> goodsCategories = categoryDao.list(queryMap);
		PageInfo<GoodsCategory> pageInfo = new PageInfo<>(goodsCategories);
		return pageInfo;
	}


}
