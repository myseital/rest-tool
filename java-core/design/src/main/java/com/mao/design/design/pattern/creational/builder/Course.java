package com.mao.design.design.pattern.creational.builder;

import lombok.Data;

/**
 * @author maojiawei
 */
@Data
public class Course {
    private String courseName;
    private String coursePPT;
    private String courseVideo;
    private String courseArticle;

    /**
     * question & answer
     */
    private String courseQA;
}
