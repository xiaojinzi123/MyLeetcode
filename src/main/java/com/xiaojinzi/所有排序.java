package com.xiaojinzi;

public class 所有排序 {

    public static void main(String[] args) {

        int[] arr1 = new int[]{5, 1};
        int[] arr2 = new int[]{8, 3, 9, 10, 6, 7, 5, 1, 4, 2};

        // quickSort(arr1, 0, arr1.length - 1);
        // quickSort(arr2, 0, arr2.length - 1);
        mergeSort(arr1);
        mergeSort(arr2);

        printArr(arr1);
        printArr(arr2);

    }

    /**
     * 归并排序
     */
    private static void mergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        doMergeSort(arr, 0, arr.length - 1, temp);
    }

    /**
     * 归并排序
     */
    private static void doMergeSort(int[] arr, int start, int end,
                                    int[] temp) {

        // 结束条件. 如果只有一个要处理的元素, 那就说明不用处理了, 直接返回
        if (start >= end) {
            return;
        }

        // 本次需要处理的长度
        int length = end - start + 1;

        // 中间的下标
        int centerIndex = (start + end) / 2;

        doMergeSort(arr, start, centerIndex, temp);
        doMergeSort(arr, centerIndex + 1, end, temp);

        // 把两段有序的区域的数据都放到 temp 中
        int left = start, right = centerIndex + 1;
        for (int n = 0; n < length; n++) {
            int targetIndex = 0;
            if (left > centerIndex) {
                targetIndex = right;
                right++;
            } else if (right > end) {
                targetIndex = left;
                left++;
            } else {
                if (arr[left] <= arr[right]) {
                    targetIndex = left;
                    left++;
                } else {
                    targetIndex = right;
                    right++;
                }
            }
            temp[n] = arr[targetIndex];
        }

        System.arraycopy(temp, 0, arr, start, length);

    }

    /**
     * 快速排序
     */
    private static void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int i = start, j = end;
        // 拿第一个值当参考值, 基准值的选取直接影响 while 循环中的 i 和 j 谁先动
        // 如果选择左边 start, 则必须 j 先动, 如果选择 end, 则选择 i 先动
        int pivot = arr[start];
        int temp = 0;
        // 小的往左边换, 大的往右边
        while (i < j) {
            while (arr[j] >= pivot && i < j) {
                j--;
            }
            while (arr[i] <= pivot && i < j) {
                i++;
            }
            if (i != j) { // 这里的 if 写不写都无所有, 因为 i = j 的时候换一下也没事
                // 将找到的下标为 i 和 j 的位置换一下.
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // 更换基准位置, 换了之后, 基准值的两边数组进入下一轮快排5
        // 这里的 i = j, 如果基准值选择的 arr[end]
        // 则下面的 arr[start] 换成 arr[end]
        arr[start] = arr[i];
        arr[i] = pivot;
        quickSort(arr, 0, i - 1);
        quickSort(arr, i + 1, end);
    }

    /**
     * 冒泡排序
     */
    private static void bubbleSort(int[] arr) {
        if (arr.length == 1) {
            return;
        }
        int temp = 0;
        // N 个元素, 只需要冒泡 N - 1 次即可
        for (int i = 0; i < arr.length - 1; i++) {
            // 相邻两个比较, 从 index = 0 开始, 下面的结束条件需要注意
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // arr[j] 和 arr[j + 1] 比较大小并换位
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 选择排序
     */
    private static void selectSort(int[] arr) {
        if (arr.length == 1) {
            return;
        }
        int temp = 0;
        // N 个元素, 只需要选择 N - 1 次即可
        for (int i = 0; i < arr.length - 1; i++) {
            // 第 i 个元素和之后的所有元素进行对比
            for (int j = i; j < arr.length; j++) {
                // arr[j] 和 arr[j + 1] 比较大小并换位
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    private static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                System.out.print(arr[i]);
            } else {
                System.out.print("," + arr[i]);
            }
        }
        System.out.println();
    }

}
