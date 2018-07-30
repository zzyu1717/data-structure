package zzy.linkedlist;

/**
 * created in 16:53 2018/7/27 by zhaozhenyu.
 */

public class Test {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            list.addFirst(i);
            System.out.println(list);
        }

        list.set(2,666);

        System.out.println(list);

        list.remove(2);

        System.out.println(list);

        list.removeFirst();
        System.out.println(list);

        list.removeLast();
        System.out.println(list);

        list.removeElement(3);

        System.out.println(list);

    }
}
