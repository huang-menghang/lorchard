package com.ysdevelop.lorchard.api.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.api.entity.MemberVo;
import com.ysdevelop.lorchard.api.mapper.ApiMemberDao;
import com.ysdevelop.lorchard.api.service.ApiMemberService;
import com.ysdevelop.lorchard.api.util.ApiMemberUtils;
import com.ysdevelop.lorchard.common.exception.WebServiceException;
import com.ysdevelop.lorchard.common.result.CodeMsg;
import com.ysdevelop.lorchard.common.utils.Constant;
import com.ysdevelop.lorchard.common.utils.HttpUtils;

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
	public PageInfo<MemberVo> list(Map<String, String> queryMap) {
		String pageSize = queryMap.get("limit");
		String pageNum = queryMap.get("page");
		if (pageSize == null || pageNum == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		
		Integer integerPageSize = Integer.parseInt(pageSize);
		Integer integerPageNum = Integer.parseInt(pageNum);
		String getStr = "nicknameStr";
		String nicknameStr = null;
		byte[] nickname = new byte[1024];
        if((nicknameStr=queryMap.get(getStr))!=null){
			nickname = nicknameStr.getBytes();
        }
		PageHelper.startPage(integerPageNum, integerPageSize, Boolean.TRUE);

		List<MemberVo> members = memberDao.list(queryMap,nickname);
		// 将二进制昵称转化为String类型
		PageInfo<MemberVo> pageInfo = new PageInfo<>(members);
		return pageInfo;
	}

	@Override
	public MemberVo getById(Integer id) {
		if(id == null){
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		return memberDao.getById(id);
	}

	@Override
	public MemberVo getByCode(String code) {
		if (code == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}

		// 获取openid
		String requestUrl = ApiMemberUtils.getWebAccess(code);
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
			memberDao.addMemeber(member);
			logger.info("memberId---->" + member.getId());
		} catch (Exception e) {
			throw new WebServiceException(CodeMsg.MEMBER_REGISTER_FAILDED);
		}
		return member;
	}

}
