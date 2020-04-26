package com.xiaojinzi;

import org.junit.Assert;

public class Answer_11_移除元素 {

    public static void main(String[] args) {

        int[] nums1 = new int[]{1, 2, 3, 4, 5, 5};
        int length1 = removeElement(nums1, 5);
        int[] nums1Result = new int[length1];
        System.arraycopy(nums1, 0, nums1Result, 0, length1);
        Assert.assertArrayEquals(
                new int[]{1, 2, 3, 4},
                nums1Result
        );

        int[] nums2 = new int[]{1, 8, 5, 2, 5, 6, 8, 2, 5};
        int length2 = removeElement(nums2, 5);
        int[] nums2Result = new int[length2];
        System.arraycopy(nums2, 0, nums2Result, 0, length2);
        Assert.assertArrayEquals(
                new int[]{1, 8, 2, 6, 8, 2},
                nums2Result
        );

    }

    public static int removeElement(int[] nums, int val) {
        int length = nums.length;
        for (int i = length - 1; i >= 0; i--) {
            if (nums[i] == val) {
                System.arraycopy(
                        nums, i + 1,
                        nums, i,
                        length - i - 1
                );
                length--;
            }
        }
        return length;
    }

}
