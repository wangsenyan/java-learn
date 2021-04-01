package com.wsy.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.wsy.spring.config.PersonConfig;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
//    public static void main(String[] args) {
//        SpringApplication.run(MainApplication.class, args);
//        AnnotationConfigApplicationContext applicationContext
//               = new AnnotationConfigApplicationContext(PersonService.class);
//        PersonService bean = applicationContext.getBean(PersonService.class);
//        //Person person = applicationContext.getBean(Person.class);
//        //System.out.println(person);
//
//        //String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
////        for (String name: beanNamesForType) {
////            //System.out.println(name);
////        }
//    }
}
