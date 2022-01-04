package com.mao.design.design.pattern.creational.factorymethod;

/**
 * @author maojiawei
 */
public class JavaVideoFactory extends VideoFactory {
    @Override
    public Video getVideo() {
        return new JavaVideo();
    }
}
