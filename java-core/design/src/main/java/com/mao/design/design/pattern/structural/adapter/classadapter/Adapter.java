package com.mao.design.design.pattern.structural.adapter.classadapter;

/**
 * @author maojiawei
 */
public class Adapter extends Adaptee implements Target {

    @Override
    public void request() {
        //...
        super.adapteeRequest();
        //...
    }
}
