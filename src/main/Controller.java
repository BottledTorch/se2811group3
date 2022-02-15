package main;

import javafx.fxml.FXML;

import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class Controller {

    @FXML
    TextField textBox;
    TextArea textArea;

    @FXML
    ProgressBar progressBar;
    @FXML
    ProgressBar progressBar2;
    @FXML
    ProgressBar progressBar3;
    @FXML
    ProgressBar progressBar4;
    @FXML
    ProgressBar progressBar5;
    @FXML
    ProgressBar progressBar6;

    private ConcurrentHashMap<Long, ArrayList<Long>> results;
    private ThreadPool latestThreadPool;

    @FXML
    public void runTwo() {

        ArrayList<ProgressBar> bars = new ArrayList<>(2);
        bars.add(progressBar);
        bars.add(progressBar2);

        ThreadPool threadPool = new ThreadPool(2, bars);
        int numToFactor = Integer.parseInt(textBox.getText());
        Task factoringTask = new Task(numToFactor);

        threadPool.take(factoringTask);
        threadPool.start();

        latestThreadPool = threadPool;
    }

    @FXML
    public void runFour() {
        ArrayList<ProgressBar> bars = new ArrayList<>(4);
        bars.add(progressBar3);
        bars.add(progressBar4);
        bars.add(progressBar5);
        bars.add(progressBar6);

        ThreadPool threadPool = new ThreadPool(4, bars);
        int numToFactor = Integer.parseInt(textBox.getText());
        Task factoringTask = new Task(numToFactor);

        threadPool.take(factoringTask);
        threadPool.start();

        latestThreadPool = threadPool;
    }

    @FXML
    public void runBoth() {
        ArrayList<ProgressBar> bars = new ArrayList<>(2);
        bars.add(progressBar);
        bars.add(progressBar2);

        ThreadPool threadPool = new ThreadPool(2, bars);
        int numToFactor = Integer.parseInt(textBox.getText());

        Task factoringTask = new Task(numToFactor);

        threadPool.take(factoringTask);

        //-------------------------------------------------------

        ArrayList<ProgressBar> bars2 = new ArrayList<>(4);
        bars2.add(progressBar3);
        bars2.add(progressBar4);
        bars2.add(progressBar5);
        bars2.add(progressBar6);

        ThreadPool threadPool2 = new ThreadPool(4, bars2);
        Task factoringTask2 = new Task(numToFactor);

        threadPool2.take(factoringTask2);

        //--------------------------------------------------------

        threadPool.start();
        threadPool2.start();
        latestThreadPool = threadPool2;
    }


    public void showResults() {
        int numToFactor = Integer.parseInt(textBox.getText());

        for(Long a : results.get(numToFactor)) {
            System.out.println(a);
        }
    }
}