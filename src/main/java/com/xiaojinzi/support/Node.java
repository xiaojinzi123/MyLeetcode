package com.xiaojinzi.support;

import com.xiaojinzi.support.annotation.NonNull;

/**
 * 链表的节点
 */
public class Node<T> {

    private T value;
    private Node pre;
    private Node next;

    public Node() {
    }

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, Node pre, Node next) {
        this.value = value;
        this.pre = pre;
        this.next = next;
    }

    @NonNull
    public <T> Node<T> newNext(T value) {
        Node<T> nextNode = new Node<>(value, this, null);
        this.next = nextNode;
        return nextNode;
    }

    public Node getPre() {
        return pre;
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

}
