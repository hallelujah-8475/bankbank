package com.example.demo.login;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		var form = LoginForm.class.cast(target);

//		if(form.getName() != null) {
//			errors.rejectValue("name", "systemUser.validate.test");
//		}

	}


}
