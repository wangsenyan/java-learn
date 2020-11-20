package com.wsy.basis;

import java.util.*;

/**
 * 内部类：可访问其外围对象的所有成员,还拥有外围类的所有元素访问权
 * 构建内部类对象时,需要一个指向其外围类对象的引用,如果编译器访问不到这个引用就报错
 *    普通的内部类对象隐式地保存了一个引用,指向创建它的外围类对象
 * 在匿名类中不可能有命名构造器(根本没有名字),但通过实例初始化,达到改构造器效果
 * 普通内部类的字段和方法,只能放在类的外部层次上,不能有static数据和static字段
 * 嵌套类:
 * 要创建嵌套类的对象,并不需要其外围类的对象
 * 不能从嵌套类的对象中访问非静态的外围类对象
 * 方到接口中的任何类都自动地是public和static的
 *
 * 每个内部类都能独立地继承自一个（接口的）实现，所以无论外围类是否已经继承了某个(接口的)实现，对于内部类都没有影响。
 * 内部类多重继承
 */
public class InnerClassTest {

}

/**
 * 内部类
 */
interface Selector {
    boolean end();
    Object current();
    void next();
}

class Sequence {
    private Object[] items;
    private int next = 0;
    public Sequence(int size){items = new Object[size];}
    public void add(Object x){
        if(next < items.length)
            items[next++] = x;
    }
    private class SequenceSelector implements Selector {
        //static int name = "hh";
        private int i=0;
        public boolean end(){return i == items.length;}
        public Object current(){return items[i];}
        public void next(){if(i<items.length) i++;}
    }
    public Selector selector(){
        return new SequenceSelector();
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);
        for(int i=0;i<10;i++)
            sequence.add(Integer.toString(i));
        Selector selector = sequence.selector();
        while (!selector.end()){
            System.out.println(selector.current() + " ");
            selector.next();
        }
    }
}

class DotNew{
    public class Inner {}

    public static void main(String[] args) {
        DotNew dn = new DotNew();
        //在拥有外部类之前是不能创建内部类对象的
        DotNew.Inner dni = dn.new Inner();
        //Inner i = new Inner();//cannot be referenced from a static context
    }
//    public void dnew(){
//        Inner i = new Inner();//能成功的原因是,创建Inner对象时,已经有DotNew对象
//    }
}

/**
 * 对象方法可以使用常规定义,因为外部对象已经创建
 * 类方法不可以,因为不保证外部对象已经创建,必读先创建后.new
 */
class Parcel3 {
    class Contents {
        private int i =11;
        public int value(){return i;}
    }
    class Destination {
        private String label;
        Destination(String whereTo){ label = whereTo;}
        String readLabel(){return label;}

    }
    public static void main(String[] args) {
        Parcel3 p = new Parcel3();
        Parcel3.Contents c = p.new Contents();
        Parcel3.Destination d = p.new Destination("ShangHai");
    }
}

/**
 * 内部类pContent是private,所以除了Parcel4没人能访问它
 * 可以完全阻止任何依赖于类型的编码,并且完全隐藏了实现的细节
 */
interface Contents{
    int value();
}
interface Destination {
    String readLabel();
}
class Parcel4 {
    private class pContent implements Contents{
        private int i = 11;
        public int value(){return i;}
    }
    public Contents contents(){
        return new pContent();
    }
}
class TestParcel {
    public static void main(String[] args) {
        Parcel4 p = new Parcel4();
        Contents c = p.contents();//只能由类的公共方法提供内部私有类给外部
        //Parcel4.pContent pc = p.new pContent();
    }
}

/**
 * 定义在方法中的类,只能在方法内访问
 */
class Parcel5 {
    public Destination destination(String s){
        class pDestination implements Destination {
            private String label;
            private pDestination(String whereTo){
                label = whereTo;
            }
            public String readLabel() {
                return label;
            }
        }
        return new pDestination(s);
    }

    public static void main(String[] args) {
        Parcel5 p = new Parcel5();
        Destination d = p.destination("Beijing");
    }
}

/**
 * 与别的类一起编译过,但是在定义TrackingSlip作用域外,不可用
 */
