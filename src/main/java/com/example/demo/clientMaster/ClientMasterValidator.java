package com.example.demo.clientMaster;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ClientMasterValidator implements Validator {
	   
	@Override
	public boolean supports(Class<?> clazz) {
		return ClientMasterForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		
		var form = ClientMasterForm.class.cast(target);

		if(StringUtils.isBlank(form.getName())) {
			errors.rejectValue("name", "org.hibernate.validator.constraints.NotBlank.message");
		}

		if(StringUtils.isBlank(form.getDaihyoname())) {
			errors.rejectValue("daihyoname", "org.hibernate.validator.constraints.NotBlank.message");
		}

		if(form.getDaihyoage() == 0) {
			errors.rejectValue("daihyoage", "org.hibernate.validator.constraints.NotBlank.message");
		}
		
		if(form.getYoshinlevel() == 0) {
			errors.rejectValue("yoshinlevel", "org.hibernate.validator.constraints.NotBlank.message");
		}
		
		if(form.getPostcode1() == 0 || form.getPostcode2() == 0) {
			errors.rejectValue("postcode1", "org.hibernate.validator.constraints.NotBlank.message");
		}

		if(StringUtils.isBlank(form.getPrefecture())) {
			errors.rejectValue("prefecture", "org.hibernate.validator.constraints.NotBlank.message");
		}
		
		if(StringUtils.isBlank(form.getAddress1())) {
			errors.rejectValue("address1", "org.hibernate.validator.constraints.NotBlank.message");
		}
		
		if(StringUtils.isBlank(form.getAddress2())) {
			errors.rejectValue("address2", "org.hibernate.validator.constraints.NotBlank.message");
		}
		
		if(form.getPhonenumber1() == 0 || form.getPhonenumber2() == 0) {
			errors.rejectValue("phonenumber1", "org.hibernate.validator.constraints.NotBlank.message");
		}
	}
}
