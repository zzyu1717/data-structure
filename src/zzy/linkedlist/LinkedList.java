package zzy.linkedlist;

/**
 * created in 15:30 2018/7/27 by zhaozhenyu.
 */

public class LinkedList<E> {

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

    /*************************************outer class************************************/

    private Node dummyHead;

    private int size;

    public LinkedList() {
        this.dummyHead = new Node(null,null);
        size = 0;
    }


    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * 根据索引添加节点
     * @param index 从零开始(dummyHead 在零之前的节点）
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("illegal index.");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        // 添加元素一步到位
        prev.next = new Node(e,prev.next);

        size ++;
    }

    /**
     * 从头节点添加元素
     * @param e
     */
    public void addFirst(E e) {
        add(0,e);
    }

    /**
     * 链表尾部添加元素
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 通过索引获取数据，index从零开始
     * @param index
     * @return
     */
    public E get(int index) {

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("illegal index.");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size-1);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("illegal index.");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    public boolean contains(E e) {
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            if (cur.e == e) return true;
        }
        return false;
    }

    /**
     * 根据索引删除元素
     * @param index
     * @return
     */
    public E remove(int index) {

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("illegal index");
        }

        Node prev = dummyHead;

        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;

        size --;

        return delNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size-1);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();

        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            ret.append(cur.e).append("->");
        }
        ret.append("NULL");

        return ret.toString();
    }

}


