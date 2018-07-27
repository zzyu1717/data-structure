package zzy.queue.test;

import org.junit.jupiter.api.Test;
import zzy.queue.impl.LinkedListQueue;


public class LinkedListQueueTest {

    @Test
    public void testLinkedListQueue() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            if (i % 3 == 2) {
                queue.dequeue();
            }
            System.out.println(queue);
        }
    }
}