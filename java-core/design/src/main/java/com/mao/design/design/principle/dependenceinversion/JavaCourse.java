package com.mao.design.design.principle.dependenceinversion;

/**
 * @author maojiawei
 */
public class JavaCourse implements ICourse {

    @Override
    public void studyCourse() {
        System.out.println("Geely在学习Java课程");
    }
}
