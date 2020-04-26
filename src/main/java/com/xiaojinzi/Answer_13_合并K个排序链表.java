package com.xiaojinzi;

import com.xiaojinzi.support.LinkedList;
import com.xiaojinzi.support.Node;
import org.junit.Assert;

import java.util.Arrays;

public class Answer_13_合并K个排序链表 {

    public static void main(String[] args) {

        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        LinkedList<Integer> list3 = new LinkedList<>();
        list1.add(1).add(4).add(5);
        list2.add(1).add(3).add(4);
        list3.add(2).add(6);

        Node<Integer>[] arr = new Node[]{
                list1.getHeader(), list2.getHeader(), list3.getHeader()
        };

        Assert.assertEquals(
                Arrays.asList(1, 1, 2, 3, 4, 4, 5, 6),
                new LinkedList<>(merge1(arr)).toList()
        );

    }

    /**
     * 普通的方式
     */
    private static Node<Integer> merge1(Node<Integer>[] nodes) {
        // 声明返回值
        Node<Integer> header = new Node<>();
        boolean b = false;
        for (Node<Integer> node : nodes) {
            if (node != null) {
                b = true;
                break;
            }
        }
        Node<Integer> tempNode = header;
        while (b) {
            int targetIndex = -1;
            Integer tempMin = null;
            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i] != null) {
                    if (tempMin == null) {
                        tempMin = nodes[i].getValue();
                        targetIndex = i;
                    } else {
                        if (nodes[i].getValue() < tempMin) {
                            tempMin = nodes[i].getValue();
                            targetIndex = i;
                        }
                    }
                }
            }
            tempNode = tempNode.newNext(nodes[targetIndex].getValue());
            nodes[targetIndex] = nodes[targetIndex].getNext();
            b = false;
            for (Node<Integer> node : nodes) {
                if (node != null) {
                    b = true;
                    break;
                }
            }
        }
        return header.getNext();
    }


}
