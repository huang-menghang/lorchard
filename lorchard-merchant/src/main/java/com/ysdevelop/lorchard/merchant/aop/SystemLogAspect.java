package com.ysdevelop.lorchard.merchant.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ysdevelop.lorchard.common.annotation.SystemControllerLog;
import com.ysdevelop.lorchard.common.utils.Constant;
import com.ysdevelop.lorchard.merchant.entity.Order;
import com.ysdevelop.lorchard.merchant.entity.OrderLog;
import com.ysdevelop.lorchard.merchant.service.OrderLogService;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.loarchard.merchant.aop
 * 
 * @Description Aop日志切面类,实现日志拦截
 * 
 * @Date 2018年8月31日
 * 
 * @Version
 * 
 */
@Component
@Aspect 
public class SystemLogAspect {

	@Autowired
	OrderLogService orderLogService;
	

	
	@Before(value = "@annotation(log)")
	public void doBefore(JoinPoint joinPoint, SystemControllerLog log) {
			if(log.orderType().getIndex()!=Constant.OrderType.NOTORDERTYPE.getIndex()) {
				
				Order order  = (Order)joinPoint.getArgs()[0];
				orderLog(log, order);
			}

	}
	
//	@Around(value = "execution(* com.ysdevelop.lorchard.merchant.controller..*(..)) && @annotation(log)")
//	public Object around(ProceedingJoinPoint joinPoint, SystemControllerLog log) {
//		Object result = null;
//		System.out.println(log.description());
//		try {
//			System.out.println("前置通知");
//			result = joinPoint.proceed();
//			System.out.println("后置通知");
//		} catch (Throwable e) {
//			System.out.println("异常通知");
//		}
//		System.out.println("返回通知");
//		return result;
//	}
	
	
	private void orderLog(SystemControllerLog log, Order order){
		OrderLog orderLog = new OrderLog();
		orderLog.setMemberId(order.getOrderMemberId());
		orderLog.setMerchantId(order.getOrderMerchantId());
		orderLog.setOrderNo(order.getOrderNo());
		orderLog.setOrderPendingBalance(order.getOrderPendingBalance());
		orderLog.setOrderType(log.orderType());
		orderLog.setDescription(log.description());
		orderLogService.addOrderLog(orderLog);
	}
}
