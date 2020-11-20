package com.wsy.basis;

/**
 * 0. 初始化阶段
 *    - 前端编译时期  final static 变量分配
 *    - 链接准备阶段  类变量默认初始化(零值), final static 变量显式初始化
 *    - 链接解析阶段  符号引用转换为直接引用
 *    - 初始化阶段  类构造器<clinit>()过程
 *                类变量的赋值和静态代码块
 * 1. 是否使用继承->是否需要从新类进行向上转型
 * 2. final
 *    - 使引用恒定不变，一旦引用被初始化指向一个对象，
 *    就无法再把它指向另一个对象。然而对象本身可以被修改
 *    - 必须在域的定义处或每个构造器中用表达式对final进行赋值
 */
class Instrument {
    public void play(){
        System.out.println("Instrument");
    }
    static void tune(Instrument i){
        i.play();
    }
}
class Wind extends Instrument {
//    public void play(){
//        System.out.println("wind");
//    }
    public static void main(String[] args) {
        Wind flute = new Wind();
        Instrument.tune(flute);
    }
}

class Gizmo {
    public void spin(){}
}
class FinalArgs{
    void with(final Gizmo g){
        //g =new Gizmo(); //final修饰的不能使用
    }
    void without(Gizmo g){
        g = new Gizmo();
        g.spin();
    }
}

/**
 * 先静后常
 * 先父后子
 * 先初始后构造
 */
class Insect {
    private int i = 9;
    protected int j;
    Insect(){
        System.out.println("i = " + i + ", j= "+j); //5  9 0  i为什么是9   因为i是父类的i，在构造函数执行前初始化
        j=39;
    }
    private static int x1 = printInit("static insect.x1 initialized"); //1 47
    static int printInit(String s){
        System.out.println(s);
        return 47;
    }
}
class Beetle extends Insect {
    private int k = printInit("Beetle.k initialized"); //4  47
    public Beetle(){
        System.out.println("k = "+k); //6  47
        System.out.println("j = "+j); //7  39
    }

    private static int x2 = printInit("static Beetle.x2 initialized");//2 47

    public static void main(String[] args) {
        System.out.println("Beetle Constructor"); //3 这个为什么?执行main的时候,已经加载了类
        Beetle b = new Beetle();
        //Beetle d = new Beetle();
    }
}
public class GenerateTest {
    private final int age = 10;
    public static void main(String[] args) {

    }
}
