package com.xiaojinzi;

import com.xiaojinzi.support.BinaryTree;
import com.xiaojinzi.support.BinaryTreeNode;
import com.xiaojinzi.support.annotation.Nullable;
import org.junit.Assert;

import java.util.Stack;

public class Answer_31_二叉搜索树的验证 {

    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree1 = new BinaryTree<>(
                new Integer[]{
                        5, 1, 4, null, null, 3, 6
                }
        );
        BinaryTree<Integer> binaryTree2 = new BinaryTree<>(
                new Integer[]{}
        );
        Assert.assertEquals(false, isValidBST(binaryTree1.getHeader()));
        Assert.assertEquals(true, isValidBST(binaryTree2.getHeader()));
    }

    public static boolean isValidBST(@Nullable BinaryTreeNode root) {
        boolean b1 = isValidBSTRecursion(root, null, null);
        boolean b2 = isValidBSTRecursion(root);
        if (b1 != b2) {
            throw new IllegalArgumentException("b1 should be equal to b2");
        }
        return b1;
    }

    public static boolean isValidBSTRecursion(@Nullable BinaryTreeNode<Integer> root, Integer lower, Integer upper) {
        if (root == null) {
            return true;
        }
        if (lower != null && root.getValue() <= lower) return false;
        if (upper != null && root.getValue() >= upper) return false;
        return isValidBSTRecursion(root.getLeft(), lower, root.getValue()) &&
                isValidBSTRecursion(root.getRight(), root.getValue(), upper);
    }

    public static boolean isValidBSTRecursion(@Nullable BinaryTreeNode<Integer> root) {
        if (root == null) {
            return true;
        }
        // 栈只会
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        Integer lowerValue = null;
        BinaryTreeNode<Integer> tempNode = root;
        while (!stack.isEmpty() || tempNode != null) {
            while (tempNode != null) {
                stack.push(tempNode);
                tempNode = tempNode.getLeft();
            }
            BinaryTreeNode<Integer> node = stack.pop();
            if (lowerValue != null && lowerValue >= node.getValue()) {
                return false;
            }
            // 较小值覆盖一下
            lowerValue = node.getValue();
            tempNode = node.getRight();
        }
        return true;
    }

}
