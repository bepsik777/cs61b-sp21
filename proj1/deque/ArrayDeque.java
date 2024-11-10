package deque;

import java.lang.Iterable;
import java.util.Iterator;
import deque.interfaces.Deque;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    private class DequeIterator implements Iterator<T> {
        private int pointer;
        private T currValue;

        public DequeIterator() {
            this.pointer = nextFirst + 1;
        }

        public boolean hasNext() {
            return items[pointer] != null;
        }

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

    private int getUsageFactor() {
        return size * 100 / items.length;
    }

    private void mapItemsToNewArray(T[] newArr) {
        int currPointer;

        if (nextFirst + 1 >= items.length)  {
            currPointer = 0;
        } else {
            currPointer = nextFirst + 1;
        }

        int currIndexOfNewArr = 0;

        for (int i = 0; i < size; i++) {
            newArr[currIndexOfNewArr] = items[currPointer];

            if (currPointer >= items.length - 1) {
                currPointer = 0;
            } else {
                currPointer += 1;
            }
            currIndexOfNewArr += 1;
        }
    }

    private void shrinkArray() {
        int newSize = size * 4;
        T[] newArr = (T[]) new Object[newSize];

        mapItemsToNewArray(newArr);

        this.items = newArr;
    }

    private void growArray(int factor) {
        int newSize = items.length * factor;
        T[] newArr = (T[]) new Object[newSize];
        mapItemsToNewArray(newArr);

        this.items = newArr;
    }

    private void resizeArray() {
        int usageFactor = getUsageFactor();

        if (usageFactor < 25) {
            shrinkArray();
        } else {
            growArray(4);
        }

        nextFirst = items.length - 1;
        nextLast = size;
    }

    private boolean isResizeNeeded() {
        if (size == items.length) {
            return true;
        }
        return size >= 16 && getUsageFactor() < 25;
    }

    public void addFirst(T item) {
        if (isResizeNeeded()) {
            resizeArray();
        }

        items[nextFirst] = item;
        size += 1;

        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
    };

    public void addLast(T item) {
        if (isResizeNeeded()) {
            resizeArray();
        }

        items[nextLast] = item;
        size += 1;

        if (nextLast >= items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
    };

    public boolean isEmpty() {
        return size == 0;
    };

    public int size() {
        return size;
    };

    public void printDeque() {
        for (int i = 0; i <= size - 2; i ++) {
            System.out.print(get(i) + " -> ");
        }
        System.out.println(get(size - 1));
        System.out.println(size);
    };

    private void printWholeArray() {
        for (int i = 0; i < items.length - 1; i += 1) {
            System.out.print(items[i] + " -> ");
        }
        System.out.println(items[items.length - 1]);
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        int indexOfFirst;

        if (nextFirst + 1 >= items.length - 1) {
            indexOfFirst = 0;
        } else {
            indexOfFirst = nextFirst + 1;
        }

        T removed = items[indexOfFirst];
        items[indexOfFirst] = null;
        size -= 1;

        return removed;
    };

    public T removeLast() {
        if (size == 0) {
            return null;
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

        return removed;
    };

    public T get(int index) {
        int indexOfFirst = nextFirst + 1;

        if (indexOfFirst + index >= items.length) {
            return items[index - (items.length - indexOfFirst)];
        } else {
            return items[indexOfFirst + index];
        }
    };

    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

}
