package com.ysdevelop.lorchard.merchant.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.common.exception.WebServiceException;
import com.ysdevelop.lorchard.common.result.CodeMsg;
import com.ysdevelop.lorchard.common.utils.ApiConstant;
import com.ysdevelop.lorchard.merchant.entity.Activity;
import com.ysdevelop.lorchard.merchant.entity.Goods;
import com.ysdevelop.lorchard.merchant.entity.SpellingGroup;
import com.ysdevelop.lorchard.merchant.mapper.ActivityDao;
import com.ysdevelop.lorchard.merchant.service.ActivityService;
import com.ysdevelop.lorchard.shiro.token.TokenManager;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityDao activityDao;
	
	
	private List<Long> goodsId;
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void add(SpellingGroup spellingGroup) {
		if(spellingGroup==null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		Long merchantId = TokenManager.getUserId();
		spellingGroup.setMerchantId(merchantId);
		activityDao.add(spellingGroup);
		goodsId = spellingGroup.getGoodsId();
		Long id = spellingGroup.getId();
		System.out.println("id"+id);
		activityDao.addActivityGoods(goodsId,spellingGroup);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(Integer totalNumber, Double spellingGroupPrice) {
		if(totalNumber==null||spellingGroupPrice==null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		
		activityDao.update(totalNumber, spellingGroupPrice,goodsId);
	}
	
	/**
	 * 分页查询所有活动
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public PageInfo<Activity> list(Map<String, String> queryMap) {
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
		List<Activity> activity = activityDao.list(queryMap);
		PageInfo<Activity> pageInfo = new PageInfo<>(activity);
		return pageInfo;	
	}
	/**
	 * 
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteById(Integer id) {
		if (id == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}

		Integer changeCount =activityDao.deleteById(id);
		if (changeCount == ApiConstant.DEFALULT_ZERO) {
			throw new WebServiceException(CodeMsg.DELETE_ERROR);
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public SpellingGroup getById(Integer id) {
		if (id == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		SpellingGroup spellingGroup = activityDao.getById(id);
		List<Goods> goodsById = activityDao.getGoodsById(id);
		spellingGroup.setGoods(goodsById);
		return spellingGroup;
	}
	
}
