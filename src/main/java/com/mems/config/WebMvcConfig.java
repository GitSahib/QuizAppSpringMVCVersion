package com.mems.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.mems.aop.ExecuteTimeInterceptor;

@EnableWebMvc
@Configuration
@Import({ ThymeleafConfig.class })
class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/404.html").setViewName("404");
	}
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/static/img/**")          
          .addResourceLocations("/static/img/");
        registry.addResourceHandler("/static/**")
        .addResourceLocations("/static/");
        registry.addResourceHandler("/img/**")
        .addResourceLocations("/static/img/");
    }
	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());
		return validator;
	}
	@Autowired
	 @Bean
	    public ResourceBundleMessageSource messageSource() {
	        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	        messageSource.setBasename("messages");
	        return messageSource;
	    }
	@Bean
	public ExecuteTimeInterceptor executeTimeInterceptor() {
		ExecuteTimeInterceptor interceptor = new ExecuteTimeInterceptor();
		return interceptor;
	}
	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(new Locale("en"));
		resolver.setCookieName("lang");
		resolver.setCookieMaxAge(4800);
		return resolver;
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		registry.addInterceptor(interceptor);
		registry.addInterceptor(executeTimeInterceptor());
		
	}
}