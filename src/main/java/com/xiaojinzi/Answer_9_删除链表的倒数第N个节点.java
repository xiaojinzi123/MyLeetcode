package com.xiaojinzi;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 这题目的关键是只能使用一遍遍历
 */
public class Answer_9_删除链表的倒数第N个节点 {

    private static List<Integer> listNodeToList(ListNode node) {
        if (node == null) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        while (node != null) {
            result.add(node.val);
            node = node.next;
        }
        return result;
    }

    private static ListNode create(int n) {
        ListNode header = new ListNode();
        ListNode temp = header;
        for (int i = 0; i < n; i++) {
            temp.next = new ListNode(i + 1, null);
            temp = temp.next;
        }
        return header.next;
    }

    public static void main(String[] args) {
        ListNode head = create(5);
        head = removeNthFromEnd(head, 3);
        Assert.assertEquals(
                Arrays.asList(1, 2, 4, 5),
                listNodeToList(head)
        );

        head = create(5);
        Assert.assertEquals(
                Arrays.asList(2, 3, 4, 5),
                listNodeToList(removeNthFromEnd(head, 5))
        );

        head = create(1);
        Assert.assertEquals(
                Arrays.asList(),
                listNodeToList(removeNthFromEnd(head, 1))
        );

        head = create(2);
        Assert.assertEquals(
                Arrays.asList(1),
                listNodeToList(removeNthFromEnd(head, 1))
        );

        head = create(2);
        Assert.assertEquals(
                Arrays.asList(2),
                listNodeToList(removeNthFromEnd(head, 2))
        );

        head = create(2);
        try {
            removeNthFromEnd(head, 3);
            Assert.assertEquals(true, false);
        } catch (Exception ignore) {
            Assert.assertEquals(true, true);
        }

        head = create(1);
        try {
            removeNthFromEnd(head, 2);
            Assert.assertEquals(true, false);
        } catch (Exception ignore) {
            Assert.assertEquals(true, true);
        }

        head = create(1);
        try {
            removeNthFromEnd(head, 3);
            Assert.assertEquals(true, false);
        } catch (Exception ignore) {
            Assert.assertEquals(true, true);
        }

        head = create(3);
        try {
            removeNthFromEnd(head, 4);
            Assert.assertEquals(true, false);
        } catch (Exception ignore) {
            Assert.assertEquals(true, true);
        }

    }

    /**
     * 1 -> 2 -> 3 -> 4 -> 5
     * n = 3
     * <p>
     * 1
     * n = 1
     */
    private static ListNode removeNthFromEnd(ListNode head, int n) {
        if (n <= 0) {
            throw new RuntimeException(n + " 不符合规范");
        }
        // 额外创建一个 Header
        ListNode header = new ListNode();
        header.next = head;

        // 这里的声明的初始化是难点：为什么 targetDeleteNextNode = head 而不是 targetDeleteNextNode = header
        // 是因为当链表长度只有1的时候, 循环进行一次, 那么如果是 header 的话, 就不对了
        ListNode targetDeletePreNode = header, targetDeleteNextNode = head;

        // 初始化是链表的第一个节点
        ListNode temp = head;
        int count = -n;
        // 这个 while 循环的次数一定是列表的长度的值
        // 内部的两个 if 是为了控制两个变量往下走的次数. 完成倒数第 N 个的需求.
        // 因为我们没法提前知道链表长度, 所以只能通过下面的 if 的方式
        while (temp != null) {
            count++;
            if (count >= 1) {
                if (targetDeletePreNode != null) {
                    targetDeletePreNode = targetDeletePreNode.next;
                }
            }
            if (count >= 0) {
                if (targetDeleteNextNode != null) {
                    targetDeleteNextNode = targetDeleteNextNode.next;
                }
            }
            temp = temp.next;
        }
        if (count < 0) {
            throw new RuntimeException(n + " 不符合规范");
        }
        targetDeletePreNode.next = targetDeleteNextNode;
        return header.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

    }

}
