package com.wsy.spring.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WindowConditon implements Condition {
    /**
     * @param context 判断条件能使用的上下文(环境)
     * @param metadata 注释信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //1. 获取到IOC使用的beanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //2. 获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        //3. 获取环境变量
        Environment environment = context.getEnvironment();
        //4. 获取到bean定义的注册类,注册bean定义或移除bean定义
        BeanDefinitionRegistry registry = context.getRegistry();

        String osName = environment.getProperty("os.name");
        if(osName.contains("Windows"))
            return true;
        return false;
    }
}
