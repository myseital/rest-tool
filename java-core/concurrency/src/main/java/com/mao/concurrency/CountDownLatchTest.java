package com.mao.concurrency;

/*
    CountDownLatch
 */

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class CountDownLatchTest {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        IntStream.range(0, 3).forEach(i -> new Thread(() -> {
            try {
                Thread.sleep(2000);

                System.out.println("hello");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }).start());

        System.out.println("启动子线程完毕");

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程执行完毕");
    }
}



