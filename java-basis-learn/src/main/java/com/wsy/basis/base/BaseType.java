package com.wsy.basis.base;

/**
 * 装箱: 自动调用valueOf
 * 拆箱：自动调用intValue..
 * 包装器类型,不会自动类型转换
 * == 如果有表达式,自动拆箱
 *
 * Boolean: TRUE,FALSE
 * Character: CharacterCache { Character[127+1]}
 * Integer: IntegerCache{ Integer[-128,127]}
 */
public class BaseType {
    public void load(){
        Integer i = 10;
        int n = i;
    }

    /**
     * Integer.valueOf
     *  - [-128,127] 使用IntegerCache.cache中已经存在的引用,否则创建新的Integer对象
     */
    public void ieq(){
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;
        System.out.println(i1==i2);
        System.out.println(i3==i4);
    }
    public void ieq1(){
        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);

        System.out.println("i1=i2   " + (i1 == i2));
        System.out.println("i1=i2+i3   " + (i1 == i2 + i3));
        System.out.println("i1=i4   " + (i1 == i4));
        System.out.println("i4=i5   " + (i4 == i5));
        System.out.println("i4=i5+i6   " + (i4 == i5 + i6));
        System.out.println("40=i5+i6   " + (40 == i5 + i6));
    }
    public void deq(){
        Double i1 = 100.0;
        Double i2 = 100.0;
        Double i3 = 200.0;
        Double i4 = 200.0;
        System.out.println(i1==i2);
        System.out.println(i3==i4);
    }
    public void beq(){
        Boolean i1 = false;
        Boolean i2 = false;
        Boolean i3 = true;
        Boolean i4 = true;
        System.out.println(i1==i2);
        System.out.println(i3==i4);
    }
    public void leq(){
        Long i1 = 100L;
        Long i2 = 200L;
        System.out.println(i1 == i2);
    }
    public void ceq(){
        Character i1 = 'a';
        Character i2 = 'a';
        System.out.println(i1 == i2);
    }
    public static void main(String[] args) {
        BaseType T = new BaseType();
        //T.load();
        //T.ieq();
        //T.deq();
        //T.beq();
        //T.leq();
        //T.ceq();
        T.ieq1();
    }
}
