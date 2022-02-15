/*
 * Course:     SE 2811
 * Term:       Winter 2020-21
 * Assignment: Final Presentation
 * Author:     Mitchell Mahnke, Alex Moran
 * Date:       2/10/22
 */

package main;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Our implementation of a JavaFX Service
 * which represents a singe thread.
 */
public class FactoringService extends Service {

    static ConcurrentHashMap<Long, Set<Long>> prevRuns = new ConcurrentHashMap<>(1000);

    private long numToFactor;
    private long start;
    private long end;

    /**
     * Constructor containing only the number
     * to factor
     */
    public void load(long numToFactor) {
        this.numToFactor = numToFactor;
        this.start = -1;
        this.end = -1;
    }

    /**
     * Constructor containing only the number
     * to factor and start num
     */
    public void load(long numToFactor, long start) {
        this.numToFactor = numToFactor;
        this.start = start;
        this.end = -1;
    }

    /**
     * Constructor containing everything
     * other than a Collection of previous runs
     */
    public void load(long numToFactor, long start, long end) {
        this.numToFactor = numToFactor;
        this.start = start;
        this.end = end;
    }

    /**
     * This is the default constructor which should be used
     * for this implementation.
     * @param numToFactor - num being factored
     * @param start - starting number to test
     * @param end - final number to test
     * @param prev - previous runs
     */
    public void load(long numToFactor, long start, long end, Set<Long> prev) {
        this.numToFactor = numToFactor;
        this.start = start;
        this.end = end;
        if(prev!=null){
            prevRuns.put(numToFactor, prev);
        }
    }

    /**
     * Creating the factoring task
     * @return - the task which will run
     *           run a singular thread to
     *           factor the given number
     */
    @Override
    protected Task createTask() {
        Task task = new Task() {

            @Override
            protected Object call() {
                long startTime = System.nanoTime();
                long elapsedTime;

                // Determining if any of the
                // other constructors were used
                // and if so, changing some of the
                // parameters
                if (start != -1) {
                    if (end != -1) {
                        // do nothing
                    } else {
                        end = numToFactor;
                    }
                } else {
                    start = 1;
                    end = numToFactor;
                }

                // Creating a new HashSet for this threads progress
                Set<Long> factors = new HashSet<>();

                // Printing this info to the console for demonstration purposes
                System.out.println("start: "+start);
                System.out.println("Number to factor: "+numToFactor);
                System.out.println("end: "+end);

                // setting the progress to 0%
                updateProgress(0, 1);

                // Checking to see if this run has occurred before
                boolean previouslyRan = prevRuns.containsKey(numToFactor);
                if (previouslyRan) {
                    System.out.println("number found in previous runs: " + previouslyRan);
                    factors = prevRuns.get(numToFactor);
                } else {
                    // Factor number
                    for (long i = start; i <= numToFactor && i <= end; i++) {
                        if (i == 0) {
                            i++;
                        }
                        if (numToFactor % i == 0) {
                            factors.add(i);
                        }
                        // Updating the progress of this thread in relation
                        // to the entirety of the task.
                        updateProgress(i, end);

                    }
                    //Store the results into the previous runs
                    Set<Long> results = prevRuns.putIfAbsent(numToFactor, factors);
                    if(results!=null){
                        results.addAll(factors);
                        prevRuns.put(numToFactor,results);
                    }
                }

                // Printing out the time taken to factor, and printing the factors
                elapsedTime = (System.nanoTime() - startTime) / 1000000;
                System.out.println(elapsedTime + "ms");
                // updating the progress to 100%
                updateProgress(1, 1);
                System.out.println(prevRuns.get(numToFactor).toString());
                return prevRuns.get(numToFactor);
            }
        };
        return task;
    }

}
