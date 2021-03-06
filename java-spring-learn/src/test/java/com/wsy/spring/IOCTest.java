package com.wsy.spring;

import com.alibaba.druid.pool.DruidDataSource;
import com.wsy.spring.autowire.Emp;
import com.wsy.spring.bean.*;
import com.wsy.spring.config.PersonConfig;
import com.wsy.spring.config.PersonConfig1;
import com.wsy.spring.config.SpringConfig;
import com.wsy.spring.controller.PersonController;
import com.wsy.spring.ioc.Employee;
import com.wsy.spring.ioc.Student;
import com.wsy.spring.ioc.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
    @Test
    public void test04(){
        //BeanFactory->ListableBeanFactory->ApplicationContext
        //BeanFactory懒加载对象
        ApplicationContext context = new ClassPathXmlApplicationContext("spring5/spring5.xml");
        User user = context.getBean("user", User.class);
        user.add();
    }
    @Test
    public void test05(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring5/spring5.xml");
        Book book = context.getBean("book", Book.class);
        Book book1 = context.getBean("book", Book.class);
        System.out.println(book == book1);
    }
    @Test
    public void test06(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring5/bean1.xml");
        UserService service = context.getBean("userService", UserService.class);
        service.execute();
    }
    @Test
    public void test07(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring5/bean2.xml");
        Employee service = context.getBean("employee", Employee.class);
        service.add();
    }
    @Test
    public void test08(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring5/bean3.xml");
        Employee service = context.getBean("employee", Employee.class);
        service.add();
    }
    @Test
    public void test09(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring5/bean4.xml");
        Student service = context.getBean("student", Student.class);
        service.test();
    }
    @Test
    public void test10(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring5/bean5.xml");
        Father service = context.getBean("father", Father.class);
        service.toString();
    }
    @Test
    public void test11(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring5/fbean1.xml");
        User service = context.getBean("factoryUser", User.class);
        System.out.println(service.toString());
    }
    @Test
    public void test12(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring5/bean6.xml");
        Order service = context.getBean("order", Order.class);
        System.out.println(service.toString());
        context.close();
    }

    @Test
    public void test13(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring5/bean7.xml");
        Emp emp = context.getBean("emp", Emp.class);
        System.out.println(emp.toString());
        context.close();
    }
    @Test
    public void test14(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring5/bean8.xml");
        DruidDataSource emp = context.getBean("dataSource", DruidDataSource.class);
        System.out.println(emp.toString());
        context.close();
    }
    @Test
    public void test15(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring5/bean9.xml");
        PersonController emp = context.getBean("personController", PersonController.class);
        emp.test();
        context.close();
    }
    @Test
    public void test16(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        PersonController emp = context.getBean("personController", PersonController.class);
        emp.test();
    }
}
