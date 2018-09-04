package com.ysdevelop.lorchard.api.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.api.entity.MemberVo;

/**
 * 
 * @author USER
 *
 */
public interface ApiMemberService {
	
	/**
	 * 获取会员集合
	 * @param queryMap
	 * @return
	 */
	PageInfo<MemberVo> list(Map<String, String> queryMap);
	
	/**
	 * 获取指定会员
	 * @param id
	 * @return
	 */
	MemberVo getById(Integer id);
	
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
	
}
