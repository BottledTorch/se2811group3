package main;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class FactoringService extends Service {

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
                //
                ArrayList<Long> factors = new ArrayList<>();
                System.out.println(start);
                System.out.println(numToFactor);
                System.out.println(end);
                for(long i = start; i <= numToFactor && i <= end; i++) {
                    if (i == 0) {
                        i++;
                    }
                    if (numToFactor % i == 0) {
                        factors.add(i);
                    }
                    updateProgress(i, numToFactor);

                }

                //
                elapsedTime = (System.nanoTime() - startTime) / 1000000;
                System.out.println(elapsedTime + "ms");
                System.out.println(factors);
                updateProgress(1,1);
                return factors;
            }

        };

        return task;
    }

}
