package com.mems.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MEMSCtxHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    	MEMSCtxHolder.applicationContext = applicationContext;
    }
}