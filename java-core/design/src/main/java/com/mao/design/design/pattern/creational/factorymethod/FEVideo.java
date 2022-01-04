package com.mao.design.design.pattern.creational.factorymethod;

/**
 * @author maojiawei
 */
public class FEVideo extends Video{
    @Override
    public void produce() {
        System.out.println("录制FE课程视频");
    }
}
