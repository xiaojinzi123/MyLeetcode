package com.xiaojinzi;

import java.util.*;

public class 括号生成 {

    public static void main(String[] args) {
        Set<String> set = generateBracket(3);
        System.out.println("set = \n" + set);
    }

    private static Set<String> generateBracket(int n) {
        if (n == 1) {
            return new HashSet<>(Arrays.asList("()"));
        } else {
            Set<String> result = new HashSet<>();
            Set<String> set = generateBracket(n - 1);
            for (String item : set) {
                result.add("(" + item + ")");
                result.add("()" + item);
                result.add(item + "()");
            }
            return result;
        }
    }

}
