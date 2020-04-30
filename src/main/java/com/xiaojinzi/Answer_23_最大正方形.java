package com.xiaojinzi;

import org.junit.Assert;

public class Answer_23_最大正方形 {

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
        Assert.assertEquals(4, maximalSquare(arr1));
        Assert.assertEquals(9, maximalSquare(arr2));
        Assert.assertEquals(0, maximalSquare(arr3));
        Assert.assertEquals(9, maximalSquare(arr4));
        Assert.assertEquals(0, maximalSquare(arr5));
        Assert.assertEquals(0, maximalSquare(arr6));

    }

    /**
     * 算法实现：
     * 利用二维数组. 如果为 0 就是 0, 如果为 1, 那么就是此点能组成的正方形的个数
     * 为1 的时候的动态方程为：dp(i,j) = min(dp(i-1,j), dp(i,j-1), dp(i-1,j-1)) + 1
     */
    public static int maximalSquare(int[][] matrix) {
        int m = matrix.length;
        if(m == 0) {
            return 0;
        }
        int[][] result = new int[matrix.length][matrix[0].length];
        int side = 0;
        for (int i = 0; i < matrix.length; i++) {
            int[] itemArr = matrix[i];
            for (int j = 0; j < itemArr.length; j++) {
                if (i == 0 || j == 0) {
                    result[i][j] = matrix[i][j];
                } else {
                    if (itemArr[j] == 0) {
                        result[i][j] = 0;
                    } else {
                        result[i][j] = Math.min(
                                Math.min(
                                        result[i - 1][j], result[i][j - 1]
                                ),
                                result[i - 1][j - 1]
                        ) + 1;
                    }
                }
                side = Math.max(side, result[i][j]);
            }
        }
        return side * side;
    }

}
