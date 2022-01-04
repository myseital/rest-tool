package com.mao.design.design.pattern.creational.abstractfactory;

/**
 * @author maojiawei
 */
public class JavaVideo extends Video {
    @Override
    public void produce() {
        System.out.println("录制Java课程视频");
    }
}
