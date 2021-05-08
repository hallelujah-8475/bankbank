package com.example.demo.shitenMaster;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ShitenMasterValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ShitenMasterForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		var form = ShitenMasterForm.class.cast(target);

		if(StringUtils.isBlank(form.getShitenname())) {
			errors.rejectValue("shitenname", "org.hibernate.validator.constraints.NotBlank.message");
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
