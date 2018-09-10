package com.ysdevelop.lorchard.api.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ysdevelop.lorchard.api.entity.MemberVo;
import com.ysdevelop.lorchard.api.mapper.ApiMemberDao;
import com.ysdevelop.lorchard.api.service.ApiMemberService;
import com.ysdevelop.lorchard.common.exception.WebServiceException;
import com.ysdevelop.lorchard.common.result.CodeMsg;
import com.ysdevelop.lorchard.common.utils.Constant;
import com.ysdevelop.lorchard.common.utils.HttpUtils;
import com.ysdevelop.lorchard.common.utils.WechantAppletApiUtil;

/**
 * 
 * @author USER
 *
 */
@Service
public class ApiMemberServiceImpl implements ApiMemberService {

	@Autowired
	private ApiMemberDao memberDao;
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public MemberVo getByCode(String code) {
		if (code == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}

		// 获取openid
		String requestUrl = WechantAppletApiUtil.getWebAccess(code);
		System.out.println(requestUrl);
		JSONObject jsonObject = HttpUtils.httpsRequest(requestUrl, Constant.HttpMethod.GET.getValue(), null);
		logger.info("jsonObject--->"+jsonObject);
		if (jsonObject == null) {
			// 服务端异常或则根本找不到该用户
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		System.out.println(jsonObject.toString());
		String sessionKey = null;
		String openid = null;
		try {
			sessionKey = jsonObject.getString("session_key");
			openid = jsonObject.getString("openid");

		} catch (Exception e) {
			logger.error("获取用户信息异常");
			e.printStackTrace();
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		if (sessionKey == null || openid == null) {
			logger.error("用户数据");
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		logger.info("openid ---->" + openid);
		MemberVo member = memberDao.getByOpenid(openid);
		if (member == null) {
			member = new MemberVo();
			member.setOpenid(openid);
		}
		return member;
	}

	@Override
	public MemberVo addMember(MemberVo member, HttpServletRequest request) {
		try {
			member.setNickname(member.getNicknameStr().getBytes());
			member.setRegisterIp(HttpUtils.getIp(request));
			System.out.println("member--->"+member);
			memberDao.addMemeber(member);
			logger.info("memberId---->" + member.getId());
		} catch (Exception e) {
			throw new WebServiceException(CodeMsg.MEMBER_REGISTER_FAILDED);
		}
		return member;
	}

	@Override
	public MemberVo getMemberById(Long orderMemberId) {
		MemberVo member = memberDao.getByMemberId(orderMemberId);
		return member;
	}

}
