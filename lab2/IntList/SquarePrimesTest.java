package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesNoChange() {
        IntList lst = IntList.of(6, 4, 8, 16, 24, 30);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("6 -> 4 -> 8 -> 16 -> 24 -> 30", lst.toString());
        assertFalse(changed);
    }

    @Test
    public void testSquarePrimesOnlyPrimes() {
        IntList lst = IntList.of(1, 5, 7, 11);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("1 -> 25 -> 49 -> 121", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesTwoPrimes() {
        IntList lst = IntList.of(7, 4, 8, 7);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("49 -> 4 -> 8 -> 49", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesFirstIsPrime() {
        IntList lst = IntList.of(7, 9, 4, 8);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("49 -> 9 -> 4 -> 8", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesLastIsPrime() {
        IntList lst = IntList.of(6, 4, 8, 7);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("6 -> 4 -> 8 -> 49", lst.toString());
        assertTrue(changed);
    }
}
