package com.wsy.spring;

import com.wsy.spring.bean.Person;
import com.wsy.spring.config.PersonConfig;
import com.wsy.spring.config.PersonConfig1;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

public class IOCTest {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(PersonConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name:beanDefinitionNames){
            System.out.println(name);
        }
    }
    @Test
    public void test02(){
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(PersonConfig1.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name:beanDefinitionNames){
            System.out.println(name);
        }
    }
    @Test
    public void test03(){
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(PersonConfig.class);
        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        for(String name:beanNamesForType){
            System.out.println(name);
        }
        Map<String, Person> beansOfType = applicationContext.getBeansOfType(Person.class);
        System.out.println(beansOfType);

        //获取环境变量的值
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String osName = environment.getProperty("os.name");
        System.out.println(osName);
    }
}
