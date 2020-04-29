package com.xiaojinzi;

import org.junit.Assert;

public class Answer_17_x的平方根 {

    public static void main(String[] args) {

        Assert.assertEquals(0, sqrt(0));
        Assert.assertEquals(1, sqrt(1));
        Assert.assertEquals(1, sqrt(2));
        Assert.assertEquals(2, sqrt(4));
        Assert.assertEquals(2, sqrt(5));
        Assert.assertEquals(2, sqrt(8));
        Assert.assertEquals(3, sqrt(9));
        Assert.assertEquals(3, sqrt(11));
        Assert.assertEquals(46339, sqrt(2147395599));

    }

    /**
     * 这里使用的是二分的算法, 时间复杂度 O(log N)
     * 其实还有牛顿迭代法, 但是比较难理解先不写
     */
    public static int sqrt(int x) {
        if (x < 0) {
            throw new RuntimeException();
        }
        int left = 0, right = x;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (mid > x / mid) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

}
