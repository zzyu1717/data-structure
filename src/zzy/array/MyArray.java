package zzy.array;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * created in 0:04 2018/7/25 by zhaozhenyu.
 *
 * 动态数组：自动扩容和减容，同时避免复杂度震荡
 * 使用泛型机制
 *
 */

public class MyArray<T> {

    private T[] data;

    /**
     * size 表示数组中有多少元素，同时指向了数组中第一个没有元素的位置
     */
    private int size;

    public MyArray(int capacity) {
        data = (T[])new Object[capacity];
    }

    public MyArray(T[] arr) {
        size = arr.length;
        data = Arrays.copyOf(arr,size);
    }

    public MyArray() {
        this(10);
    }

    /**
     * 向所有元素后添加一个新元素
     * @param e
     */
    public void addLast(T e) {
        add(size,e);
    }

    /**
     * 在所有元素前添加一个新元素
     * @param e
     */
    public void addFirst(T e) {
        add(0,e);
    }

    /**
     * 在index个位置插入一个新元素e,index 从0开始
     * @param index
     * @param e
     */
    public void add(int index, T e) {
        if (size == data.length) {
            resize(2*data.length);
        }

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed, require index >= 0 and index <= size.");
        }

        for (int i = size-1; i >= index; i--) {
            data[i+1] = data[i];
        }

        data[index] = e;
        size ++;
    }

    /**
     * update exist value.
     * @param index
     * @param e
     */
    public void set(int index, T e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index illegal");
        }
        data[index] = e;
    }

    private void resize(int newCapacity) {
        T[] newData = (T[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * 从数组中删除index位置的元素，并且返回删除的元素
     * @param index
     * @return
     */
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed, index is illegal");
        }
        T result = data[index];
        for (int i = index+1; i < size; i++) {
            data[i-1] = data[i];
        }

        size --;
        data[size] = null; // loitering object.

        /**
         * 防止复杂度震荡
         */
        if (size == data.length/4 && data.length/2 != 0) {
            resize(data.length/2);
        }

        return result;
    }

    public T removeFirst() {
        return remove(0);
    }

    /**
     * 返回删除数组末尾的值
     * @return
     */
    public T removeLast() {
        return remove(size-1);
    }

    /**
     * 从数组中删除e
     * @param e
     */
    public void removeElement(T e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    /**
     * 删除所有与e相同的元素
     * @param e
     */
    public void removeAllElements(T e) {
        int[] all = findAll(e);
        for (int i = 0; i < all.length; i++) {
            remove(all[i]);
        }
    }


    /**
     * 获取index索引位置的元素
     * @param index
     * @return
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed, index is illegal");
        }
        return data[index];
    }

    public T getLast() {
        return get(size-1);
    }

    public T getFirst() {
        return get(0);
    }

    /**
     * 查找数组中是否有元素e
     * @param e
     * @return
     */
    public boolean contains(T e) {
        return find(e) != -1;
    }

    /**
     * 查找数组中元素e所在的索引，如果不存在元素e,则返回-1
     * @param e
     * @return
     */
    public int find(T e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找元素e所在的所有索引，若不存在，则返回空数组
     * @param e
     * @return
     */
    public int[] findAll(T e) {
        int[] ret = new int[size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                ret[count++] = i;
            }
        }

       return Arrays.copyOf(ret,count);
    }

    /**
     * 获取数组中的元素个数
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组的容量
     * @return
     */
    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n",size,data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size -1) {
                res.append(", ");
            }
        }
        res.append(']');

        return res.toString();
    }

}
