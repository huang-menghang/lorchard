package com.ysdevelop.lorchard.merchant.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.ysdevelop.lorchard.common.annotation.SystemControllerLog;

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

	@Around(value = "execution(* com.ysdevelop.lorchard.merchant.controller..*(..)) && @annotation(log)")
	public Object around(ProceedingJoinPoint joinPoint, SystemControllerLog log) {
		Object result = null;
		System.out.println(log.description());
		try {
			System.out.println("前置通知");
			result = joinPoint.proceed();
			System.out.println("后置通知");
		} catch (Throwable e) {
			System.out.println("异常通知");
		}
		System.out.println("返回通知");
		return result;
	}

}
