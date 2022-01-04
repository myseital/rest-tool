package com.mao.design.design.pattern.creational.singleton;

/**
 * @author maojiawei
 */
public class ThreadLocalInstance {
    private static final ThreadLocal<ThreadLocalInstance> threadLocalInstanceThreadLocal
            = new ThreadLocal<ThreadLocalInstance>() {
        @Override
        protected ThreadLocalInstance initialValue() {
            return new ThreadLocalInstance();
        }
    };

    private ThreadLocalInstance() {

    }

    public static ThreadLocalInstance getInstance() {
        return threadLocalInstanceThreadLocal.get();
    }

}
