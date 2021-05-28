### ioc
* 对象创建和对象调用交给spring管理
* 底层原理：xml解析,工厂模式,反射

* IOC思想基于IOC容器完成,IOC容器底层就是对象工厂
  - BeanFactory
  - ApplicationContext
  
* bean 管理  
  - Spring创建对象
  - Spring属性注入
* bean管理操作
  1. xml
    - 创建对象
      - bean
        - id 标识符
        - class
        - name 同id,但可加特殊符号
      - 默认使用无参构造
      
    - 注入属性
      - DI 依赖注入,IOC实现一种
        - 使用set方法
        - 构造函数
        - 使用p名称空间
          - xmlns:p = "http://www.springframework.org/schema/p"
          - 属性注入
            - 空值 value=“” 或 <null></null>
            - 特殊符号 转义 或 <![CDATA[<<中国>>]]> == <<中国>>
        - bean注入
          - 外部bean
          - 内部bean
          - 级联赋值
        - 集合 map
        - 集合注入部分提取出来
          - 引入名称空间 xmlns:util="http://www.springframework.org/schema/util"
          - 使用util标签完成list集合注入提取
      - 自动注入
        - .properties
        - context命名空间引入
        - value ="${prop}"
    
  2. 注解生成配置bean
    - 引入依赖 spring-aop
    - 包扫描 <context:component-scan base-package="com.wsy.spring.dao,com.wsy.spring.service,com.wsy.spring.controller"></context:component-scan>
    - use-default-filters="false" 使用自己定义的filters         <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/> 只扫描带Controller注解的类
    
  3. 注解属性注入
    - @Autowired 属性类型注入
    - @Qualifier 根据属性名称进行注入
    - @Resource 根据类型或名称注入
    - @Value 注入普通类型属性
  
* FactoryBean==Factory->Bean
* scope
  - singleton  单例,饿汉式
  - prototype  多例,懒汉式
  - request
* 声明周期 
  - singleton
  - property
  - request
  - session
  