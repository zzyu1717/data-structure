package zzy.stack;

public interface Stack<E> {

    void push(E e);

    int getSize();

    boolean isEmpty();

    E pop();

    E peek();

}
