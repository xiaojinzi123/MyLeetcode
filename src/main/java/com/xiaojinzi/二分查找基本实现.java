package com.xiaojinzi;

import org.junit.Assert;

public class 二分查找基本实现 {

    public static void main(String[] args) {

        int[] arr1 = new int[]{
                1, 2, 3, 5, 6, 6, 7, 8, 9, 10
        };
        int[] arr2 = new int[]{
                2, 5
        };
        Assert.assertEquals(1, find1(arr1, 2));
        Assert.assertEquals(-1, find1(arr1, 11));
        Assert.assertEquals(0, find1(arr2, 2));
        Assert.assertEquals(-1, find1(arr1, 11));

        Assert.assertEquals(1, find2(arr1, 2));
        Assert.assertEquals(-1, find2(arr1, 11));
        Assert.assertEquals(0, find2(arr2, 2));
        Assert.assertEquals(-1, find2(arr2, 11));

    }

    /**
     * while 实现
     * arr 必须递增
     */
    private static int find1(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private static int find2(int[] arr, int target) {
        return find2Recursion(arr, 0, arr.length - 1, target);
    }

    private static int find2Recursion(int[] arr, int l, int r, int target) {
        if (l == r) {
            return target == arr[l] ? l : -1;
        } else {
            // 这里如果 r- l = 1 相差一个的话, mid = l
            // 那么下面的的左右边界就需要慎重, 容易死循环
            int mid = (l + r) / 2;
            if (target > arr[mid]) {
                return find2Recursion(arr, mid + 1, r, target);
            } else {
                return find2Recursion(arr, l, mid, target);
            }
        }
    }

}
