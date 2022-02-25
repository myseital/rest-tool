package com.mao.concurrency.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 对于线程池来说，其提供了execute与submit两种方式来向线程池提交任务
 * 总体来说，submit方法是可以取代execute方法的，因为它既可以接收Callable任务，也可以接收Runnable任务。
 * <p>
 * 关于线程池的总体执行策略：
 * <p>
 * 1. 如果线程池中正在执行的线程数 < corePoolSize，那么线程池就会优先选择创建新的线程而非将提交的任务加到阻塞队列中。
 * 2. 如果线程池中正在执行的线程数 >= corePoolSize，那么线程池就会优先选择对提交的任务进行阻塞排队而非创建新的线程。
 * 3. 如果提交的任务无法加入到阻塞队列当中，那么线程池就会创建新的线程；如果创建的线程数超过了maximumPoolSize，那么拒绝策略就会起作用。
 * <p>
 * 关于线程池任务提交的总结：
 * <p>
 * 1. 两种提交方式：submit与execute
 * 2. submit有三种方式，无论哪种方式，最终都是将传递进来的任务转换为一个Callable对象进行处理
 * 3. 当Callable对象构造完毕后，最终都会调用Executor接口中声明的execute方法进行统一的处理
 * <p>
 * 对于线程池来说，存在两个状态需要维护：
 * <p>
 * 1. 线程池本身的状态：ctl的高3位来表示
 * 2. 线程池中所运行着的线程的数量：ctl的其余29位来表示
 * <p>
 * 线程池一共存在5种状态：
 * <p>
 * 1. RUNNING：线程池可以接收新的任务提交，并且还可以正常处理阻塞队列中的任务。
 * 2. SHUTDOWN：不再接收新的任务提交，不过线程池可以继续处理阻塞队列中的任务。
 * 3. STOP：不再接收新的任务，同时还会丢弃阻塞队列中的既有任务；此外，它还会中断正在处理中的任务。
 * 4. TIDYING：所有的任务都执行完毕后（同时也涵盖了阻塞队列中的任务），当前线程池中的活动的线程数量降为0，将会调用terminated方法。
 * 5. TERMINATED：线程池的终止状态， 当terminated方法执行完毕后，线程池将会处于该状态之下。
 * <p>
 * RUNNING -> SHUTDOWN：当调用了线程池的shutdown方法时，或者当finalize方法被隐式调用后（该方法内部会调用shutdown方法）
 * RUNNING， SHUTDOWN -> STOP：当调用了线程池的shutdownNow方法时
 * SHUTDOWN -> TIDYING：在线程池与阻塞队列均变为空时
 * STOP -> TIDYING：在线程池变为空时
 * TIDYING -> TERMINATED：在terminated方法被执行完毕时
 */
public class ExecutorsTest2 {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(3, 5, 0, TimeUnit.SECONDS,
                new LinkedBlockingQueue(3), new ThreadPoolExecutor.CallerRunsPolicy());


        IntStream.range(0, 9).forEach(i -> {
            executorService.submit(() -> {

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                IntStream.range(0, 1).forEach(j -> {
                    System.out.println(Thread.currentThread().getName());
                });
            });
        });

        executorService.shutdown();
    }
}
