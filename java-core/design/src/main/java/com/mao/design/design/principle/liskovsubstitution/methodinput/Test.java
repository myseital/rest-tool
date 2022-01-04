package com.mao.design.design.principle.liskovsubstitution.methodinput;

import java.util.HashMap;

/**
 * @author maojiawei
 */
public class Test {
    public static void main(String[] args) {
        Base child = new Child();
        HashMap hashMap = new HashMap();
        child.method(hashMap);
    }
}
