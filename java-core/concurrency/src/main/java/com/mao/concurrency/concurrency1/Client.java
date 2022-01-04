package com.mao.concurrency.concurrency1;

public class Client {

    public static void main(String[] args) {
        MyObject myObject = new MyObject();

        Thread increaseThread = new IncreaseThread(myObject);
        Thread increaseThread2 = new IncreaseThread(myObject);
        Thread decreaseThread = new DecreaseThread(myObject);
        Thread decreaseThread2 = new DecreaseThread(myObject);

        increaseThread.start();
        increaseThread2.start();
        decreaseThread.start();
        decreaseThread2.start();
    }
}
