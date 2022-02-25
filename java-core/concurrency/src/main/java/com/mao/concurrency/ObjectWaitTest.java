package com.mao.concurrency;


public class ObjectWaitTest {

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();

        synchronized (object) {
            object.wait();
        }
    }
}
