package zzy.heap;

import zzy.array.MyArray;

/**
 * created in 16:36 2018/7/31 by zhaozhenyu.
 *
 * 使用动态数组创建最大堆；最大堆从零开始
 * parent = (i-1)/2; leftChild=2*i+1; rightChild=2*i+2
 *
 */

public class MaxHeap<E extends Comparable<E>> {

    //dynamic array.
    private MyArray<E> array;

    public MaxHeap(int capacity) {
        array = new MyArray<>(capacity);
    }

    public MaxHeap(E[] arr) {
        heapify(arr);
        array = new MyArray<>(arr);
    }

    /**
     * O(n)时间复杂度
     * @param arr
     */
    private void heapify(E[] arr) {
        for (int i = ((arr.length-2)/2); i >= 0 ; i--) {
            siftDown(i);
        }
    }

    public MaxHeap() {
        array = new MyArray<>();
    }

    public void add(E e) {
        array.addLast(e);
        siftUp(array.getSize()-1);
    }

    private void siftUp(int k) {
        E tmp = array.get(k);
        int p = (k-1)/2;
        while (k > 0 && tmp.compareTo(array.get(p)) > 0) {
            array.set(k,array.get(p));
            k = p;
            p = (k-1)/2;
        }
        array.set(k,tmp);
    }

    public E extractMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("heap is empty.");
        }
        E ret = array.get(0);
        // 将数组末尾元素替换最大首元素
        E tmp = array.removeLast();
        if (!isEmpty()) {
            array.set(0,tmp);
            siftDown(0);
        }
        return ret;
    }

    private void siftDown(int k) {
        E tmp = array.get(k);
        while (2*k+1 < size()) {
            int i = 2*k+1;
            if (i + 1 < size() && array.get(i+1).compareTo(array.get(i)) > 0) i++;
            if (tmp.compareTo(array.get(i)) >= 0 ) break;

            array.set(k,array.get(i));
            k = i;
        }
        array.set(k,tmp);
    }

    public E findMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("heap is empty.");
        }
        return array.get(0);
    }

    /**
     * 取出堆中的最大元素，并且替换成元素e
     * @param e
     * @return
     */
    public E replace(E e) {
        E ret = findMax();
        array.set(0,e);
        siftDown(0);
        return ret;
    }


    public int size() {
        return array.getSize();
    }

    public boolean isEmpty() {
        return array.isEmpty();
    }

}
