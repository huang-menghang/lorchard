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

/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.service.impl
 *
 * @Description 活动
 *
 * @Date 2018年10月12日
 *
 * @Version
 */
@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityDao activityDao;
	
	
	private List<Long> goodsId;
	
	/**
	 *将商品设置为活动商品
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void add(SpellingGroup spellingGroup) {
		if(spellingGroup==null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		Long merchantId = TokenManager.getUserId();
		spellingGroup.setMerchantId(merchantId);
		SpellingGroup groupIsExist = activityDao.groupIsExist(spellingGroup);
		if(groupIsExist.getId()!=null&&!groupIsExist.getId().toString().isEmpty()) {
			throw new WebServiceException(CodeMsg.ACTIVITY_IS_EXIST);
		}
		
		Integer add = activityDao.add(spellingGroup);
		goodsId = spellingGroup.getGoodsId();
		Long id = spellingGroup.getId();
		System.out.println("id"+id);
		Integer addActivityGoods = activityDao.addActivityGoods(goodsId,spellingGroup);
		if(add==ApiConstant.DEFALULT_ZERO||addActivityGoods==ApiConstant.DEFALULT_ZERO) {
			throw new WebServiceException(CodeMsg.ACTIVITY_ADD_ERROR);
		}
	}
	
	/**
	 * 修改活动商品的拼团人数和拼团价
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(Integer totalNumber, Double spellingGroupPrice) {
		if(totalNumber==null||spellingGroupPrice==null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		
		Integer update = activityDao.update(totalNumber, spellingGroupPrice,goodsId);
		if(update==ApiConstant.DEFALULT_ZERO) {
			throw new WebServiceException(CodeMsg.ACTIVITY_GOODS_UPDATE_ERROR);
		}
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
	 * 删除活动
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
	
	/**
	 *获取活动信息 
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public SpellingGroup getById(Integer id) {
		if (id == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		SpellingGroup spellingGroup = activityDao.getById(id);
		return spellingGroup;
	}

	@Override
	public PageInfo<Goods> getGoodsById(Map<String, String> queryMap) {
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
		List<Goods> goods= activityDao.getGoodsById(queryMap);
		
		PageInfo<Goods> pageInfo = new PageInfo<>(goods);
		return pageInfo;
	}

	
	
}
