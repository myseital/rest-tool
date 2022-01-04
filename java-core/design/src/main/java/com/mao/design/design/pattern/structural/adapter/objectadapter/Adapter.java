package com.mao.design.design.pattern.structural.adapter.objectadapter;

/**
 * @author maojiawei
 */
public class Adapter implements Target {

    private Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        //...
        adaptee.adapteeRequest();
        //...
    }
}
