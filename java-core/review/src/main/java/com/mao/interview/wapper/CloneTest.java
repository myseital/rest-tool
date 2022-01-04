package com.mao.interview.wapper;

import java.util.Arrays;

/**
 * @author myseital
 * @date 2021/4/1 17:25
 */
public class CloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        int[] a = {3, 1, 4, 2, 5};
        int[] b = a.clone();
        b[0] = 10;
        System.out.println(b[0] + "  " + a[0]);


        CloneObj cloneObj = new CloneObj();
        cloneObj.name = "老王";
        cloneObj.age = 30;
        cloneObj.sistersAge = new int[]{18, 19};
        CloneObj cloneObj2 = (CloneObj) cloneObj.clone();
        cloneObj2.name = "磊哥";
        cloneObj2.age = 33;
        cloneObj2.sistersAge[0] = 20;
        System.out.println(cloneObj.name + "|" + cloneObj2.name);
        System.out.println(cloneObj.age + "|" + cloneObj2.age);
        System.out.println(Arrays.toString(cloneObj.sistersAge) + "|" + Arrays.toString(cloneObj2.sistersAge));
    }
}

class CloneObj implements Cloneable {
    public String name;
    public int age;
    public int[] sistersAge;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}