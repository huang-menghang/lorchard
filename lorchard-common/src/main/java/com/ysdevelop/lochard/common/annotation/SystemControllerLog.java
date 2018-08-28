package com.ysdevelop.lochard.common.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

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
//作用于参数或方法上
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLog {
	String description() default "";
}
