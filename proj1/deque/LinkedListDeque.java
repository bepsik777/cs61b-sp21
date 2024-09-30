package deque;

public class LinkedListDeque<T> {

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

    private int size;
    private Node<Integer> sentinel;

    public LinkedListDeque() {
        sentinel = new Node(1312, null, null);
        size = 0;
    }

    private void createFirstNode(T value) {
        Node<T> node = new Node<>(value, (Node<T>)sentinel, (Node<T>)sentinel);
        sentinel.prev = node;
        sentinel.next = node;
        size += 1;
    }

    public void addFirst(T value) {

        if (size == 0) {
            createFirstNode(value);
        } else {
            Node<T> newNode = new Node(value, null, null);
            Node<T> firstNode = sentinel.next;
            firstNode.prev = newNode;
            newNode.next = firstNode;
            newNode.prev = (Node<T>) sentinel;
            sentinel.next = newNode;
            size += 1;
        }
    }

    public void addLast(T value) {

        if (size == 0) {
            createFirstNode(value);
        } else {
            Node<T> newNode = new Node(value, null, null);
            Node<T> lastNode = sentinel.prev;
            lastNode.next = newNode;
            newNode.next = (Node<T>) sentinel;
            newNode.prev = lastNode;
            sentinel.prev = newNode;
            size += 1;
        }
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
        Node<T> last = sentinel.prev;
        last.prev.next = (Node<T>) sentinel;
        sentinel.prev = last.prev;
        last.next = null;
        last.prev = null;

        size -= 1;
        return last.item;
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


}
