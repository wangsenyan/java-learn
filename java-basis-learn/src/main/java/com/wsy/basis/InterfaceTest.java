package com.wsy.basis;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

/**
 * 接口 interface
 * 1. 不能为抽象类创建任何实例
 * 2. 接口也能包含域,但是这些域隐式地是public、static、final的
 * 3. 策略设计模式: 抽象类 + 对抽象类的引用 + 后续对抽象类的实现
 * 4. 适配器模式: 伪多重继承机制
 * 5. 没有任何与接口相关的存储
 * 6. 只能继承(extends)一个类,实现(implements)多个接口,向上转型为每个接口
 * 7. 接口的嵌套
 *    - 嵌套在另一个接口中的接口自动就是public的,而不能声明为private
 */
public class InterfaceTest {

}
enum  Note {
   S_name,M_name,L_name
}
//abstract class Inst {
interface Inst{
    int i =0;//变量是static final,必须被初始化
    public abstract void  play(Note n);
    public String what();
    public abstract void adjust();
}
//class Win extends Inst{
class Win implements Inst{
    public void play(Note n) {
        System.out.println("wind.play()"+n);
    }
    public String what(){return "wind";}
    public void adjust() {
    }
}
//class Perc extends Inst{
class Perc implements Inst{
    public void play(Note n) {
        System.out.println("Perc.play()"+n);
    }
    public String what(){return "Percussion";}
    public void adjust() {
    }
}
//class Stringed extends Inst{
class Stringed implements Inst{
    public void play(Note n) {
        System.out.println("Stringed.play()"+n);
    }
    public String what(){return "Stringed";}
    public void adjust() {
    }
}
class Brass extends Win {
    public void play(Note n) {
        System.out.println("Brass.play()"+n);
    }
    public void adjust() {
        System.out.println("Brass.adjust()");
    }
}
class Woodwind extends Win{
    public void play(Note n) {
        System.out.println("Woodwind.play()"+n);
    }
    public String what(){return "Woodwind";}
}
class Music4 {
    static void tune(Inst i){
        i.play(Note.M_name);
    }
    static void tuneAll(Inst[] e){
        for(Inst i:e)
            tune(i);
    }

    public static void main(String[] args) {
        Inst[] orchestra = {
                new Win(),
                new Perc(),
                new Stringed(),
                new Brass(),
                new Woodwind()
        };
        tuneAll(orchestra);
    }
}

/**
 * 抽象类继承必须重写
 */
abstract class Abs{
    public String name = "Abs";
    public abstract void print();
    Abs(){
      print();
    }
}
class AbsTest extends Abs{
    String name = "AbsTest";
    public void print() {
        System.out.println(name);
    }
    AbsTest(){

        print();
    }

    public static void main(String[] args) {
        AbsTest absTest = new AbsTest();
    }
}

class Processor {
    public String name(){
        return getClass().getSimpleName();
    }
    Object process(Object input){return input;}
}
class Upcase extends Processor{
    String process(Object input){
        return ((String)input).toUpperCase();
    }
}
class Downcase extends Processor{
    String process(Object input){
        return ((String)input).toUpperCase();
    }
}
class Apply{
    public static void process(Processor p,Object  s){
        System.out.println("using Processor " + p.name());
        System.out.println(p.process(s));
    }
    public static String s = "Disagreement with beliefs is by definition incorrect";
    public static void main(String[] args) {
        process(new Upcase(),s);
        process(new Downcase(),s);
    }
}

/**
 * 用户必须实现接口类
 */
interface Processor1 {
    String name();
    Object process(Object input);
}
class Apply1 {
    public static void process(Processor1 p,Object s){
        System.out.println("Using Processor1 " + p.name());
        System.out.println(p.process(s));
    }

    public static void main(String[] args) {
        //Apply1 apply1 = new Apply1();
    }
}

/**
 * 接口继承
 * 1. 定义和实现存在？？？
 */
interface CanFight{
    void fight();
}
interface CanSwim{
    void swim();
}
interface CanFly{
    void fly();
}
class ActionCharacter{
    public void fight(){}
}
class Hero extends ActionCharacter
        implements CanFight, CanSwim,CanFly{
    public void swim() {
    }
    public void fly() {
    }
}
class Adventure{
    public static void t(CanFight x){x.fight();}
    public static void u(CanSwim x){x.swim();}
    public static void v(CanFly x){x.fly();}
    public static void w(ActionCharacter x){x.fight();}

