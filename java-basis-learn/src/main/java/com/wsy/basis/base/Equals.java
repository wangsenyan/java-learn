package com.wsy.basis.base;

import java.util.*;

/**
 * equals重写规则(特别注意父子之间的关系)
 * 1. 自反性 x.equals(x)
 * 2. 对称性 y.equals(x) <==> x.equals(y)
 * 3. 传递性 y.equals(x) && z.equals(y) ==> z.equals(x)
 * 4. 一致性 多次调用结果相同
 * 5. 非空x,x.equals(null)==false
 *
 * 建议
 * this == otherObject
 * otherObject == null => false
 * getClass
 * instanceof
 * 强制类型转换
 * super.equals(otherObject)
 *
 * hashcode
 * 1. 如果两个对象通过equals方法是相等的,那么hashCode方法必须返回相同的整数
 * 2.
 */
public class Equals {
    public static void main(String[] args) {
       Equals.hashcode();
    }
    public static void equals(){
        List<A> list = new ArrayList<A>();
        A a = new A();
        B b = new B();
        list.add(a);
        System.out.println("list.contains(a)->" + list.contains(a));
        System.out.println("list.contains(b)->" + list.contains(b));
        list.clear();
        list.add(b);
        System.out.println("list.contains(a)->" + list.contains(a));
        System.out.println("list.contains(b)->" + list.contains(b));
    }
    public static void hashcode(){
        Map<String,Value> map1 = new HashMap<String,Value>();
        String s1 = new String("key");
        String s2 = new String("key");
        Value value = new Value(2);
        map1.put(s1, value);
        System.out.println("s1.equals(s2):"+s1.equals(s2));
        System.out.println("map1.get(s1):"+map1.get(s1));
        System.out.println("map1.get(s2):"+map1.get(s2));


        Map<Key,Value> map2 = new HashMap<Key,Value>();
        Key k1 = new Key("A");
        Key k2 = new Key("A");
        map2.put(k1, value);
        System.out.println("k1.equals(k2):"+s1.equals(s2));
        System.out.println("map2.get(k1):"+map2.get(k1));
        System.out.println("map2.get(k2):"+map2.get(k2));
    }
    static class A {
        @Override
        public boolean equals(Object obj) {
            return obj instanceof A;
        }
    }
    static class B extends A{
        @Override
        public boolean equals(Object obj) {
            return obj instanceof B;
        }
    }
    static class Key{
        private String k;
        public Key(String key){
            this.k=key;
        }
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Key){
                Key key=(Key)obj;
                return k.equals(key.k);
            }
            return false;
        }
        @Override
        public int hashCode() {
            return k.hashCode();
        }
    }
    static class Value{
        private int v;
        public Value(int v){
            this.v=v;
        }
        @Override
        public String toString() {
            return "类Value的值－－>"+v;
        }
    }
}
