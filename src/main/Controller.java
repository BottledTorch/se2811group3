package main;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

import java.util.ArrayList;

public class Controller {
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

    @FXML
    public void runTwo() {

        ArrayList<ProgressBar> bars = new ArrayList<>(2);
        bars.add(progressBar);
        bars.add(progressBar2);

        ThreadPool threadPool = new ThreadPool(2, bars);
        Task factoringTask = new Task(100000000);

        threadPool.take(factoringTask);
        threadPool.start();


    }

    @FXML
    public void runFour() {
        ArrayList<ProgressBar> bars = new ArrayList<>(2);
        bars.add(progressBar3);
        bars.add(progressBar4);
        bars.add(progressBar5);
        bars.add(progressBar6);

        ThreadPool threadPool = new ThreadPool(4, bars);
        Task factoringTask = new Task(100000000);

        threadPool.take(factoringTask);
        threadPool.start();
    }

    @FXML
    public void runBoth() {
        ArrayList<ProgressBar> bars = new ArrayList<>(2);
        bars.add(progressBar);
        bars.add(progressBar2);

        ThreadPool threadPool = new ThreadPool(2, bars);
        Task factoringTask = new Task(100000000);

        threadPool.take(factoringTask);

        //-------------------------------------------------------

        ArrayList<ProgressBar> bars2 = new ArrayList<>(4);
        bars2.add(progressBar3);
        bars2.add(progressBar4);
        bars2.add(progressBar5);
        bars2.add(progressBar6);

        ThreadPool threadPool2 = new ThreadPool(4, bars2);
        Task factoringTask2 = new Task(100000000);

        threadPool2.take(factoringTask2);

        //--------------------------------------------------------

        threadPool.start();
        threadPool2.start();
    }
}