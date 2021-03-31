package com.qwy.blogs.factory;

import com.qwy.blogs.Interface.IStudent;
import com.qwy.blogs.aspect.MyAspect;
import com.qwy.blogs.service.StudentService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class StudentFactory {
    public static StudentService createStudentService(){
        //目标类
        final IStudent studentService = new StudentService();
        System.out.println(studentService);
        //切面类
        final MyAspect aspect = new MyAspect();
        //代理类
        /**
         * new ProxyInstance(ClassLoader, Class<?>[] interfaces, InvocationHandler h)
         * 参数1：类加载器，一般写当前类
         * 参数2：代理类所需实现的接口
         * 参数3：处理类，一般写匿名类
         */
        StudentService proxyService = (StudentService) Proxy.newProxyInstance(StudentFactory.class.getClassLoader(),
                studentService.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        return null;
                        //放行前
                        aspect.before();
                        //放行
                        Object object = method.invoke(studentService, args);
                        //放行后
                        aspect.after();
                        return object;
                    }
                });
        return proxyService;
    }
}
