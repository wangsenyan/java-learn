package com.wsy.basis;

import java.util.Random;

/**
 * 多态
 * 1. 方法调用绑定：
 *   - 定义: 将一个方法调用同一个方法主体关联起来别成为绑定
 *   - 前期绑定: 程序执行前(编译器和连接程序)进行绑定
 *   - 后期绑定: 运行时根据对象的类型进行绑定
 * 2. java中除了static和final方法(private属于final)之外,其他方法都是后期绑定
 * 3. 只有非private方法才能被重载
 */
public class PolymorphismTest {
}


class Shape {
    public void draw(){}
    public void erase(){}
}

class Circle extends Shape{
    public void draw(){
        System.out.println("Circle.draw()");
    }
    public void erase(){
        System.out.println("Circle.erase()");
    }
}

class Square extends Shape{
    public void draw(){
        System.out.println("Square.draw()");
    }
    public void erase(){
        System.out.println("Square.erase()");
    }
}

class Triangle extends Shape{
    public void draw(){
        System.out.println("Triangle.draw()");
    }
    public void erase(){
        System.out.println("Triangle.erase()");
    }
}

class RandomShapeGenerator {
    private Random rand = new Random(3) ;
    public Shape next(){
        int i = rand.nextInt() & 0x03;
        System.out.println(i);
        switch (i){
            default:
            case 0: return new Circle();
            case 1: return new Square();
            case 2: return new Triangle();
        }
    }
}

class Shapes{
    private static RandomShapeGenerator gen = new RandomShapeGenerator();

    public static void main(String[] args) {
        Shape[] s = new Shape[9];
        for(int i=0;i<s.length;i++)
            s[i] = gen.next();
        for(Shape shp:s)
            shp.draw();
    }
}

/**
 * 1. 域(就是变量)与静态方法没有多态
 * 2. 如果直接访问某个域,这个访问就将在编译期进行解析
 * 3. 构造器实际上是static方法,该static声明是隐式的
 *    导出类只能访问它自己的成员,不能访问基类中的成员
 *
 * 4. 继承与清理
 *
 */
class Super {
    public int field = 0;
    public int getField(){return field;}
}
class Sub extends Super {
    public int field = 1;
    public int getField(){return field;}
    public int getSuperField(){return super.field;}
}

class FieldAccess{
    public static void main(String[] args) {
        Super sup = new Sub();
        System.out.println("sup,field = " + sup.field +
                ". sup.getField = " + sup.getField());
        Sub sub = new Sub();
        System.out.println("sub.field = " + sub.field +
                ". sub.getField = "+ sub.getField() +
                " .sub.getSuperField = " + sub.getSuperField() );
    }
}

class Grain {
    public String toString(){return "Grain";}
}

class Wheat extends Grain {
    public String toString(){return "Wheat";}
}
class Mill {
    Grain process(){return  new Grain();}
}
class WheatMill extends Mill {
    Wheat process(){return new Wheat();}
}
class CovariantReturn{
    public static void main(String[] args) {
        Mill m = new Mill();
        Grain g = m.process();
        System.out.println(g);
        m = new WheatMill(); //Mill m = new WheatMill();
        g = m.process();     //Grain g =  new Wheat();
        System.out.println(g);
    }
}

/**
 * 向下转型:
 * - 运行期间对类型进行转型进行检查的操作称为 "运行时类型识别"(RTTI)
 * - 进入运行时期进行检查,出错返回 ClassCastException
 */
class Useful {
    public void f(){}
    public void g(){}
}
class MoreUseful extends Useful {
    public void f(){}
    public void g(){}
    public void u(){}
    public void v(){}
    public void w(){}
}
class RTTI {
    public static void main(String[] args) {
        Useful[] x = {
                new Useful(),
                new MoreUseful()
        };
        x[0].f();
        x[1].g();
        ((MoreUseful)x[1]).u();
        ((MoreUseful)x[0]).u();
    }
}