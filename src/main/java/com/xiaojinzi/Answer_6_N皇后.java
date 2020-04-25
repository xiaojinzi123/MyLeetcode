package com.xiaojinzi;


import com.xiaojinzi.support.Point;
import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Answer_6_N皇后 {

    private static void printAnswer(List<List<Point>> answers) {
        int answerCount = answers.size();
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < answerCount; n++) {
            // 获取到一组答案
            List<Point> answer = answers.get(n);
            // 答案的长度表示题目就是 N 皇后的
            int size = answer.size();
            sb.append(size).append("皇后的第")
                    .append(n + 1)
                    .append("种方案\n");
            for (int i = 1; i <= size; i++) {
                for (int j = 1; j <= size; j++) {
                    Point point = new Point(i, j);
                    // 如果这个点是答案中的一个点
                    if (answer.contains(point)) {
                        sb.append("X").append(" ").append(" ");
                    } else {
                        sb.append("*").append(" ").append(" ");
                    }
                }
                sb.append('\n');
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        List<List<Point>> answer1 = nQueen(4);
        printAnswer(answer1);
        Assert.assertSame(
                2,
                answer1.stream()
                        .distinct()
                        .collect(Collectors.toList())
                        .size());

        List<List<Point>> answer2 = nQueen(8);
        printAnswer(answer2);
        Assert.assertSame(
                92,
                answer2.stream()
                        .distinct()
                        .collect(Collectors.toList())
                        .size());
    }

    private static List<List<Point>> nQueen(int n) {
        List<List<Point>> result = new LinkedList<>();
        nQueenRecursion(n, 1, new LinkedList<>(), result);
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
        if (point1.getX() - point1.getY() == point2.getX() - point2.getY()) {
            return true;
        }
        if (point1.getX() + point1.getY() == point2.getX() + point2.getY()) {
            return true;
        }
        return false;
    }

}
