package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        int[] lengthList = {16000, 32000, 64000, 128000, 256000, 512000};
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCount = new AList<>();

        for (int len: lengthList) {
            AList<Integer> testedList = new AList<>();
            Stopwatch watch = new Stopwatch();

            for (int i = 0; i < len; i++) {
                testedList.addLast(i);
            }

            double time = watch.elapsedTime();

            Ns.addLast(len);
            times.addLast(time);
            opCount.addLast(len);
        }

        printTimingTable(Ns, times, opCount);
    }
}
