package zzy.stack.impl;

import zzy.array.MyArray;
import zzy.stack.Stack;

/**
 * created in 16:38 2018/7/26 by zhaozhenyu.
 */

public class ArrayStack<E> implements Stack<E> {

    private MyArray<E> array;

    public ArrayStack(int capacity) {
        array = new MyArray<>(capacity);
    }

    public ArrayStack() {
        array = new MyArray<>();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
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
        res.append("Stack: ");
        res.append("[");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize()-1) {
                res.append(", ");
            }
        }
        res.append("] top");

        return res.toString();
    }
}
