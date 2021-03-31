package com.qwy.blogs.aspect;

public class MyAspect {
    public void before(){
        System.out.println("开始事务");
    }
    public void after(){
        System.out.println("提交事务");
    }
}
