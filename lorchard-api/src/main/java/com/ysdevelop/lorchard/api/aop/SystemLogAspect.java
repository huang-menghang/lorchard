package com.ysdevelop.lorchard.api.aop;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ysdevelop.lorchard.api.entity.OrderLogVo;
import com.ysdevelop.lorchard.api.entity.SystemAccessLogVo;
import com.ysdevelop.lorchard.api.service.ApiSystemAccessLogService;
import com.ysdevelop.lorchard.api.util.ApiConstant;
import com.ysdevelop.lorchard.common.annotation.SystemControllerLog;
import com.ysdevelop.lorchard.common.utils.HttpUtils;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:14:56 
 *
 * @Package com.ysdevelop.lorchard.api.aop
 *
 * @Description: TODO
 *
 * @version V1.0
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
		HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
		Map<String, String> queryMap = HttpUtils.getParameterMap(request);
		if(log.logType() != null){
			accessLog(log, queryMap);			
		}else if(log.orderType() != null){
			orderLog(log, queryMap);
		}
	}
	
	private void accessLog(SystemControllerLog log, Map<String, String> queryMap){
		String description = log.description();
		int index = log.logType().getIndex();
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
	
	private void orderLog(SystemControllerLog log, Map<String, String> queryMap){
		String description = log.description();
		int index = log.logType().getIndex();
		OrderLogVo orderLog = null;
		
	}
}
