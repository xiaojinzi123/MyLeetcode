package com.xiaojinzi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * <p>
 * 个人对这题的考虑.
 * 1. 数组中是否有重复的数字
 * 2. 是否输出多对满足条件的
 */
public class 两数之和 {

    public static void main(String[] args) {
        int nums[] = {2, 7, 11, 15, 7, 2};
        int target = 9;
        method1(nums, target);
        System.out.println("========================");
        method2(nums, target);
    }

    /**
     * 暴力法
     * 时间复杂度：O(N^2)
     */
    private static void method1(int[] nums, int target) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if (nums[i] + nums[j] == target) {
                    System.out.println("method1.result = [" + i + "," + j + "]");
                }
            }
        }
    }

    /**
     * 先放入 map 中, key 为数组的值, 值重复的 index, 存放到 value 的 List 中
     * 利用空间换时间
     * 时间复杂度：O(N)
     */
    private static void method2(int[] nums, int target) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            List<Integer> indexs = map.get(nums[i]);
            if (indexs == null) {
                indexs = new ArrayList<>();
                map.put(nums[i], indexs);
            }
            indexs.add(i);
        }
        for (int i = 0; i < length; i++) {
            if (map.containsKey(target - nums[i])) {
                List<Integer> indexs = map.get(target - nums[i]);
                for (Integer index : indexs) {
                    if (index >= i) {
                        System.out.println("method2.result = [" + i + "," + index + "]");
                    }
                }
            }
        }
    }

}
