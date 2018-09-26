package com.ysdevelop.lorchard.merchant.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysdevelop.lorchard.common.annotation.SystemControllerLog;
import com.ysdevelop.lorchard.common.utils.Constant;
import com.ysdevelop.lorchard.shiro.entity.BaseAuth;
import com.ysdevelop.lorchard.shiro.token.TokenManager;

@Controller
@RequestMapping(value = "/index")
public class IndexController {

	@SystemControllerLog(description = "用户进入首页", logType = Constant.SystemLogType.SHOP)
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	public String index(HttpServletRequest request) {

		return "shop/index";
	}

	@RequestMapping(value = "/refreshToken", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String refreshToken() {
		BaseAuth auth = TokenManager.getToken();
		if(auth!= null){
		TokenManager.USER_REALM.clearAuthorizationInfo(auth.getLoginName());
		}
		return "缓存清空成功";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET,produces="text/html;charset=utf-8")
	public String logout(){
		TokenManager.logout();
		return "merchant/login";
	}

}
