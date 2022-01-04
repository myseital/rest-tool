package com.mao.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author myseital
 * @date 2021/4/1 02:00
 */
public class BubbleSort {

    public static void main(String[] args) {
        /**
         * 第 1 次排序：[110, 122, 90, 50, 132]
         * 第 2 次排序：[110, 90, 50, 122, 132]
         * 第 3 次排序：[90, 50, 110, 122, 132]
         * 第 4 次排序：[50, 90, 110, 122, 132]
         */
        int[] arr = {132, 110, 122, 90, 50};
        System.out.println("排序前：" + Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 因为冒泡是把每轮循环中较大的数飘到后面，所以是 arr.length-i-1
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 元素交换
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
