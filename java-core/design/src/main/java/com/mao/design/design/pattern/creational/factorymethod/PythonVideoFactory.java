package com.mao.design.design.pattern.creational.factorymethod;

/**
 * @author maojiawei
 */
public class PythonVideoFactory extends VideoFactory {
    @Override
    public Video getVideo() {
        return new PythonVideo();
    }
}
