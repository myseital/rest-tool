package com.mao.design.design.pattern.behavioral.iterator;

/**
 * @author maojiawei
 */
public interface CourseAggregate {

    void addCourse(Course course);

    void removeCourse(Course course);

    CourseIterator getCourseIterator();


}
