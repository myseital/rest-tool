package com.mao.interview.juc.mock.mq;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Resource2 {
    //当前资源的数量
    private int num = 0;
    //当前资源的上限
    private int size = 10;
    private Lock lock = new ReentrantLock();//创建锁对象
    private Condition condition = lock.newCondition();//创建锁的条件，情况

    //消费资源
    public void remove() {
        //开启锁
        lock.lock();
        try {
            //如果num为0，没有资源了，需要等待
            while (num == 0) {
                //这里jdk源码里推荐用while，因为有可能出现虚假唤醒，所以要再次确认
                try {
                    System.out.println("消费者进入等待");
                    //线程等待，并释放锁
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //如果线程可以执行到这里，说明资源里有资源可以消费
            num--;
            System.out.println("消费者线程为:" + Thread.currentThread().getName() + "--资源数量:" + num);
            //唤醒其他等待的线程
            condition.signal();
        } finally {
            lock.unlock();//释放锁
        }

    }

    //生产资源
    public void put() {
        //开启锁
        lock.lock();
        try {
            //如果资源满了，就进入阻塞状态
            while (num == size) {
                //这里jdk源码里推荐用while，因为有可能出现虚假唤醒，所以要再次确认
                try {
                    System.out.println("生产者进入等待");
                    condition.await();//线程等待，并释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //如果线程执行到这里，说明资源未满，可以开始生产
            num++;
            System.out.println("生产者线程为:" + Thread.currentThread().getName() + "--资源数量:" + num);
            //唤醒其他等待的线程
            condition.signal();
        } finally {
            //释放锁
            lock.unlock();
        }
    }
}
