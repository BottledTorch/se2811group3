package main;

import javafx.application.Platform;
import javafx.fxml.FXML;

import java.util.concurrent.TimeUnit;

public class Controller {

    @FXML
    public void run() throws InterruptedException {

        // 2 Services
        FactoringService factoringService = new FactoringService(Math.pow(2,26),Math.pow(2,26)/2 );

        Runnable task = () -> Platform.runLater(factoringService::start);
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

        FactoringService factoringService2 = new FactoringService(Math.pow(2,26), 1, Math.pow(2,26)/2 );

        Runnable task2 = () -> Platform.runLater(factoringService2::start);
        Thread thread2 = new Thread(task2);
        thread2.setDaemon(true);
        thread2.start();


        // 1 Service
        FactoringService factoringService3 = new FactoringService(Math.pow(2,26));

        Runnable task3 = () -> Platform.runLater(factoringService3::start);
        Thread thread3 = new Thread(task3);
        thread3.setDaemon(true);
        thread3.start();

    }
}