class Parcel6 {
    private void internalTracking(boolean b){
        if(b){
            class TrackingSlip{
                private String id;
                TrackingSlip(String s){
                    id = s;
                }
                String getSlip(){return id;}
            }
            TrackingSlip ts = new TrackingSlip("slip");
            String s = ts.getSlip();
        }
    }
    public void track(){ internalTracking(true);}

    public static void main(String[] args) {
        Parcel6 p =  new Parcel6();
        p.track();
    }
}

/**
 *
 * 匿名类: 将类的生成与定义结合在一起
 */
class Parcel7 {
    public Contents contents(){
        //匿名实现类
        //创建一个继承自Contents的内名类的对象
        return new Contents() {
            private int i = 12;
            public int value() {
                return i;
            }
        };
    }
    public static void main(String[] args) {
        Parcel7 p = new Parcel7();
        Contents c = p.contents();
    }
}

/**
 * Parcel7的写法
 */
class Parcel7b {
    class MyContents implements Contents {
        private  int i = 12;
        public int value() {
            return i;
        }
    }
    public Contents contents(){
        return new MyContents();
    }
    public static void main(String[] args) {
        Parcel7 p = new Parcel7();
        Contents c = p.contents();
    }
}

/**
 * 有参匿名
 */
class Parcel8 {
    public Wrapping wrapping(int x){
        return new Wrapping(x){
            public int value(){
                return super.value() *47;
            }
        };//; 代表表达式的结束
    }

    public static void main(String[] args) {
        Parcel8 p = new Parcel8();
        Wrapping w = p.wrapping(10);
    }
}

class Wrapping {
    private int i;
    public Wrapping(int x){i=x;}
    public int value(){return i;}
}

/**
 * 匿名内部类,使用一个在其外部定义的对象,编译器要求其参数是final？？？ jdk14没要求
 */
class Parcel9 {
    public Destination destination(String dest){
        return new Destination() {
            private String label = dest;
            public String readLabel() {
                return label;
            }
        };
    }
    public static void main(String[] args) {
        Parcel9 p = new Parcel9();
        Destination d = p.destination("Zhejiang");
    }
}

/**
 * 匿名类 + 实例初始化 == 构造器
 */
class Parcel10 {
    public Destination destination(final String dest,final float price){
        return new Destination() {
            private int cost;
            {
                cost = Math.round(price);
                if(cost > 100)
                    System.out.println("over budget");
            }
            private String label = dest;
            public String readLabel() {
                return label;
            }
        };
    }

    public static void main(String[] args) {
        Parcel10 p = new Parcel10();
        Destination d = p.destination("HeBei",(float) 188.0);
    }
}

interface Service {
    void method1();
    void method2();
}
interface ServiceFactory {
    Service getService();
}
class Implementation1 implements Service {
    private Implementation1(){}
    public void method1() {
        System.out.println("Implementation1 method1");
    }
    public void method2() {
        System.out.println("Implementation1 method2");
    }
    public static ServiceFactory factory =
            new ServiceFactory() {
                public Service getService() {
                    return new Implementation1();
                }
            };
}
class Implementation2 implements Service {
    private Implementation2(){}
    public void method1() {
        System.out.println("Implementation2 method1");
    }
    public void method2() {
        System.out.println("Implementation2 method2");
    }
    public static ServiceFactory factory =
            new ServiceFactory() {
                public Service getService() {
                    return new Implementation2();
                }
            };
}
class Factories {
    public static void serviceConsumer(ServiceFactory factory){
        Service s = factory.getService();
        s.method1();
        s.method2();
    }

    public static void main(String[] args) {
        serviceConsumer(Implementation1.factory);
        serviceConsumer(Implementation2.factory);
    }
}

/**
 * 嵌套类
 */
class Parcel11 {
    private static class ParcelContents implements Contents {
        private int i = 13;
        public int value() {
            return i;
        }
    }

