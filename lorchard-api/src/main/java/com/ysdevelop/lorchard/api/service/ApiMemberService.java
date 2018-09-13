package com.ysdevelop.lorchard.api.service;

import javax.servlet.http.HttpServletRequest;

import com.ysdevelop.lorchard.api.entity.MemberVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:20:12 
 *
 * @Package com.ysdevelop.lorchard.api.service
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public interface ApiMemberService {
	
	/**
	 * 通过code获取指定会员
	 * @param code
	 * @return
	 */
	MemberVo getByCode(String code);
	
	/**
	 * 添加用户
	 * @param member
	 * @param request
	 * @return
	 */
	MemberVo addMember(MemberVo member, HttpServletRequest request);
	
	/**
	 * 获取用户
	 * @param orderMemberId
	 * @return
	 */
	MemberVo getMemberById(Long orderMemberId);
	
}
