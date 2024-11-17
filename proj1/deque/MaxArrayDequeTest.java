package deque;

import net.sf.saxon.expr.Component;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;


public class MaxArrayDequeTest {

    @Test
    public void testWithIntComparator() {
        MaxArrayDeque<Integer> l = new MaxArrayDeque<>(new IntComparator());
        for (int i = 0; i < 100; i += 1) {
            l.addLast((i));
        }

        assertEquals(99, (int) l.max());
    }

    @Test
    public void testWithStringComparator() {
        MaxArrayDeque<String> l = new MaxArrayDeque<>(new StringLengthComparator());
        String[] arrOfStrings = {"hello", "goodbye", "such a long string", "acab"};

        for (String s : arrOfStrings) {
            l.addLast(s);
        }

        assertEquals(arrOfStrings[2], l.max());
    }

    @Test
    public void testMaxWithAlphabeticalComparatorArgument() {
        MaxArrayDeque<String> l = new MaxArrayDeque<>(new StringLengthComparator());
        String[] arrOfStrings = {"hello", "goodbye", "such a long string", "acab", "zzz"};

        for (String s : arrOfStrings) {
            l.addLast(s);
        }
        assertEquals(arrOfStrings[3], l.max(new StringAlphabeticalComparator()));
    }


    private static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer one, Integer two) {
            return one - two;
        }
    }

    private static class StringLengthComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    private static class StringAlphabeticalComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return b.compareTo(a);
        }
    }

}
