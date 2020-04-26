package com.xiaojinzi;

import com.xiaojinzi.support.LinkedList;
import com.xiaojinzi.support.annotation.Nullable;
import org.junit.Assert;

import java.util.Arrays;

public class Answer_10_合并两个有序链表 {

    public static void main(String[] args) {

        LinkedList<Integer> list1 = new LinkedList();
        LinkedList<Integer> list2 = new LinkedList();
        list1.add(1).add(2).add(4);
        list2.add(1).add(3).add(4);
        LinkedList<Integer> result1 = mergeList(list1, list2);
        Assert.assertEquals(
                Arrays.asList(1, 1, 2, 3, 4, 4),
                result1.toList()
        );

        LinkedList<Integer> list3 = new LinkedList();
        LinkedList<Integer> list4 = new LinkedList();
        list3.add(1);
        LinkedList<Integer> result2 = mergeList(list3, list4);
        Assert.assertEquals(
                Arrays.asList(1),
                result2.toList()
        );

        LinkedList<Integer> list5 = new LinkedList();
        LinkedList<Integer> list6 = new LinkedList();
        LinkedList<Integer> result3 = mergeList(list5, list6);
        Assert.assertEquals(
                Arrays.asList(),
                result3.toList()
        );

    }

    private static LinkedList<Integer> mergeList(@Nullable LinkedList<Integer> list1,
                                                 @Nullable LinkedList<Integer> list2) {
        LinkedList<Integer> result = new LinkedList<>();
        int index1 = 0, index2 = 0;
        while (index1 < list1.size() || index2 < list2.size()) {
            if (list1.size() <= index1) {
                result.add(list2.get(index2).getValue());
                index2++;
            } else if (list2.size() <= index2) {
                result.add(list1.get(index1).getValue());
                index1++;
            } else {
                if (list1.get(index1).getValue() <= list2.get(index2).getValue()) {
                    result.add(list1.get(index1).getValue());
                    index1++;
                } else {
                    result.add(list2.get(index2).getValue());
                    index2++;
                }
            }
        }
        return result;
    }

}
