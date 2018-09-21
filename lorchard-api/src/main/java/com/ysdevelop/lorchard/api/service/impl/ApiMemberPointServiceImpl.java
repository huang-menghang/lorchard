package com.ysdevelop.lorchard.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysdevelop.lorchard.api.entity.MemberPointVo;
import com.ysdevelop.lorchard.api.mapper.ApiMemberPointDao;
import com.ysdevelop.lorchard.api.service.ApiMemberPointService;
import com.ysdevelop.lorchard.common.exception.WebServiceException;
import com.ysdevelop.lorchard.common.result.CodeMsg;
import com.ysdevelop.lorchard.common.utils.ApiConstant;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月13日 上午11:49:07 
 *
 * @Package com.ysdevelop.lorchard.api.service.impl
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
@Service
public class ApiMemberPointServiceImpl implements ApiMemberPointService{
	@Autowired
	private ApiMemberPointDao memberPointDao;

	@Override
	public MemberPointVo getById(MemberPointVo memberPoint) {
		MemberPointVo memberPointVo = memberPointDao.getById(memberPoint);
		if (memberPointVo == null) {
			memberPointVo = memberPoint;
			memberPointVo.setStatus(0);
			Integer changeCount = memberPointDao.add(memberPointVo);
			if (changeCount == ApiConstant.DEFALULT_ZERO) {
				throw new WebServiceException(CodeMsg.CATEGORY_ADD_FAILED);
			}
		}
		return memberPointVo;

	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void update(MemberPointVo memberPoint) {
		if (memberPoint == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		
		if(memberPoint.getStatus() == ApiConstant.DEFALULT_ZERO) {
			long availableScore=memberPoint.getAvailableScore()+memberPoint.getTodayScore();
			long totalDay=memberPoint.getTotalDay()+1;
			memberPoint.setAvailableScore(availableScore);
			memberPoint.setTotalDay(totalDay);
			Integer changeCount = memberPointDao.update(memberPoint);
			if (changeCount == ApiConstant.DEFALULT_ZERO) {
				throw new WebServiceException(CodeMsg.CATEGORY_UPDATE_ERROR);
			}
		}else{
			throw new WebServiceException(CodeMsg.CATEGORY_UPDATE_ERROR); 
		}

	}

	@Override
	public void refreshPointStatus() {
		memberPointDao.refreshPointStatus();
	}
	
	

}
