package com.xiaojinzi;

import org.junit.Assert;

public class 寻找两个有序数组的中位数 {

    public static void main(String[] args) {

        int[] arr1 = new int[]{1, 3};
        int[] arr2 = new int[]{2, 4, 6};
        Assert.assertEquals(3f, find(new int[][]{arr1, arr2}), 0);

        int[] arr3 = new int[]{1, 3};
        int[] arr4 = new int[]{2, 4};
        Assert.assertEquals(2.5f, find(new int[][]{arr3, arr4}), 0);

        int[] arr5 = new int[]{4};
        int[] arr6 = new int[]{1, 2, 3, 5};
        // find(new int[][]{arr5, arr6});
        Assert.assertEquals(3f, find(new int[][]{arr5, arr6}), 0);

        int[] arr7 = new int[]{1, 5, 9, 13, 17};
        int[] arr8 = new int[]{2, 6, 10, 14, 18};
        int[] arr9 = new int[]{3, 7, 11, 15, 19};
        int[] arr10 = new int[]{4, 8, 12, 16, 20};
        Assert.assertEquals(10.5f, find(new int[][]{arr7, arr8, arr9, arr10}), 0);

        int[] arr11 = new int[]{4};
        int[] arr12 = new int[]{5};
        Assert.assertEquals(4.5f, find(new int[][]{arr11, arr12}), 0);

        int[] arr13 = new int[]{4};
        int[] arr14 = new int[]{5};
        int[] arr15 = new int[]{6};
        find(new int[][]{arr13, arr14, arr15});
        Assert.assertEquals(5f, find(new int[][]{arr13, arr14, arr15}), 0);

    }

    /**
     * 返回两个数组中的中位数
     * [1, 3]
     * [2, 4, 5]
     * <p>
     * [1, 4]
     * [2, 3, 5]
     * <p>
     * [1, 3]
     * [2, 4]
     * <p>
     * [1, 4]
     * [2, 3]
     * <p>
     * [4]
     * [1, 2, 3, 5]
     * <p>
     * [1]
     * [2, 3, 4, 5]
     * <p>
     * [3]
     * [1, 2, 4, 5]
     * <p>
     * [3]
     * [1, 2, 4, 5, 6, 7]
     * <p>
     * 思路：多个数组找出中位数, 换句话说就是找出第 K 个顺序排列的数字. 由于奇偶数的问题, 当偶数的时候需要多找一个.
     * 所以我们找第 K 的值计算方式如下：K = totalLength / 2 + 1 比如 totalLength 为 6 就找第四个, totalLength = 5 就找第三个
     * 多找一个的原因是因为, 我们能在循环中记录循环的最后两次的值. 这样子就可以直接找出第 K - 1 个最小值和第 K 个最小值.
     * 因为 totalLength 是偶数的时候, 需要找出中间两个相加除以2
     * 暴力方式的就不说了. 没有啥陈述的价值. 现在的想法是:
     * - startIndexs 用来记录每一个数组要比较的数字的下标
     * - 每个数组从第一个开始相互比较. 排除掉 startIndexs[j] 超过数组 arr[j].length 下标的
     * - 比较出哪个数字是最小的, 记录是哪个数组 currentTargetArrIndex 和记录下标 currentArrResultIndex, 并且记录下一个要比较的 startIndexs[targetArrIndex]++
     * - 那么最终的 currentTargetArrIndex 就是答案的数组下标, 并且 currentArrResultIndex 就是对应数组的下标.
     * - 输出的值为：arr[currentTargetArrIndex][currentArrResultIndex], 上一个值为：arr[preTargetArrIndex][preArrResultIndex]
     * <p>
     * 时间复杂度：O((m + n) / 2 + 1)
     * 很多人觉得你这不是很明显两个 for 循环吗, 怎么可能是这个时间复杂度. 首先我这里的算法是针对的是多个数组同时求中位数, 并不是两个数组.
     * 如果定死了两个数组, 那么我内部的那个循环可以完全不用. 用普通代码即可. 我写通用的只想让这个算法变得更通用. 如果把内循环看成是一句代码.
     * 那么时间复杂度就是 O((m + n) / 2 + 1), 因为比较两个数组我完全可以不用内部的循环. 还请明白这点
     * 但是这个时间复杂度还是没有满足题中的要求的时间复杂度. 其实下面的代码还可以进行改进, 因为我们比较的时候是一个一个比较的,
     * 其实可以一次比较多个, 这得益于多个数组都是有序的. 那么我们直接从每个数组的第 i 个进行比较.
     * 暂时不写优化的版本了
     */
    private static float find(int[][] arr) {
        // 数组的个数
        int arrCount = arr.length;
        // 总元素个数
        int totalLength = 0;
        // 元素最多的那个数组的长度
        int maxArrLength = 0;
        // 计算出所有元素的总元素个数
        for (int i = 0; i < arrCount; i++) {
            totalLength += arr[i].length;
            // 找出数组长度的最大值
            if (arr[i].length > maxArrLength) {
                maxArrLength = arr[i].length;
            }
        }
        // 每个数组的 startIndex
        int[] startIndexs = new int[arrCount];
        // 要找的第 N 个小的数字, 奇数找中间的, 偶数找中间两个, 但是这里是后一位的数目, 比如 8 个, 则找 第 5 个.
        int targetN = totalLength / 2 + 1;
        System.out.println("要寻找第" + targetN + "个小的数字");
        // 是否比较的时候是第一个数
        boolean isFirstNum = true;
        int preTargetArrIndex = -1, preArrResultIndex = -1;
        int currentTargetArrIndex = -1, currentArrResultIndex = -1;
        // 循环 N 次找出第 N 个小的数字
        for (int i = 0; i < targetN; i++) {
            int min = 0;
            int targetArrIndex = -1;
            isFirstNum = true;
            for (int j = 0; j < arrCount; j++) {
                // 如果没有超出下标
                if (!(startIndexs[j] >= arr[j].length)) {
                    int item = arr[j][startIndexs[j]];
                    if (isFirstNum) {
                        // 赋值第一个满足条件的
                        min = item;
                        isFirstNum = false;
                        targetArrIndex = j;
                    } else {
                        if (item < min) {
                            min = item;
                            targetArrIndex = j;
                        }
                    }
                }
            }
            if (targetArrIndex == -1) {
                throw new IllegalArgumentException("targetArrIndex error");
            }

            preTargetArrIndex = currentTargetArrIndex;
            preArrResultIndex = currentArrResultIndex;
            currentTargetArrIndex = targetArrIndex;
            currentArrResultIndex = startIndexs[targetArrIndex];

            // 最小的那个数组的下一个需要比较的数字下标 ++
            startIndexs[targetArrIndex]++;

        }
        // 声明最后的结果
        float result;
        // 如果是偶数
        if (totalLength % 2 == 0) {
            result = (arr[preTargetArrIndex][preArrResultIndex] + arr[currentTargetArrIndex][currentArrResultIndex]) / 2f;
        } else {
            result = arr[currentTargetArrIndex][currentArrResultIndex];
        }
        return result;
    }

}
