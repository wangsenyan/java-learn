package com.wsy.basis.base;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Generics {
    public void generics() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(12);
//        list.add("a");
        Class<? extends List> clazz = list.getClass();
        Method add = clazz.getDeclaredMethod("add", Object.class);
        add.invoke(list,"k1");
        System.out.println(list);
    }
    //泛型类
    //泛型接口

    /**
     * 泛型方法
     * ? 不确定的java类型
     * T 表示具体的一个java类型
     * K V 分别代表java键值中的key value
     * E 代表element
     */
    public <T> void printArray(T[] inputArray){
        for (T t : inputArray) {
            System.out.printf("%s ",t);
        }
        System.out.println();
    }

    /**
     * 泛型擦除
     * 1. 使用限定类型或者Object.class
     * 2. 调用泛型方法
     *   -  不指定泛型,泛型变量的类型为该方法中的几种类型的同一父类的最小级,直到Object
     *   -  指定泛型,该方法的几种类型必须是泛型的实例的类型或者其子类
     * 3. 真正类型检查(编译时期)的是对象的引用 classErase4
     * 4. 桥方法,解决类型冲突和多态的冲突 -- 父类调用重写方法
     * 5. 虚拟机通过返回和参数类型确定一个方法
     * 6. 泛型类中的静态方法和静态变量不可以使用泛型类所声明的泛型类型参数
     */

    public void classErase(){
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("abc");
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        list2.add(123);
        System.out.println(list1.getClass() == list2.getClass());

    }
    public void classErase1() throws Exception {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(123);
        list.getClass().getMethod("add", Object.class).invoke(list, "abc");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    public <T extends Integer> void classErase2() throws Exception {
        ArrayList<T> list = new ArrayList<>();

        list.getClass().getMethod("add", Object.class).invoke(list, "abc");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    public <T> T  classErase3(T x,T y){
        return y;
    }
    public void  classErase4(){
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList list1 = new ArrayList<Integer>();
//        list.add("a");
        list1.add("a");
//        new ArrayList<Integer>().add("a");
    }
    public static void main(String[] args) throws Exception {
        Generics generics = new Generics();
//        generics.generics();
//        Integer[] arr = {1,2,4,5};
//        generics.printArray(arr);
//        generics.classErase2();
//        generics.<Integer>classErase3(1,2);
        DatePair datePair = new DatePair();
        datePair.setValue(new Date());
        //datePair.setValue(new Object());
    }

    static class Pair<T> {
        private T value;
        public T getValue(){
            return value;
        }
        public void setValue(T value){
            this.value = value;
        }
    }
    static class DatePair extends Pair<Date>{
        @Override
        public Date getValue() {
            return super.getValue();
        }

        @Override
        public void setValue(Date value) {
            super.setValue(value);
        }
    }
}
