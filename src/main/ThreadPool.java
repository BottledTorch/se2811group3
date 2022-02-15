/*
 * Course:     SE 2811
 * Term:       Winter 2020-21
 * Assignment: Final Presentation
 * Author:     Mitchell Mahnke, Alex Moran
 * Date:       2/10/22
 */

package main;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This is the thread pool, it manages the JavaFX
 * Services (Threads), utilizes a FactoringService
 * to factor the given Task, and divides the workload
 * accordingly.
 */
public class ThreadPool {

    private ArrayList<FactoringService> factoringServices;
    private ConcurrentHashMap<Long, Set<Long>> runs;
    private Queue<Task> queue = new ArrayDeque<>(1);
    private ArrayList<ProgressBar> bars;

    /**
     * Default Constructor for the ThreadPool
     * determines how many threads are needed
     * and properly connects them to the provided
     * progressbars.
     * @param numThreads - number of threads to be used
     * @param bars - progress bars for each thread
     */
    public ThreadPool(int numThreads, ArrayList<ProgressBar> bars) {
        runs = new ConcurrentHashMap<>();
        factoringServices = new ArrayList<>(1);
        this.bars = bars;

        for (int i = 0; i < numThreads; i++) {
            factoringServices.add(new FactoringService());
        }
    }

    /**
     * Adds another task to the queue
     * @param task - factoring task
     */
    public void take(Task task) {
        queue.add(task);
    }

    public void start() {
        while (!queue.isEmpty()) {
            Task currentTask = queue.poll();
            int numThreads = factoringServices.size();

            for (int i = 0; i < numThreads; i++) {
                long[] params = currentTask.getParams(i, numThreads);
                // Pass in previous run of this number
                factoringServices.get(i).load(params[0], params[1], params[2], runs.get(params[0]));
                bars.get(i).progressProperty().bind(factoringServices.get(i).progressProperty());

                int finalI = i;
                Platform.runLater(() -> {
                    Runnable task = () -> Platform.runLater(factoringServices.get(finalI)::start);
                    Thread thread = new Thread(task);
                    thread.setDaemon(true);
                    thread.start();
                });
            }
        }
    }
}
