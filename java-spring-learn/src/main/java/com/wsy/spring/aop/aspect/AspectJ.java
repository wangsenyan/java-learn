package com.wsy.spring.aop.aspect;

/**
 * 1. AspectJ不是Spring组成部分,而是独立AOP框架
 * 2. 实现方式
 *   - xml
 *   - 注解
 * 3. 使用
 *   - 引入依赖
 *   - 包扫描
 *   - 生成bean
 *   - 在增强类上面添加注解@Aspect
 *   - 在spring配置文件中开始生成代理对象
 *     - <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
 *   - 配置不同类型的通知
 * 4. 有多个增强类,配置增强类的优先级
 *   - 增强类@Order(数字) 数字越小,优先级越高
 * 5. 完全使用注解开发
 *    AopConfig
 */
public class AspectJ {

    public void test(){

    }
}
