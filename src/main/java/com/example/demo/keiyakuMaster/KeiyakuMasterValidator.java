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

		if(form.getPrice() == 0) {
			
			errors.rejectValue("price", "org.hibernate.validator.constraints.NotBlank.message");
		}
		
		if(form.getKinri() == 0) {
			
			errors.rejectValue("kinri", "org.hibernate.validator.constraints.NotBlank.message");
		}
		
		if(form.getClientid() == 0) {
			
			errors.rejectValue("clientid", "org.hibernate.validator.constraints.NotBlank.message");
		}
		
		if(form.getKoinid() == 0) {
			
			errors.rejectValue("koinid", "org.hibernate.validator.constraints.NotBlank.message");
		}

	}


}
