package com.example.demo.shohinMaster;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ShohinMasterValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ShohinMasterForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		var form = ShohinMasterForm.class.cast(target);

		if(StringUtils.isBlank(form.getName())) {
			errors.rejectValue("name", "org.hibernate.validator.constraints.NotBlank.message");
		}
		
		if(form.getKinri() == 0 ) {
			errors.rejectValue("kinri", "org.hibernate.validator.constraints.NotBlank.message");
		}
	}
}
