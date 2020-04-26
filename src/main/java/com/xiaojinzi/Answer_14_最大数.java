package com.xiaojinzi;

import org.junit.Assert;

import java.util.Arrays;

public class Answer_14_最大数 {

    public static void main(String[] args) {
        int[] arr1 = new int[]{3, 30, 34, 5, 9};
        Assert.assertEquals(
                "9534330",
                largestNumber(arr1)
        );
        int[] arr2 = new int[]{0, 0, 0};
        Assert.assertEquals(
                "0",
                largestNumber(arr2)
        );
        int[] arr3 = new int[]{0, 1, 0};
        Assert.assertEquals(
                "100",
                largestNumber(arr3)
        );
        int[] arr4 = new int[]{0};
        Assert.assertEquals(
                "0",
                largestNumber(arr4)
        );
    }

    public static String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        if (strs[0].equals("0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]);
        }
        return sb.toString();
    }

}