    public static void main(String[] args) {
        Hero h = new Hero();
        t(h);
        u(h);
        v(h);
        w(h);
    }
}

/**
 * 只有返回类型不一样的方法,导致重载失败
 */
interface I1{void f();}
interface I2{int f(int i);}
interface I3{int f();}
class C {public int f(){return  1;}}
class C2 implements I1,I2{
    public void f() {
    }
    public int f(int i) {
        return 1;
    }
}
class C3 extends C implements I2{
    public int f(int i) { //overload
        return 1;
    }
}
class C4 extends C implements  I3{
    public int f() {
        return 1;
    }
}
//class C5 extends C implements I1{} //只有返回不一样的方法,导致重载失败

class RandomDoubles {
    private static Random rand = new Random(47);
    public double next() {return rand.nextDouble();}
    public static void main(String[] args) {
        RandomDoubles rd = new RandomDoubles();
        for(int i=0;i<7;i++)
            System.out.println(rd.next() + " ");
    }
}

class AdaptedRandomDoubles extends RandomDoubles
        implements Readable{
    private int count;
    public AdaptedRandomDoubles(int count){
        this.count = count;
    }
    public int read(CharBuffer cb) throws IOException {
       if(count-- ==0)
           return -1;
       String result = Double.toHexString(next()) + " ";
       cb.append(result);
       return result.length();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(new AdaptedRandomDoubles(7));
        while (s.hasNextDouble())
            System.out.println(s.nextDouble() + " ");
    }
}

/**
 * 嵌套类,严格按照类的访问权限来
 */
class A {
    interface B{
        void f();
    }
    public class BImp implements B{
        public void f(){}
    }
    public interface C{
        void f();
    }
    class CImp implements C{
        public void f() {}
    }
    private class CImp2 implements C{
        public void f() {}
    }
    private interface D{
        void f();
    }
    private class DImp implements D{
        public void f() {}
    }
    //因为D是private,所以A.DImp2 只能被自身使用
    public class DImp2 implements D{
        public void f() {}
    }
    public D getD(){ return new DImp2(); }
    private D dRef;
    public void receiveD(D d){
        dRef = d;
        dRef.f();
    }
}
interface E {
    interface G {
        void f();
    }
    public interface H {
        void f();
    }
    void g();
}
class NestingInterfaces {
    public class BImp implements A.B {
        public void f(){};
    }
    class CImp implements A.C {
        public void f(){};
    }
//    class DImp implements A.D {
//        public void f() {};
//    }
    class EImp implements E {
        public void g(){};
    }
    class EGImp implements E.G {
        public void f() {};
    }
    class EImp2 implements  E {
        public void g(){};
        class EG implements E.G {
            public void f(){};
        }
    }

    public static void main(String[] args) {
        A a = new A();
        //A.D ad = a.getD();
        //A.DImp2 di2  = (A.DImp2) a.getD();
        //a.getD().f(); //f()为private方法
        A a2 = new A();
        a2.receiveD(a.getD());//DImp2
    }
}

/**
 * 工厂模式
 */

interface Game { boolean move();}
interface GameFactory { Game getGame();}

class Checkers implements Game {
    private int moves = 0;
    private static final int MOVES= 3;
    public boolean move() {
        System.out.println("Chckers move "+moves);
        return ++moves!=MOVES;
    }
}
class CheckersFactory implements GameFactory {
    public Game getGame(){return new Checkers();}
}

class Chess implements Game {
    private int moves = 0;
    private static final int MOVES = 4;
    public boolean move(){
        System.out.println("Chess move "+ moves);
        return ++moves!=MOVES;
    }
}
class ChessFactory implements GameFactory {
    public Game getGame() {  return new Chess(); }
}

class Games {
    public static void playGames(GameFactory factory){
        Game s = factory.getGame();
        while (s.move())
            ;
    }

    public static void main(String[] args) {
        playGames(new CheckersFactory());
        playGames(new ChessFactory());
    }
}