package com.wsy.spring.config;

import com.wsy.spring.bean.Person;
import com.wsy.spring.condition.LinuxCondition;
import com.wsy.spring.condition.WindowConditon;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
//扫描包是将bean添加到AnnotationConfigApplicationContext中
//value 指定要扫描的包,
//excludeFilters 排除, 下面按注解排除
//includeFilters  + useDefaultFilters=false 只包含
//@ComponentScan 或 @ComponentScans 可以多个
//@Filter
//  -  FilterType.ANNOTATION
//  -  FilterType.ASSIGNABLE_TYPE 给定的类型,会重复person与_person_
//  -  FilterType.ASPECTJ
//  -  FilterType.REGEX 正则表达式
//  -  FilterType.CUSTOM 自定义规范
//     - 实现TypeFilter PersonTypeFilter
//@ComponentScan(value = "com.wsy.spring",includeFilters = {
////        @Filter(type = FilterType.ANNOTATION,classes = {Controller.class}),
////        @Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {Person.class}),
//        @Filter(type = FilterType.CUSTOM,classes = {PersonTypeFilter.class})
//},useDefaultFilters = false)
public class PersonConfig {
//    @Scope(value = "prototype") //非单例,每次创建一个,默认单例
//    @Lazy //只对单例模式,懒汉加载
    @Bean("person") //注册一个bean,类型为返回值的类型,id默认使用方法名
    public Person person(){
        return new Person("person",20);
    }
    @Conditional(WindowConditon.class)
    @Bean("Bile Gate") //注册一个bean,类型为返回值的类型,id默认使用方法名
    public Person person1(){
        return new Person("Bile Gate",64);
    }
    //vm:  -Dos.name=linux
    @Conditional(LinuxCondition.class)
    @Bean("linus") //注册一个bean,类型为返回值的类型,id默认使用方法名
    public Person person2(){
        return new Person("linus",48);
    }
}
