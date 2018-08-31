package com.ysdevelop.lorchard.merchant.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ysdevelop.lorchard.merchant.entity.Member;

public interface MemberDao {

	List<Member> list(@Param(value = "queryMap") Map<String, String> queryMap, @Param(value = "nickname") byte[] nickname);

	Member getById(Integer id);

}
