package com.wsy.basis.aop.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
//https://www.jianshu.com/p/23d3f1a2b3c7
public class PersonProxy implements InvocationHandler {
    private Object delegate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public Object bind(Object delegate){
        this.delegate = delegate;
        return Proxy.newProxyInstance(delegate.getClass().getClassLoader(),delegate.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {
            logger.info("Before Proxy");
            result = method.invoke(delegate,args);
            logger.info("After Proxy");
        }catch (Exception e){
            throw e;
        }
        return result;
    }

    public static void main(String[] args) {
        PersonProxy personProxy = new PersonProxy();
        IPerson iPerson = (IPerson) personProxy.bind(new MyPerson());
        iPerson.doSomething();
    }
}


