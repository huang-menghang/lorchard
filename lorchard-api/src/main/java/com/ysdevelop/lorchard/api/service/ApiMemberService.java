package com.ysdevelop.lorchard.api.service;

import javax.servlet.http.HttpServletRequest;

import com.ysdevelop.lorchard.api.entity.MemberVo;

/**
 * 
 * @author USER
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

	MemberVo getMemberById(Long orderMemberId);
	
}
