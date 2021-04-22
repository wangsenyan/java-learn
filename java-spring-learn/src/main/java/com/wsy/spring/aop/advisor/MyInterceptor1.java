package com.wsy.spring.aop.advisor;

import com.wsy.spring.annotation.HiTrace;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

public class MyInterceptor1 implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        HiTrace annotation = getAnnotation(method);
        if(annotation == null) return invocation.proceed();

        System.out.println("method " + invocation.getMethod()
                + " is called on " + invocation.getThis()
                + " with args" + " " + invocation.getArguments());
        Object proceed = invocation.proceed();
        System.out.println("method " + invocation.getMethod()
                + " returns " + proceed);
        return proceed;
    }

    private HiTrace getAnnotation(Method method) {
        // 如果有多个annotation 似乎就不好用了 如放在controller上 由于已经有了@RequestMapping注解了 所以...
        if (method.isAnnotationPresent(HiTrace.class)) {
            return method.getAnnotation(HiTrace.class);
        }
        return null;
    }
}
