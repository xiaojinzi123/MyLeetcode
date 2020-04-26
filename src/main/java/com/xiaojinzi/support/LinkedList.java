package com.xiaojinzi.support;

import com.xiaojinzi.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LinkedList<T> {

    /**
     * 对这个类进行全部的测试
     */
    public static void main(String[] args) {

    }

    private int mSize;

    private Node header;

    public LinkedList() {
    }

    public LinkedList(@Nullable Node header) {
        this.header = header;
        int size = 0;
        Node<T> temp = header;
        while (temp != null) {
            size++;
            temp = temp.getNext();
        }
        this.mSize = size;
    }

    @Nullable
    public Node getHeader() {
        return header;
    }

    public boolean isEmpty() {
        return mSize == 0;
    }

    public int size() {
        return mSize;
    }

    public LinkedList<T> add(T value) {
        if (header == null) {
            header = new Node(value);
        } else {
            get(mSize - 1).newNext(value);
        }
        mSize++;
        return this;
    }

    public LinkedList<T> add(int index, T value) {
        if (index > mSize || index < 0) {
            throw new IndexOutOfBoundsException("index " + index);
        }
        // 先排除是添加到最后一个的
        if (index == mSize) {
            return add(value);
        } else {
            // target 不可能为 null
            Node target = header;
            for (int i = 0; i < index; i++) {
                target = target.getNext();
            }
            Node preAddNode = new Node(value);
            preAddNode.setPre(preAddNode.getPre());
            preAddNode.setNext(target);
            target.setPre(preAddNode);
            mSize++;
            return this;
        }
    }

    @Nullable
    public Node<T> get(int index) {
        if (index >= mSize || index < 0) {
            throw new IndexOutOfBoundsException("index " + index);
        }
        Node target = header;
        for (int i = 0; i < index; i++) {
            target = target.getNext();
        }
        return target;
    }

    public LinkedList<T> set(int index, T value) {
        if (index >= mSize || index < 0) {
            throw new IndexOutOfBoundsException("index " + index);
        }
        Node target = header;
        for (int i = 0; i < index; i++) {
            target = target.getNext();
        }
        target.setValue(value);
        return this;
    }

    public LinkedList<T> remove(int index) {
        if (index >= mSize || index < 0) {
            throw new IndexOutOfBoundsException("index " + index);
        }
        Node target = header;
        for (int i = 0; i < index; i++) {
            target = target.getNext();
        }
        // 如果是头一个
        if (target.getPre() == null) {
            header = target.getNext();
            header.setPre(null);
        } else {
            // 如果有上一个, 就把上一个接到下一个
            target.getPre().setNext(target.getNext());
        }
        mSize--;
        return this;
    }

    public List<T> toList() {
        if (header == null) {
            return Collections.emptyList();
        }else {
            List<T> result = new ArrayList<>();
            Node<T> temp = header;
            while (temp != null) {
                result.add(temp.getValue());
                temp = temp.getNext();
            }
            return result;
        }
    }

}
