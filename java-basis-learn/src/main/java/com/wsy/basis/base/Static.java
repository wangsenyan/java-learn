package com.wsy.basis.base;

import static java.lang.Math.*;

/**
 * static关键字
 * 1. 静态代码块(clinit)->非静态代码块(init)->构造方法(init) 先后顺序
 * 2. static修饰的类只能是内部类
 *    1. 非static内部类
 *       - 隐含保存指向外部类的引用
 *    2. static内部类
 *       - 它的创建是不需要依赖外围类的创建。
 *       - 它不能使用任何外围类的非static成员变量和方法。
 * 3. 静态导包
 *    import static : 指定导入某个类中的指定静态资源,不需要类名
 *
 *    import static java.lang.Math.*;
 *    max(1,2)
 */

public class Static {
    static {
        max(1,3);
        j = 3;
        //System.out.println(j);//可以提前赋值但是不可访问
        System.out.println("静态代码块");
    }
    {
        System.out.println("非静态代码块");
    }
    private double i = Math.random();
    public Static(){
        System.out.println("b");
    }
    private static int j;
}
