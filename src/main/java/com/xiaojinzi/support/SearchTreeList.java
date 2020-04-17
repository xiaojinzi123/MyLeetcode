package com.xiaojinzi.support;

import java.util.logging.Handler;

/**
 * 添加进来的每一个元素都是会排序好的. 每一个操作都是 O(logN)
 *
 * @param <T> 需要实现 {@link Comparable} 接口
 */
public class SearchTreeList<T extends Comparable> {

    /**
     * 列表的长度
     */
    private int mSize;

    /**
     * 头指针
     */
    @Nullable
    private TreeNode<T> header;

    public int size() {
        return mSize;
    }

    public boolean isEmpty() {
        return mSize == 0;
    }

    public boolean contains(T o) {
        return false;
    }

    public boolean add(@NonNull T o) {
        boolean result;
        if (isEmpty()) {
            header = new TreeNode<T>(o);
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
        TreeNode<T> targetDeleteParentNode = null, targetDeleteNode = null;
        boolean isLeft = false;

        TreeNode<T> tempNode = header;
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
            TreeNode<T> node = targetDeleteNode.getLeft();
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
        TreeNode<T> targetDeleteSuccessorNode = null, targetDeleteSuccessorParentNode;

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
        //
    }

    public T get(int index) {
        if (index > mSize - 1) {
            throw new IndexOutOfBoundsException("index = " + index);
        }
        return null;
    }

    public T set(int index, T element) {
        return null;
    }

    public void add(int index, T element) {

    }

    public T remove(int index) {
        return null;
    }

    public int indexOf(T o) {
        return 0;
    }

    public int lastIndexOf(T o) {
        return 0;
    }

    private boolean addNode(@NonNull TreeNode<T> treeNode, @NonNull T targetValue) {

        if (treeNode.getValue().compareTo(targetValue) == 0) {
            return false;
        }

        // 如果目标值大于现在的节点的值, 说明要放到右节点
        if (targetValue.compareTo(treeNode.getValue()) > 0) {
            if (treeNode.getRight() == null) {
                treeNode.setRight(new TreeNode(targetValue));
            } else {
                return addNode(treeNode.getRight(), targetValue);
            }
        } else {
            if (treeNode.getLeft() == null) {
                treeNode.setLeft(new TreeNode(targetValue));
            } else {
                return addNode(treeNode.getLeft(), targetValue);
            }
        }
        return true;

    }

    @Nullable
    private TreeNode<T> findNode(@Nullable TreeNode<T> treeNode, @NonNull T targetValue) {

        if (treeNode == null) {
            return null;
        }
        // 如果相等
        if (treeNode.getValue().compareTo(targetValue) == 0) {
            return treeNode;
        } else if (targetValue.compareTo(treeNode.getValue()) > 0) { // 如果目标值比较大
            return findNode(treeNode.getRight(), targetValue);
        } else {
            return findNode(treeNode.getLeft(), targetValue);
        }

    }

    public void inOrder() {
        System.out.println("中序遍历开始");
        inOrderRecursive(header);
        System.out.println("中序遍历结束\n");
    }

    private void inOrderRecursive(@Nullable TreeNode<T> node) {
        if (node != null) {
            inOrderRecursive(node.getLeft());
            System.out.println(node.getValue());
            inOrderRecursive(node.getRight());
        }
    }

}
