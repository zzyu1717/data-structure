package zzy.binarysearchtree.test;

import org.junit.Before;
import org.junit.Test;
import zzy.binarysearchtree.BST;
import zzy.binarysearchtree.BST2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BSTTest {

    private BST2<Integer> bst;

    @Before
    public void init() {
        bst = new BST2<>();
    }

    @Test
    public void testBasic() {
        int[] arr = {5,3,6,8,2,4};
        BST<Integer> bst = new BST<>();

        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }

        bst.preOrder();

        System.out.println();

        System.out.println(bst);

        System.out.println();

        bst.inOrder();
    }

    @Test
    public void testRemoveMin() {

        Random random = new Random();
        int n = 1000;
        for (int i = 0; i < n; i++) {
            int num = random.nextInt(10000);
            bst.add(num);
        }

        List<Integer> list = new ArrayList<>();
        n = bst.size();
        for (int i = 0; i < n; i++) {
            list.add(bst.removeMin());
        }

        System.out.println(list);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i-1) > list.get(i)) {
                throw new RuntimeException("ERROR");
            }
        }
        System.out.println("test success");
    }

    @Test
    public void testRemove() {
        int[] arr = {5,3,6,8,2,4};

        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }
        bst.remove(5);
        System.out.println(bst);
    }

    @Test
    public void testTraverse() {
        int[] arr = {5,3,6,8,2,4};

        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }

        /*bst.preOrder();
        bst.preOrderNR1();
        bst.preOrderNR2();
        bst.preOrderNR3();
        */
        /*bst.inOrder();
        bst.inOrderNR1();
        bst.inOrderNR2();*/

        bst.postOrder();
        bst.postOrderNR1();
        bst.postOrderNR2();
    }

    @Test
    public void testFloor() {
        int[] arr = {5,3,6,8,2,4};

        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }

        Integer result = bst.floor(1);
        System.out.println(result);
    }

    @Test
    public void testCeiling() {
        int[] arr = {5,3,6,8,2,4};

        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }

        Integer result = bst.ceiling(1);
        System.out.println(result);
    }

    @Test
    public void testSelect() {
        int[] arr = {5,3,6,8,2,4};

        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }

        Integer result = bst.select(2);
        System.out.println(result);
    }

    @Test
    public void testRank() {
        int[] arr = {5,3,6,8,2,4};

        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }

        Integer result = bst.rank(8);
        System.out.println(result);
    }
}