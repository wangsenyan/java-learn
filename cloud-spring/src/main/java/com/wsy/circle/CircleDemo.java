package com.wsy.circle;

import com.wsy.circle.bean.A;
import com.wsy.circle.bean.B;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 一。循环依赖: scope = "prototype"
 * 1. 错误: BeanCurrentlyInCreationException
 * 2. 解决: DefaultSingletonBeanRegistry
 *   - 3级缓存解决循环依赖
 *    1. singletonObjects 单例池,存放已经经历了完整生命周期的Bean对象
 *    2. earlySingletonObjects 存放早期暴露出来的Bean对象,Bean的生命周期未结束
 *    3. singletonFactories 存放可以生成Bean的工厂
 *   - 只有单例的bean会通过三级缓存解决循环依赖的问题
 *   - 非单例的bean不会存放在三级缓存
 *   - 三级缓存中的对象可以填充属性
 *     依靠Bean的“中间态”这个概念,指已经实例化但还没有初始化的状态(即半成品)
 * 3. 实例化、初始化
 *   - 实例化: 申请内存
 *   - 初始化: 属性填充
 * 4. 关键方法
 *   - getSingleton
 *   - doCreateBean
 *   - populateBean
 *   - addSingleton
 *
 */
public class CircleDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        A a = context.getBean("a",A.class);
        B b = context.getBean("b",B.class);
    }
}
