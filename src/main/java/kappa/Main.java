package kappa;

import javafx.application.Application;
import javafx.stage.Stage;
import kappa.view.KappaStage;
import kappa.model.Kappa;


public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        newSession();
    }

    private void newSession() {
        Kappa KAppa = new Kappa();
        KappaStage stage = new KappaStage(KAppa);
    }
}