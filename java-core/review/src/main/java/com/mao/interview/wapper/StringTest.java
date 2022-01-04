package com.mao.interview.wapper;

/**
 * @author myseital
 * @date 2021/4/1 00:43
 */
public class StringTest {

    public static void main(String[] args) {
        try {
            System.out.println("try");
            int k = 3 / 0;
        } catch (Exception e) {
            System.out.println("catch");
        } finally {
//            int k = 3 / 0;
            System.out.println("finally");
        }
        System.out.println("main");
    }
}
