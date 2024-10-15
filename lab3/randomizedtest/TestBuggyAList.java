package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {

    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correctList = new AListNoResizing<>();
        BuggyAList<Integer> buggyList = new BuggyAList<>();

        for (int i = 0; i < 3; i += 1) {
            correctList.addLast(i);
            buggyList.addLast(i);
        }

        for (int i = 0; i < 3; i += 1) {
            int resOne = correctList.removeLast();
            int resTwo = buggyList.removeLast();
            assertEquals(resOne, resTwo);
        }
    }

    @Test
    public void randomizedTest() {
        int N = 2000;

        AListNoResizing<Integer> correctList = new AListNoResizing<>();
        BuggyAList<Integer> buggyList = new BuggyAList<>();

        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);

            if (operationNumber == 0) {
                int ranValue = StdRandom.uniform(0, 100);
                correctList.addLast(ranValue);
                buggyList.addLast(ranValue);
            } else if (operationNumber == 1 && correctList.size() > 0 && buggyList.size() > 0) {
                int removedCorrect = correctList.removeLast();
                int removedBuggy = buggyList.removeLast();
                assertEquals(removedBuggy, removedCorrect);
            } else {
                int corrSize = correctList.size();
                int buggySize = buggyList.size();
                assertEquals(corrSize, buggySize);
            }
        }
    }
 }
