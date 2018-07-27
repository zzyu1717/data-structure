package zzy.queue.impl;

import zzy.queue.Queue;

/**
 * created in 10:50 2018/7/27 by zhaozhenyu.
 */

public class LoopQueue<E> implements Queue<E>{


    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity) {
        // 采用的循环队列，有意识的浪费了一个空间
        data = (E[])new Object[capacity+1];
    }

    public LoopQueue() {
        this(10);
    }

    @Override
    public void enqueue(E e) {
        // 队列已满
        if ((tail+1)%data.length == front) {
            resize(getCapacity()*2);
        }

        data[tail] = e;
        tail = (tail+1)%data.length;
        size ++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity+1];

        for (int i = 0; i < size; i++) {
            newData[i] = data[(i+front)%data.length]; // 循环队列，需要加偏移量
        }

        data = newData;
        tail = size;
        front = 0;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is empty");
        }
        E ret = data[front];
        data[front] = null;
        front = (front+1)%data.length;
        size --;

        //缩减容量
        if (size == getCapacity()/4 && getCapacity()/2 != 0) {
            resize(getCapacity()/2);
        }

        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is empty");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n",size,getCapacity()));
        res.append("LoopQueue: ");
        res.append("front [");
        // 另一种遍历方式
        for (int i = front; i != tail; i = (i+1)%data.length) {
            res.append(data[i]);
            if ((i+1)%data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] tail");

        return res.toString();
    }

}
