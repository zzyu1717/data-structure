package zzy.sort;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.function.Consumer;

/**
 * 提供冒泡排序、插入排序、选择排序、归并排序、快速排序
 *
 * *
 * @since: 1.0 created by zhaozhenyu on 2020/8/4 22:09
 * @link: zzyu1010@163.com
 */
public class SortUtil {

    public static void main(String[] args) {
        Random random = new Random();
        int N = 10000000;
        int[] array = random.ints(N, 1,  10000).toArray();

        testTime("快速排序twoWay...",array,SortUtil::quickSortTwoWay);
        testTime("快速排序twoWay...",array,SortUtil::quickSortTwoWay);
        testTime("快速排序twoWay...",array,SortUtil::quickSortTwoWay);
        testTime("快速排序oneWay...",array,SortUtil::quickSortOneWay);
        testTime("快速排序oneWay...",array,SortUtil::quickSortOneWay);
        testTime("快速排序ThreeWay...",array,SortUtil::quickSortThreeWay);
        testTime("快速排序ThreeWay...",array,SortUtil::quickSortThreeWay);

//        testTime("插入排序...",array,SortUtil::insertSort);


//        testTime("选择排序加强...",array,SortUtil::selectionSortAdvance);
//        testTime("选择排序...",array,SortUtil::selectionSort);
//        testTime("冒泡排序...",array,SortUtil::bubbleSort);


//        print("快速排序ThreeWay...",array,SortUtil::quickSortThreeWay);
//        print("快速排序TwoWay...",array,SortUtil::quickSortTwoWay);
//        print("快速排序...",array,SortUtil::quickSortOneWay);
//        print("归并排序...",array,SortUtil::mergeSort);
//        print("插入排序...",array,SortUtil::insertSort);
//        print("选择排序加强...",arlray,SortUtil::selectionSortAdvance);
//        print("选择排序...",array,SortUtil::selectionSort);
//        print("冒泡排序...",array,SortUtil::bubbleSort);


    }

    private static void heapSort(int[] arr) {

    }

    private static void quickSortThreeWay(int[] arr) {
        quickSortThreeWay(arr,0,arr.length-1);
    }

    private static void quickSortThreeWay(int[] arr, int l, int r) {
        if (r - l <= 15) {
            insertSort(arr,l,r);
            return;
        }
        Random random = new Random();
        int pivot = random.nextInt(r-l+1)+l;
        swap(arr,l, pivot);
        int v = arr[l];
        // arr[l+1..lt] < v
        int lt = l;
        // arr[gt...r] > v
        int gt = r+1;
        // arr[lt+1...i) == v
        int i = l+1;
        while (i < gt) {
            if (arr[i] < v) {
                swap(arr,i,lt+1);
                i++;
                lt++;
            } else if (arr[i] > v) {
                swap(arr,i,gt-1);
                gt--;
            } else {
                i++;
            }
        }
        swap(arr,l,lt);
        quickSortThreeWay(arr,l,lt-1);
        quickSortThreeWay(arr,gt,r);
    }


    private static void quickSortTwoWay(int[] arr) {
        quickSortTwoWay(arr,0,arr.length-1);
    }

    private static void quickSortTwoWay(int[] arr, int l, int r) {
        if (r -l <= 15) {
            insertSort(arr,l,r);
            return;
        }
        int p = partitionTwoWay(arr,l,r);
        quickSortTwoWay(arr,l,p-1);
        quickSortTwoWay(arr,p+1,r);
    }

    private static int partitionTwoWay(int[] arr, int l, int r) {
        Random random = new Random();
        int pivot = random.nextInt(r-l+1)+l;
        swap(arr,l, pivot);
        // arr[l+1...i) <= v; arr(j...r] >= v
        int i = l+1,j = r;
        int v = arr[l];
        while (true) {
            while (i <= r && arr[i] < v) {
                i++;
            }
            while (j >= l+1 && arr[j] > v) {
                j --;
            }
            if (i > j) {
                break;
            }
            swap(arr,i,j);
            i++;
            j--;
        }
        swap(arr,l,j);
        return j;
    }

    /**
     * 一路快排
     * @param arr
     */
    private static void quickSortOneWay(int[] arr) {
        quickSortOneWay(arr,0,arr.length-1);
    }

    private static void quickSortOneWay(int[] arr, int l, int r) {
        if (r -l <= 15) {
            insertSort(arr,l,r);
            return;
        }
        int p = partitionOneWay(arr,l,r);
        quickSortOneWay(arr,l,p-1);
        quickSortOneWay(arr,p+1,r);
    }

