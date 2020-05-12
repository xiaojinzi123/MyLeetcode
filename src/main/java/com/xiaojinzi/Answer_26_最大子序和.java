package com.xiaojinzi;

import org.junit.Assert;

public class Answer_26_最大子序和 {

    public static void main(String[] args) {

        int[] arr1 = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] arr2 = new int[]{2};
        int[] arr3 = new int[]{-2, -4, -8, -1, -6, -5};

        Assert.assertEquals(6, force(arr1));
        Assert.assertEquals(2, force(arr2));
        Assert.assertEquals(-1, force(arr3));

        Assert.assertEquals(6, dp(arr1));
        Assert.assertEquals(2, dp(arr2));
        Assert.assertEquals(-1, dp(arr3));

    }

    // O(N^2)
    public static int force(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int maxSum = nums[0];
        for (int i = 0; i < length; i++) {
            int sum = 0;
            for (int j = i; j < length; j++) {
                sum += nums[j];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    public static int dp(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int sum = 0, startIndex = -1, maxSum = nums[0];
        for (int index = 0; index < length; index++) {
            if (startIndex == -1) {
                startIndex = 0;
                sum = nums[index];
            } else {
                sum += nums[index];
            }
            if (sum < 0) {
                startIndex = -1;
            }
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

}
