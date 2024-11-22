package deque.test;

import deque.ArrayDeque;
import org.junit.Test;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;


public class ArrayDequeTest {

    @Test
    public void testGetWithZeroElements() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        assertNull(list.get(1));
    }


    @Test
    public void testAddLast() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        Integer[] compareArr = new Integer[1000];


        for (int i = 0; i < 1000; i += 1) {
            int random = (int) (Math.random() * (1000 - 0) + 0);
            list.addLast(random);
            compareArr[i] = random;
        }

        for (int i = 0; list.size() < 100; i += 1) {
            assertEquals(compareArr[i], list.get(i));
        }
    }

    @Test
    public void testAddFirst() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        Integer[] compareArr = new Integer[100];

        for (int i = 0; i < compareArr.length; i++) {
            compareArr[i] = i;
        }
        for (int i = 1; i < compareArr.length; i++) {
            list.addLast(i);
        }

        list.addFirst(0);
        assertEquals(list.get(0), compareArr[0]);
    }

    @Test
    public void removeFirstWhenLengthIsOne() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        list.addLast(0);
        assertEquals(0, (int) list.removeFirst());
    }

    @Test
    public void testFillUpEmptyThenFillUpWithRemoveFirst() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        for (int i = 0; i < 10; i += 1) {
            list.addLast(i);
        }

        for (int i = 0; i < 10; i += 1) {
            int removed = list.removeFirst();
            assertEquals(removed, i);
        }

        for (int i = 0; i < 10; i += 1) {
            list.addLast(i);
        }

        assertEquals(0, (int) list.removeFirst());
    }

    @Test
    public void testFillUpEmptyThenFillUpWithRemoveLast() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        for (int i = 0; i < 10; i += 1) {
            list.addLast(i);
        }

        for (int i = 9; i >= 0; i -= 1) {
            int removed = list.removeLast();
            assertEquals(removed, i);
        }

        for (int i = 0; i < 10; i += 1) {
            list.addLast(i);
        }

        assertEquals(9, (int) list.removeLast());
    }

    @Test
    public void randomizedRemoveFirst() {
        ArrayDeque<Integer> listTwo = new ArrayDeque<>();
        java.util.ArrayDeque<Integer> listOne = new java.util.ArrayDeque<>();

        for (int i = 0; i < 10000; i += 1) {
            int random = StdRandom.uniform(0, 2);

            if (random == 0) {
                listOne.addLast(i);
                listTwo.addLast(i);
            } else if (random == 1 && !listOne.isEmpty() && !listTwo.isEmpty()) {
                int removedOne = listOne.removeFirst();
                int removedTwo = listTwo.removeFirst();
                assertEquals(removedOne, removedTwo);
            }
        }
    }

    @Test
    public void randomizedRemoveFirstAndLast() {
        ArrayDeque<Integer> myDeque = new ArrayDeque<>();
        java.util.ArrayDeque<Integer> javaDeque = new java.util.ArrayDeque<>();

        for (int i = 0; i < 10000; i++) {
            int random = StdRandom.uniform(0, 4);

            if (random == 0) {
                myDeque.addFirst(i);
                javaDeque.addFirst(i);
            } else if (random == 1) {
                myDeque.addLast(i);
                javaDeque.addLast(i);
            } else if (!myDeque.isEmpty() && !javaDeque.isEmpty()) {
                if (random == 2) {
                    int myRemoved = myDeque.removeFirst();
                    int javaRemoved = javaDeque.removeFirst();
                    assertEquals(javaRemoved, myRemoved);
                } else if (random == 3) {
                    int myRemoved = myDeque.removeLast();
                    int javaRemoved = javaDeque.removeLast();
                    assertEquals(javaRemoved, myRemoved);
                }
            }
            assertEquals(javaDeque.size(), myDeque.size());
        }
    }

    @Test
    public void getRandomizedTest() {
        ArrayDeque<Integer> myDeque = new ArrayDeque<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            int random = StdRandom.uniform(0, 10000);
            myDeque.addLast(random);
            list.add(random);
        }

        for (int i = 0; i < 10000; i++) {
            int random = StdRandom.uniform(0, 10000);
            assertEquals(list.get(random), myDeque.get(random));
        }
    }

//    @Test
//    public void resizingTest() {
//        ArrayDeque<Integer> myDeque = new ArrayDeque<>();
//
//        for (int i = 0; i < 10000; i++) {
//            int random = StdRandom.uniform(0, 4);
//
//            if (random == 0) {
//                myDeque.addFirst(i);
//            } else if (random == 1) {
//                myDeque.addLast(i);
//            } else if (!myDeque.isEmpty()) {
//                if (random == 2) {
//                    myDeque.removeFirst();
//                } else if (random == 3) {
//                    myDeque.removeLast();
//                }
//            }
//            if (myDeque.getContainerSize() >= 16) {
//                assertTrue("should be at least 25%",(double) myDeque.size()  / myDeque.getContainerSize() >= 0.25);
//            }
//        }
//    }
}


