package com.example.demo.systemUser;

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

//		if(form.getName() != null) {
//			errors.rejectValue("name", "systemUser.validate.test");
//		}

	}


}
