package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private class Node<K> {
        private K item;
        private Node<T> next;
        private Node<T> prev;

        Node(K value, Node<T> nextNode, Node<T> prevNode) {
            item = value;
            next = nextNode;
            prev = prevNode;
        }
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        int pointer;
        Node<T> currNode;

        LinkedListDequeIterator() {
            pointer = 0;
            currNode = (Node<T>) sentinel;
        }

        @Override
        public boolean hasNext() {
            return pointer < size;
        }

        @Override
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
        sentinel = new Node<>(-1, null, null);
        size = 0;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        Node<T> lastNode = sentinel.prev;

        if (size == 1) {
            sentinel.prev = null;
            sentinel.next = null;
        } else {
            sentinel.prev = lastNode.prev;
            lastNode.prev.next = (Node<T>) sentinel;
        }

        size -= 1;
        return lastNode.item;
    }

    @Override
    public T get(int index) {
        if (index < size) {
            Node<T> curr = (Node<T>) sentinel;
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

    @Override
    public int size() {
        return size;
    }

    @Override
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

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Deque)) {
            return false;
        }

        Deque<T> compared = (Deque<T>) o;

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
