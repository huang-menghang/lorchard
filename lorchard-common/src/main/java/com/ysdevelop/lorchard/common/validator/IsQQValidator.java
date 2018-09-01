package com.ysdevelop.lorchard.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.ysdevelop.lorchard.common.utils.RegexUtil;

public class IsQQValidator implements ConstraintValidator<IsQQ, String> {

	private boolean required = false;

	@Override
	public void initialize(IsQQ constraintAnnotation) {
		required = constraintAnnotation.required();

	}

	@Override
	public boolean isValid(String qq, ConstraintValidatorContext context) {
		if (qq == null) {
			return true;
		}
		if (required) {
			System.out.println(qq);
			return RegexUtil.checkQQ(qq);
		} else {
			if (StringUtils.isEmpty(qq)) {
				return true;
			} else {
				return RegexUtil.checkQQ(qq);
			}
		}
	}

}
