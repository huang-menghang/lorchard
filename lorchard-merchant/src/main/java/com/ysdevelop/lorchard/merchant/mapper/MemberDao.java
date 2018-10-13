package com.ysdevelop.lorchard.merchant.mapper;
/**
 * @author oldHuang
 * 
 * @Package com.ysdevelop.loarchard.merchant.controller
 * 
 * @Description 会员
 * 
 * */
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ysdevelop.lorchard.merchant.entity.Member;

public interface MemberDao {
	
	/**
	 * 获取会员信息
	 * 
	 * @param queryMap 
	 * 
	 * @param nickname 会员昵称
	 * 
	 * @return 会员信息集合
	 */
	List<Member> list(@Param(value = "queryMap") Map<String, String> queryMap, @Param(value = "nickname") byte[] nickname);
	
	/**
	 * 获取单个会员信息
	 * 
	 * @param id 会员id
	 * 
	 * @return Member 会员信息
	 */
	Member getById(Integer id);

}