    protected static class ParcelDestination
            implements Destination {

        private String label;
        private ParcelDestination(String whereTo){
            label = whereTo;
        }
        public String readLabel() {
            return label;
        }
        public static void f(){}
        static int x =  10;
        static class AnotherLevel{
            public static void f(){}
            static int x = 10;
        }
    }
    public static Destination destination(String s ){
        return new ParcelDestination(s);
    }
    public static Contents contents(){
        return new ParcelContents();
    }

    public static void main(String[] args) {
        Contents c = contents();
        Destination d = destination("Shanxi");
    }
}

class NNA {
    private void f(){}
    class A{
        private void g(){}
        public class B{
            void h(){
                g();
                f();
            }
        }
    }
}
class MultiNestingAccess {
    public static void main(String[] args) {
        NNA nna = new NNA();
        NNA.A nnaa = nna.new A();
        NNA.A.B nnaab = nnaa.new B();
        nnaab.h();
    }
}

/**
 * 多重继承
 */
class D{}
abstract class G{}
class Z extends D {
    G makeG() {return new G() {};}
}

/**
 * 内部实现外部引用
 */
interface Incrementable {
    void increment();
}

class Callee1 implements Incrementable {
    private int i = 0;
    public void increment() {
        i++;
        System.out.println(i);
    }
}
class MyIncrement {
    public void increment(){
        System.out.println("Other operation");
    }
    static void f(MyIncrement mi){mi.increment();}//static
}
//要求Callee2能兼容 Incrementable,还能运行Callee2的increment()方法
//不破坏原来的结构
//如果class Callee2 extends MyIncrement implements Incrementable 呢
//class Callee2 extends MyIncrement implements Incrementable{
class Callee2 extends MyIncrement  {
    private int i = 0;
    public void increment(){
        super.increment();
        i++;
        System.out.println(i);
    }
    private class Closure implements Incrementable{
        public void increment(){
            //运行Callee2的increment()方法
            Callee2.this.increment();
        }
    }
    Incrementable getCallbackReference(){
        return new Closure();
    }
}
class Caller {
    private Incrementable callbackReference;
    Caller(Incrementable cbh){callbackReference = cbh ;}
    void go(){callbackReference.increment();}
}
class Callbacks {
    public static void main(String[] args) {
        Callee1 c1 = new Callee1();
        Callee2 c2 = new Callee2();
        MyIncrement.f(c2);
        Caller caller1 = new Caller(c1);
        //Caller caller2 = new Caller(c2);
        Caller caller2 = new Caller(c2.getCallbackReference());
        caller1.go();
        caller1.go();
        caller2.go();
        caller2.go();
    }
}

/**
 * start()能重复使用Event对象(重新启动计数)
 */
abstract class Event {
    private long eventTime;//私有变量保证安全性
    protected final long delayTime;
    protected Event(long delayTime) {
        this.delayTime = delayTime;
        start();
    }
    public void start(){
        eventTime = System.nanoTime() + delayTime;
    }
    public boolean ready(){
        return System.nanoTime() >= eventTime;
    }
    public abstract void action();
}
class Controller {
    private List<Event> eventList =  new ArrayList<Event>();
    public void addEvent(Event c){eventList.add(c);}
    public void run(){
        //在运行的时候,可能会添加进新的对象
        while (eventList.size()>0){
//            for(Event e: eventList)
            for(Event e: new ArrayList<Event>(eventList))
                if(e.ready()){
                    System.out.println(e);
                    e.action();
                    eventList.remove(e);
                }
        }
    }
}

class GreenHouseControls extends Controller {
    //不会继承父类的eventList
    private boolean light = false;
    public class LightOn extends Event {
        public LightOn(long delayTime){super(delayTime);}
        public void action() {
            light = true;
        }
        public String toString(){return "Light is on";}
    }
    public class LightOff extends Event {
        public LightOff(long delayTime){super(delayTime);}
        public void action() {
            light = false;
        }
        public String toString(){return "Light is off";}
    }
    public class Bell extends Event {
        protected Bell(long delayTime) {
            super(delayTime);
        }
        public void action() {
            addEvent(new Bell(delayTime));
        }
        public String toString() {
            return "Bing!";
        }
    }
    public class Restart extends Event {
        private Event[] eventList;
        //本身是Event,具有delayTime属性和action属性
        public Restart(long delayTime,Event[] eventList){
            super(delayTime);
            this.eventList = eventList;
            for(Event e: eventList)
                addEvent(e);//外部类的addEvent
        }
        public void action() {
            for(Event e: eventList){
                //将传入的事件重启并加入事件队列
                e.start();
                addEvent(e);
            }
            //将自己重启
            start();
            //将自己加入事件队列
            addEvent(this);
        }
        public String toString(){
            return "Restarting system";
        }
    }
}

