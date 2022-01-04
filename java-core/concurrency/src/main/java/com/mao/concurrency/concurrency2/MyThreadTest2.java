package com.mao.concurrency.concurrency2;

public class MyThreadTest2 {

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        MyClass myClass2 = new MyClass();

        Thread t1 = new Thread1(myClass);
        Thread t2 = new Thread2(myClass2);

        t1.start();

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
    }
}

class MyClass {

    public synchronized void hello() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("hello");
    }

    public synchronized void world() {
        System.out.println("world");
    }
}

class Thread1 extends Thread {

    private MyClass myClass;

    public Thread1(MyClass myClass) {
        this.myClass = myClass;
    }

    @Override
    public void run() {
        myClass.hello();
    }
}

class Thread2 extends Thread {

    private MyClass myClass;

    public Thread2(MyClass myClass) {
        this.myClass = myClass;
    }

    @Override
    public void run() {
        myClass.world();
    }
}

