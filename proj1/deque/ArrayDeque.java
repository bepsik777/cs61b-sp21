package deque;

import java.lang.Iterable;
import java.util.Iterator;
import deque.interfaces.Deque;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

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
        int currPointer = nextFirst + 1;

        int currIndexOfNewArr = 0;

        for (int i = 0; i < size; i++) {
            newArr[currIndexOfNewArr] = items[currPointer];

            if (currPointer >= items.length) {
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

    public void printWholeArray() {
        for (int i = 0; i < items.length - 1; i += 1) {
            System.out.print(items[i] + " -> ");
        }
        System.out.println(items[items.length - 1]);
    }

    private void resizeArray() {
        int usageFactor = getUsageFactor();

        if (usageFactor < 25) {
            shrinkArray();
        } else {
            growArray(4);
        }
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
        nextFirst -= 1;
    };

    public void addLast(T item) {

    };

    public boolean isEmpty() {
        return false;
    };

    public int size() {
        return 0;
    };

    public void printDeque() {

    };

    public T removeFirst() {
        return null;
    };

    public T removeLast() {
        return null;
    };

    public T get(int index) {
        return null;
    };

    public Iterator<T> iterator() {
        return null;
    }

}
