package deque;

import java.lang.Iterable;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    private class DequeIterator implements Iterator<T> {
        private int pointer;
        private T currValue;

        public DequeIterator() {
            this.pointer = nextFirst + 1 >= items.length ? 0 : nextFirst + 1;
        }

        @Override
        public boolean hasNext() {
            return items[pointer] != null;
        }

        @Override
        public T next() {
            currValue = items[pointer];

            if (pointer == items.length - 1) {
                pointer = 0;
            } else {
                pointer += 1;
            }

            return currValue;
        }
    }

    public ArrayDeque() {
        this.items = (T[]) new Object[8];
        this.size = 0;
        this.nextFirst = 4;
        this.nextLast = 5;
    }

    public int getContainerSize() {
        return items.length;
    }

    private int getUsageFactor(int size) {
        return size * 100 / items.length;
    }

    private void mapItemsToNewArray(T[] newArr) {
        for (int i = 0; i < size; i += 1) {
            T mapped = this.get(i);
            newArr[i] = mapped;
        }
    }

    public void shrinkArray() {
        int newSize = size * 3;
        T[] newArr = (T[]) new Object[newSize];
        mapItemsToNewArray(newArr);

        items = newArr;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    private void growArray(int factor) {
        int newSize = items.length * factor;
        T[] newArr = (T[]) new Object[newSize];
        mapItemsToNewArray(newArr);

        items = newArr;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            growArray(4);
        }

        items[nextFirst] = item;
        size += 1;

        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            growArray(4);
        }

        items[nextLast] = item;
        size += 1;

        if (nextLast >= items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        if (getUsageFactor(size - 1) < 25) {
            shrinkArray();
        }

        int indexOfFirst;

        if (nextFirst + 1 >= items.length) {
            indexOfFirst = 0;
        } else {
            indexOfFirst = nextFirst + 1;
        }

        T removed = items[indexOfFirst];
        size -= 1;
        items[indexOfFirst] = null;


        nextFirst = nextFirst == items.length - 1 ? 0 : nextFirst + 1;

        return removed;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        if (getUsageFactor(size - 1) < 25) {
            shrinkArray();
        }

        int indexOfLast;

        if (nextLast - 1 < 0) {
            indexOfLast = items.length - 1;
        } else {
            indexOfLast = nextLast - 1;
        }

        T removed = items[indexOfLast];
        items[indexOfLast] = null;
        size -= 1;

        nextLast = nextLast == 0 ? items.length - 1 : nextLast - 1;

        return removed;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i <= size - 2; i++) {
            System.out.print(get(i) + " -> ");
        }
        System.out.println(get(size - 1));
    }


    public void printWholeArray() {
        for (int i = 0; i < items.length - 1; i += 1) {
            System.out.print(items[i] + " -> ");
        }
        System.out.println(items[items.length - 1]);
    }

    @Override
    public T get(int index) {
        int indexOfFirst = nextFirst + 1 >= items.length ? 0 : nextFirst + 1;

        if (indexOfFirst + index >= items.length) {
            return items[index - (items.length - indexOfFirst)];
        } else {
            return items[indexOfFirst + index];
        }
    }

    ;

    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
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

        ArrayDeque<T> compared = (ArrayDeque<T>) o;

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
