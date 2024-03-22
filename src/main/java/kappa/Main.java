package kappa;

import javafx.application.Application;
import javafx.stage.Stage;
import kappa.control.Controller;
import kappa.view.KappaStage;
import kappa.model.Kappa;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        newSession(primaryStage);
    }

    public void newSession(Stage primaryStage) {
        Kappa kappa = new Kappa(primaryStage);
        KappaStage stage = new KappaStage(primaryStage);
        Controller controller = new Controller(stage);
    }
}