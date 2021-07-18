package com.example.demo.systemUser;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SystemUserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SystemUserForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		var form = SystemUserForm.class.cast(target);

		if(StringUtils.isBlank(form.getLoginid())) {
			errors.rejectValue("loginid", "org.hibernate.validator.constraints.NotBlank.message");
		}

		if(StringUtils.isBlank(form.getPassword())) {
			errors.rejectValue("password", "org.hibernate.validator.constraints.NotBlank.message");
		}

		if(StringUtils.isBlank(form.getRole())) {
			errors.rejectValue("role", "org.hibernate.validator.constraints.NotBlank.message");
		}

	}
}
