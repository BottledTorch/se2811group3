/*
 * Course:     SE 2811
 * Term:       Winter 2020-21
 * Assignment: Final Presentation
 * Author:     Mitchell Mahnke, Alex Moran
 * Date:       2/10/22
 */

package main;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

import java.util.ArrayList;

/**
 * Controller for the JavaFX application
 */
public class Controller {

    @FXML
    TextField textBox;

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


    /**
     * Factor the provided number
     * using a ThreadPool with only
     * 2 threads
     */
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
    }

    /**
     * Factor the provided number
     * using a ThreadPool with only
     * 4 threads
     */
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

    }

    /**
     * Factor the provided number
     * using a ThreadPool with both
     * 2 and 4 threads
     */
    @FXML
    public void runBoth() {
        runTwo();
        runFour();
    }



}