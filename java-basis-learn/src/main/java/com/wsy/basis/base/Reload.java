package com.wsy.basis.base;

/**
 * 重载:同样的方法,参数不同,不同处理
 *    - 必须是同一个类
 *    - 方法名一样
 *    - 参数类型不一样或参数数量不一样
 * 重写:(两同两小一大)
 *    - 方法名相同，参数类型相同
 *    - 子类返回的类型小于等于父类方法返回类型
 *    - 子类抛出异常小于等于父类方法抛出异常
 *    - 子类访问权限大于等于父类方法访问权限
 */
public class Reload {
    public void test(){
        int a  = 10,b = 10;
        System.out.println("ret=" + a + b);
        //System.out.println("ret=" + a - b);
        System.out.println("ret=" + a * b);
        System.out.println("ret=" + a / b);
    }

    public static void main(String[] args) {
        Reload reload = new Reload();
        reload.test();
    }
}