class GreenHouseController {
    public static void main(String[] args) {
        GreenHouseControls gc =  new GreenHouseControls();
        gc.addEvent(gc.new Bell(900));
        Event[] events = {
                gc.new LightOn(200),
                gc.new LightOn(400)
        };
        gc.addEvent(gc.new Restart(2000,events));
        gc.run();
    }
}

/**
 * 内部类的构造器必须连接到指向其外围类对象的引用
 * 那个指向外围对象的"秘密的"引用必须被初始化,在导出类中不再存在可连接的默认对象
 * 内部类不能被覆盖
 */
class WithInner {

    class Inner{
        private String name;
        public Inner(String name){
           this.name = name;
        }
    };
}
class InheritInner extends WithInner.Inner {
    //继承内部类的构造器必须传递父类并初始化
    InheritInner(WithInner wi,String name){
        wi.super(name);
    }
//    InheritInner(){
//        enclosingClassReference.super();
//    }
    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner ii = new InheritInner(wi,"ni");
    }
}

/**
 * 这两个内部类是完全独立的两个实体,各自在自己的命名空间内
 */
class Egg {
    private Yolk y;
    protected class Yolk{
        public Yolk() {
            System.out.println("Egg.Yolk()");
        }
    }
    public Egg(){
        System.out.println("New Egg()");
        y = new Yolk();
    }
}
class BigEgg extends Egg {
    public class Yolk{
        public Yolk(){
            System.out.println("BigEgg.Yolk()");
        }
    }
    public static void main(String[] args) {
        new BigEgg();
    }
}

class Egg2 {
    protected class Yolk{
        public Yolk() {
            System.out.println("Egg2.Yolk()");
        }
        public void f(){
            System.out.println("Egg2.Yolk.f()");
        }
    }
    private Egg2.Yolk y = new Yolk();
    public Egg2(){
        System.out.println("New Egg2()");
    }
    public void insertYolk(Yolk yy){y=yy;}
    public void g(){y.f();}
}

class BigEgg2 extends Egg2{
    public class Yolk extends Egg2.Yolk{
        public Yolk() {
            System.out.println("BigEgg2.Yolk()");
        }
        public void f(){
            System.out.println("BigEgg2.Yolk.f()");
        }
    }
    public BigEgg2(){insertYolk(new Yolk());}

    public static void main(String[] args) {
        Egg2 e2 = new BigEgg2();
        e2.g();
    }
}

/**
 * 局部内部类不能有访问说明符,因为它不是外围类的一部分
 * 但是它可以访问当前代码块内的常量,以及此外围类的所有成员
 * 局部内部类可以使用已命名的构造器或者需要重载构造器,而匿名类只能用于实例初始化
 */
interface Counter{
    int next();
}
class LocalInnerClass {
  private int count = 0;
  Counter getCounter(final String name){
      class LocalCounter implements Counter {
          public LocalCounter(){
              System.out.println("LocalCounter");
          }
          public int next(){
              System.out.println(name);
              return count++;
          }
      }
      return new LocalCounter();
  }
  Counter getCounter2(final String name){
      return new Counter() {
          {
              System.out.println("Counter()");
          }
          public int next() {
              System.out.println(name);
              return count++;
          }
      };
  }

    public static void main(String[] args) {
        LocalInnerClass lic = new LocalInnerClass();
        Counter c1 = lic.getCounter("Local inner"),
                c2 = lic.getCounter2("Anonymous inner");
        for(int i =0;i<5;i++)
            System.out.println(c1.next());
        for(int i =0;i<5;i++)
            System.out.println(c2.next());
    }
}