    /**
     * 对arr[l...r]部分进行partition操作
     * 返回p, 使得arr[l...p-1] <= arr[p]; arr[p+1...r] > arr[p]
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int partitionOneWay(int[] arr, int l, int r) {
        Random random = new Random();
        int pivot = random.nextInt(r-l+1)+l;
        swap(arr,l, pivot);
        // arr[l+1...j] < v; arr[j+1...i) >= v
        int j = l;
        int v = arr[l];
        for (int i = l+1; i <= r; i++) {
            if (arr[i] < v) {
                j++;
                swap(arr,i,j);
            }
        }
        swap(arr,l,j);
        return j;
    }


    /**
     * 归并排序
     * @param arr
     */
    private static void mergeSort(int[] arr) {
        mergeSort(arr,0,arr.length-1);
    }

    private static void mergeSort(int[] arr,int left, int right) {
        if (right - left <= 15) {
            insertSort(arr,left, right);
            return ;
        }
        int mid = left + (right-left)/2;
        mergeSort(arr,left,mid);
        mergeSort(arr,mid+1,right);
        merge2(arr,left,mid,right);
    }

    private static void merge2(int[] arr, int l, int m, int r) {
        // 合并[l,m]和[m+1,r]
        int[] tmp = new int[r-l+1];
        int i = l,j = m+1,k = 0;
        while (i <= m && j <= r) {
            while (i <= m && arr[i] < arr[j]) {
                tmp[k++] = arr[i++];
            }
            while (j <= r && arr[j] <= arr[i] ) {
                tmp[k++] = arr[j++];
            }
        }
        while (i <= m) {
            tmp[k++] = arr[i++];
        }
        while (j <= r) {
            tmp[k++] = arr[j++];
        }
        System.arraycopy(tmp,0,arr,l,tmp.length);
    }

    // 将arr[left...mid]和arr[mid+1...right]两部分进行归并
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] origin = Arrays.copyOfRange(arr, left, right + 1);

        int i = left, j = mid+1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                // 左半部分已经处理完毕
                arr[k] = origin[j-left];
                j++;
            } else if (j > right) {
                // 右边部分已经处理
                arr[k] = origin[i - left];
                i++;
            } else if (origin[i-left] < origin[j-left]) {
                arr[k] = origin[i-left];
                i++;
            } else {
                arr[k] = origin[j-left];
                j++;
            }
        }

    }

    private static void insertSort(int[] arr, int l, int r) {
        for (int i = l+1; i <= r; i++) {
            int ai = arr[i];
            int j = i;
            for (; j > l && ai < arr[j-1]; j--) {
                arr[j] = arr[j-1];
            }
            arr[j] = ai;
        }
    }

    /**
     * 插入排序
     * @param arr
     */
    private static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int ai = arr[i];
            // [0,j] already sorted
            int j = i;
            for (; j > 0 &&  arr[j-1] > ai; j--) {
                arr[j] = arr[j-1];
            }
            arr[j] = ai;
        }
    }

    /**
     * 选择排序
     * @param arr
     */
    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            // 寻找[i,arr.length)区间的最小值
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(arr,i,min);
        }
    }

    private static void selectionSortAdvance(int[] arr) {
        int left = 0;
        int right = arr.length-1;
        while (left < right) {
            int minIndex = left;
            int maxIndex = right;
            if (arr[minIndex] > arr[maxIndex]) {
                swap(arr,minIndex,maxIndex);
            }
            for (int i = left+1; i < right; i++) {
                if (arr[minIndex] > arr[i]) {
                    minIndex = i;
                } else if (arr[maxIndex] < arr[i]) {
                    maxIndex = i;
                }
            }
            swap(arr,minIndex,left);
            swap(arr,maxIndex,right);
            left ++;
            right --;
        }
    }


    /**
     * 冒泡排序
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        if (Objects.isNull(arr) || arr.length == 0) {
            return;
        }
        int len = arr.length;
        do {
            int newLength = 0;
            for (int i = 1; i < len; i++) {
                if (arr[i - 1] > arr[i]) {
                    swap(arr,i-1,i);
                    // 记录最后一次交换的位置 [i,arr.Length)已经有序
                    newLength = i;
                }
            }
            len = newLength;
        } while (len > 1);
    }


    private static void swap(int[] arr,int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void testTime(String msg, int[] array, Consumer<int[]> consumer) {
        int[] copyArray = Arrays.copyOf(array, array.length);
        long start = System.currentTimeMillis();
        consumer.accept(copyArray);
        System.out.printf("%s执行耗时：%s s\n",msg,(System.currentTimeMillis() - start)/1000.0);
    }

    private static void print(String msg, int[] array, Consumer<int[]> consumer) {
        int[] custom = Arrays.copyOf(array, array.length);
        int[] standard = Arrays.copyOf(array, array.length);
        Arrays.sort(standard);
        consumer.accept(custom);
        System.out.println(msg+Arrays.equals(custom,standard));
    }
}
