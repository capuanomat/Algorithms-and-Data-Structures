package Cracking_The_Coding_Interview.My_Solutions;

import java.util.EmptyStackException;

public class Ch3Prob2<T extends Comparable <T>> {

    public static class StackNode<T> {
        private T data;
        private StackNode<T> previousMin;

        public StackNode(T data) {
            this.data = data;
        }
    }

    private static final int DEFAULT_CAPACITY = 10;
    private StackNode<T>[] backingArray;
    private int size = 0;
    private StackNode<T> currentMin;


    public Ch3Prob2(int capacity) {
        backingArray = new StackNode[capacity];
    }

    public Ch3Prob2() {
        backingArray = new StackNode[DEFAULT_CAPACITY];
    }

    public void push(T data) {
        // TODO: Add a check if adding would exceed array capacity, if yes, resize
        StackNode<T> t = new StackNode<>(data);
        t.previousMin = currentMin;
        if (size == 0) {
            currentMin = t;
        } else if (data.compareTo(currentMin.data) < 0) {
            currentMin = t;
        }
        backingArray[size] = t;
        size++;
    }

    public T pop() {
        if (size == 0) throw new EmptyStackException();
        size--;
        StackNode<T> removed = backingArray[size];
        backingArray[size] = null;

        if (removed == currentMin) {
            currentMin = removed.previousMin;
        }
        return removed.data;
    }

    public T min() {
        return currentMin.data;
    }

    public T peek() {
        return backingArray[size - 1].data;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}


