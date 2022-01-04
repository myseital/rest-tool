package com.mao.concurrency.concurrency7;
/*
    ThreadLocal

    本质上，ThreadLocal是通过空间来换取时间，从而实现每个线程当中都会有一个变量的副本，这样每个线程就都会操作该副本，从而完全
    规避了多线程的并发问题。

    Java中存在四种类型的引用：

    1. 强引用(strong)
    2. 软引用(soft)
    3. 弱引用(weak)
    4. 虚引用(phantom)

    public class Test{
        private static final ThreadLocal<String> tl = new ThreadLocal();
    }

    try {
        ...
        ...
        ...


    } finally {
        tl.remove();
    }

 */

public class MyTest3 {

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal();

        threadLocal.set("hello world");

        System.out.println(threadLocal.get());

        threadLocal.set("welcome");

        System.out.println(threadLocal.get());
    }
}
