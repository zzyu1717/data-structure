package zzy.heap.test;

import zzy.heap.MaxHeap;

/**
 * created in 17:38 2018/7/31 by zhaozhenyu.
 */

public class Test {
    public static void main(String[] args) {
        MaxHeap<Integer> mh = new MaxHeap<>();

        mh.add(34);
        mh.add(56);
        mh.add(20);
        mh.add(38);
        mh.add(18);
        mh.add(4);
        mh.add(54);


        System.out.println(mh.size());

        System.out.println(mh.extractMax());
        System.out.println(mh.extractMax());
        System.out.println(mh.extractMax());
        System.out.println(mh.extractMax());
        System.out.println(mh.extractMax());
        System.out.println(mh.extractMax());
    }
}
