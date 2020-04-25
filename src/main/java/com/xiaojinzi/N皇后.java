package com.xiaojinzi;


import com.xiaojinzi.support.Point;
import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;

public class N皇后 {

    public static void main(String[] args) {
        Assert.assertSame(2, nQueen(4).size());
        Assert.assertSame(92, nQueen(8).size());
    }

    private static List<List<Point>> nQueen(int n) {
        List<List<Point>> result = new LinkedList<>();
        nQueenRecursion(n,1, new LinkedList<>(), result);
        return result;
    }

    private static void nQueenRecursion(int level, int currLevel, List<Point> chooseList, List<List<Point>> result) {
        if (currLevel > level) {
            // 需要 new 一个把元素都放进来
            result.add(new LinkedList<>(chooseList));
            return;
        }
        for (int i = 1; i <= level; i++) {
            Point point = new Point(i, currLevel);
            boolean isAttacted = false;
            // 判断当前 point 是否和已经选择的有冲突
            for (Point chooseItemPoint : chooseList) {
                if (isAttacted(chooseItemPoint, point)) {
                    isAttacted = true;
                    break;
                }
            }
            if (!isAttacted) {
                // 添加选择
                chooseList.add(point);
                nQueenRecursion(level, currLevel + 1, chooseList, result);
                // 移除选择
                chooseList.remove(chooseList.size() - 1);
            }
        }
    }

    private static boolean isAttacted(Point point1, Point point2) {
        if (point1.getX() == point2.getX()) {
            return true;
        }
        if (point1.getY() == point2.getY()) {
            return true;
        }
        if(point1.getX() - point1.getY() == point2.getX() - point2.getY()){
            return true;
        }
        if(point1.getX() + point1.getY() == point2.getX() + point2.getY()){
            return true;
        }
        return false;
    }

}
