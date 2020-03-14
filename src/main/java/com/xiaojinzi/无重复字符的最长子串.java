package com.xiaojinzi;

import com.xiaojinzi.support.NonNull;

/**
 * 我认为字符串的字符都是 ascii 中的字符
 */
public class 无重复字符的最长子串 {

    public static void main(String[] args) {

        String targetStr = "pwwkew";

        findMaxLengthStr(targetStr);

    }

    /**
     * 时间复杂度：O(N)
     * 利用移动窗口方式解决的. 核心有两点：
     * 1. 如何判断当前字符和之前的有重复了
     * 2. 当判断到重复了, startIndex 直接跳到重复的那个字符的 index + 1
     *
     * 对于第一点：我利用了字符和 ascii 码是可以互通的, 弄了一个 Integer[256] 数组来充当 map,
     * 当有值说明重复了. 没有则说明没重复
     * 第二点：因为当开始重复了, 则重复字符的前面的字符都无需再进行处理了. 原因如下：
     *
     * qewrtyw 当 index = 6 的 w 开始与 index = 2 的 w 重复, 那么 在这个整段字符串中再也不会有比 qewrty 更长的子串了
     * 所以 startIndex 直接跳到 index = 3 的 r 即可
     *
     * 如果想找到多个答案, 把结果弄成一个 List 来装载找到的最长字串即可
     */
    private static void findMaxLengthStr(@NonNull String targetStr) {

        if (targetStr.isEmpty()) {
            System.out.println("目标字符串为空, 答案为0");
            return;
        }

        // 如果下标对应的值不是 null, 表示有值了, 是值对应的下标
        Integer[] arr = new Integer[256];
        int maxSubLength = 0;
        int length = targetStr.length();
        int startIndex = 0, endIndex = 0;
        int maxStartIndex = 0, maxEndIndex = 0;
        for (int i = 0; i < length; i++) {
            char c = targetStr.charAt(i);
            // 如果为空, 说明之前没有这个字符
            if (arr[c] == null) {
                endIndex = i;
                if ((endIndex - startIndex + 1) > maxSubLength) {
                    maxStartIndex = startIndex;
                    maxEndIndex = endIndex;
                }
            } else { // 不为空说明之前已经有这个字符了, 表示重复了
                startIndex = arr[c] + 1;
            }
            arr[c] = i;
        }

        System.out.println("result = " + targetStr.substring(maxStartIndex, maxEndIndex + 1));

    }

}
