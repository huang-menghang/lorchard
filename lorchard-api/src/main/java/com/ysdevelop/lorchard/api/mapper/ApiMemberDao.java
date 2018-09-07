package com.ysdevelop.lorchard.api.mapper;

import com.ysdevelop.lorchard.api.entity.MemberVo;

/**
 * 
 * @author USER
 *
 */
public interface ApiMemberDao {
	
	/**
	 * 通过openid 获取指定会员
	 * @param openid
	 * @return
	 */
	MemberVo getByOpenid(String openid);
	
	/**
	 * 添加用户
	 * @param member
	 * @return
	 */
	Integer addMemeber(MemberVo member);

	MemberVo getByMemberId(Long orderMemberId);

}
