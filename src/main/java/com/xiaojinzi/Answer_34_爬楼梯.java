package com.xiaojinzi;

import org.junit.Assert;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Answer_34_爬楼梯 {

    public static void main(String[] args) {
        Assert.assertEquals(1, recursion(1));
        Assert.assertEquals(1, dp(1));
        Assert.assertEquals(2, recursion(2));
        Assert.assertEquals(2, dp(2));
        Assert.assertEquals(3, recursion(3));
        Assert.assertEquals(3, dp(3));
        Assert.assertEquals(5, recursion(4));
        Assert.assertEquals(5, dp(4));
        Assert.assertEquals(8, recursion(5));
        Assert.assertEquals(8, dp(5));
        Assert.assertEquals(13, recursion(6));
        Assert.assertEquals(13, dp(6));
        Assert.assertEquals(21, recursion(7));
        Assert.assertEquals(21, dp(7));
    }

    private static int recursion(int n) {
        if (n <= 0) {
            throw new RuntimeException();
        }
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);
        cache[1] = 1;
        if (n > 1) {
            cache[2] = 2;
        }
        return doRecursion(n, cache);
    }

    private static int doRecursion(int n, int[] cache) {
        if (cache[n] != -1) {
            return cache[n];
        }
        int result = doRecursion(n - 2, cache) + doRecursion(n - 1, cache);
        cache[n] = result;
        return result;
    }

    private static int dp(int n) {
        if (n <= 2) {
            return n;
        }
        int a = 1, b = 2, c = 0;
        for (int i = 2; i < n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

}
