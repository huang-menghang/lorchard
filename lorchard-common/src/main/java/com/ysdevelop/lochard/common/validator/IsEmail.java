package com.ysdevelop.lochard.common.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import javax.validation.Payload;
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
	ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { IsEmaiValidator.class })
public @interface IsEmail {

	boolean required() default true;

	String message() default "邮箱格式错误";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
