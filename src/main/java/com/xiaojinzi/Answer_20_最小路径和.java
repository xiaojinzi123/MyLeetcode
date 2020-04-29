package com.xiaojinzi;

import org.junit.Assert;

import java.util.Arrays;

/**
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 */
public class Answer_20_最小路径和 {

    public static void main(String[] args) {

        int[][] arr1 = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };


        int[][] arr2 = {
                {3, 8, 6, 0, 5, 9, 9, 6, 3, 4, 0, 5, 7, 3, 9, 3},
                {0, 9, 2, 5, 5, 4, 9, 1, 4, 6, 9, 5, 6, 7, 3, 2},
                {8, 2, 2, 3, 3, 3, 1, 6, 9, 1, 1, 6, 6, 2, 1, 9},
                {1, 3, 6, 9, 9, 5, 0, 3, 4, 9, 1, 0, 9, 6, 2, 7},
                {8, 6, 2, 2, 1, 3, 0, 0, 7, 2, 7, 5, 4, 8, 4, 8},
                {4, 1, 9, 5, 8, 9, 9, 2, 0, 2, 5, 1, 8, 7, 0, 9},
                {6, 2, 1, 7, 8, 1, 8, 5, 5, 7, 0, 2, 5, 7, 2, 1},
                {8, 1, 7, 6, 2, 8, 1, 2, 2, 6, 4, 0, 5, 4, 1, 3},
                {9, 2, 1, 7, 6, 1, 4, 3, 8, 6, 5, 5, 3, 9, 7, 3},
                {0, 6, 0, 2, 4, 3, 7, 6, 1, 3, 8, 6, 9, 0, 0, 8},
                {4, 3, 7, 2, 4, 3, 6, 4, 0, 3, 9, 5, 3, 6, 9, 3},
                {2, 1, 8, 8, 4, 5, 6, 5, 8, 7, 3, 7, 7, 5, 8, 3},
                {0, 7, 6, 6, 1, 2, 0, 3, 5, 0, 8, 0, 8, 7, 4, 3},
                {0, 4, 3, 4, 9, 0, 1, 9, 7, 7, 8, 6, 4, 6, 9, 5},
                {6, 5, 1, 9, 9, 2, 2, 7, 4, 2, 7, 2, 2, 3, 7, 2},
                {7, 1, 9, 6, 1, 2, 7, 0, 9, 6, 6, 4, 4, 5, 1, 0},
                {3, 4, 9, 2, 8, 3, 1, 2, 6, 9, 7, 0, 2, 4, 2, 0},
                {5, 1, 8, 8, 4, 6, 8, 5, 2, 4, 1, 6, 2, 2, 9, 7}
        };

        Assert.assertEquals(7, minPathSum(arr1));
        Assert.assertEquals(7, minPathSumUseDp(arr1));

        Assert.assertEquals(83, minPathSum(arr2));
        Assert.assertEquals(83, minPathSumUseDp(arr2));

    }

    /**
     * 使用动态规划做的
     */
    public static int minPathSumUseDp(int[][] arr) {
        int length = arr[0].length;
        // 开始计算出第一行的数据
        int[] result = new int[length];
        int[] firstRowArr = arr[0];
        for (int i = 0; i < firstRowArr.length; i++) {
            if (i == 0) {
                result[i] = firstRowArr[i];
            } else {
                result[i] = result[i - 1] + firstRowArr[i];
            }
        }
        for (int i = 1; i < arr.length; i++) {
            int[] itemArr = arr[i];
            for (int j = 0; j < itemArr.length; j++) {
                if (j == 0) {
                    result[j] = result[j] + itemArr[j];
                } else {
                    result[j] = itemArr[j] + Math.min(result[j], result[j - 1]);
                }
            }
        }
        return result[result.length - 1];
    }

    /**
     * 使用递归, 有些是重复的, 可以使用缓存降低时间复杂度. 如果不优化, leetcode 上时间是通不过的
     */
    public static int minPathSum(int[][] arr) {
        // 因为是非负, 所以这里可以用一个一样大小的缓存数组
        int[][] caches = new int[arr.length][arr[0].length];
        for (int i = 0; i < caches.length; i++) {
            Arrays.fill(caches[i], -1);
        }
        return minPathSumRecursion(arr, arr.length - 1, arr[0].length - 1, caches);
    }

    public static int minPathSumRecursion(int[][] arr, int x, int y, int[][] caches) {
        if (x == 0 && y == 0) {
            return arr[0][0];
        }
        if (caches[x][y] != -1) {
            return caches[x][y];
        }
        if (x == 0) {
            caches[x][y] = arr[x][y] + minPathSumRecursion(arr, x, y - 1, caches);
            return caches[x][y];
        }
        if (y == 0) {
            caches[x][y] = arr[x][y] + minPathSumRecursion(arr, x - 1, y, caches);
            return caches[x][y];
        }
        // 递归
        caches[x][y] = arr[x][y] + Math.min(
                minPathSumRecursion(arr, x - 1, y, caches),
                minPathSumRecursion(arr, x, y - 1, caches)
        );
        return caches[x][y];
    }

}
