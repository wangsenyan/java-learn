package com.wsy.basis.base;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 主要看Class类的方法
 * package sun.reflect;
 */
public class Reflect {
    public static void main(String[] args) throws Exception {
        Reflect reflect = new Reflect();
        reflect.reflect();
    }
    public void Four() throws ClassNotFoundException {
        //1.
        Class clazz0 = Reflect.class;
        //2.
        Class clazz1 = Class.forName("com.wsy.basis.base.Reflect");
        //3.
        Reflect ins = new Reflect();
        Class clazz2 = ins.getClass();
        //4. 不会初始化,静态块和静态对象不会得到执行
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        loader.loadClass("com.wsy.basis.base.Reflect");
    }

    /**
     * 通过全类名获取
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void reflect() throws ClassNotFoundException,
            InstantiationException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class<?> targetClass = Class.forName("com.wsy.basis.base.Target");
        Target target = (Target)targetClass.newInstance();

        //获取所有类中所有定义的方法
        Method[] methods = targetClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        //获取指定方法并调用
        Method publicM = targetClass.getDeclaredMethod("publicM", String.class);
        publicM.invoke(target,"JavaGuide");
        //获取指定参数并对参数进行修改
        Field field = targetClass.getDeclaredField("value");
        field.setAccessible(true);
        field.set(target,"JavaBase");

        //调用private方法
        Method privateM = targetClass.getDeclaredMethod("privateM");
        privateM.setAccessible(true);
        privateM.invoke(target);
    }
}
class Target{
    private String value;
    public Target(){
        value = "JavaGuide";
    }
    public void publicM(String s){
        System.out.println(s);
    }
    private void privateM(){
        System.out.println("value = " + value);
    }
}