package com.xiaojinzi;

import org.junit.Assert;

import java.util.*;

public class Answer_36_字母异位词分组 {

    public static void main(String[] args) {
        String[] arr1 = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        Assert.assertEquals(
                Arrays.asList(
                        Arrays.asList(new String[]{"eat", "tea", "ate"}),
                        Arrays.asList(new String[]{"tan", "nat"}),
                        Arrays.asList(new String[]{"bat"})
                ),
                groupAnagrams(arr1)
        );
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return Collections.emptyList();
        }
        Map<String, List<String>> result = new LinkedHashMap();
        // 单词的个数
        int length = strs.length;
        int[] cache = new int[26];
        for (int i = 0; i < length; i++) {
            // 每个单词
            String word = strs[i];
            String key, value;
            if (word == null || word.isEmpty()) {
                key = "";
                value = "";
            } else {
                Arrays.fill(cache, 0);
                for (int j = 0; j < word.length(); j++) {
                    cache[word.charAt(j) - 'a']++;
                }
                StringBuilder sb = new StringBuilder();
                for (int n = 0; n < 26; n++) {
                    sb.append("&").append(n).append(cache[n]);
                }
                key = sb.toString();
                value = word;
            }
            List<String> mapValue = null;
            if (result.containsKey(key)) {
                mapValue = result.get(key);
            } else {
                mapValue = new ArrayList();
                result.put(key, mapValue);
            }
            mapValue.add(value);
        }
        return new ArrayList<>(result.values());
    }

}
