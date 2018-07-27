package zzy.stack.impl;

import zzy.linkedlist.LinkedList;
import zzy.stack.Stack;

/**
 * created in 18:39 2018/7/27 by zhaozhenyu.
 */

public class LinkedListStack<E> implements Stack<E>{

    private LinkedList<E> list;

    public LinkedListStack() {
        list = new LinkedList<>();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.get(0);
    }
}
