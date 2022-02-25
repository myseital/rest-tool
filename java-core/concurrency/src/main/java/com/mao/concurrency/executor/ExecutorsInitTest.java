package com.mao.concurrency.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/*
      int corePoolSize：线程池当中所一直维护的线程数量，如果线程池处于任务空闲期间，那么该线程也并不会被回收掉
      int maximumPoolSize：线程池中所维护的线程数的最大数量
      long keepAliveTime：超过了corePoolSize的线程在经过keepAliveTime时间后如果一直处于空闲状态，那么超过的这部分线程将会被回收掉
      TimeUnit unit：指的是keepAliveTime的时间单位
      BlockingQueue<Runnable> workQueue：向线程池所提交的任务位于的阻塞队列，它的实现有多种方式
      ThreadFactory threadFactory：线程工厂，用于创建新的线程并被线程池所管理，默认线程工厂所创建的线程都是用户线程且优先级为正常优先级
      RejectedExecutionHandler handler：表示当线程池中的线程都在忙于执行任务且阻塞队列也已经满了的情况下，新到来的任务该如何被对待和处理。
      它有四种实现策略：

      AbortPolicy: 直接抛出一个运行期异常。
      DiscardPolicy：默默地丢弃掉提交的任务，什么都不做且不抛出任何异常
      DiscardOldestPolicy：丢弃掉阻塞队列中存放时间最久的任务（队头元素），并且为当前所提交的任务留出一个队列中的空闲空间，以便将其放进到队列中
      CallerRunsPolicy：直接由提交任务的线程来运行这个提交的任务

      在线程池中，最好将偏向锁的标记关闭。
 */


public class ExecutorsInitTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        IntStream.range(0, 50).forEach(i -> {
            executorService.submit(() -> {
                IntStream.range(0, 50).forEach(j -> {
                    System.out.println(Thread.currentThread().getName());
                });
            });
        });

        executorService.shutdown();
    }
}
