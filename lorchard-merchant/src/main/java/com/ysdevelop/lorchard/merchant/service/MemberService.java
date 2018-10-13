package com.ysdevelop.lorchard.merchant.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.merchant.entity.Member;
/**
 * @author oldHuang
 * 
 * @Package com.ysdevelop.loarchard.merchant.controller
 * 
 * */
public interface MemberService {
	
	/**
	 * 获取会员分页
	 * 
	 * @param queryMap 
	 * 
	 * @return PageInfo<Member> 会员信息
	 */
	PageInfo<Member> list(Map<String, String> queryMap);
	
	/**
	 * 获取会员信息
	 * 
	 * @param id 会员id
	 * 
	 * @return Member 会员信息
	 */
	Member getById(Integer id);
	
	
	
}
