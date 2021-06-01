package com.wsy.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.wsy.spring.aop"})
@EnableAspectJAutoProxy //开启aop
public class AopConfig {
}
