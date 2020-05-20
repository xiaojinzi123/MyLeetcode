package com.xiaojinzi.support;

import com.xiaojinzi.support.annotation.NonNull;
import com.xiaojinzi.support.annotation.Nullable;
import com.xiaojinzi.support.inter.BTree;
import org.junit.Assert;

import java.util.*;
import java.util.LinkedList;

/**
 * @param <T>
 */
public class BinaryTree<T> implements BTree<T> {

    public static void main(String[] args) {

        BinaryTree<Integer> binaryTree1 = new BinaryTree<>();
        Assert.assertEquals(0, binaryTree1.getSize());
        Assert.assertEquals(Collections.emptyList(), binaryTree1.preOrderRecursion());
        Assert.assertEquals(Collections.emptyList(), binaryTree1.preOrderLoop());
        Assert.assertEquals(Collections.emptyList(), binaryTree1.inOrderRecursion());
        Assert.assertEquals(Collections.emptyList(), binaryTree1.inOrderLoop());
        Assert.assertEquals(Collections.emptyList(), binaryTree1.postOrderRecursion());
        Assert.assertEquals(Collections.emptyList(), binaryTree1.postOrderLoop());

        BinaryTree<Integer> binaryTree2 = new BinaryTree<>(
                new Integer[]{
                        5,
                        1, 4,
                        null, null, 3, 6,
                }
        );
        Assert.assertEquals(5, binaryTree2.getSize());
        Assert.assertEquals(Arrays.asList(5, 1, 4, 3, 6), binaryTree2.preOrderRecursion());
        Assert.assertEquals(Arrays.asList(5, 1, 4, 3, 6), binaryTree2.preOrderLoop());
        Assert.assertEquals(Arrays.asList(1, 5, 3, 4, 6), binaryTree2.inOrderRecursion());
        Assert.assertEquals(Arrays.asList(1, 5, 3, 4, 6), binaryTree2.inOrderLoop());
        Assert.assertEquals(Arrays.asList(1, 3, 6, 4, 5), binaryTree2.postOrderRecursion());
        Assert.assertEquals(Arrays.asList(1, 3, 6, 4, 5), binaryTree2.postOrderLoop());

        BinaryTree<Integer> binaryTree3 = new BinaryTree<>(
                new Integer[]{
                        5,
                        1, 4,
                        null, null, 3, 6,
                        null, null, null, null, 2, null, null, 5
                }
        );
        Assert.assertEquals(7, binaryTree3.getSize());
        Assert.assertEquals(Arrays.asList(5, 1, 4, 3, 2, 6, 5), binaryTree3.preOrderRecursion());
        Assert.assertEquals(Arrays.asList(5, 1, 4, 3, 2, 6, 5), binaryTree3.preOrderLoop());
        Assert.assertEquals(Arrays.asList(1, 5, 2, 3, 4, 6, 5), binaryTree3.inOrderRecursion());
        Assert.assertEquals(Arrays.asList(1, 5, 2, 3, 4, 6, 5), binaryTree3.inOrderLoop());
        Assert.assertEquals(Arrays.asList(1, 2, 3, 5, 6, 4, 5), binaryTree3.postOrderRecursion());
        Assert.assertEquals(Arrays.asList(1, 2, 3, 5, 6, 4, 5), binaryTree3.postOrderLoop());

        try {
            new BinaryTree<>(
                    new Integer[]{
                            5,
                            1, 4,
                            null, null, 3, 6,
                            4, null, null, null, 2, null, null, 5
                    }
            );
            Assert.assertEquals(false, true);
        } catch (Exception e) {
            Assert.assertEquals(true, true);
        }

        try {
            new BinaryTree<>(
                    new Integer[]{
                            5,
                            1, 4,
                            null, null, 3, 6,
                            null, 4, null, null, 2, null, null, 5
                    }
            );
            Assert.assertEquals(false, true);
        } catch (Exception e) {
            Assert.assertEquals(true, true);
        }

    }

    /**
     * 真正的头
     */
    private BinaryTreeNode<T> mHeader;

    private int mSize = 0;

