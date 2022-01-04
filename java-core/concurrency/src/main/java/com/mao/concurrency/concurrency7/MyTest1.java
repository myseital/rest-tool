package com.mao.concurrency.concurrency7;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class MyTest1 {

    public static void main(String[] args) {
        Callable<Integer> callable = () -> {
            System.out.println("pre execution");

            Thread.sleep(5000);

            int randomNumber = new Random().nextInt(500);

            System.out.println("post execution");

            return randomNumber;
        };

        FutureTask<Integer> futureTask = new FutureTask(callable);

        new Thread(futureTask).start();

        System.out.println("thread has started");

        try {
            Thread.sleep(2000);
            System.out.println(futureTask.get(1, TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
