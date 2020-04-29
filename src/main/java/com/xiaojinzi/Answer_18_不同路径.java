package com.xiaojinzi;

import org.junit.Assert;

import java.util.Arrays;

public class Answer_18_不同路径 {

    public static void main(String[] args) {

        Assert.assertEquals(3, uniquePaths(2, 3));
        Assert.assertEquals(3, uniquePaths(3, 2));
        Assert.assertEquals(6, uniquePaths(3, 3));
        Assert.assertEquals(20, uniquePaths(4, 4));
        Assert.assertEquals(70, uniquePaths(5, 5));
        Assert.assertEquals(1916797311, uniquePaths(51, 9));

    }

    /**
     * arr[i][j] = arr[i-1][j] + arr[i][j-1] 算出推出所有的值
     */
    public static int uniquePaths(int m, int n) {
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    arr[i][j] = 1;
                } else {
                    arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
                }
            }
        }
        return arr[m - 1][n - 1];
    }

}
