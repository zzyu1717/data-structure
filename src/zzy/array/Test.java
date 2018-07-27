package zzy.array;

/**
 * created in 23:17 2018/7/24 by zhaozhenyu.
 */

public class Test {

    public static void main(String[] args) {
        MyArray<Integer> arr = new MyArray<>(5);

        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }

        System.out.println(arr);

        arr.add(1,100);

        System.out.println(arr);

        arr.remove(0);

        System.out.println(arr);

        System.out.println(null == null);

    }
}
