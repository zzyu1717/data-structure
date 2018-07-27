package zzy.queue;

import zzy.queue.impl.LoopQueue;

/**
 * created in 11:39 2018/7/27 by zhaozhenyu.
 */

public class QueueTest {
    public static void main(String[] args) {
        LoopQueue<Integer> lq = new LoopQueue<>(5);

        lq.enqueue(1);
        lq.enqueue(2);
        lq.enqueue(3);
        lq.enqueue(4);
        lq.enqueue(5);
        System.out.println(lq);
        lq.enqueue(6);

        System.out.println(" 扩容： "+lq);

        lq.dequeue();
        lq.dequeue();
        lq.dequeue();
        lq.dequeue();

        System.out.println(" 减容： "+lq);

        lq.enqueue(1);
        lq.enqueue(2);
        lq.enqueue(3);

        lq.dequeue();
        lq.dequeue();
        lq.enqueue(4);

        System.out.println(lq);
    }
}
