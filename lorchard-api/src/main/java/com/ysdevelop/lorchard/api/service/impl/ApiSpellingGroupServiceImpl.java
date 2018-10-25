package com.ysdevelop.lorchard.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysdevelop.lorchard.api.entity.SpellingGroupOrderVo;
import com.ysdevelop.lorchard.api.entity.SpellingGroupVo;
import com.ysdevelop.lorchard.api.mapper.ApiSpellingGroupDao;
import com.ysdevelop.lorchard.api.service.ApiSpellingGroupService;
import com.ysdevelop.lorchard.common.exception.WebServiceException;
import com.ysdevelop.lorchard.common.result.CodeMsg;
import com.ysdevelop.lorchard.common.utils.ApiConstant;

/** 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年10月10日 上午11:18:05 
 *
 * @Package com.ysdevelop.lorchard.api.service.impl
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
@Service
public class ApiSpellingGroupServiceImpl implements ApiSpellingGroupService {
	
	@Autowired
	private ApiSpellingGroupDao spellingGroupDao;
	
	@Override
	public List<Long> goodsIdList(Long activityId) {
		return spellingGroupDao.goodsIdList(activityId);
	}
	
	private SpellingGroupOrderVo spellingGroupOrderVo = new SpellingGroupOrderVo();
	
	@Override
	public SpellingGroupVo getByMerchantId(Long merchantId) {
		return spellingGroupDao.getByMerchantId(merchantId);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void addSpellingGroupOrder(Long merchantId, Long memberId, String orderNo) {
		spellingGroupOrderVo.setInviteId(memberId);
		spellingGroupOrderVo.setMerchantId(merchantId);
		List<String> orderNos = spellingGroupOrderVo.getOrderNos();
		if(orderNos==null||orderNos.isEmpty()) {
			orderNos=new ArrayList<String>();
		}
		orderNos.add(orderNo);
		spellingGroupOrderVo.setOrderNos(orderNos);
		spellingGroupOrderVo.setOrderNoLast(orderNo);
		
		Integer addSpellingGroupOrder = spellingGroupDao.addSpellingGroupOrder(spellingGroupOrderVo);
		Integer updateSpellingGroupOrderId = spellingGroupDao.updateSpellingGroupOrderId(spellingGroupOrderVo);
		
		if(addSpellingGroupOrder==ApiConstant.DEFALULT_ZERO||updateSpellingGroupOrderId==ApiConstant.DEFALULT_ZERO) {
			throw new WebServiceException(CodeMsg.SPELLINGGROUPORDER_ERROR);
		}
	}

	@Override
	public void increaseSpellingGroupOrder(Long inviteId,Long merchantId, Long memberId, String orderNo) {
		spellingGroupOrderVo.setMemberId(memberId);
		List<String> orderNos = spellingGroupOrderVo.getOrderNos();
		orderNos.add(orderNo);
		spellingGroupOrderVo.setOrderNoLast(orderNo);
		Integer updateSpellingGroupOrder = spellingGroupDao.updateSpellingGroupOrder(spellingGroupOrderVo);
		Integer updateSpellingGroupOrderId = spellingGroupDao.updateSpellingGroupOrderId(spellingGroupOrderVo);
		if(updateSpellingGroupOrder==ApiConstant.DEFALULT_ZERO||updateSpellingGroupOrderId==ApiConstant.DEFALULT_ZERO) {
			throw new WebServiceException(CodeMsg.SPELLINGGROUPORDER_ERROR);
		}
	}
    
	@Override
	public Long getMemberCount(String orderNo) {
		Long memberCount=spellingGroupDao.getMemberCount();
		if(memberCount==null) {
			memberCount=(long) 1;
		}memberCount=(long) 2;
		return memberCount;
	}

}
