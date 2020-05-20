package com.xiaojinzi.support.inter;

import com.xiaojinzi.support.BinaryTreeNode;
import com.xiaojinzi.support.annotation.Nullable;

/**
 * 二叉树的一些定义
 */
public interface BTree<T> extends Tree<T> {

    /**
     * 获取头
     */
    @Nullable
    BinaryTreeNode getHeader();

}
