package com.xiaojinzi;

import org.junit.Assert;

public class Answer_32_判断顺子 {

    public static void main(String[] args) {

        // 0 53 下标表示排序过的牌. 0~12 是一种花色, 13~25 是第二种
        // 表示抽到的牌, 抽到的牌不重复, 并且会经过排序, 排序的算法就不写了
        int[] arr1 = new int[]{0, 14, 15, 16, 17};
        int[] arr2 = new int[]{1, 14, 15, 16, 17};
        int[] arr3 = new int[]{14, 15, 16, 18, 52}; // 52 是王
        int[] arr4 = new int[]{14, 16, 18, 52, 53}; // 52 是王, 53 是王
        int[] arr5 = new int[]{14, 16, 19, 52, 53}; // 52 是王, 53 是王
        Assert.assertEquals(true, isShunZu(arr1));
        Assert.assertEquals(false, isShunZu(arr2));
        Assert.assertEquals(true, isShunZu(arr3));
        Assert.assertEquals(true, isShunZu(arr4));
        Assert.assertEquals(false, isShunZu(arr5));

    }

    public static boolean isShunZu(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int length = arr.length;
        // 王的个数
        int queenCount = 0;
        // 判断最后两个是否是王, 是的话计数
        if (arr[length - 1] >= 52) {
            queenCount++;
        }
        if (arr[length - 2] >= 52) {
            queenCount++;
        }
        int size = length - queenCount;
        int preValue = arr[0] % 13;
        // 从下标为 1 开始循环, 因为第一个不需要判断, 肯定不是王并且肯定连续
        for (int i = 1; i < size; ) {
            // 如果下一个元素连续, 就累加标记
            if (arr[i] % 13 == preValue + 1) {
                preValue = (preValue + 1) % 13;
                i++;
            } else {
                if (queenCount == 0) {
                    // 如果不连续并且没有王了, 那么直接返回 false
                    return false;
                } else {
                    // 消耗一个 queen
                    queenCount--;
                    // 累加标记
                    preValue = (preValue + 1) % 13;
                }
            }
        }
        return true;
    }

}
