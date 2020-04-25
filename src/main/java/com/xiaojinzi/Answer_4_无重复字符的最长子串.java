package com.xiaojinzi;

import com.xiaojinzi.support.annotation.NonNull;
import org.junit.Assert;

/**
 * 题目4
 * 我认为字符串的字符都是 ascii 中的字符
 */
public class Answer_4_无重复字符的最长子串 {

    public static void main(String[] args) {

        String str1 = "pwwkew";
        Assert.assertEquals("wke", findMaxLengthStr(str1, true));
        Assert.assertEquals("kew", findMaxLengthStr(str1, false));

        String str2 = "pwertwpoj";
        Assert.assertEquals("ertwpoj", findMaxLengthStr(str2, true));
        Assert.assertEquals("ertwpoj", findMaxLengthStr(str2, false));

    }

    /**
     * 时间复杂度：O(N)
     * 利用移动窗口方式解决的. 核心有两点：
     * 1. 如何判断当前字符和之前的有重复了
     * 1.1 利用映射关系的数据结构来判断重复, 循环最后每一个字符设置到映射关系结构中
     * 2. 当判断到重复了, startIndex 直接跳到重复的那个字符的 index + 1
     * 2.1 跳转 index + 1 这里有个坑, 就是当重复的字符是 startIndex 之前的, 其实就是不重复. 特别需要注意
     * 2.2 所以当重复的是 startIndex 之前的时候, startIndex 就不需要跳到重复的那个字符的 index + 1
     * 2.3 简单来说可以用 Math.max(startIndex, index + 1)
     * <p>
     * 对于第一点：我利用了字符和 ascii 码是可以互通的, 弄了一个 Integer[256] 数组来充当 map,
     * 当有值说明重复了. 没有则说明没重复, 当重复之后, 可以用 arr[c] 字符对应的值判断是否是 startIndex 之前的字符重复
     * <p>
     * 如果想找到多个答案, 把结果弄成一个 List 来装载找到的最长字串即可
     *
     * @param isFirst 是否寻找第一个, 否则就是寻找最后一个
     */
    private static String findMaxLengthStr(@NonNull String targetStr, boolean isFirst) {

        if (targetStr.isEmpty()) {
            return null;
        }

        // 如果下标对应的值不是 null, 表示有值了, 是值对应的下标
        // 这里用 Map 也是 ok 的
        Integer[] arr = new Integer[256];
        int maxSubLength = 0;
        int length = targetStr.length();
        int startIndex = 0, endIndex = 0;
        int maxStartIndex = 0, maxEndIndex = 0;
        for (int i = 0; i < length; i++) {
            char c = targetStr.charAt(i);
            if (arr[c] != null) {
                // 不为空说明之前已经有这个字符了, 表示重复了,
                // 重复了有两种情况, 一种是 startIndex 之前的和现在重复, 这种不要改变 startIndex 的位置
                startIndex = Math.max(startIndex, arr[c] + 1);
            }
            endIndex = i;
            if (isFirst) { // 就是相等的时候是否覆盖
                if ((endIndex - startIndex + 1) > maxSubLength) {
                    maxStartIndex = startIndex;
                    maxEndIndex = endIndex;
                    maxSubLength = endIndex - startIndex + 1;
                }
            } else {
                if ((endIndex - startIndex + 1) >= maxSubLength) {
                    maxStartIndex = startIndex;
                    maxEndIndex = endIndex;
                    maxSubLength = endIndex - startIndex + 1;
                }
            }

            arr[c] = endIndex;
        }

        return targetStr.substring(maxStartIndex, maxEndIndex + 1);

    }

}
