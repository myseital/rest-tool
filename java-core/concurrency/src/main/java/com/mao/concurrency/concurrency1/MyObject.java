package com.mao.concurrency.concurrency1;

public class MyObject {

    private int counter;

    public synchronized void increase() {
        while (counter != 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        counter++;

        System.out.println(counter);

        notify();
    }

    public synchronized void decrease() {
        while (counter == 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        counter--;

        System.out.println(counter);

        notify();
    }
}
