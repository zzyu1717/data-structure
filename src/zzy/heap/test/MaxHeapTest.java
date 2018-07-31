package zzy.heap.test;

import org.junit.Before;
import org.junit.Test;
import zzy.heap.MaxHeap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class MaxHeapTest {

    private MaxHeap<Integer> mh;

    @Before
    public void init() {
        mh = new MaxHeap();
    }

    @Test
    public void testBasic() {
        int n = 1000000;
        Random random = new Random();
        for (int i = 0; i <n; i++) {
            mh.add(random.nextInt(100000));
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(mh.extractMax());
        }

        for (int i = 1; i < n; i++) {
            if (list.get(i-1) < list.get(i)) {
                throw new RuntimeException("ERROR");
            }
        }
        System.out.println("test completed.");
    }
}