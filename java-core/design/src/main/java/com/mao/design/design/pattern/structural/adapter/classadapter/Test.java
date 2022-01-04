package com.mao.design.design.pattern.structural.adapter.classadapter;

/**
 * @author maojiawei
 */
public class Test {
    public static void main(String[] args) {
        Target target = new ConcreteTarget();
        target.request();

        Target adapterTarget = new Adapter();
        adapterTarget.request();
    }
}
