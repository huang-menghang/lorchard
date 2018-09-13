package com.ysdevelop.lorchard.api.mapper;

import com.ysdevelop.lorchard.api.entity.MemberPointVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月13日 上午11:49:25 
 *
 * @Package com.ysdevelop.lorchard.api.mapper
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public interface ApiMemberPointDao {
	/**
	 * 添加用户积分
	 * @param membershipPoint
	 * @return
	 */
	Integer add(MemberPointVo memberPoint);
	
	/**
	 * 更新用户积分
	 * @param membershipPoint
	 * @return
	 */
	Integer update(MemberPointVo memberPoint);
	
	/**
	 * 获取用户积分
	 * @param membershipPoint
	 * @return
	 */
	MemberPointVo getById(MemberPointVo membershipPoint);
}
