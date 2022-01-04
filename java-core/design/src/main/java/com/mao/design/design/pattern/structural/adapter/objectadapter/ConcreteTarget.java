package com.mao.design.design.pattern.structural.adapter.objectadapter;


/**
 * @author maojiawei
 */
public class ConcreteTarget implements Target {

    @Override
    public void request() {
        System.out.println("concreteTarget目标方法");
    }

}
