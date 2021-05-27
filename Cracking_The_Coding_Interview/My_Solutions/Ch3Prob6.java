package Cracking_The_Coding_Interview.My_Solutions;

import java.util.NoSuchElementException;

public class Ch3Prob6<T> {
    private static class queueNode<T> {
        private T data;
        private queueNode<T> next;
        private queueNode<T> prev;
        private queueNode<T> nextSame;

        public queueNode(T data) {
            this.data = data;
        }
    }

    private queueNode<T> head;
    private queueNode<T> tail;
    private queueNode<T> catHead;
    private queueNode<T> dogHead;
    private queueNode<T> catTail;
    private queueNode<T> dogTail;
    private int size = 0;

    public void enqueue(T item) {
        queueNode<T> node = new queueNode<>(item);
        if (item instanceof Cat) {
            if (head == null) {
                head = node;
                tail = node;
                catHead = node;
                catTail = node;
            } else if (catHead == null) {
                catHead = node;
                catTail = node;
                node.prev = tail;
                tail.next = node;
                tail = tail.next;
            } else if (catHead != null) {
                catTail.nextSame = node;
                catTail = catTail.nextSame;
                node.prev = tail;
                tail.next = node;
                tail = tail.next;
            }
        } else if (item instanceof Dog) {
            if (head == null) {
                head = node;
                tail = node;
                dogHead = node;
                dogTail = node;
            } else if (dogHead == null) {
                dogHead = node;
                dogTail = node;
                node.prev = tail;
                tail.next = node;
                tail = tail.next;
            } else if (dogHead != null) {
                dogTail.nextSame = node;
                node.prev = tail;
                tail.next = node;
                dogTail = dogTail.nextSame;
                tail = tail.next;
            }
        }
        size++;
    }

    public T dequeueAny() {
        if (head == null) throw new NoSuchElementException();
        T data = head.data;
        if (head.data instanceof Cat) {
            if (catHead == catTail) catTail = catTail.next;
            catHead = catHead.nextSame;
        } else if (head.data instanceof Dog) {
            if (dogHead == dogTail) dogTail = dogTail.next;
            dogHead = dogHead.nextSame;
        }
        if (head == tail) tail = tail.next;
        head = head.next;
        return data;
    }

    public T dequeueDog() {
        if ((head == null) || (dogHead == null)) throw new NoSuchElementException();
        T data = dogHead.data;
        if (head.data instanceof Dog) {
            dequeueAny();
        } else {
            dogHead.prev.next = dogHead.next;
            if (dogHead == dogTail) dogTail = dogTail.next;
            dogHead = dogHead.next;
        }
        return data;
    }

    public T dequeueCat() {
        if ((head == null) || (catHead == null)) throw new NoSuchElementException();
        T data = catHead.data;
        if (head.data instanceof Cat) {
            dequeueAny();
        } else {
            catHead.prev.next = catHead.next;
            if (catHead == catTail) catTail = catTail.next;
            catHead = catHead.next;
        }
        return data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T peekAny() {
        if (head == null) throw new NoSuchElementException();
        return head.data;
    }

    public T peekDog() {
        if (dogHead == null) throw new NoSuchElementException();
        return dogHead.data;
    }

    public T peekCat() {
        if (catHead == null) throw new NoSuchElementException();
        return catHead.data;
    }
}