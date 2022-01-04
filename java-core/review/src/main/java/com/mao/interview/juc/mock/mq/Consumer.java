package com.mao.interview.juc.mock.mq;

public class Consumer implements Runnable {
 
    private Resource resource;
 
    public Consumer(Resource resource) {
        this.resource = resource;
    }
 
    @Override
    public void run() {
        while (true){
            resource.remove();
        }
 
    }
}
