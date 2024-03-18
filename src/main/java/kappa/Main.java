package kappa;

import javafx.application.Application;
import javafx.stage.Stage;
import kappa.view.KappaStage;
import kappa.model.Kappa;


public class Main extends Application{
    launch(args);
}

@Override
public void start(Stage primaryStage) {
    newSeassion();
}

private void newSession() {
    App app = new App();
    Stage stage = new Stage(app);
}