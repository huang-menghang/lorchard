package com.ysdevelop.lorchard.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

import com.ysdevelop.lorchard.common.utils.Constant;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.lochard.common.annotation
 * 
 * @Description 系统控制器日志注解类
 * 
 * @Date 2018年8月28日
 * 
 * @Version
 * 
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLog {
	/**
	 * 描述
	 * 
	 * @return
	 * 
	 */
	String description() default "";

	/**
	 * 日志类型
	 */
	Constant.SystemLogType logType() default Constant.SystemLogType.SHOP;
	
	/**
	 * 订单日志
	 */
	Constant.OrderType orderType() default Constant.OrderType.NOTORDERTYPE;

}
