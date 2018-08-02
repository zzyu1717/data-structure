package zzy.unionfind;

import zzy.unionfind.impl.*;

import java.util.Random;

/**
 * created in 10:49 2018/8/2 by zhaozhenyu.
 */

public class Main {
    private static double testUF(UF uf, int m) {
        int size = uf.size();
        Random random = new Random();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a,b);
        }
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a,b);
        }

        long endTime = System.currentTimeMillis();

        return (endTime-startTime)/1000.0;
    }

    public static void main(String[] args) {
        int size = 10000000;
        int m = 10000000;
      /*
        UnionFind1 uf1 = new UnionFind1(size);
        System.out.println("UnionFind1 : " + testUF(uf1,m) + " s");
        UnionFind2 uf2 = new UnionFind2(size);
        System.out.println("UnionFind2 : " + testUF(uf2,m) + " s");
        */
        UnionFind3 uf3 = new UnionFind3(size);
        System.out.println("UnionFind3 : " + testUF(uf3,m) + " s");
        UnionFind4 uf4 = new UnionFind4(size);
        System.out.println("UnionFind4 : " + testUF(uf4,m) + " s");
        // 性能最好
        UnionFind5 uf5 = new UnionFind5(size);
        System.out.println("UnionFind5 : " + testUF(uf5,m) + " s");
        //递归有开销
        UnionFind6 uf6 = new UnionFind6(size);
        System.out.println("UnionFind6 : " + testUF(uf6,m) + " s");

    }
}
