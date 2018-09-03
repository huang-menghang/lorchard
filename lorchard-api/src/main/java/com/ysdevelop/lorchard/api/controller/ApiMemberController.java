package com.ysdevelop.lorchard.api.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.api.entity.MemberVo;
import com.ysdevelop.lorchard.api.key.SessionKey;
import com.ysdevelop.lorchard.api.service.ApiMemberService;
import com.ysdevelop.lorchard.common.redis.RedisService;
import com.ysdevelop.lorchard.common.result.CodeMsg;
import com.ysdevelop.lorchard.common.result.Result;
import com.ysdevelop.lorchard.common.utils.HttpUtils;
import com.ysdevelop.lorchard.common.utils.RandomTokenUtil;

/**
 * 
 * @author USER
 *
 */
@Controller
@RequestMapping(value = "/member")
public class ApiMemberController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private ApiMemberService memberService;
	
	@Autowired
	private RedisService redisService;
	
	/**
	 * 用户列表
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String index() {
		return "member/index";
	}
	
	/**
	 * 验证用户token
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/checkToken", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
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
	@RequestMapping(value = "/login", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	@ResponseBody
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
	@RequestMapping(value = "/register", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<MemberVo> register(HttpServletRequest request, MemberVo member) {
		MemberVo memberVo = memberService.addMember(member,request);
		return Result.successData(memberVo);
	}

	/**
	 * 用户分页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/pagination", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<List<MemberVo>> pagination(HttpServletRequest request) {
		Map<String, String> queryMap = HttpUtils.getParameterMap(request);
		PageInfo<MemberVo> pageInfo = memberService.list(queryMap);
		return Result.successPaginationData(pageInfo.getList(), pageInfo.getTotal());
	}

	/**
	 * 跳转详情页
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String info() {
		return "member/info";
	}

	/**
	 * 用户详情 resutful-api
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<MemberVo> info(@PathVariable(value = "id") Integer id) {
		MemberVo member = memberService.getById(id);
		return Result.successData(member);
	}

}
