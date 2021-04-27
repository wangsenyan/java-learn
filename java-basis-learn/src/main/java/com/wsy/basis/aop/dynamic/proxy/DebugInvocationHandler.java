package com.wsy.basis.aop.dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Method.invoke 每个method都有invoke方法
 */
public class DebugInvocationHandler implements InvocationHandler {
    private final Object target;
    public DebugInvocationHandler(Object target){
        this.target = target;
    }

    /**
     *
     * @param proxy 动态生成的代理类
     * @param method 与代理类对象调用的方法相对应
     * @param args 当前 method 方法的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("after method " + method.getName());
        return result;
    }
}
