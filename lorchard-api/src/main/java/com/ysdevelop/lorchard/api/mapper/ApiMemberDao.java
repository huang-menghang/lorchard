package com.ysdevelop.lorchard.api.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ysdevelop.lorchard.api.entity.MemberVo;

/**
 * 
 * @author USER
 *
 */
public interface ApiMemberDao {
	/**
	 * 获取会员列表
	 * @param queryMap
	 * @param nickname
	 * @return
	 */
	List<MemberVo> list(@Param(value = "queryMap") Map<String, String> queryMap, @Param(value = "nickname") byte[] nickname);
	
	/**
	 * 获取指定会员
	 * @param id
	 * @return
	 */
	MemberVo getById(Integer id);
	
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

}
