package com.mao.design.design.principle.dependenceinversion;

/**
 * @author maojiawei
 */
public class PythonCourse implements ICourse {
    @Override
    public void studyCourse() {
        System.out.println("Geely在学习Python课程");
    }
}
