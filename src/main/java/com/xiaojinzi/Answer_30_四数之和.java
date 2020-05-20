package com.xiaojinzi;

import java.util.*;

public class Answer_30_四数之和 {

    public static void main(String[] args) {

        int[] arr1 = new int[]{1, 0, -1, 0, -2, 2};

        List<List<Integer>> lists = fourSum(arr1, 0);

        System.out.println(lists);

    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {

        // 声明 result
        Set<List<Integer>> result = new HashSet<>();
        int length = nums.length;
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        int temp = 0;
        HashSet<Integer> tempSet = new HashSet<>();
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                temp = nums[i] + nums[j];
                // 说明之前的已经有加起来是满足条件的了
                if (map.containsKey(target - temp)) {
                    ArrayList<Integer> list = map.get(target - temp);
                    // list.size() 一定是 2 的倍数
                    int size = list.size() / 2;
                    for (int n = 0; n < size; n++) {
                        tempSet.clear();
                        tempSet.add(i);
                        tempSet.add(j);
                        tempSet.add(list.get(2 * n));
                        tempSet.add(list.get(2 * n + 1));
                        if (tempSet.size() == 4) {
                            List<Integer> resultItem = new ArrayList<>();
                            resultItem.add(nums[i]);
                            resultItem.add(nums[j]);
                            resultItem.add(nums[list.get(2 * n)]);
                            resultItem.add(nums[list.get(2 * n + 1)]);
                            resultItem.sort((o1, o2) -> o1 - o2);
                            result.add(resultItem);
                        }
                    }
                }
                ArrayList<Integer> list = map.get(temp);
                if (list == null) {
                    list = new ArrayList<>();
                    map.put(temp, list);
                }
                list.add(i);
                list.add(j);
            }
        }
        return new ArrayList<>(result);

    }

}
