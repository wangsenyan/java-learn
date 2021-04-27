package com.wsy.basis.aop.gclib.proxy;

/**
 * 1. 引入依赖
 * 		<dependency>
 * 			<groupId>cglib</groupId>
 * 			<artifactId>cglib</artifactId>
 * 		</dependency>
 * 2. 定义自己的类
 * 3. 自定义MethodInterceptor方法拦截器
 * 4. 获取代理类
 * 5. 通过 Enhancer 类的 create()创建代理类
 */
public class AliSmsService {
    public String send(String message){
        System.out.println("send message: " + message);
        return message;
    }
}
