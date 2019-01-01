package Data_Structures;

import java.util.EmptyStackException;

/**
 * This is the Stack implementation from "Cracking the Coding Interview".
 * TODO: Go back and check the implementation from CS1332
 * @param <T>
 */
public class MyStack<T> {

    /* Note the use of a static class below */
    private static class StackNode<T> {
        private T data;
        private StackNode<T> next;

        public StackNode(T data) {
            this.data = data;
        }
    }

    private StackNode<T> top;

    public void push(T item) {
        StackNode<T> t = new StackNode<>(item);
        t.next = top;
        top = t;
    }

    public T pop() {
        if (top == null) { throw new EmptyStackException(); }
        T item = top.data;
        top = top.next;
        return item;
    }

    public T peek() {
        if (top == null) {throw new EmptyStackException(); }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
