package zzy.queue.impl;

import zzy.linkedlist.LinkedList;
import zzy.queue.Queue;

/**
 * created in 22:37 2018/7/27 by zhaozhenyu.
 */

public class LinkedListQueue<E> implements Queue<E> {

    private class Node {
        public  E e;

        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e,null);
        }

        public Node() {
            this(null,null);
        }

        @Override
        public String toString() {
            return e.toString();
        }

    }

    /********************************************************/

    private Node head;
    private Node tail;
    private int size;


    @Override
    public void enqueue(E e) {
        Node newNode = new Node(e);
        if (tail == null) {
            tail = newNode;
            head = newNode;
        } else {
            tail.next = newNode;
            tail = tail.next;
        }
        size ++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is empty");
        }

        Node retNode = head;
        head = head.next;
        size --;
        if (head == null) {
            tail = null;
        }
        return retNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is empty");
        }
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("Queue: head [");
        for (Node cur = head; cur != null; cur = cur.next) {
            ret.append(cur.e).append("->");
        }
        ret.append("NULL ] tail");

        return ret.toString();
    }
}
