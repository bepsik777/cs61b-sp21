package deque;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import static org.junit.Assert.assertTrue;


public class Main {
    private static void printTimeTable(Deque<Integer> list, Deque<Double> times, Deque<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < list.size(); i += 1) {
            int N = list.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    private static void timeDequeAddLastMethod() {
        ArrayDeque<Integer> ADeque = new ArrayDeque<>();
        LinkedListDeque<Integer> LLDeque = new LinkedListDeque<>();
        LinkedListDeque<Double> LLTimes = new LinkedListDeque<>();
        ArrayDeque<Double> ADTimes = new ArrayDeque<>();
        ArrayDeque<Integer> opCounts = new ArrayDeque<>();
        int[] numOfOps = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 120000};


        for (int numOfOp : numOfOps) {
            opCounts.addLast(numOfOp);
            ADeque.addLast(numOfOp);
            LLDeque.addLast(numOfOp);

            // Array Deque Timing
            ArrayDeque<Integer> AD = new ArrayDeque<>();
            Stopwatch swa = new Stopwatch();
            for (int j = 0; j < numOfOp; j++) {
                AD.addLast(j);
            }
            double elapsedTimeADeque = swa.elapsedTime();

            // Linked List Deque Timing
            LinkedListDeque<Integer> LLD = new LinkedListDeque<>();
            Stopwatch swl = new Stopwatch();
            for (int j = 0; j < numOfOp; j++) {
                LLD.addLast(j);
            }
            double elapsedTimeLDeque = swl.elapsedTime();

            ADTimes.addLast(elapsedTimeADeque);
            LLTimes.addLast(elapsedTimeLDeque);
        }

        System.out.println("Array Deque add last time table");
        printTimeTable(ADeque, ADTimes, opCounts);
        System.out.println();
        System.out.println("Linked List Deque add last time table");
        printTimeTable(LLDeque, LLTimes, opCounts);
    }

    public static void main(String[] args) {

    }
}
