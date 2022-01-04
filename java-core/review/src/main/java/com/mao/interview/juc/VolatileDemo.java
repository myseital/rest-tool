package com.mao.interview.juc;

/**
 * @author myseital
 * @date 2021/4/14 15:30
 */
public class VolatileDemo {

    private static final int THREADS_COUNT = 10;

    private static volatile int value = 0;

    private static void increase() {
        // 对value变量进行自增操作
        value++;
    }

    public static void main(String[] args) {
        // 创建10个线程
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    // 每个线程对value变量进行1000次自增操作
                    increase();
                }
            });
            threads[i].start();
        }
        // 主线程等待子线程运行结束
//        for (Thread thread : threads) {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        System.out.println("Thread.activeCount: " + Thread.activeCount());
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println("value的值：" + value);
    }


}
