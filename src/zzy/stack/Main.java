package zzy.stack;

/**
 * created in 12:22 2018/7/27 by zhaozhenyu.
 */

import zzy.stack.impl.ArrayStack;
import zzy.stack.impl.LinkedListStack;

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
    public static double testQueue(Stack<Integer> stack, int opCount) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < opCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i <opCount; i++) {
            stack.pop();
        }

        long end = System.currentTimeMillis();

        return (end-start)/1000.0;
    }

    public static void main(String[] args) {
        int opCount = 10000000;

        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        double time1 = testQueue(linkedListStack,opCount);
        System.out.println("LinkedListStack: time1 = " + time1);


        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double time2 = testQueue(arrayStack,opCount);
        System.out.println("ArrayStack: time2 = " + time2);
    }

}
