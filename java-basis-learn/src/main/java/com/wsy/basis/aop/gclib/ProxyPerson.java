//package com.wsy.basis.aop.gclib;
//
//import com.wsy.basis.interview.Person;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cglib.proxy.Enhancer;
//import org.springframework.cglib.proxy.MethodInterceptor;
//import org.springframework.cglib.proxy.MethodProxy;
//
//import java.lang.reflect.Method;
//
//public class ProxyPerson implements MethodInterceptor {
//    private Object delegate;
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    public Object intercept(Object proxy, Method method, Object[] args,  MethodProxy methodProxy) throws Throwable {
//        logger.info("Before Proxy");
//        Object result = methodProxy.invokeSuper(method, args);
//        logger.info("After Proxy");
//        return result;
//    }
//    public static Person getProxyInstance() {
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(Person.class);
//
//        enhancer.setCallback(new ProxyPerson());
//        return (Person) enhancer.create();
//    }
//}