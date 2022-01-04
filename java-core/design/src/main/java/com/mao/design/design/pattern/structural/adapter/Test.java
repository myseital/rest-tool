package com.mao.design.design.pattern.structural.adapter;

/**
 * @author maojiawei
 */
public class Test {

    public static void main(String[] args) {
        DC5 dc5 = new PowerAdapter();
        dc5.outputDC5V();
    }
}
