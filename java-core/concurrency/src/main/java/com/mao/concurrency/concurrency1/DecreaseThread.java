package com.mao.concurrency.concurrency1;

public class DecreaseThread extends Thread {

    private MyObject myObject;

    public DecreaseThread(MyObject myObject) {
        this.myObject = myObject;
    }

    @Override
    public void run() {
        for (int i = 0; i < 30; ++i) {
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            myObject.decrease();
        }
    }
}
