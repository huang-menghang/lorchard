package com.ysdevelop.lorchard.api.aop;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ysdevelop.lorchard.api.entity.SystemAccessLogVo;
import com.ysdevelop.lorchard.api.service.ApiSystemAccessLogService;
import com.ysdevelop.lorchard.api.util.ApiConstant;
import com.ysdevelop.lorchard.common.annotation.SystemControllerLog;
import com.ysdevelop.lorchard.common.utils.HttpUtils;

/**
 * 
 * @author USER
 *
 */
@Aspect
@Component
public class SystemLogAspect {

	@Autowired
	ApiSystemAccessLogService accessLogService;

	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint
	 */
	@Before(value = "@annotation(log)")
	public void doBefore(JoinPoint joinPoint, SystemControllerLog log) {
		String description = log.description();
		
		int index = log.logType().getIndex();
		HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
		Map<String, String> queryMap = HttpUtils.getParameterMap(request);
		SystemAccessLogVo accessLogVo = null;
		switch (index) {
		case ApiConstant.DEFALULT_ZERO:
			accessLogVo = new SystemAccessLogVo();
			accessLogVo.setMemberId(new Long(queryMap.get("memberId")));
			accessLogVo.setMerchantId(new Long(queryMap.get("merchantId")));
			accessLogVo.setDescription(description);
			accessLogVo.setLogType(log.logType());
			break;
		case ApiConstant.DEFALULT_ONE:
			accessLogVo = new SystemAccessLogVo();
			accessLogVo.setMemberId(new Long(queryMap.get("memberId")));
			accessLogVo.setMerchantId(new Long(queryMap.get("merchantId")));
			accessLogVo.setGoodsId(new Long(queryMap.get("goodsId")));
			accessLogVo.setDescription(description);
			accessLogVo.setLogType(log.logType());
			break;
		default:
			break;
		}
		
		if(accessLogVo != null){
			accessLogService.addSystemAccessLog(accessLogVo);
		}
	}
}
