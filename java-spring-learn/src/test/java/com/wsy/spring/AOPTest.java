package com.wsy.spring;

import com.wsy.spring.aop.aspect.Book;
import com.wsy.spring.aop.aspect.User;
import com.wsy.spring.config.AopConfig;
import com.wsy.spring.config.TxConfig;
import com.wsy.spring.jdbc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {
    @Test
    public void test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring5/bean10.xml");
        User user = context.getBean("user", User.class);
        user.add();
    }
    @Test
    public void test02(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring5/bean11.xml");
        Book book = context.getBean("book", Book.class);
        book.book();
    }
    @Test
    public void test03(){
        ApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        User emp = context.getBean("user", User.class);
        emp.add();
    }

    @Test
    public void test04(){
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc.xml");
        UserService emp = context.getBean("userService", UserService.class);
        emp.transfer();
    }

    @Test
    public void test05(){
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc.xml");
        UserService emp = context.getBean("userService", UserService.class);
        emp.transfer();
    }
    @Test
    public void test06(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);
        UserService emp = context.getBean("userService", UserService.class);
        emp.transfer();
    }
}
