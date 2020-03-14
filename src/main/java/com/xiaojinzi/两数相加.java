package com.xiaojinzi;

import com.xiaojinzi.support.LinkedList;

/**
 * 本次解答中. 首先利用了自定义的 {@link LinkedList} 以供链表部分的功能.
 * 然后 {@link #sum1(LinkedList, LinkedList)} 是链表正向表示数字的相加算法
 * {@link #sum2(LinkedList, LinkedList)} 是链表反向表示数字的相加算法
 * 两种方式作者都给出了解答和测试.
 */
public class 两数相加 {

    public static void main(String[] args) {

        try {

            LinkedList linkedList1 = new LinkedList()
                    .add(1).add(4).add(3);

            LinkedList linkedList2 = new LinkedList()
                    .add(5).add(6).add(4);

            LinkedList resultLinkedList1 = sum1(linkedList1, linkedList2);

            System.out.println("143 + 464 = ");
            printNode(resultLinkedList1);

            LinkedList linkedList3 = new LinkedList()
                    .add(1).add(7);

            LinkedList linkedList4 = new LinkedList()
                    .add(5).add(6).add(4);

            LinkedList resultLinkedList2 = sum1(linkedList3, linkedList4);

            System.out.println("17 + 464 = ");
            printNode(resultLinkedList2);

            LinkedList linkedList5 = new LinkedList()
                    .add(1).add(4).add(3);

            LinkedList linkedList6 = new LinkedList()
                    .add(5).add(6).add(4);

            LinkedList resultLinkedList3 = sum2(linkedList5, linkedList6);

            System.out.println("反向表示的 143 + 564 = ");
            printNode(resultLinkedList3);

            LinkedList linkedList7 = new LinkedList()
                    .add(1).add(4);

            LinkedList linkedList8 = new LinkedList()
                    .add(5).add(6).add(4);

            LinkedList resultLinkedList4 = sum2(linkedList7, linkedList8);

            System.out.println("反向表示的 14 + 564 = ");
            printNode(resultLinkedList4);

        } catch (Exception ignore) {
            ignore.printStackTrace();
        }

    }

    /**
     * 此方法是链表正向表示数字的
     * 1 -> 4 -> 3
     * +
     * 5 -> 6 -> 4
     * =
     * 7 -> 0 -> 7
     * 因为 143 + 564 = 707
     * 1 -> 7
     * +
     * 5 -> 6 -> 4
     * =
     * 7 -> 0 -> 7
     * 因为 17 + 564 = 581
     */
    private static LinkedList<Integer> sum1(LinkedList<Integer> linkedList1, LinkedList<Integer> linkedList2) {

        int size1 = linkedList1.size();
        int size2 = linkedList2.size();
        int maxLength = Math.max(size1, size2);

        LinkedList<Integer> resultLinkedList = new LinkedList();
        resultLinkedList.add(0);

        for (int i = 0; i < maxLength; i++) {
            // 拿到第一个数字应该取的下标
            int index1 = size1 - maxLength + i;
            // 拿到第二个数字应该取的下标
            int index2 = size2 - maxLength + i;
            // 检查下标并且拿到下标对应的值, 下标越界就取 0
            int a = index1 >= size1 || index1 < 0 ? 0 : linkedList1.get(index1).getValue();
            int b = index2 >= size2 || index2 < 0 ? 0 : linkedList2.get(index2).getValue();
            // 数字中某位数的和
            int sum = a + b;
            // 进位处理
            if (sum >= 10) {
                sum -= 10;
                // 上一位进 1
                resultLinkedList.get(i).setValue(resultLinkedList.get(i).getValue() + 1);
            }
            // 计算好了
            resultLinkedList.add(i + 1, sum);
        }

        if (resultLinkedList.get(0).getValue().equals(0)) {
            resultLinkedList.remove(0);
        }

        return resultLinkedList;

    }

    /**
     * 此方法是链表反向表示数字的
     * 1 -> 4 -> 3
     * +
     * 5 -> 6 -> 4
     * =
     * 6 -> 0 -> 8
     * 因为 341 + 465 = 806
     * 1 -> 4
     * +
     * 5 -> 6 -> 4
     * =
     * 6 -> 0 -> 8
     * 因为 41 + 465 = 506
     */
    private static LinkedList<Integer> sum2(LinkedList<Integer> linkedList1, LinkedList<Integer> linkedList2) {

        int size1 = linkedList1.size();
        int size2 = linkedList2.size();
        int maxLength = Math.max(size1, size2);

        LinkedList<Integer> resultLinkedList = new LinkedList();
        // 下一位是否进1
        boolean isNextNumDecade = false;

        for (int i = 0; i < maxLength; i++) {
            // 检查下标并且拿到下标对应的值, 下标越界就取 0
            int a = i >= size1 || i < 0 ? 0 : linkedList1.get(i).getValue();
            int b = i >= size2 || i < 0 ? 0 : linkedList2.get(i).getValue();
            // 数字中某位数的和
            int sum = a + b;
            if (isNextNumDecade) {
                sum++;
                isNextNumDecade = false;
            }
            // 进位处理
            if (sum >= 10) {
                sum -= 10;
                isNextNumDecade = true;
            }
            // 计算好了
            resultLinkedList.add(sum);
        }

        if (resultLinkedList.get(resultLinkedList.size() - 1).getValue().equals(0)) {
            resultLinkedList.remove(resultLinkedList.size() - 1);
        }

        return resultLinkedList;
    }

    private static void printNode(LinkedList linkedList) {
        if (linkedList.isEmpty()) {
            return;
        }
        int size = linkedList.size();
        for (int i = 0; i < size; i++) {
            System.out.print(linkedList.get(i).getValue());
        }
        System.out.println();
    }

}
