package com.mao.interview.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockQueue<T> {
 
    private int size;
    private Object[] queue;
 
    private Lock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();
 
    private int index;
    private int removeIndex;
    private int currLen;
 
    public MyBlockQueue() {
        this(10);
    }
 
    public MyBlockQueue(int size) {
        this.index = 0;
        this.removeIndex = 0;
        this.currLen = 0;
        this.size = size;
        queue = new Object[size];
    }
 
    public void push(T element) throws InterruptedException {
        lock.lock();
        try {
            while (currLen == size) {
                System.out.println("队列满。。。");
                full.await();
            }
            queue[index] = element;
            if (++index == size) {
                index = 0;
            }
            currLen++;
            empty.signal();
        } finally {
            lock.unlock();
        }
    }
 
    public T pop() throws InterruptedException {
        lock.lock();
        try {
            while (currLen == 0) {
                System.out.println("队列空。。。");
                empty.await();
            }
            Object obj = queue[removeIndex];
            if (++removeIndex == size) {
                removeIndex = 0;
            }
            currLen--;
            full.signal();
            return (T) obj;
        } finally {
            lock.unlock();
        }
    }
 
    public static void main(String[] args) throws InterruptedException {
        MyBlockQueue<Integer> blockQueue = new MyBlockQueue<Integer>(3);
        blockQueue.push(1);
        System.out.println(blockQueue.pop());
        blockQueue.push(2);
        System.out.println(blockQueue.pop());
        blockQueue.push(3);
        System.out.println(blockQueue.pop());
 
        blockQueue.push(5);
        blockQueue.push(5);
        System.out.println(blockQueue.pop());
    }
 
}