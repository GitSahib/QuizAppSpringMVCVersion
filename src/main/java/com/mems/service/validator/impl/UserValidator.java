package com.mems.service.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mems.domain.User;

import com.mems.service.security.interfaces.UserService;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;
    public void setUserService(UserService userService)
    {
    	this.userService = userService;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }
    
    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "errors.required");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "errors.username.size.less.than.six");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "error.username.already.exists");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "errors.required");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "errors.password.size.less.than.eight");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "errors.passwords.doesnot.match");
        }
    }
}
