package com.xiaojinzi;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class Answer_33_找出超过数组一半的数字 {
    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 1, 4, 5, 1};
        int[] arr2 = new int[]{1, 2, 3};
        Assert.assertEquals(new Integer(1), getNumber(arr1));
        Assert.assertEquals(null, getNumber(arr2));
    }

    private static Integer getNumber(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int length = arr.length;
        if (length <= 2) {
            return arr[0];
        }
        int count = (length + 1) / 2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            int value = map.getOrDefault(arr[i], 0) + 1;
            if (value >= count) {
                return arr[i];
            }
            map.put(arr[i], value);
        }
        return null;
    }

}
