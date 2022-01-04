package com.mao.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序（Quick Sort）算法和冒泡排序算法类似，
 * 都是基于交换排序思想实现的，快速排序算法是对冒泡排序算法的改进，从而具有更高的执行效率。
 * • 首先设定一个分界值，通过该分界值把数组分为左右两个部分；
 * • 将大于等于分界值的元素放到分界值的右边，将小于分界值的元素放到分界值的左边；
 * • 然后对左右两边的数据进行独立的排序，在左边数据中取一个分界值，把小于分界值的元素放到分界值的左边，
 * 大于等于分界值的元素，放到数组的右边；右边的数据也执行同样的操作；
 * • 重复上述操作，当左右各数据排序完成后，整个数组也就完成了排序。
 *
 * @author myseital
 * @date 2021/4/1 02:07
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] quickNums = {18, 1, 6, 27, 15};
        System.out.println("排序前：" + Arrays.toString(quickNums));
        quickSort(quickNums, 0, quickNums.length - 1);
        System.out.println("排序后：" + Arrays.toString(quickNums));
    }

    /**
     * 快速排序 (二分排序)
     */
    private static void quickSort(int[] nums, int left, int right) {
        int f, t;
        int ltemp = left;
        int rtemp = right;
        // 分界值
        f = nums[(left + right) / 2];
        while (ltemp < rtemp) {
            while (nums[ltemp] < f) {
                ++ltemp;
            }
            while (nums[rtemp] > f) {
                --rtemp;
            }
            if (ltemp <= rtemp) {
                t = nums[ltemp];
                nums[ltemp] = nums[rtemp];
                nums[rtemp] = t;
                --rtemp;
                ++ltemp;
            }
        }
        if (ltemp == rtemp) {
            ltemp++;
        }
        if (left < rtemp) {
            // 递归调用
            quickSort(nums, left, ltemp - 1);
        }
        if (right > ltemp) {
            // 递归调用
            quickSort(nums, rtemp + 1, right);
        }
    }
}
