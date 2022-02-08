package main;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.Collection;
import java.util.Objects;

public class FactoringService extends Service {

    private final long numToFactor;
    private long start;
    private long end;

    private Collection results;

    public FactoringService(long numToFactor) {
        this.numToFactor = numToFactor;
        this.start = -1;
        this.end = -1;
    }

    public FactoringService(long numToFactor, long start) {
        this.numToFactor = numToFactor;
        this.start = start;
        this.end = -1;
    }

    public FactoringService(long numToFactor, long start, long end) {
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
                long elapsedTime = 0;
                if (start != -1) {
                    if (end != -1) {
                        results = Factorer.factor(numToFactor, start, end);
                    } else {
                        results = Factorer.factor(numToFactor, start);
                    }
                } else {
                    results = Factorer.factor(numToFactor);
                }
                elapsedTime = (System.nanoTime() - startTime) / 1000000;
                System.out.println(elapsedTime + "ms");
                System.out.println(results);
                return results;
            }

        };

        return task;
    }

    public void startTheService() {
        if (!isRunning()) {
            reset();
            start();
            createTask();
        }
    }

    public void offloadData() {
        System.out.println(results);
    }
}
