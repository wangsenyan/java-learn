package com.wsy.spring;

import com.wsy.spring.bean.Person;
import com.wsy.spring.config.PersonConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class MainApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(PersonConfig.class);
        Person person = applicationContext.getBean(Person.class);
        //System.out.println(person);

        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        for (String name: beanNamesForType) {
            //System.out.println(name);
        }
    }
}
