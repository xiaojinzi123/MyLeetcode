package com.xiaojinzi;

import com.xiaojinzi.support.SearchTreeList;

public class 二叉树的遍历 {

    public static void main(String[] args) {

        SearchTreeList<Integer> treeList = new SearchTreeList<>();

        treeList.add(5);
        treeList.add(2);
        treeList.add(7);
        treeList.add(1);
        treeList.add(3);

        // treeList.inOrder();

        treeList.depthFirst();
        // treeList.breadthFirst();
        System.out.println();

        // treeList.remove(1);
        // treeList.remove(1);

        // treeList.inOrder();

    }

}
