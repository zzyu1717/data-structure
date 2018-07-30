package zzy.set.impl;

import zzy.linkedlist.LinkedList;
import zzy.set.Set;

/**
 * created in 22:37 2018/7/30 by zhaozhenyu.
 */

public class LinkedListSet<E> implements Set<E> {

    private LinkedList list;

    public LinkedListSet() {
        list = new LinkedList();
    }

    @Override
    public void add(E e) {
        if (!list.contains(e)) {
            list.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
