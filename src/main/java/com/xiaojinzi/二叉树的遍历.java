package com.xiaojinzi;

import com.xiaojinzi.support.BinarySearchTree;
import org.junit.Assert;

import java.util.Arrays;

public class 二叉树的遍历 {

    public static void main(String[] args) {

        BinarySearchTree<Integer> treeList = new BinarySearchTree<>();

        /**
         *     5
         *   2   7
         * 1  3    9
         *        8 20
         *
         *     5
         *   3   7
         * 1       9
         *        8 20
         *
         *     5
         *   3   7
         *         9
         *        8 20
         *
         *     5
         *   3   7
         *         20
         *        8
         *
         *    5
         *  3   7
         *     6  20
         *       8
         */

        treeList.add(5);
        treeList.add(2);
        treeList.add(7);
        treeList.add(1);
        treeList.add(3);
        treeList.add(9);
        treeList.add(8);
        treeList.add(20);

        // 长度校验
        Assert.assertSame(8, treeList.size());

        // 比较中序遍历(升序的)
        Assert.assertEquals(
                Arrays.asList(1, 2, 3, 5, 7, 8, 9, 20),
                treeList.inOrder()
        );

        // 比较深度优先遍历
        Assert.assertEquals(
                Arrays.asList(5, 2, 1, 3, 7, 9, 8, 20),
                treeList.depthFirst()
        );

        // 比较广度优先遍历
        Assert.assertEquals(
                Arrays.asList(5, 2, 7, 1, 3, 9, 8, 20),
                treeList.breadthFirst()
        );

        // 删掉第二个
        treeList.remove(1);

        // 比较中序遍历(升序的)
        Assert.assertEquals(
                Arrays.asList(1, 3, 5, 7, 8, 9, 20),
                treeList.inOrder()
        );

        // 比较深度优先遍历
        Assert.assertEquals(
                Arrays.asList(5, 3, 1, 7, 9, 8, 20),
                treeList.depthFirst()
        );

        // 比较广度优先遍历
        Assert.assertEquals(
                Arrays.asList(5, 3, 7, 1, 9, 8, 20),
                treeList.breadthFirst()
        );

        // 删除 1 这个元素
        treeList.remove(new Integer(1));

        // 比较中序遍历(升序的)
        Assert.assertEquals(
                Arrays.asList(3, 5, 7, 8, 9, 20),
                treeList.inOrder()
        );

        // 比较深度优先遍历
        Assert.assertEquals(
                Arrays.asList(5, 3, 7, 9, 8, 20),
                treeList.depthFirst()
        );

        // 比较广度优先遍历
        Assert.assertEquals(
                Arrays.asList(5, 3, 7, 9, 8, 20),
                treeList.breadthFirst()
        );

        // 删除 9 这个元素
        treeList.remove(new Integer(9));

        // 比较中序遍历(升序的)
        Assert.assertEquals(
                Arrays.asList(3, 5, 7, 8, 20),
                treeList.inOrder()
        );

        // 比较深度优先遍历
        Assert.assertEquals(
                Arrays.asList(5, 3, 7, 20, 8),
                treeList.depthFirst()
        );

        // 比较广度优先遍历
        Assert.assertEquals(
                Arrays.asList(5, 3, 7, 20, 8),
                treeList.breadthFirst()
        );

        treeList.add(6);

        // 比较中序遍历(升序的)
        Assert.assertEquals(
                Arrays.asList(3, 5, 6, 7, 8, 20),
                treeList.inOrder()
        );

        // 比较深度优先遍历
        Assert.assertEquals(
                Arrays.asList(5, 3, 7, 6, 20, 8),
                treeList.depthFirst()
        );

        // 比较广度优先遍历
        Assert.assertEquals(
                Arrays.asList(5, 3, 7, 6, 20, 8),
                treeList.breadthFirst()
        );

    }

}
