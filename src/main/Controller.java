package main;

import javafx.application.Platform;
import javafx.fxml.FXML;

import java.util.concurrent.TimeUnit;

public class Controller {

    @FXML
    public void run() throws InterruptedException {
        // 4 Services
        FactoringService factoringService7 = new FactoringService((long)Math.pow(2,26), 1, (long)Math.pow(2,26)/4 );

        Runnable task7 = () -> Platform.runLater(factoringService7::start);
        Thread thread7 = new Thread(task7);
        thread7.setDaemon(true);
        thread7.start();

        FactoringService factoringService4 = new FactoringService((long)Math.pow(2,26), (long)Math.pow(2,26)/4 + 1, (long)Math.pow(2,26)/2 );

        Runnable task4 = () -> Platform.runLater(factoringService4::start);
        Thread thread4 = new Thread(task4);
        thread4.setDaemon(true);
        thread4.start();

        FactoringService factoringService5 = new FactoringService((long)Math.pow(2,26)/2,3*(long)Math.pow(2,26)/4 );

        Runnable task5 = () -> Platform.runLater(factoringService5::start);
        Thread thread5 = new Thread(task5);
        thread5.setDaemon(true);
        thread5.start();

        FactoringService factoringService6 = new FactoringService((long)Math.pow(2,26), 3*(long)Math.pow(2,26)/4, (long)Math.pow(2,26));

        Runnable task6 = () -> Platform.runLater(factoringService6::start);
        Thread thread6 = new Thread(task6);
        thread6.setDaemon(true);
        thread6.start();

        // 2 Services
        FactoringService factoringService = new FactoringService((long)Math.pow(2,26),(long)Math.pow(2,26)/2 );

        Runnable task = () -> Platform.runLater(factoringService::start);
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

        FactoringService factoringService2 = new FactoringService((long)Math.pow(2,26), 1, (long)Math.pow(2,26)/2 );

        Runnable task2 = () -> Platform.runLater(factoringService2::start);
        Thread thread2 = new Thread(task2);
        thread2.setDaemon(true);
        thread2.start();


        // 1 Service
        FactoringService factoringService3 = new FactoringService((long)Math.pow(2,26));

        Runnable task3 = () -> Platform.runLater(factoringService3::start);
        Thread thread3 = new Thread(task3);
        thread3.setDaemon(true);
        thread3.start();

    }
}