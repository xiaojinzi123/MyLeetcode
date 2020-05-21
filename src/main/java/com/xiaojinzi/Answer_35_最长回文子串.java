package com.xiaojinzi;

import com.xiaojinzi.support.annotation.NonNull;
import org.junit.Assert;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Answer_35_最长回文子串 {

    public static void main(String[] args) {
        Assert.assertEquals("bab", longestPalindrome("babad"));
        Assert.assertEquals("bb", longestPalindrome("cbbd"));
        Assert.assertEquals("bb", longestPalindrome("cbbd"));
    }

    private static String longestPalindrome(@NonNull String s) {
        if (s == null) {
            return null;
        }
        int length = s.length();
        if (length < 2) {
            return s;
        }
        // 状态方程定义
        // dp(start, end) = (arr[start] == arr[end])
        // && ( end - start < 3 || dp(start + 1, end - 1))
        // 表示
        boolean[][] dp = new boolean[length][length];
        int maxStart = 0, maxLength = 0;
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i; j++) {
                start = j;
                end = j + i;
                dp[start][end] = (s.charAt(start) == s.charAt(end)) &&
                        ((end - start < 3) || dp[start + 1][end - 1]);
                if (dp[start][end]) {
                    if (end - start + 1 > maxLength) {
                        maxLength = end - start + 1;
                        maxStart = start;
                    }
                }
            }
        }
        HashMap<String, List<String>> result = new HashMap();


        return s.substring(maxStart, maxStart + maxLength);
    }

}
