package com.ysdevelop.lochard.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.ysdevelop.lochard.common.utils.RegexUtil;



public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

	private boolean required = false;

	@Override
	public void initialize(IsMobile constraintAnnotation) {
		required = constraintAnnotation.required();

	}

	@Override
	public boolean isValid(String mobile, ConstraintValidatorContext context) {
		if (mobile == null) {
			return true;
		}
		if (required) {
			System.out.println(mobile);
			return RegexUtil.checkMobile(mobile);
		} else {
			if (StringUtils.isEmpty(mobile)) {
				return true;
			} else {
				return RegexUtil.checkMobile(mobile);
			}
		}

	}

}
