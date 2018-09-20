package com.ysdevelop.lorchard.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysdevelop.lorchard.api.entity.GoodsCategoryVo;
import com.ysdevelop.lorchard.api.mapper.ApiGoodsCategoryDao;
import com.ysdevelop.lorchard.api.service.ApiGoodsCategoryService;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:21:07 
 *
 * @Package com.ysdevelop.lorchard.api.service.impl
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
@Service
public class ApiGoodsCategoryServiceImpl implements ApiGoodsCategoryService {

	@Autowired
	private ApiGoodsCategoryDao categoryDao;

	@Override
	public List<GoodsCategoryVo> listParent(Long merchantId) {

		return categoryDao.listParent(merchantId);
	}

}