    public BinaryTree(T... arr) {

        if (arr == null || arr.length == 0) {
            mSize = 0;
            return;
        }

        mHeader = new BinaryTreeNode<>(arr[0]);
        int count = 1;
        Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
        queue.offer(mHeader);

        int length = arr.length;
        // 获取当前层的个数, 如果是 N 个, 表示可以处理 2N 的数据
        int size = queue.size();
        int index = 1;
        while (index < length) {
            for (int q = 0; q < size && index < length; q++) {
                BinaryTreeNode<T> node = queue.poll();
                for (int n = 0; n < 2 && index < length; n++) {
                    // 这是一种非法的
                    if (node == null && arr[index] != null) {
                        throw new IllegalArgumentException("value " + arr[index] + " is illegal, it must be null, it's index is " + index);
                    }
                    if (node == null || arr[index] == null) {
                        queue.offer(null);
                    } else {
                        BinaryTreeNode<T> newNode = new BinaryTreeNode<>(arr[index]);
                        if (n == 0) {
                            node.setLeft(newNode);
                        } else {
                            node.setRight(newNode);
                        }
                        queue.offer(newNode);
                        count++;
                    }
                    index++;
                }
            }
        }
        mSize = count;
    }

    @Nullable
    public BinaryTreeNode<T> getHeader() {
        return mHeader;
    }


    @Override
    public int getSize() {
        return mSize;
    }

    @Override
    public List<T> preOrderRecursion() {
        if (mHeader == null) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>();
        preOrderRecursion(mHeader, list);
        return list;
    }

    private void preOrderRecursion(@Nullable BinaryTreeNode<T> node,
                                   @NonNull List<T> list) {
        if (node != null) {
            list.add(node.getValue());
            preOrderRecursion(node.getLeft(), list);
            preOrderRecursion(node.getRight(), list);
        }
    }

    @Override
    public List<T> preOrderLoop() {
        if (mHeader == null) {
            return Collections.emptyList();
        }
        // 声明结果
        List<T> list = new ArrayList<>();
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> tempNode = mHeader;
        while (!stack.isEmpty() || tempNode != null) {
            while (tempNode != null) {
                list.add(tempNode.getValue());
                stack.push(tempNode);
                tempNode = tempNode.getLeft();
            }
            BinaryTreeNode<T> node = stack.pop();
            tempNode = node.getRight();
        }
        return list;
    }

    @Override
    public List<T> inOrderRecursion() {
        if (mHeader == null) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>();
        inOrderRecursion(mHeader, list);
        return list;
    }

    private void inOrderRecursion(@Nullable BinaryTreeNode<T> node,
                                  @NonNull List<T> list) {
        if (node != null) {
            inOrderRecursion(node.getLeft(), list);
            list.add(node.getValue());
            inOrderRecursion(node.getRight(), list);
        }
    }

    @Override
    public List<T> inOrderLoop() {
        if (mHeader == null) {
            return Collections.emptyList();
        }
        // 声明结果
        List<T> list = new ArrayList<>();
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> tempNode = mHeader;
        while (!stack.isEmpty() || tempNode != null) {
            while (tempNode != null) {
                stack.push(tempNode);
                tempNode = tempNode.getLeft();
            }
            BinaryTreeNode<T> node = stack.pop();
            list.add(node.getValue());
            tempNode = node.getRight();
        }
        return list;
    }

    @Override
    public List<T> postOrderRecursion() {
        if (mHeader == null) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>();
        postOrderRecursion(mHeader, list);
        return list;
    }

    private void postOrderRecursion(@Nullable BinaryTreeNode<T> node,
                                    @NonNull List<T> list) {
        if (node != null) {
            postOrderRecursion(node.getLeft(), list);
            postOrderRecursion(node.getRight(), list);
            list.add(node.getValue());
        }
    }

    @Override
    public List<T> postOrderLoop() {
        if (mHeader == null) {
            return Collections.emptyList();
        }
        // 声明结果
        List<T> list = new ArrayList<>();
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> tempNode = mHeader;
        while (!stack.isEmpty() || tempNode != null) {
            while (tempNode != null) {
                stack.push(tempNode);
                list.add(tempNode.getValue());
                tempNode = tempNode.getRight();
            }
            BinaryTreeNode<T> node = stack.pop();
            tempNode = node.getLeft();
        }
        Collections.reverse(list);
        return list;
    }
}
