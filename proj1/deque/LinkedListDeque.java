package deque;

import deque.interfaces.Deque;
import java.lang.Iterable;
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private class Node<K> {
        private K item;
        private Node<T> next;
        private Node<T> prev;

        public Node(K value, Node<T> nextNode, Node<T> prevNode) {
            item = value;
            next = nextNode;
            prev = prevNode;
        }
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        int pointer;
        Node<T> currNode;

        public LinkedListDequeIterator() {
            pointer = 0;
            currNode = (Node<T>) sentinel;
        }

        public boolean hasNext() {
            return pointer < size;
        }

        public T next() {
            if (hasNext()) {
                currNode = currNode.next;
                pointer += 1;
                return currNode.item;
            }
            return null;
        }
    }

    private int size;
    private Node<Integer> sentinel;

    public LinkedListDeque() {
        sentinel = new Node<>(1312, null, null);
        size = 0;
    }


    public void addFirst(T item) {
        Node<T> newNode = new Node(item, null, null);

        if (size == 0) {
            sentinel.prev = newNode;
            newNode.next = (Node<T>) sentinel;
        } else {
            Node<T> firstNode = sentinel.next;
            firstNode.prev = newNode;
            newNode.next = firstNode;
        }
        newNode.prev = (Node<T>) sentinel;


        sentinel.next = newNode;
        size += 1;
    }

    public void addLast(T item) {
        if (sentinel.next == null) {
            addFirst(item);
            return;
        }

        Node<T> lastNode = sentinel.prev;
        Node<T> newNode = new Node<>(item, (Node<T>) sentinel, lastNode);

        lastNode.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node<T> first = sentinel.next;

        sentinel.next = first.next;
        first.next.prev = (Node<T>) sentinel;
        first.next = null;
        first.prev = null;

        size -= 1;

        return first.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        Node<T> lastNode = sentinel.prev;

        if (size == 1) {
            sentinel.prev = null;
        } else {
            sentinel.prev = lastNode.prev;
            lastNode.prev.next = (Node<T>) sentinel;
        }

        size -= 1;
        return lastNode.item;
    }

    public T get(int index) {
        if (index < size) {
            Node<T> curr = sentinel.next;
            for (int i = 0; i <= index; i += 1) {
                curr = curr.next;
            }
            return curr.item;
        }
        return null;
    }

    public T getRecursive(int index) {
        if (index < size) {
            return getRecursive(index, sentinel.next);
        }

        return null;
    }

    private T getRecursive(int index, Node<T> currNode) {
        if (index == 0) {
            return currNode.item;
        }

        currNode = currNode.next;
        return getRecursive(index - 1, currNode);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        printDeque(sentinel.next);
    }

    private void printDeque(Node<T> node) {
        if (size == 0) {
            return;
        }

        if (node.next == sentinel) {
            System.out.println(node.item);
            return;
        }


        System.out.print(node.item + " -> ");
        printDeque(node.next);
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (o.getClass() != this.getClass()) {
            return false;
        }

        LinkedListDeque<T> compared = (LinkedListDeque<T>) o;

        if (compared.size() != this.size) {
            return false;
        }

        for (int i = 0; i < this.size; i += 1) {
            if (!compared.get(i).equals(this.get(i))) {
                return false;
            }
        }

        return true;
    }
}
