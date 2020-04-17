package com.xiaojinzi;

import com.xiaojinzi.support.SearchTreeList;
import com.xiaojinzi.support.TreeNode;

import java.util.List;

public class 二叉树的遍历 {

    public static void main(String[] args) {

        SearchTreeList<Integer> treeList = new SearchTreeList<>();

        treeList.add(5);
        treeList.add(2);
        treeList.add(7);
        treeList.add(1);
        treeList.add(3);

        treeList.inOrder();

        treeList.remove(new Integer(3));
        treeList.inOrder();
        treeList.remove(new Integer(2));
        treeList.inOrder();
        treeList.remove(new Integer(5));
        treeList.inOrder();
        treeList.remove(new Integer(7));
        treeList.inOrder();
        treeList.remove(new Integer(1));
        treeList.inOrder();


    }

}
