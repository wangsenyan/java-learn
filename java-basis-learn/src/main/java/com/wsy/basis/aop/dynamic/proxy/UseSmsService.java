package com.wsy.basis.aop.dynamic.proxy;

import com.wsy.basis.aop.dynamic.proxy.impl.SmsServiceImpl;

import java.lang.reflect.Method;

public class UseSmsService {
    public static void main(String[] args) throws Exception {
        SmsService smsService = (SmsService)JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("java");
        smsService.ping("127.0.0.1");
        SmsServiceImpl smsService1 = new SmsServiceImpl();
        Method[] methods = smsService1.getClass().getDeclaredMethods();
        for (Method method : methods) {
            method.invoke(smsService1,"fuck");
        }
    }
}
