package zzy.queue.impl;

import zzy.array.MyArray;
import zzy.queue.Queue;

/**
 * created in 18:34 2018/7/26 by zhaozhenyu.
 */

public class ArrayQueue<E> implements Queue<E> {

    private MyArray<E> array;

    public ArrayQueue(int capactiy) {
        array = new MyArray<>(capactiy);
    }

    public ArrayQueue() {
        array = new MyArray<>();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() -1) {
                res.append(", ");
            }
        }
        res.append("] tail");

        return res.toString();
    }
}
