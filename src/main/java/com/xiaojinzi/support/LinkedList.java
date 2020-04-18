package com.xiaojinzi.support;

import com.xiaojinzi.support.annotation.Nullable;

public class LinkedList<T> {

    private int mSize;

    private Node firstNode;

    public boolean isEmpty() {
        return mSize == 0;
    }

    public int size() {
        return mSize;
    }

    public LinkedList<T> add(T value) {
        if (firstNode == null) {
            firstNode = new Node(value);
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
            Node target = firstNode;
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
        Node target = firstNode;
        for (int i = 0; i < index; i++) {
            target = target.getNext();
        }
        return target;
    }

    public LinkedList<T> set(int index, T value) {
        if (index >= mSize || index < 0) {
            throw new IndexOutOfBoundsException("index " + index);
        }
        Node target = firstNode;
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
        Node target = firstNode;
        for (int i = 0; i < index; i++) {
            target = target.getNext();
        }
        // 如果是头一个
        if (target.getPre() == null) {
            firstNode = target.getNext();
            firstNode.setPre(null);
        }else {
            // 如果有上一个, 就把上一个接到下一个
            target.getPre().setNext(target.getNext());
        }
        mSize--;
        return this;
    }

}
