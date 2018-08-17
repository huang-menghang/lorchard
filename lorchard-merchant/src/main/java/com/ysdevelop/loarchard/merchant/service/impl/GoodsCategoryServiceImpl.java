package com.ysdevelop.loarchard.merchant.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
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
		// 调用存储过程实现树形分类
		categoryDao.callTreeProcedure(Constant.DEFALULT_ZERO);
		PageHelper.startPage(integerPageNum, integerPageSize, Boolean.TRUE);
		List<GoodsCategory> goodsCategories = categoryDao.list(queryMap);
		PageInfo<GoodsCategory> pageInfo = new PageInfo<>(goodsCategories);
		return pageInfo;
	}

	@Override
	public List<GoodsCategory> listParent() {

		return categoryDao.listParent();
	}

	@Override
	@Transactional
	public void add(GoodsCategory category) {
		if (category == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		if (category.getParentId() == Constant.DEFALULT_ZERO.longValue()) {
			category.setLevel(Constant.DEFALULT_ONE);
		} else {
			category.setLevel(Constant.DEFALULT_TWO);
		}

		Integer changeCount = categoryDao.add(category);
		if (changeCount == Constant.DEFALULT_ZERO) {
			throw new WebServiceException(CodeMsg.CATEGORY_ADD_FAILED);
		}
	}

	@Override
	public GoodsCategory getById(Integer id) {
		if (id == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		return categoryDao.getById(id);
	}

	@Transactional
	@Override
	public void update(GoodsCategory category) {
		if (category == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		if (category.getParentId() == Constant.DEFALULT_ZERO.longValue()) {
			category.setLevel(Constant.DEFALULT_ONE);
		} else {
			category.setLevel(Constant.DEFALULT_TWO);
		}

		Integer changeCount = categoryDao.update(category);

		if (changeCount == Constant.DEFALULT_ZERO) {
			throw new WebServiceException(CodeMsg.CATEGORY_UPDATE_ERROR);
		}

	}

	@Override
	public void deleteById(Integer id) {
		if (id == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}

		Integer changeCount = categoryDao.deleteById(id);
		if(changeCount == Constant.DEFALULT_ZERO){
			throw new WebServiceException(CodeMsg.DELETE_ERROR);
		}

	}

}
