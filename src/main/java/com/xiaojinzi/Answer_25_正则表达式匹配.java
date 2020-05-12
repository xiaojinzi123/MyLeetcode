package com.xiaojinzi;

import org.junit.Assert;

public class Answer_25_正则表达式匹配 {

    public static void main(String[] args) {
        Assert.assertEquals(true, isMatch(
                "", "a*b*"
        ));
        Assert.assertEquals(false, isMatch(
                "aa", "a"
        ));
        Assert.assertEquals(true, isMatch(
                "aa", "a*"
        ));
        Assert.assertEquals(true, isMatch(
                "ab", ".*"
        ));
        Assert.assertEquals(true, isMatch(
                "aab", "c*a*b"
        ));
        Assert.assertEquals(false, isMatch(
                "mississippi", "mis*is*p*."
        ));
    }

    public static boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        // 开头是否匹配
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }

}
