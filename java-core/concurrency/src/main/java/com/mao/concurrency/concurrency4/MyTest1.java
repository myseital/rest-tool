package com.mao.concurrency.concurrency4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
    关于Lock与synchronized关键字在锁的处理上的重要差别

    1. 锁的获取方式：前者是通过程序代码的方式由开发者手工获取，后者是通过JVM来获取（无需开发者干预）
    2. 具体实现方式：前者是通过Java代码的方式来实现，后者是通过JVM底层来实现 （无需开发者关注）
    3. 锁的释放方式：前者务必通过unlock()方法在finally块中手工释放，后者是通过JVM来释放（无需开发者关注）
    4. 锁的具体类型：前者提供了多种，如公平锁、非公平锁，后者与前者均提供了可重入锁
 */

public class MyTest1 {

    // 可重入锁
    private Lock lock = new ReentrantLock();

    public void myMethod1() {
        try {
            lock.lock();
            System.out.println("myMethod1 invoked");
        } finally {
            lock.unlock();
        }
    }

    public void myMethod2() {
        try {
            lock.lock();
            System.out.println("myMethod2 invoked");
        } finally {
            lock.unlock();
        }

//        boolean result = false;
//
//        try {
//            result = lock.tryLock(800, TimeUnit.MILLISECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        if (result) {
//            System.out.println("get the lock");
//        } else {
//            System.out.println("can't get the lock");
//        }
    }

    public static void main(String[] args) {
        MyTest1 myTest1 = new MyTest1();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; ++i) {
                myTest1.myMethod1();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; ++i) {
                myTest1.myMethod2();

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
