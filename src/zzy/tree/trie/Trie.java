package zzy.tree.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * created in 16:13 2018/8/1 by zhaozhenyu.
 */

public class Trie {

    private class Node {
        boolean isWord;
        Map<Character,Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new HashMap<>();
        }

        public Node() {
            this(false);
        }
    }

    /********************************************************/

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    // 向Trie中添加一个新的单词word
    public void add(String word) {

        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) { // 新元素，没有被添加过
            cur.isWord = true;
            size ++;
        }
    }

    /**
     * 查询是否包含
     * @return
     */
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    // 查询是否在Trie中有单词以prefix为前缀
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

    // 获得Trie中存储的单词数量
    public int size() {
        return size;
    }
}
