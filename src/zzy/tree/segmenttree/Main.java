package zzy.tree.segmenttree;

/**
 * created in 11:17 2018/8/1 by zhaozhenyu.
 */

public class Main {
    public static void main(String[] args) {
        Integer[] data = {-3,6,8,9,0,2,-4,5};


        // 区间求和
        SegmentTree<Integer> segmentTree = new SegmentTree<>(data, (a,b)->a+b);
        // [23,20,3,3,17,2,1,-3,6,8,9,0,2,-4,5,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null]

        System.out.println(segmentTree);

        segmentTree.set(0,-2);

        System.out.println(segmentTree);


        // 求区间最大值
        //segmentTree = new SegmentTree<>(data, (a,b)->Math.max(a,b));
        // [9,9,5,6,9,2,5,-3,6,8,9,0,2,-4,5,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null]
        //System.out.println(segmentTree);

        System.out.println(segmentTree.query(2,6));
        System.out.println(segmentTree.query(2,5));


    }
}
