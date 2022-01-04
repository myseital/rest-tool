package com.mao.design.design.principle.singleresponsibility;

/**
 * @author maojiawei
 */
public interface ICourse {
    String getCourseName();
    byte[] getCourseVideo();

    void studyCourse();
    void refundCourse();

}
