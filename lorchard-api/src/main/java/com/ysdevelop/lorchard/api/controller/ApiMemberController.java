package com.ysdevelop.lorchard.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ysdevelop.lorchard.api.entity.MemberVo;
import com.ysdevelop.lorchard.api.key.SessionKey;
import com.ysdevelop.lorchard.api.service.ApiMemberService;
import com.ysdevelop.lorchard.common.redis.RedisService;
import com.ysdevelop.lorchard.common.result.CodeMsg;
import com.ysdevelop.lorchard.common.result.Result;
import com.ysdevelop.lorchard.common.utils.RandomTokenUtil;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:15:08 
 *
 * @Package com.ysdevelop.lorchard.api.controller
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
@RestController
@RequestMapping(value = "/member")
public class ApiMemberController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private ApiMemberService memberService;
	
	@Autowired
	private RedisService redisService;
	
	/**
	 * 验证用户token
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/checkToken", method = RequestMethod.GET)
	public Result<String> checkToken(String token) {
		if (token == null) {
			return Result.error(CodeMsg.SESSION_EXPRISE);
		}
		logger.info("wxapplet ----->" + token);
		boolean isExperise = redisService.get(SessionKey.sessionKey, token, String.class) == null ? true : false;
		if (isExperise) {
			return Result.error(CodeMsg.SESSION_EXPRISE);
		} else {
			return Result.success("token 未失效");
		}

	}
	
	/**
	 * 用户登录
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public Result<MemberVo> login(String code) {
		logger.info("code ----->" + code);
		MemberVo member = memberService.getByCode(code);
		// 用户未注册
		if (member.getId() == null) {
			return Result.successData(member);
		} else {
			String token = RandomTokenUtil.getToken();
			logger.info("member token ---->" + token);
			redisService.set(SessionKey.sessionKey, token, token);
			member.setToken(token);
			return Result.successData(member);
		}

	}
	
	/**
	 * 用户注册
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Result<MemberVo> register(HttpServletRequest request, MemberVo member) {
		MemberVo memberVo = memberService.addMember(member,request);
		return Result.successData(memberVo);
	}

}
