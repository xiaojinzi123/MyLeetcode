package com.xiaojinzi;

import com.xiaojinzi.support.SearchTreeList;
import com.xiaojinzi.support.BinaryTreeNode;
import org.junit.Assert;

import java.util.*;

/**
 * 就是每一层的数据弄成一个集合, 然后多层优势一个集合, 类似于下面这种
 * [
 * [1,2,3],
 * [4,5]
 * ]
 */
public class 二叉树的层次遍历 {

    public static void main(String[] args) {

        // 创建一个搜索二叉树
        SearchTreeList<Integer> treeList = new SearchTreeList<>();

        treeList.add(5);
        treeList.add(2);
        treeList.add(7);
        treeList.add(1);
        treeList.add(3);
        treeList.add(9);
        treeList.add(8);
        treeList.add(20);

        BinaryTreeNode<Integer> header = treeList.getHeader();
        Assert.assertEquals(
                Arrays.asList(
                        Arrays.asList(5),
                        Arrays.asList(2, 7),
                        Arrays.asList(1, 3, 9),
                        Arrays.asList(8, 20)
                )
                , preOrderRecursion(header)
        );
        Assert.assertEquals(
                Arrays.asList(
                        Arrays.asList(5),
                        Arrays.asList(2, 7),
                        Arrays.asList(1, 3, 9),
                        Arrays.asList(8, 20)
                )
                , breadthFirst(header)
        );

    }

    /**
     * 利用广度优先遍历实现
     */
    private static List<List<Integer>> breadthFirst(BinaryTreeNode<Integer> header) {

        List<List<Integer>> result = new ArrayList<>();
        // 声明一个队列
        Queue<BinaryTreeNode<Integer>> q = new LinkedList();
        q.add(header);
        int level = 0;
        while (!q.isEmpty()) {
            if (result.size() <= level) {
                result.add(new ArrayList<>());
            }
            int size = q.size();
            for (int count = 0; count < size; count++) {
                BinaryTreeNode<Integer> node = q.remove();
                result.get(level).add(node.getValue());
                if (node.getLeft() != null) {
                    q.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    q.add(node.getRight());
                }
            }
            level++;
        }
        return result;
    }

    /**
     * 利用前序遍历实现,
     * 递归添加到集合中.
     * 递归中添加一个 level 参数表示当前是第几层,
     * 这样就能找到集合中对应的集合进行惭怍
     */
    private static List<List<Integer>> preOrderRecursion(BinaryTreeNode<Integer> header) {
        List<List<Integer>> result = new ArrayList<>();
        doPreOrderRecursion(header, 1, result);
        return result;
    }

    private static void doPreOrderRecursion(BinaryTreeNode<Integer> node, int level,
                                            List<List<Integer>> result) {
        if (node == null) {
            return;
        }
        while (result.size() < level) {
            result.add(new ArrayList<>());
        }
        result.get(level - 1).add(node.getValue());
        doPreOrderRecursion(node.getLeft(), level + 1, result);
        doPreOrderRecursion(node.getRight(), level + 1, result);
    }

}
