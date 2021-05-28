package com.wsy.spring.aop.jdk;

import com.wsy.spring.dao.UserDao;
import com.wsy.spring.dao.impl.UserDaoImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 利用FactoryBean
 */
public class JDKProxy {
    public static void main(String[] args) {
        Class[] interfaces = { UserDao.class };
        UserDaoImpl userDao = new UserDaoImpl();
        UserDao dao =(UserDao) Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces, new UserDaoProxy(userDao));
        int result = dao.add(1, 2);
        System.out.println("result:" + result);
    }
}
class UserDaoProxy implements InvocationHandler{
    //1. 把创建的是谁的代理对象,把谁传递过来
    private Object obj;
    public UserDaoProxy(Object obj){
        this.obj = obj;
    }
    //2. 在invoke中增强方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //2.1 方法之前
        System.out.println("方法之前执行......" + method.getName() + " :传递的参数是......" + Arrays.toString(args));
        //2.2 执行当前方法
        Object res = method.invoke(obj,args);
        //2.2 方法之后
        System.out.println("方法之后执行......" + obj);
        return res;
    }
}