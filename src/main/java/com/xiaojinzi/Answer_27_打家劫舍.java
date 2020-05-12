package com.xiaojinzi;

import org.junit.Assert;

public class Answer_27_打家劫舍 {

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
     * 如果i是要抢劫的
     * dp(i)[1] = dp(i-1)[0] + nums[i]
     * 如果i是不要抢劫的
     * dp(i)[0] = max(dp(i-1)[0],dp(i-1)[1])
     */
    public static int rob(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return nums[0];
        }
        int[][] dp = new int[length][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < length; i++) {
            dp[i][1] = dp[i - 1][0] + nums[i];
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
        }
        return Math.max(
                dp[length - 1][0],
                dp[length - 1][1]
        );
    }

}
