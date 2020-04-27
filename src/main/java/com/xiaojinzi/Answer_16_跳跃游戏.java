package com.xiaojinzi;

import org.junit.Assert;

public class Answer_16_跳跃游戏 {

    public static void main(String[] args) {

        Assert.assertEquals(
                true,
                canJump(new int[]{2, 3, 1, 1, 4})
        );
        Assert.assertEquals(
                true,
                canJump(new int[]{4, 2, 1, 0, 4})
        );
        Assert.assertEquals(
                false,
                canJump(new int[]{0, 3, 1, 0, 4})
        );
        Assert.assertEquals(
                false,
                canJump(new int[]{2, 2, 1, 0, 4})
        );

    }


    public static boolean canJump(int[] nums) {
        int lastIndex = nums.length - 1;
        for (int index = nums.length - 2; index >= 0; index--) {
            if (index + nums[index] >= lastIndex) {
                lastIndex = index;
            }
        }
        return lastIndex == 0;
    }

}
