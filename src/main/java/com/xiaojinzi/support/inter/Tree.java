package com.xiaojinzi.support.inter;

import com.xiaojinzi.support.annotation.NonNull;

import java.util.List;

/**
 * 表示一棵树, 树的一些方法定义
 */
public interface Tree<T> {

    /**
     * 获取个数
     */
    int getSize();

    /**
     * 前序遍历, 递归的方式
     *
     * @return 返回前序遍历的值的集合
     */
    @NonNull
    List<T> preOrderRecursion();

    /**
     * 前序遍历, 循环的方式
     *
     * @return 返回前序遍历的值的集合
     */
    @NonNull
    List<T> preOrderLoop();

    /**
     * 中序遍历, 递归的方式
     *
     * @return 返回中序遍历的值的集合
     */
    @NonNull
    List<T> inOrderRecursion();

    /**
     * 中序遍历, 循环的方式
     *
     * @return 返回中序遍历的值的集合
     */
    @NonNull
    List<T> inOrderLoop();

    /**
     * 后序遍历, 递归的方式
     *
     * @return 返回后序遍历的值的集合
     */
    @NonNull
    List<T> postOrderRecursion();

    /**
     * 后序遍历, 循环的方式
     *
     * @return 返回后序遍历的值的集合
     */
    @NonNull
    List<T> postOrderLoop();

}
