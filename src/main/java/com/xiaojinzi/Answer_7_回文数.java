package com.xiaojinzi;

import org.junit.Assert;

import java.util.*;

public class Answer_7_回文数 {

    public static void main(String[] args) {

        Map<Integer, Boolean> map = new HashMap<>();
        map.put(121, true);
        map.put(11, true);
        for (int i = 0; i <= 9; i++) {
            map.put(i, true);
        }
        map.put(-8, false);
        map.put(-121, false);
        map.put(1221, true);
        map.put(123321, true);
        map.put(33123, false);

        for (Map.Entry<Integer, Boolean> item : map.entrySet()) {
            Assert.assertEquals(item.getValue(), isPalindrome1(item.getKey()));
            Assert.assertEquals(item.getValue(), isPalindrome2(item.getKey()));
        }

    }

    /**
     * int 转化为字符串解决的
     */
    public static boolean isPalindrome1(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        // 转化为字符串
        String str = String.valueOf(x);
        boolean result = true;
        // 让两个下标是字符串的最左边和右边
        int startIndex = 0, endIndex = str.length() - 1;
        while (startIndex < endIndex) {
            // 如果任何一个不相等就不是了
            if (str.charAt(startIndex) != str.charAt(endIndex)) {
                result = false;
                break;
            }
            startIndex++;
            endIndex--;
        }
        return result;
    }

    /**
     * 把数字 % 10 然后 /10 循环都弄到 list 中, 然后就可以像第一种方法一样处理了
     */
    public static boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        boolean result = true;
        List<Integer> list = new ArrayList<>();
        int temp = x;
        while (temp >= 10) {
            list.add(temp % 10);
            temp /= 10;
        }
        // 添加最后一位数字
        list.add(temp);

        int startIndex = 0, endIndex = list.size() - 1;
        while (startIndex < endIndex) {
            // 如果任何一个不相等就不是了
            if (list.get(startIndex).compareTo(list.get(endIndex)) != 0) {
                result = false;
                break;
            }
            startIndex++;
            endIndex--;
        }

        return result;
    }

}
