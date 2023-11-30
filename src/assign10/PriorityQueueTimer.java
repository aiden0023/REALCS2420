package assign10;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static assign10.FindKLargest.findKLargestHeap;
import static assign10.FindKLargest.findKLargestSort;

public class PriorityQueueTimer {
    public static void main(String[] args) {
        // Identify your problem size: N
        // We will generally want to find runtimes at regular intervals of N
        // For example, N = 100000, 110000, 120000, 130000, ..., 200000
        // Smallest value of N.
        int nMin = 1000;
        // Largest value of N.
        int nMax = 20000;
        // Increment value for N.
        int nStep = 1000;
        // Setup storage for problem sizes and average runtimes.
        ArrayList<Integer> problemSizes = new ArrayList<>();
        ArrayList<Double> addTimes = new ArrayList<>();
        ArrayList<Double> peekTimes = new ArrayList<>();
        ArrayList<Double> extractMaxTimes = new ArrayList<>();

        for (int n = nMin; n <= nMax; n += nStep) {
            // We will generally want to run the timing test for N
            // multiple times to smooth out any abnormalities.
            // Number of times to run each test.
            // Larger values yield more reliable averages.
            int loopCount = 1000;
            // Now we run 100 timing tests for each value of N.
            // Variables to keep track of time in nanoseconds.
            long start, end, extra;
            Random rand = new Random();

            // Placeholder for any shared setup for the timing test.
            List<Integer> list = new ArrayList<>();

            // Warm up the system
            start = System.nanoTime();
            while (System.nanoTime() - start <= 1_000_000_000) {
                  // Warm-up by adding an element
            }

            ArrayList<Double> allAddTimes = new ArrayList<>();
            for (int l = 0; l < loopCount; l++) {
                // Timing for add method
                for (int i = 0; i < n; i++) {
                    list.add(rand.nextInt());
                }
                start = System.nanoTime();
                findKLargestSort(list, n);
                end = System.nanoTime();
                allAddTimes.add((double) (end - start));
            }
            double addTime = 0;
            for (Double time : allAddTimes) {
                addTime+=time;
            }
            addTime/=loopCount;


            // Timing for peek method
            /*start = System.nanoTime();
            for (int l = 0; l < loopCount; l++) {
                maxHeap.peek();
            }
            end = System.nanoTime();
            double peekTime = (end - start) / (double) loopCount;

            // Timing for extractMax method
            start = System.nanoTime();
            for (int l = 0; l < loopCount; l++) {
                maxHeap.extractMax();
            }
            end = System.nanoTime();
            double extractMaxTime = (end - start) / (double) loopCount;*/

            // Store data size and average runtime
            problemSizes.add(n);
            addTimes.add(addTime);
            //peekTimes.add(peekTime);
            //extractMaxTimes.add(extractMaxTime);

            // Print results to console if you want
            System.out.println(n + "\t" + addTime);
            //System.out.println(n + "\t " + addTime + "\t " + peekTime + "\t " + extractMaxTime);
        }

        // Print problemSizes and averageTimes
        System.out.println("\nproblemSizes = " + problemSizes);
        System.out.println("addTimes = " + addTimes);
        System.out.println("peekTimes = " + peekTimes);
        System.out.println("extractMaxTimes = " + extractMaxTimes);
    }
}
