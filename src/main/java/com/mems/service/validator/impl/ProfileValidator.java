package com.mems.service.validator.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mems.domain.Profile;
import com.mems.domain.User;

import com.mems.service.security.interfaces.UserService;

@Component
public class ProfileValidator implements Validator {
	@Autowired
	UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Profile profile = (Profile) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "errors.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "errors.required");
		if(!isPhoneNumberCorrect(profile.getPhoneNumber()))
		{
			errors.rejectValue("phoneNumber", "errors.invalid.phone.number");
		}
		if (!profile.getUser().getPassword().isEmpty()) {
			if (profile.getUser().getOldPassword().length() < 7 || !userService.authenticate(profile.getUser().getOldPassword())) {
				errors.rejectValue("user.oldPassword", "errors.invalid.old.password");
			}
			if (profile.getUser().getPasswordConfirm() == null || !profile.getUser().getPasswordConfirm().equals(profile.getUser().getPassword())) {
				errors.rejectValue("user.passwordConfirm", "errors.passwords.doesnot.match");
			}
			if (profile.getUser().getPassword()==null || profile.getUser().getPassword().length() < 8 || profile.getUser().getPassword().length() > 32) {
				errors.rejectValue("user.password", "errors.password.size.less.than.eight");
			}
		}
	}

	/**
	 * @param pPhoneNumber
	 * @return true if the phone number is correct
	 */
	private boolean isPhoneNumberCorrect(String pPhoneNumber) {

		Pattern pattern = Pattern.compile("((\\+[1-9]{3,4}|0[1-9]{4}|00[1-9]{3})\\-?)?\\d{8,20}");
		Matcher matcher = pattern.matcher(pPhoneNumber);

		if (matcher.matches())
			return true;

		return false;
	}
}
