package com.ysdevelop.lorchard.api.mapper;

import com.ysdevelop.lorchard.api.entity.MemberVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:17:39 
 *
 * @Package com.ysdevelop.lorchard.api.mapper
 *
 * @Description: TODO
 *
 * @version V1.0
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
	
	/**
	 * 通过id获取会员
	 * @param orderMemberId
	 * @return
	 */
	MemberVo getByMemberId(Long orderMemberId);

}

