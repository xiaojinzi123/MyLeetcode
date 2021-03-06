package com.xiaojinzi.support;

import com.xiaojinzi.support.annotation.NonNull;
import com.xiaojinzi.support.annotation.Nullable;

import java.util.*;
import java.util.LinkedList;

/**
 * 添加进来的每一个元素都是会排序好的. 每一个操作都是 O(logN)
 *
 * @param <T> 需要实现 {@link Comparable} 接口
 */
public class BinarySearchTree<T extends Comparable> {

    /**
     * 列表的长度
     */
    private int mSize;

    /**
     * 头指针
     */
    @Nullable
    private BinaryTreeNode<T> header;

    @Nullable
    public BinaryTreeNode<T> getHeader() {
        return header;
    }

    public int size() {
        return mSize;
    }

    public boolean isEmpty() {
        return mSize == 0;
    }

    public boolean contains(T o) {
        return indexOf(o) != -1;
    }

    public boolean add(@NonNull T o) {
        boolean result;
        if (isEmpty()) {
            header = new BinaryTreeNode<T>(o);
            result = true;
        } else {
            result = addNode(header, o);
        }
        if (result) {
            mSize++;
        }
        return result;
    }

    public boolean remove(@NonNull T o) {

        // 找到要删除的节点和父节点
        BinaryTreeNode<T> targetDeleteParentNode = null, targetDeleteNode = null;
        boolean isLeft = false;

        BinaryTreeNode<T> tempNode = header;
        while (tempNode != null) {
            // 如果相等
            if (o.compareTo(tempNode.getValue()) == 0) {
                targetDeleteNode = tempNode;
                break;
            } else {
                targetDeleteParentNode = tempNode;
                if (o.compareTo(tempNode.getValue()) > 0) { // 如果目标值比较大
                    tempNode = tempNode.getRight();
                    isLeft = false;
                } else {
                    tempNode = tempNode.getLeft();
                    isLeft = true;
                }
            }
        }

        // 如果没找到, 就表示删除失败, 包括了当空树的情况
        if (targetDeleteNode == null) {
            return false;
        }

        // 处理 size = 1 的情况

        if (mSize == 1) {
            mSize--;
            header = null;
            return true;
        }

        // 1. 如果要删除的节点没有子节点
        // 如果正好是 header, 又没有子节点, 那么正好是上面 size = 1 的情况
        if (targetDeleteNode.getLeft() == null && targetDeleteNode.getRight() == null) {
            if (isLeft) {
                targetDeleteParentNode.setLeft(null);
            } else {
                targetDeleteParentNode.setRight(null);
            }
            mSize--;
            return true;
        }

        // 2. 如果要删除的节点只有一个子节点
        if (targetDeleteNode.getLeft() == null || targetDeleteNode.getRight() == null) {
            BinaryTreeNode<T> node = targetDeleteNode.getLeft();
            if (node == null) {
                node = targetDeleteNode.getRight();
            }
            if (targetDeleteParentNode == null) {
                header = node;
            } else {
                if (isLeft) {
                    targetDeleteParentNode.setLeft(node);
                } else {
                    targetDeleteParentNode.setRight(node);
                }
            }
            mSize--;
            return true;
        }

        // 3. 剩下一个情况就是有两个节点的了, 两个节点的又有两种情况
        // 要删除节点的后继节点, 也就是下一个大的节点
        BinaryTreeNode<T> targetDeleteSuccessorNode = null, targetDeleteSuccessorParentNode;

        // 寻找要删除节点的后继节点
        targetDeleteSuccessorParentNode = targetDeleteNode;
        targetDeleteSuccessorNode = targetDeleteNode.getRight(); // 因为要删除的节点是肯定有两个节点, 所以这里一定不会为空

        while (targetDeleteSuccessorNode.getLeft() != null) {
            targetDeleteSuccessorParentNode = targetDeleteSuccessorNode;
            targetDeleteSuccessorNode = targetDeleteSuccessorNode.getLeft();
        }

        // 3.1 如果后继节点是目标节点的右子节点, 说明这个后继节点没有 left 节点
        if (targetDeleteNode.getRight() == targetDeleteSuccessorNode) {
            targetDeleteSuccessorNode.setLeft(targetDeleteNode.getLeft());
            if (targetDeleteParentNode == null) {
                header = targetDeleteSuccessorNode;
            } else {
                if (isLeft) {
                    targetDeleteParentNode.setLeft(targetDeleteSuccessorNode);
                } else {
                    targetDeleteParentNode.setRight(targetDeleteSuccessorNode);
                }
            }
            mSize--;
            return true;
        }

        // 3.2 如果后继节点是要删除节点的右节点的左子树, 这个后继节点没有 left 节点的

        targetDeleteSuccessorNode.setLeft(targetDeleteNode.getLeft());
        targetDeleteSuccessorNode.setRight(targetDeleteNode.getRight());
        targetDeleteSuccessorParentNode.setLeft(targetDeleteSuccessorNode.getRight());
        if (targetDeleteParentNode != null) { // 如果是删除 header
            targetDeleteParentNode.setRight(targetDeleteSuccessorNode);
        }
        mSize--;
        return true;

    }

