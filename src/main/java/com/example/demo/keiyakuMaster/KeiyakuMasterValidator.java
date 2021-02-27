package com.example.demo.keiyakuMaster;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class KeiyakuMasterValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return KeiyakuMasterForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		var form = KeiyakuMasterForm.class.cast(target);

//		if(form.getName() != null) {
//			errors.rejectValue("name", "systemUser.validate.test");
//		}

	}


}
