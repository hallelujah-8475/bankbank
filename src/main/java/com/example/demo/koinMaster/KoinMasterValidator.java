package com.example.demo.koinMaster;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class KoinMasterValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return KoinMasterForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		var form = KoinMasterForm.class.cast(target);

		if(StringUtils.isBlank(form.getKoinname())) {
			errors.rejectValue("koinname", "org.hibernate.validator.constraints.NotBlank.message");
		}

		if(form.getAge() == 0) {
			errors.rejectValue("age", "org.hibernate.validator.constraints.NotBlank.message");
		}

		if(form.getBusho() == 0) {
			errors.rejectValue("busho", "org.hibernate.validator.constraints.NotBlank.message");
		}

		if(form.getShitenid() == 0) {
			errors.rejectValue("shitenid", "org.hibernate.validator.constraints.NotBlank.message");
		}

	}
}
