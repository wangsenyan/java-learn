package com.wsy.spring.config;

import com.wsy.spring.bean.Person;
import org.springframework.context.annotation.Bean;

public class PersonConfig1 {
    @Bean("person") //注册一个bean,类型为返回值的类型,id默认使用方法名
    public Person person(){
        return new Person("lisi",20);
    }
}
