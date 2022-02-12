package main;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadPool {

    private ArrayList<FactoringService> factoringServices;
    private ConcurrentHashMap<Long, ArrayList<Integer>> runs;
    private Queue<Task> queue = new ArrayDeque<>(1);
    private boolean isRunning = false;
    private ArrayList<ProgressBar> bars;

    public ThreadPool(int numThreads, ArrayList<ProgressBar> bars) {
        runs = new ConcurrentHashMap<>();
        factoringServices = new ArrayList<>(1);
        this.bars = bars;

        for (int i = 0; i < numThreads; i++) {
            factoringServices.add(new FactoringService());
        }
    }

    public void take(Task task) {
        queue.add(task);
    }

    public void start() {
        while (!queue.isEmpty()) {
            isRunning = true;
            Task currentTask = queue.poll();
            int numThreads = factoringServices.size();

            for (int i = 0; i < numThreads; i++) {
                long[] params = currentTask.getParams(i, numThreads);
                factoringServices.get(i).load(params[0], params[1], params[2]);
                bars.get(i).progressProperty().bind(factoringServices.get(i).progressProperty());

                int finalI = i;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Runnable task = () -> Platform.runLater(factoringServices.get(finalI)::start);
                        Thread thread = new Thread(task);
                        thread.setDaemon(true);
                        thread.start();
                    }
                });

            }
        }
        isRunning = false;
    }



}
