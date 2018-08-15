package com.ysdevelop.loarchard.merchant.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.loarchard.merchant.entity.Member;

public interface MemberService {

	PageInfo<Member> list(Map<String, String> queryMap);

	Member getById(Integer id);
	
}
