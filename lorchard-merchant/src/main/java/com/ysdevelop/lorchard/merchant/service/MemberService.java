package com.ysdevelop.lorchard.merchant.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.merchant.entity.Member;

public interface MemberService {

	PageInfo<Member> list(Map<String, String> queryMap);

	Member getById(Integer id);
	
	
	
}
