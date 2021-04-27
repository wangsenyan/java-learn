package com.wsy.basis.base;

public class Singleton {

}

/**
 * 饿汉模式
 */
class Singleton1{
    //利用静态变量存储唯一实例
    private static final Singleton1 instance = new Singleton1();
    //私有化构造函数
    private Singleton1(){
        if(instance!=null){
            throw new RuntimeException("实例已经存在,请通过getInstance()方法获取");
        }
    }
    //提供公开获取实例接口
    public static Singleton1 getInstance(){
        return instance;
    }
}

/**
 * 懒汉模式:线程不安全
 * 1. 不加锁: 线程不安全
 * 2. 加锁: 串行化,性能问题
 */
class Singleton2{
    private static Singleton2 instance;
    private Singleton2(){
        if(instance!=null){
            throw new RuntimeException("实例已经存在,请通过getInstance()方法获取");
        }
    }
    public synchronized static Singleton2 getInstance(){
        if(instance == null)
            instance = new Singleton2();
        return instance;
    }
}

/**
 * 双重检查锁模式
 * 1. 多线程空指针问题(指令重排原因) -> 添加volatile
 *    分配内存->对象初始化->内存地址复制给对象
 */
class Singleton3{
    private static volatile Singleton3 instance;
    private Singleton3(){
        if(instance!=null){
            throw new RuntimeException("实例已经存在,请通过getInstance()方法获取");
        }
    }
    public  static Singleton3 getInstance(){
        if(instance == null) {
            synchronized (Singleton3.class) {
                if(instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}

/**
 * 静态内部类
 * JVM加载外部类的过程中,不会加载内部类
 * 内部类的属性/方法被调用才会加载,初始化静态属性
 * static保证只被实例化一次
 */
class Singleton4{
    private Singleton4(){
    }
    private static class InstanceHolder{
        private InstanceHolder(){
            if(instance!=null){
                throw new RuntimeException("实例已经存在,请通过getInstance()方法获取");
            }
        }
        private final static Singleton4 instance = new Singleton4();
    }
    public static Singleton4 getInstance(){
        return InstanceHolder.instance;
    }
}

/**
 * 单例模式,
 * 1. 除枚举方式外,其他方法都会通过反射的方式破坏单例
 * 2. 序列化接口,通过反序列化破坏单例
 *    重写 readResolve()
 *    public Object readResolve() throws ObjectStreamException {
 *         return instance;
 *    }
 */
class Singleton5{
    private Singleton5(){
    }
    private enum Singleton{
        INSTANCE;
        private final Singleton5 instance;
        Singleton(){
            instance = new Singleton5();
        }
        private Singleton5 getInstance(){
            return instance;
        }
    }
    public static Singleton5 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }
}