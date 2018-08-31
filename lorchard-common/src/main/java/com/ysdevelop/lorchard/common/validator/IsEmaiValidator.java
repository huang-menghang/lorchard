package com.ysdevelop.lorchard.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.ysdevelop.lorchard.common.utils.RegexUtil;

public class IsEmaiValidator implements ConstraintValidator<IsEmail, String> {

	private boolean required = false;

	@Override
	public void initialize(IsEmail constraintAnnotation) {
		required = constraintAnnotation.required();

	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (email == null) {
			return true;
		}
		if (required) {
			return RegexUtil.checkEmail(email);
		} else {
			if (StringUtils.isEmpty(email)) {
				return true;
			} else {
				return RegexUtil.checkEmail(email);
			}
		}
	}

}
