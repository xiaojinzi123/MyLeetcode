package com.xiaojinzi;

import org.junit.Assert;

public class Answer_24_统计全为1的正方形子阵 {

    public static void main(String[] args) {

        int[][] arr1 = new int[][]{
                {1, 0, 1, 0, 0},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0}
        };
        int[][] arr2 = new int[][]{
                {1, 1, 1, 0, 0},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0}
        };
        int[][] arr3 = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        int[][] arr4 = new int[][]{
                {1, 1, 0, 0, 0},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 0, 1, 1, 1}
        };
        int[][] arr5 = new int[][]{};
        int[][] arr6 = new int[][]{{0}};

        Assert.assertEquals(15, countSquares(arr1));
        Assert.assertEquals(22, countSquares(arr2));
        Assert.assertEquals(0, countSquares(arr3));
        Assert.assertEquals(20, countSquares(arr4));
        Assert.assertEquals(0, countSquares(arr5));
        Assert.assertEquals(0, countSquares(arr6));

    }

    public static int countSquares(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int result = 0;
        for (int i = 0; i < m; i++) {
            int[] itemArr = matrix[i];
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = itemArr[j];
                } else {
                    if (itemArr[j] == 0) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = Math.min(
                                Math.min(
                                        dp[i - 1][j], dp[i][j - 1]
                                ),
                                dp[i - 1][j - 1]
                        ) + 1;
                    }
                }
                result += dp[i][j];
            }
        }
        return result;
    }

}