    public void clear() {
        header = null;
        mSize = 0;
    }

    public T get(int index) {
        return getNode(index).getValue();
    }

    private BinaryTreeNode<T> getNode(int index) {
        if (index > mSize - 1 || index < 0) {
            throw new IndexOutOfBoundsException("index = " + index);
        }
        // index = 0 表示要找的下标, index = 1 表示总数量
        int arr[] = new int[]{index, 0};
        return getRecursion(header, arr);
    }

    @Nullable
    private BinaryTreeNode<T> getRecursion(BinaryTreeNode<T> node, int[] arr) {
        if (node == null) {
            return null;
        }
        BinaryTreeNode<T> result = getRecursion(node.getLeft(), arr);
        if (result != null) {
            return result;
        }
        if (arr[0] == arr[1]) {
            return node;
        }
        arr[1]++;
        return getRecursion(node.getRight(), arr);
    }

    public T remove(int index) {
        T targetValue = getNode(index).getValue();
        remove(targetValue);
        return targetValue;
    }

    /**
     * 使用中序遍历的方式进行遍历
     *
     * @param o 目标值
     * @return 如果返回 -1 表示没找到, 否则返回找到的元素的下标
     */
    public int indexOf(T o) {
        int[] result = new int[]{-1, 0};
        indexElementRecursion(header, o, result);
        return result[0];
    }

    /**
     * 需要使用中序遍历
     *
     * @param node   当前节点
     * @param target 目标值
     * @param result 保存找到的 index 和 节点 count 的数据对象
     */
    private void indexElementRecursion(@Nullable BinaryTreeNode<T> node,
                                       @NonNull T target, int[] result) {
        if (node == null) {
            return;
        }
        indexElementRecursion(node.getLeft(), target, result);
        // 如果没找到
        if (result[0] == -1) {
            // 如果和节点值相等
            if (target.compareTo(node.getValue()) == 0) {
                result[0] = result[1];
                return;
            } else {
                result[1]++;
                indexElementRecursion(node.getRight(), target, result);
            }
        }
    }

    private boolean addNode(@NonNull BinaryTreeNode<T> binaryTreeNode, @NonNull T targetValue) {

        if (binaryTreeNode.getValue().compareTo(targetValue) == 0) {
            return false;
        }

        // 如果目标值大于现在的节点的值, 说明要放到右节点
        if (targetValue.compareTo(binaryTreeNode.getValue()) > 0) {
            if (binaryTreeNode.getRight() == null) {
                binaryTreeNode.setRight(new BinaryTreeNode(targetValue));
            } else {
                return addNode(binaryTreeNode.getRight(), targetValue);
            }
        } else {
            if (binaryTreeNode.getLeft() == null) {
                binaryTreeNode.setLeft(new BinaryTreeNode(targetValue));
            } else {
                return addNode(binaryTreeNode.getLeft(), targetValue);
            }
        }
        return true;

    }

    @Nullable
    private BinaryTreeNode<T> findNode(@Nullable BinaryTreeNode<T> binaryTreeNode, @NonNull T targetValue) {

        if (binaryTreeNode == null) {
            return null;
        }
        // 如果相等
        if (binaryTreeNode.getValue().compareTo(targetValue) == 0) {
            return binaryTreeNode;
        } else if (targetValue.compareTo(binaryTreeNode.getValue()) > 0) { // 如果目标值比较大
            return findNode(binaryTreeNode.getRight(), targetValue);
        } else {
            return findNode(binaryTreeNode.getLeft(), targetValue);
        }

    }

    /**
     * 中序遍历
     */
    public List<T> inOrder() {
        List<T> result = new ArrayList<>();
        inOrderRecursive(header, result);
        return result;
    }

    /**
     * 深度优先遍历
     */
    @NonNull
    public List<T> depthFirst() {
        if (isEmpty()) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>();
        depthFirstRecursion(header, result);
        return result;
    }

    @NonNull
    private void depthFirstRecursion(BinaryTreeNode<T> node, List<T> result) {
        if (node == null) {
            return;
        }
        result.add(node.getValue());
        depthFirstRecursion(node.getLeft(), result);
        depthFirstRecursion(node.getRight(), result);
    }

    /**
     * 广度优先遍历
     */
    public List<T> breadthFirst() {
        if (isEmpty()) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>();
        Deque<BinaryTreeNode<T>> deque = new LinkedList<>();
        deque.add(header);
        while (!deque.isEmpty()) {
            BinaryTreeNode<T> node = deque.pop();
            result.add(node.getValue());
            if (node.getLeft() != null) {
                deque.add(node.getLeft());
            }
            if (node.getRight() != null) {
                deque.add(node.getRight());
            }
        }
        return result;
    }

    private void inOrderRecursive(@Nullable BinaryTreeNode<T> node, List<T> result) {
        if (node != null) {
            inOrderRecursive(node.getLeft(), result);
            result.add(node.getValue());
            inOrderRecursive(node.getRight(), result);
        }
    }

}
