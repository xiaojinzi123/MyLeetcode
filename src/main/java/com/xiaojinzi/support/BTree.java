package com.xiaojinzi.support;

import java.util.*;
import java.util.LinkedList;

/**
 * 失败的
 *
 * @param <T>
 */
public class BTree<T> {

    /**
     * 真正的头
     */
    private TreeNode<T> header;

    public BTree(T... arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int layer = (int) Math.ceil(Math.log(arr.length) / Math.log(2));
        int count = (int) (Math.pow(2, layer) - 1);
        List<T> list = new ArrayList<>();
        for (T t : arr) {
            list.add(t);
        }
        int num = count - list.size();
        for (int i = 0; i < num; i++) {
            list.add(null);
        }
        header = new TreeNode(list.get(0));
        int targetIndex = 1;
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(header);
        while (!queue.isEmpty() && targetIndex < list.size()) {
            // 广度优先当前层的节点个数
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode<T> node = queue.poll();
                node.setLeft(new TreeNode<>(list.get(targetIndex)));
                targetIndex++;
                node.setRight(new TreeNode<>(list.get(targetIndex)));
                targetIndex++;
                queue.add(node.getLeft());
                queue.add(node.getRight());
            }
        }
    }

    public TreeNode getHeader() {
        return header;
    }

}
