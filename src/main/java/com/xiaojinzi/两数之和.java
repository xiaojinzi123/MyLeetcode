package com.xiaojinzi;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 两数之和 {

    public static void main(String[] args) {

        int nums[] = {2, 7, 11, 15, 7, 2};
        int target1 = 9;
        int target2 = 22;

        Assert.assertArrayEquals(new int[]{0, 1}, method1(nums, target1));
        Assert.assertArrayEquals(new int[]{1, 3}, method1(nums, target2));

        Assert.assertArrayEquals(new int[]{0, 1}, method2(nums, target1));
        Assert.assertArrayEquals(new int[]{1, 3}, method2(nums, target2));

    }

    /**
     * 暴力法
     * 时间复杂度：O(N^2)
     */
    private static int[] method1(int[] nums, int target) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            // 如果 index 不能相同, 这里的 j = i 变成 j = i + 1 即可
            for (int j = i; j < length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * 先放入 map 中, key 为数组的值, 值重复的 index, 存放到 value 的 List 中
     * 假设数据是这样的: {2, 7, 11, 15, 7, 2}
     * map{
     * 2  -> 0, 5
     * 7  -> 1, 4
     * 11 -> 2,
     * 15 -> 3
     * }
     * 然后循环数组, target - nums[i] 就可以得到满足等式需要的另一个值. 然后根据这个值判断 map 中是否存在即可
     * 比如 target = 9, i = 0, 那么 target - nums[i] = 7. 根据 7 为 key, 找到 [1,4] 这两个下标的都是满足条件的.
     * 即两个答案： nums[0] + nums[1] = 9, nums[0] + nums[4] = 9
     * 利用空间换时间
     * 时间复杂度：O(N). 这里是把打印答案的 for 循环看成是一个整体了. 所以是 O(N)
     * 因为我们的核心是找到答案, 找答案我们确实是 O(N)
     */
    private static int[] method2(int[] nums, int target) {
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
                    // 如果 index 不能相同, 这里的 index >= i 变成 index > i 即可
                    if (index >= i) {
                        return new int[]{i, index};
                    }
                }
            }
        }
        return null;
    }

}
