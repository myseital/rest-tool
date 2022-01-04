package com.mao.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 * @author myseital
 * @date 2021/4/1 02:10
 */
public class SelectSort {
    public static void main(String[] args) {
        /**
         * 第 0 次排序：[1, 18, 6, 27, 15]
         * 第 1 次排序：[1, 6, 18, 27, 15]
         * 第 2 次排序：[1, 6, 15, 27, 18]
         * 第 3 次排序：[1, 6, 15, 18, 27]
         */
        int[] arr = {18, 1, 6, 27, 15};
        System.out.println("排序前：" + Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    private static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int lowerIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                // 找出最小的一个索引
                if (arr[j] < arr[lowerIndex]) {
                    lowerIndex = j;
                }
            }
            // 交换
            int temp = arr[i];
            arr[i] = arr[lowerIndex];
            arr[lowerIndex] = temp;
        }
    }
}
