package com.qwy.blogs.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

final public class ApplicationContextConf {
    public static ApplicationContext getApplicationContext(String source){
        ApplicationContext context = new ClassPathXmlApplicationContext(source);
        return context;
    }
}
