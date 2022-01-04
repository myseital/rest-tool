package com.mao.algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序（Heap Sort）算法是利用堆结构和二叉树的一些特性来完成排序的。
 * 堆结构是一种树结构，准确来说是一个完全二叉树。完全二叉树每个节点应满足以下条件：
 * • 如果按照从小到大的顺序排序，要求非叶节点的数据要大于等于，其左、右子节点的数据；
 * • 如果按照从大到小的顺序排序，要求非叶节点的数据小于等于，其左、右子节点的数据。
 * 可以看出，堆结构对左、右子节点的大小没有要求，只规定叶节点要和子节点（左、右）的数据满足大小关系。
 *
 * @author myseital
 * @date 2021/4/4 03:30
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] heapNums = {18, 1, 6, 27, 15};
        System.out.println("堆排序前：" + Arrays.toString(heapNums));
        heapSort(heapNums, heapNums.length);
        System.out.println("堆排序后：" + Arrays.toString(heapNums));
    }

    private static void heapSort(int[] nums, int n) {
        int i, j, k, temp;
        // 将 nums[0,n-1] 建成大根堆
        for (i = n / 2 - 1; i >= 0; i--) {
            // 第 i 个节点，有右子树
            while (2 * i + 1 < n) {
                j = 2 * i + 1;
                if ((j + 1) < n) {
                    // 右左子树小于右子树，则需要比较右子树
                    if (nums[j] < nums[j + 1]) {
                        // 序号增加 1，指向右子树
                        j++;
                    }
                }
                if (nums[i] < nums[j]) {
                    // 交换数据
                    temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    // 堆被破坏，重新调整
                    i = j;
                } else {
                    // 左右子节点均大，则堆未被破坏，不需要调整
                    break;
                }
            }
        }

        for (i = n - 1; i > 0; i--) {
            // 与第 i 个记录交换
            temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            k = 0;
            // 第 i 个节点有右子树
            while (2 * k + 1 < i) {
                j = 2 * k + 1;
                if ((j + 1) < i) {
                    // 右左子树小于右子树，则需要比较右子树
                    if (nums[j] < nums[j + 1]) {
                        // 序号增加 1，指向右子树
                        j++;
                    }
                }
                if (nums[k] < nums[j]) {
                    // 交换数据
                    temp = nums[k];
                    nums[k] = nums[j];
                    nums[j] = temp;
                    // 堆被破坏，重新调整
                    k = j;
                } else {
                    // 左右子节点均大，则堆未被破坏，不需要调整
                    break;
                }
            }
            // 输出每步排序结果
            System.out.print("第" + (n - i) + "次排序：");
            System.out.println(Arrays.toString(nums));
        }
    }
}
