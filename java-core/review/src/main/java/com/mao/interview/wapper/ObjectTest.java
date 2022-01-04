package com.mao.interview.wapper;

/**
 * @author myseital
 * @date 2021/4/1 02:21
 */
public class ObjectTest {

    public static void main(String[] args) {
        // 251346
        //• 执行父类的静态成员；
        //• 执行子类的静态成员；
        //• 父类的实例成员和实例初始化；
        //• 执行父类构造方法；
        //• 子类的实例成员和实例初始化；
        //• 子类构造方法。
        Son son = new Son();

        // 00B
        // 在 Java 语言中，变量不能被重写
        AA myClass = new BB();
        System.out.print(myClass.x);
        System.out.print(myClass.y);
        myClass.m();

        A a = new B();
        B b = new B();
        C c = new C();
        D d = new D();
        // • 第一个 BA：因为 A 的 m() 方法，被子类 B 重写了，所以输出是：BA；
        a.m(a);
        // • 第二个 BA：因为 B 是 A 的子类，当调用父类 m() 方法时，发现 m() 方法被 B 类重写了，所以会调用 B 中的 m() 方法，输出就是：BA；
        a.m(b);
        // • 第三个 BA：因为 C 是 B 的子类，会直接调用 B 的 m() 方法，所以输出就是：BA；
        a.m(c);
        // • 第四个 AD：因为 D 是 A 的子类，所以会调用 A 的 m() 方法，所以输出就是：AD。
        a.m(d);
    }
}

class Parent {
    {
        System.out.print("1");
    }

    static {
        System.out.print("2");
    }

    public Parent() {
        System.out.print("3");
    }
}

class Son extends Parent {

    {
        System.out.print("4");
    }

    static {
        System.out.print("5");
    }

    public Son() {
        System.out.print("6");
    }
}

class AA {
    public int x = 0;
    public static int y = 0;

    public void m() {
        System.out.print("A");
    }
}

class BB extends AA {
    public int x = 1;
    public static int y = 2;

    @Override
    public void m() {
        System.out.print("B");
    }
}

class A {
    public void m(A a) {
        System.out.println("A1--" + a.toString());
        System.out.println("AA");
    }
    public void m(D d) {
        System.out.println("A2--" + d.toString());
        System.out.println("AD");
    }

    @Override
    public String toString() {
        return "A{}";
    }
}

class B extends A {

    @Override
    public void m(A a) {
        System.out.println("B1--" + a.toString());
        System.out.println("BA");
    }

    public void m(B b) {
        System.out.println("B2--" + b.toString());
        System.out.println("BD");
    }
    @Override
    public String toString() {
        return "B{}";
    }
}

class C extends B{
    @Override
    public String toString() {
        return "C{}";
    }
}

class D extends B{
    @Override
    public String toString() {
        return "D{}";
    }
}
