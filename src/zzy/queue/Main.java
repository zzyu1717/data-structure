package zzy.queue;

/**
 * created in 12:22 2018/7/27 by zhaozhenyu.
 */

import zzy.queue.impl.ArrayQueue;
import zzy.queue.impl.LoopQueue;

import java.util.Random;

/**
 * 测试arrayQueue与loopQueue
 * arrayQueue 出队时间复杂度为O(n), loopQueue操作时间复杂度均为O(1)
 */
public class Main {

    private static Random random = new Random();

    /**
     * 返回 秒
     * @param que 测试的队列实例
     * @param opCount 操作的次数
     * @return
     */
    public static double testQueue(Queue<Integer> que, int opCount) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < opCount; i++) {
            que.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i <opCount; i++) {
            que.dequeue();
        }

        long end = System.currentTimeMillis();

        return (end-start)/1000.0;
    }

    public static void main(String[] args) {
        int opCount = 100000;

        /**
         * testQueue 时间复杂度相当于O(n^2)
         */
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue,opCount);
        System.out.println("ArrayQueue: time1 = " + time1);

        /**
         * testQueue 时间复杂度此时相当于O(n)
         */
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue,opCount);
        System.out.println("LoopQueue: time2 = " + time2);
    }

}
