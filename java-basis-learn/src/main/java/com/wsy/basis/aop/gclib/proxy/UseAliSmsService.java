package com.wsy.basis.aop.gclib.proxy;

public class UseAliSmsService {
    public static void main(String[] args) {
        AliSmsService proxy = (AliSmsService)CglibProxyFactory.getProxy(AliSmsService.class);
        proxy.send("fuck");
    }
}
