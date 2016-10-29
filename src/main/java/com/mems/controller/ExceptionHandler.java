package com.mems.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.common.base.Throwables;
@EnableWebMvc
@Component
@ControllerAdvice
class ExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger("ErrorLog");
	/**
	 * Handle exceptions thrown by handlers.
	 */	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = AccessDeniedException.class)
	public ModelAndView accessDenied(Exception exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("error/accessDenied");
        Throwable rootCause = Throwables.getRootCause(exception);
        modelAndView.addObject("errorMessage", rootCause);
        LOGGER.error(rootCause.toString(), exception);
		return modelAndView;
	}
	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
	public ModelAndView exception(Exception exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("error/general");
        Throwable rootCause = Throwables.getRootCause(exception);
        modelAndView.addObject("errorMessage", rootCause);
        modelAndView.addObject("stackTrace",Throwables.getStackTraceAsString(exception));
        LOGGER.error(rootCause.toString(), exception);
		return modelAndView;
	}
}