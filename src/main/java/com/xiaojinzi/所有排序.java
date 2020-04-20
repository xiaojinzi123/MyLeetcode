package com.xiaojinzi;

public class 所有排序 {

    public static void main(String[] args) {

        int[] arr1 = new int[]{5, 1};
        int[] arr2 = new int[]{8, 3, 9, 10, 6, 7, 5, 1, 4, 2};

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
        doMergeSort(arr, 0, arr.length - 1, temp, 0, temp.length - 1);
    }

    /**
     * 归并排序
     */
    private static void doMergeSort(int[] arr, int start, int end,
                                    int[] temp, int tempStart, int tempEnd) {

        if (start >= end) {
            return;
        }
        int length = end - start + 1;
        // 7 --> 3, 6 --> 3
        int centenrIndex = (start + end) / 2;

        doMergeSort(arr, start, centenrIndex, temp, start, centenrIndex);
        doMergeSort(arr, centenrIndex + 1, end, temp, centenrIndex + 1, end);

        // 分出来的两段排序好之后合并到 temp 中
        int i = start, j = centenrIndex + 1;

        for (int n = tempStart; n <= tempEnd; n++) {
            int targetIndex;
            if (i > centenrIndex) {
                targetIndex = j;
                j++;
            } else if (j > end) {
                targetIndex = i;
                i++;
            } else {
                if (arr[i] <= arr[j]) {
                    targetIndex = i;
                    i++;
                } else {
                    targetIndex = j;
                    j++;
                }
            }
            temp[n] = arr[targetIndex];
        }

        System.arraycopy(temp, tempStart, arr, start, length);

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
