package main;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Controller {
    @FXML
    ProgressBar progressBar;
    @FXML
    ProgressBar progressBar2;

    @FXML
    public void run() {

        ArrayList<ProgressBar> bars = new ArrayList<>(2);
        bars.add(progressBar);
        bars.add(progressBar2);

        ThreadPool threadPool = new ThreadPool(2, bars);
        Task factoringTask = new Task(10000000);

        threadPool.take(factoringTask);
        threadPool.start();


    }
}