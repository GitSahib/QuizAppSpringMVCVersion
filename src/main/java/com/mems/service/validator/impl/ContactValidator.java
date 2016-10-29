package com.mems.service.validator.impl;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mems.domain.Contact;

@Component
public class ContactValidator implements Validator {
   
    @Override
    public boolean supports(Class<?> aClass) {
        return Contact.class.equals(aClass);
    }
    
    @Override
    public void validate(Object o, Errors errors) {
    	
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "NotEmpty");
        
    }
}
