package main;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class FactoringService extends Service {

    static ConcurrentHashMap<Long, Set<Long>> prevRuns = new ConcurrentHashMap<>(1000);

    private long numToFactor;
    private long start;
    private long end;

    public void load(long numToFactor) {
        this.numToFactor = numToFactor;
        this.start = -1;
        this.end = -1;
    }

    public void load(long numToFactor, long start) {
        this.numToFactor = numToFactor;
        this.start = start;
        this.end = -1;
    }

    public void load(long numToFactor, long start, long end) {
        this.numToFactor = numToFactor;
        this.start = start;
        this.end = end;
    }

    public void load(long numToFactor, long start, long end, Set<Long> prev) {
        this.numToFactor = numToFactor;
        this.start = start;
        this.end = end;
        if(prev!=null){
            prevRuns.put(numToFactor, prev);
        }
    }

    @Override
    protected Task createTask() {
        Task task = new Task() {

            @Override
            protected Object call() {
                long startTime = System.nanoTime();
                long elapsedTime;
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
                Set<Long> factors = new HashSet<Long>();

                System.out.println("start: "+start);
                System.out.println("Number to factor: "+numToFactor);
                System.out.println("end: "+end);
                updateProgress(0, 1);
                boolean previouslyRan = prevRuns.containsKey(numToFactor);
                if(previouslyRan) {
                    System.out.println("number found in previous runs: " + previouslyRan);
                    factors = prevRuns.get(numToFactor);
                } else { // Factor number
                    for (long i = start; i <= numToFactor && i <= end; i++) {
                        if (i == 0) {
                            i++;
                        }
                        if (numToFactor % i == 0) {
                            factors.add(i);
                        }
                        updateProgress(i, end);

                    }
                    //Store the results into the previous runs
                    Set<Long> results = prevRuns.putIfAbsent(numToFactor, factors);
                    if(results!=null){
                        results.addAll(factors);
                        prevRuns.put(numToFactor,results);
                    }
                }
                elapsedTime = (System.nanoTime() - startTime) / 1000000;
                System.out.println(elapsedTime + "ms");
                updateProgress(1, 1);
                System.out.println(prevRuns.get(numToFactor).toString());
                return prevRuns.get(numToFactor);
            }
        };
        return task;
    }

}
