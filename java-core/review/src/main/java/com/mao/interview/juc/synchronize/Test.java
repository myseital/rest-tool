package com.mao.interview.juc.synchronize;

/**
 * zz2 zz
 * zz2 阻塞
 * @author myseital
 * @date 2022/3/5 11:49
 */
public class Test {
    Object lock = new Object();

    public void zz() throws InterruptedException {
        synchronized (lock) {
            lock.wait();
            System.out.println("zz");
        }
    }

    public void zz2() throws InterruptedException {
        synchronized (lock) {
            lock.notifyAll();
            System.out.println("zz2");
        }
    }

    public static void main(String[] args) {
        Test a = new Test();
        Thread t1 = new Thread(() -> {
            try {
                a.zz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                a.zz2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();
        t1.start();
    }
}
