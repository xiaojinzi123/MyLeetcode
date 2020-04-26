package com.xiaojinzi;

import org.junit.Assert;

public class Answer_12_字符串相乘 {

    private static void checkAdd() {
        Assert.assertEquals(
                "105",
                add("15", "90")
        );
        Assert.assertEquals(
                "200",
                add("77", "123")
        );
        Assert.assertEquals(
                "444",
                add("321", "123")
        );
        Assert.assertEquals(
                "0",
                add("0", "0")
        );
        Assert.assertEquals(
                "14",
                add("0", "14")
        );
        Assert.assertEquals(
                "14",
                add("14", "0")
        );
    }

    public static void main(String[] args) {
        checkAdd();
        String multiply = multiply2("39", "103");
        System.out.println("multiply = " + multiply);
    }

    public static String multiply1(String num1, String num2) {
        if (num1.length() == 1 && num1.charAt(0) == '0') {
            return "0";
        }
        if (num2.length() == 1 && num2.charAt(0) == '0') {
            return "0";
        }
        String tempStr1 = num1, tempStr2 = null;
        if (num1.length() > 1) {
            tempStr1 = num1;
            tempStr2 = num2;
        } else if (num2.length() > 1) {
            tempStr1 = num2;
            tempStr2 = num1;
        } else {
            return String.valueOf((num1.charAt(0) - 48) * (num2.charAt(0) - 48));
        }
        int length = tempStr1.length();
        String result = "0";
        StringBuilder sb = new StringBuilder();
        for (int i = length - 1; i >= 0; i--) {
            int num = tempStr1.charAt(i) - 48;
            sb.delete(0, sb.length());
            sb.append(multiply1(String.valueOf(num), tempStr2));
            int tenSize = length - i - 1;
            for (int j = 0; j < tenSize; j++) {
                sb.append(0);
            }
            result = add(result, sb.toString());
        }
        return result;
    }

    /**
     *    103
     *     39
     * -------
     *     27
     *     0
     *    9
     *     9
     *    0
     *   3
     */
    private static String multiply2(String num1, String num2) {
        int length1 = num1.length();
        int length2 = num2.length();
        // 模拟乘法, N位 * M位一定不会超过 (N + M)位
        int[] arr = new int[length1 + length2];
        for (int i = length1 - 1; i >= 0; i--) {
            for (int j = length2 - 1; j >= 0; j--) {
                int sum = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                sum += arr[i + j + 1];
                if (sum > 9) {
                    arr[i + j] += sum / 10;
                }
                arr[i + j + 1] = sum % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (sb.length() != 0 || arr[i] != 0) {
                sb.append(arr[i]);
            }
        }
        if(sb.length() == 0) {
            return "0";
        } else {
            return sb.toString();
        }
    }

    private static String add(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int length = Math.max(num1.length(), num2.length());
        int decade = 0, tempSum = 0;
        int index1 = 0, index2 = 0;
        for (int i = length - 1; i >= 0; i--) {
            index1 = i - (length - num1.length());
            index2 = i - (length - num2.length());
            tempSum = decade +
                    ((index1 < 0) ? 0 : num1.charAt(index1) - 48) +
                    ((index2 < 0) ? 0 : num2.charAt(index2) - 48);
            decade = 0;
            if (tempSum > 9) {
                decade = 1;
                tempSum -= 10;
            }
            sb.append(tempSum);
        }
        if (decade == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }

}
