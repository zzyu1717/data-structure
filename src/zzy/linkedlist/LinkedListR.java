package zzy.linkedlist;

import javafx.util.Pair;

/**
 * created in 15:48 2018/7/28 by zhaozhenyu.
 *
 * 使用递归实现封装链表
 */

public class LinkedListR<E> {

    private class Node {
        E e;
        Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e,null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    /*************************************************************/

    private Node head;
    private int size;

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        head = add(head,index,e);

        size ++;
    }

    private Node add(Node node, int index, E e) {
        if (index == 0) {
            return new Node(e,node);
        }
        node.next = add(node.next,index-1,e);

        return node;
    }

    public void addLast(E e) {
        add(size,e);
    }

    public void addFirst(E e) {
        add(0,e);
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get failed. Illegal index.");
        }
        return get(head, index);
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size-1);
    }

    private E get(Node node, int index) {
        if (index == 0) {
            return node.e;
        }
        return get(node.next,index-1);
    }


    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("set failed. Illegal index.");
        }
        set(head,index,e);
    }

    private void set(Node node, int index, E e) {
        if (index == 0) {
            node.e = e;
            return ;
        }
        set(node.next,index-1,e);
    }


    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed. Illegal index.");
        }

        Pair<Node,E> res = remove(head,index);

        size --;
        head = res.getKey();
        return res.getValue();
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size-1);
    }

    /**
     * 返回Pair键值对，key Node：表示返回的头节点，value E：返回要删除元素的值
     * @param node
     * @param index
     * @return
     */
    private Pair<Node,E> remove(Node node, int index) {
        if (index == 0) {
            return new Pair<>(node.next,node.e);
        }

        Pair<Node,E> res = remove(node.next,index-1);
        node.next = res.getKey();
        res = new Pair<>(node,res.getValue());
        return res;
    }

    public void removeElement(E e) {
        head = removeElement(head, e);
        size --;
    }

    private Node removeElement(Node node, E e) {
        if (node == null) return null;
        if (node.e.equals(e)) return node.next;

        node.next = removeElement(node.next,e);

        return node;
    }


    public boolean contains(E e) {
        return contains(head, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) return false;
        if (node.e.equals(e)) return true;
        return contains(node.next,e);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();

        for (Node cur = head; cur != null; cur = cur.next) {
            ret.append(cur.e).append("->");
        }
        ret.append("NULL");

        return ret.toString();
    }

}
