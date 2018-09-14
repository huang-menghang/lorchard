package com.ysdevelop.lorchard.api.service;

import com.ysdevelop.lorchard.api.entity.MemberPointVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月13日 上午11:47:30 
 *
 * @Package com.ysdevelop.lorchard.api.service
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public interface ApiMemberPointService {
	/**
	 * 获取用户积分
	 * @param membershipPoint
	 * @return
	 */
	MemberPointVo getById(MemberPointVo memberPoint);
	
	/**
	 * 更新用户积分
	 * @param membershipPoint
	 */
	void update(MemberPointVo memberPoint);
	
	/**
	 * 更新签到状态
	 */
	void refreshPointStatus();
}
