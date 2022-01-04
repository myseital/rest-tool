package com.mao.interview.wapper;

import java.util.HashSet;
import java.util.Set;

/**
 * @author myseital
 * @date 2021/4/1 00:09
 */
public class IntegerTest {

    public static void main(String[] args) {
        zz();
        Integer.valueOf(10);
    }

    private static void zz() {
        Set<Short> set = new HashSet<>();
        for (short i = 0; i < 5; i++) {
            set.add(i);
            System.out.println(set.toString());
            int a =  i - 1;
            set.remove(a);
//            set.remove(i);
            System.out.println(set.toString());
        }
    }
}
