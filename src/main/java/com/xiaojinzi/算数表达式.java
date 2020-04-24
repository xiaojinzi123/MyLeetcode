package com.xiaojinzi;

import java.util.*;

public class 算数表达式 {

    public static void main(String[] args) {

        String str1 = "(10 - 2 * (6 - 3))";
        System.out.println(str1 + " 的后缀表达式为：" + subfixExpression(str1));
        System.out.println(str1 + " 递归的计算结果为：" + recursion(str1));
        System.out.println(str1 + " 后缀表达式的计算结果为：" + subfixExpressionCalculate(str1));
        System.out.println();

        String str2 = "(15 - 2^2^2)";
        System.out.println(str2 + " 的后缀表达式为：" + subfixExpression(str2));
        System.out.println(str2 + " 递归的计算结果为：" + recursion(str2));
        System.out.println(str2 + " 后缀表达式的计算结果为：" + subfixExpressionCalculate(str2));
        System.out.println();

        String str3 = "10 - 2 * 6 + 3";
        System.out.println(str3 + " 的后缀表达式为：" + subfixExpression(str3));
        System.out.println(str3 + " 递归的计算结果为：" + recursion(str3));
        System.out.println(str3 + " 后缀表达式的计算结果为：" + subfixExpressionCalculate(str3));
        System.out.println();

        String str4 = "10 - 2 - 6 + 3";
        System.out.println(str4 + " 的后缀表达式为：" + subfixExpression(str4));
        System.out.println(str4 + " 递归的计算结果为：" + recursion(str4));
        System.out.println(str4 + " 后缀表达式的计算结果为：" + subfixExpressionCalculate(str4));
        System.out.println();

        String str5 = "40 - (10 * 2 - 6) + 3";
        System.out.println(str5 + " 的后缀表达式为：" + subfixExpression(str5));
        System.out.println(str5 + " 递归的计算结果为：" + recursion(str5));
        System.out.println(str5 + " 后缀表达式的计算结果为：" + subfixExpressionCalculate(str5));
        System.out.println();

    }

    /**
     * 目前能处理的符号 + - * / () ^
     * 递归处理
     */
    private static double recursion(String str) {
        int index = -1;
        // 找到最后一个左括号
        index = str.lastIndexOf("(");
        if (index > -1) {
            // 从左括号开始找右括号
            int endIndex = str.indexOf(")", index + 1);
            if (index > -1) {
                String targetStr = str.substring(0, index) + // 左括号左边的部分
                        // 括号中间的部分, 递归处理
                        recursion(str.substring(index + 1, endIndex)) +
                        // 右括号右边的
                        str.substring(endIndex + 1);
                return recursion(targetStr);
            } else {
                throw new RuntimeException("表达式 " + str + " 不正确");
            }
        }
        // +-*/ 之前要处理 ()
        index = str.lastIndexOf("+");
        if (index > -1) {
            return recursion(str.substring(0, index)) + recursion(str.substring(index + 1));
        }
        index = str.lastIndexOf("-");
        if (index > -1) {
            String substring = str.substring(0, index);
            if (substring.isEmpty()) {
                return -recursion(str.substring(index + 1));
            } else {
                return recursion(substring) - recursion(str.substring(index + 1));
            }
        }
        index = str.lastIndexOf("*");
        if (index > -1) {
            return recursion(str.substring(0, index)) * recursion(str.substring(index + 1));
        }
        index = str.lastIndexOf("/");
        if (index > -1) {
            return recursion(str.substring(0, index)) / recursion(str.substring(index + 1));
        }
        index = str.lastIndexOf("^");
        if (index > -1) {
            return Math.pow(recursion(str.substring(0, index)), recursion(str.substring(index + 1)));
        }
        return Double.parseDouble(str);
    }

    private static double subfixExpressionCalculate(String str) {
        // 拿到后缀表达式
        List<String> subfixExpression = subfixExpression(str);
        Stack<String> stack = new Stack<>();
        int size = subfixExpression.size();
        for (int i = 0; i < size; i++) {
            String item = subfixExpression.get(i);
            if ("+".equals(item)) {
                double num1 = Double.parseDouble(stack.pop());
                double num2 = Double.parseDouble(stack.pop());
                stack.push(String.valueOf(num2 + num1));
            } else if ("-".equals(item)) {
                double num1 = Double.parseDouble(stack.pop());
                double num2 = Double.parseDouble(stack.pop());
                stack.push(String.valueOf(num2 - num1));
            } else if ("*".equals(item)) {
                double num1 = Double.parseDouble(stack.pop());
                double num2 = Double.parseDouble(stack.pop());
                stack.push(String.valueOf(num2 * num1));
            } else if ("/".equals(item)) {
                double num1 = Double.parseDouble(stack.pop());
                double num2 = Double.parseDouble(stack.pop());
                stack.push(String.valueOf(num2 / num1));
            } else if ("^".equals(item)) {
                double num1 = Double.parseDouble(stack.pop());
                double num2 = Double.parseDouble(stack.pop());
                stack.push(String.valueOf(Math.pow(num2, num1)));
            } else {
                stack.push(item);
            }
        }
        return Double.parseDouble(stack.pop());
    }

    /**
     * 平常我们使用的是中缀表达式. 转化为后缀表达式
     * 10 - 2 * 6 + 3
     * 10, 2, 6, *, -, 3, +
     * <p>
     * 10 - 2 - 6 + 3
     * 10, 2, -, 6, -, 3, +
     * <p>
     * 40 - (10 * 2 - 6) + 3
     * 40 10 2 * 6 - - 3 +
     */
    private static List<String> subfixExpression(String str) {
        Map<Character, Integer> priority = getPriority();
        List<String> result = new ArrayList<>();
        StringBuilder numbers = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ' ') { // 去掉
                continue;
            }
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') { // 符号一直到 ( 之前的全部出栈
                // 添加每一个数字, 可能多个 char 组成
                if (numbers.length() > 0) {
                    result.add(numbers.toString());
                    numbers.delete(0, numbers.length());
                }
                while (stack.peek() != '(') {
                    result.add(String.valueOf(stack.pop()));
                }
                // ( 出栈
                stack.pop();
            } else if (c == '^' || c == '+' || c == '-' ||
                    c == '*' || c == '/') { // 如果是符号
                // 添加每一个数字, 可能多个 char 组成
                if (numbers.length() > 0) {
                    result.add(numbers.toString());
                    numbers.delete(0, numbers.length());
                }
                // 如果不为空或者优先级不大于顶层的,
                while (!stack.isEmpty() && stack.peek() != '(' &&
                        priority.get(c) <= priority.get(stack.peek())) {
                    result.add(String.valueOf(stack.pop()));
                }
                stack.push(c);
            } else {
                numbers.append(c);
            }
        }
        if (numbers.length() > 0) {
            result.add(numbers.toString());
        }
        while (!stack.isEmpty()) {
            result.add(String.valueOf(stack.pop()));
        }
        return result;
    }

    private static Map<Character, Integer> getPriority() {
        Map<Character, Integer> priorities = new HashMap<>();
        priorities.put('+', 1);
        priorities.put('-', 2);
        priorities.put('*', 3);
        priorities.put('/', 4);
        priorities.put('^', 5);
        return priorities;
    }

}
