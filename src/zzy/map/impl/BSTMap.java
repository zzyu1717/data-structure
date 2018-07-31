package zzy.map.impl;

import zzy.map.Map;

/**
 * created in 13:46 2018/7/31 by zhaozhenyu.
 */

public class BSTMap<K extends Comparable<K>, V> implements Map<K,V> {

    private class Node {
        K key;
        V value;
        Node left,right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    /****************************************************************/

    private Node root;
    private int size;

    /**
     * 返回以node为根的二叉搜索树中的键值为key的节点
     * @param node
     * @param key
     * @return
     */
    private Node getNode(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return getNode(node.left,key);
        } else if (cmp > 0) {
            return getNode(node.right,key);
        } else {
            return node;
        }
    }

    @Override
    public void add(K key, V value) {
        root = add(root,key,value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size ++;
            return new Node(key,value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = add(node.left, key, value);
        else if (cmp > 0) node.right = add(node.right, key, value);
        else node.value = value;

        return node;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root,key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left,key);
            return node;
        } else if (cmp > 0) {
            node.right = remove(node.right,key);
            return node;
        } else { // key == node.key
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }
            // 同时具有两个子节点
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }

    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            size --;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    private Node minimum(Node node) {
        if (node.left == null) return node;
        return minimum(node.left);
    }

    @Override
    public boolean contains(K key) {
        return getNode(root,key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root,key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root,key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn`t exist");
        }
        node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
