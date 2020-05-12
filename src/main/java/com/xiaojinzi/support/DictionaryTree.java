package com.xiaojinzi.support;

import com.xiaojinzi.support.annotation.NonNull;
import org.junit.Assert;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 字典树(Trie)
 * 1. 带有词频
 */
public class DictionaryTree {

    public static void main(String[] args) {
        DictionaryTree dictionaryTree = new DictionaryTree();

        Assert.assertEquals(true, dictionaryTree.add("apple", 1));
        Assert.assertEquals(false, dictionaryTree.add("apple"));

        // 添加一些数据
        Assert.assertEquals(true, dictionaryTree.add("banana", 2));
        Assert.assertEquals(true, dictionaryTree.add("tree", 3));
        Assert.assertEquals(true, dictionaryTree.add("cat", 4));
        Assert.assertEquals(true, dictionaryTree.add("dog", 5));

        Assert.assertEquals(false, dictionaryTree.search("app"));
        Assert.assertEquals(true, dictionaryTree.startWith("app"));
        Assert.assertEquals(true, dictionaryTree.add("app", 6));
        Assert.assertEquals(true, dictionaryTree.search("app"));

        Assert.assertEquals(false, dictionaryTree.search("bana"));
        Assert.assertEquals(true, dictionaryTree.search("banana"));
        Assert.assertEquals(true, dictionaryTree.search("tree"));

        Assert.assertEquals(true, dictionaryTree.startWith("bana"));
        Assert.assertEquals(true, dictionaryTree.startWith("app"));

    }

    @NonNull
    private TreeNode header = new TreeNode(' ');

    public boolean insert(@NonNull String str, int value) {
        return add(str, value);
    }

    public boolean add(@NonNull String str) {
        return add(str, 0);
    }

    public boolean add(@NonNull String str, int wordFrequency) {
        Utils.checkStringEmpty(str);
        int length = str.length();
        TreeNode node = header;
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (node.nextLinks.containsKey(c)) {
                node = node.nextLinks.get(c);
            } else {
                TreeNode nextNode = new TreeNode(c);
                node.nextLinks.put(c, nextNode);
                node = nextNode;
            }
            if (i == length - 1 && node.isEnd) { // 如果最后一个字符都是存在的就返回 false
                return false;
            }
        }
        node.wordFrequency = wordFrequency;
        node.isEnd = true;
        return true;
    }

    public boolean startWith(@NonNull String prefix) {
        Utils.checkStringEmpty(prefix);
        int length = prefix.length();
        TreeNode node = header;
        for (int i = 0; i < length; i++) {
            char c = prefix.charAt(i);
            if (!node.nextLinks.containsKey(c)) {
                return false;
            }
            node = node.nextLinks.get(c);
        }
        return true;
    }

    public boolean search(@NonNull String str) {
        Utils.checkStringEmpty(str);
        int length = str.length();
        TreeNode node = header;
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (!node.nextLinks.containsKey(c)) {
                return false;
            }
            node = node.nextLinks.get(c);
        }
        return node.isEnd;
    }

    /**
     * 获取指定前缀的top
     */
    public List<String> getTop(@NonNull String prefix, int count) {
        return null;
    }

    public static class TreeNode {

        /**
         * 词频
         */
        private int wordFrequency;

        private char value;

        private Map<Character, TreeNode> nextLinks = new HashMap();

        /**
         * 标记当前节点为止是否是一个 key, 若不是, 则只是某一个单词的前缀的
         */
        private boolean isEnd;

        public TreeNode(char value) {
            this.value = value;
        }

        public TreeNode(int wordFrequency, char value, Map<Character, TreeNode> nextLinks, boolean isEnd) {
            this.wordFrequency = wordFrequency;
            this.value = value;
            this.nextLinks = nextLinks;
            this.isEnd = isEnd;
        }

    }

}
