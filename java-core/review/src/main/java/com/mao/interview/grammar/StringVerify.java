package com.mao.interview.grammar;

/**
 * @author maojiawei
 * @date 2019/1/14
 */
public class StringVerify {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        // "hello"+2在编译期间就已经被优化成"hello2"，因此在运行期间，变量a和变量b指向的是同一个对象
        String a = "hello2";
        String b = "hello" + 2;
        System.out.println((a == b));

        String c = "hello";
        String d = c + 2;
        System.out.println((c == d));

        // final变量的访问在编译期间都会直接被替代为真实的值
        final String e = "hello";
        String f = e + 2;
        System.out.println((e == f));
    }
}
