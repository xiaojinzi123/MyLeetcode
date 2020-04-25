package com.xiaojinzi;

import org.junit.Assert;

public class Answer_8_整数反转 {

    public static void main(String[] args) {

        // System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
        // System.out.println("Integer.MIN_VALUE = " + Integer.MIN_VALUE);

        Assert.assertEquals(0, reverse(Integer.MAX_VALUE));
        Assert.assertEquals(-8, reverse(-8));
        Assert.assertEquals(8, reverse(8));
        Assert.assertEquals(123, reverse(321));
        Assert.assertEquals(-123, reverse(-321));
        Assert.assertEquals(321, reverse(123));
        Assert.assertEquals(1463847412, reverse(2147483641)); // 2147447412
        Assert.assertEquals(2147447412, reverse(2147447412));
        Assert.assertEquals(0, reverse(2147447413));
        Assert.assertEquals(12, reverse(210));

    }

    public static int reverse(int x) {
        // 如果是不需要反转的部分
        if (-10 < x && x < 10) {
            return x;
        }
        boolean isNegative = false;
        if (x < 0) { // 标记是负数
            isNegative = true;
        }
        int result = 0;
        int temp = x;
        while (temp != 0) {
            // 得到余数
            int remainer = temp % 10;
            int num = ((isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE) - remainer) / 10;
            if (isNegative) {
                if (num > result) {
                    return 0;
                }
            } else {
                if (num < result) {
                    return 0;
                }
            }
            result = result * 10 + remainer;
            temp /= 10;
        }
        return result;
    }

}
