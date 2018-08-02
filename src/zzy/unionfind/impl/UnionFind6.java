package zzy.unionfind.impl;

import zzy.unionfind.UF;

/**
 * created in 10:36 2018/8/2 by zhaozhenyu.
 */

/**
 * 基于rank的优化
 *
 * find方法添加路径压缩（path compression)
 *
 * 优化路径压缩过程,一次使所有节点直接指向根节点
 */
public class UnionFind6 implements UF {

    private int[] parent;

    private int[] rank; // rank[i]表示以i为根的集合所表示的树的层数

    public UnionFind6(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // 查找过程，查找元素p所对应的集合编号
    // O(h)复杂度，h为树的高度
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound");
        }
        // 路径压缩
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    @Override
    public int size() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return ;
        // 减少树的高度
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        } else {
            parent[pRoot] = qRoot;
            rank[qRoot]++;
        }
    }
}
