package com.xiaojinzi;

import org.junit.Assert;

public class Answer_28_打家劫舍2 {

    public static void main(String[] args) {

        int[] arr1 = new int[]{0};
        int[] arr2 = new int[]{3};
        int[] arr3 = new int[]{1, 2, 3, 1};
        int[] arr4 = new int[]{4, 6, 8, 2, 4, 6, 8};

        Assert.assertEquals(0, rob(arr1));
        Assert.assertEquals(3, rob(arr2));
        Assert.assertEquals(4, rob(arr3));
        Assert.assertEquals(4, rob(arr4));

    }

    /**
     *
     */
    public static int rob(int[] nums) {
        return 1;
    }

}
