package com.wsy.spring.config;

import com.wsy.spring.aop.advisor.MyInterceptor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyInterceptorConfig {
    public static final String traceExecution = "execution(* com.wsy.spring.aop.advisor.*.*(..))";

    public DefaultPointcutAdvisor defaultPointcutAdvisor(){
        MyInterceptor myInterceptor = new MyInterceptor();
        //MyInterceptor1 myInterceptor1 = new MyInterceptor1();
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(traceExecution);

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(myInterceptor);
        //advisor.setAdvice(myInterceptor1);
        return advisor;
    }
}
