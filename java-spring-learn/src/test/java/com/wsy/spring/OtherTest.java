package com.wsy.spring;

import com.wsy.spring.bean.User;
import com.wsy.spring.jdbc.service.UserService;
import com.wsy.spring.webflux.config.WebFluxConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

//@SpringJUnitConfig(locations = "classpath:jdbc.xml")
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration("classpath:jdbc.xml")
public class OtherTest {
    @Autowired
    private UserService userService;
    @Test
    public void test01(){
        GenericApplicationContext context = new GenericApplicationContext();
        context.refresh();
        context.registerBean(User.class,()->new User());
        User user = (User)context.getBean("com.wsy.spring.bean.User");
        System.out.println(user);
    }
    @Test
    public void test02(){
        userService.transfer();
    }
    @Test
    public void test03(){
        ApplicationContext context = new AnnotationConfigApplicationContext(WebFluxConfig.class);
        com.wsy.spring.webflux.service.UserService emp = context.getBean("userService", com.wsy.spring.webflux.service.impl.UserServiceImpl.class);
        System.out.println(emp.getUserById(1));
    }
}
