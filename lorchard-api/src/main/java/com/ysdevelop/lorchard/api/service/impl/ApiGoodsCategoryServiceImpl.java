package com.ysdevelop.lorchard.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysdevelop.lorchard.api.entity.GoodsCategoryVo;
import com.ysdevelop.lorchard.api.mapper.ApiGoodsCategoryDao;
import com.ysdevelop.lorchard.api.service.ApiGoodsCategoryService;

/**
 * 
 * @author USER
 *
 */
@Service
public class ApiGoodsCategoryServiceImpl implements ApiGoodsCategoryService {

	@Autowired
	private ApiGoodsCategoryDao categoryDao;

	@Override
	public List<GoodsCategoryVo> listParent() {

		return categoryDao.listParent();
	}

}
