package kappa;

import javafx.application.Application;
import javafx.stage.Stage;
import kappa.control.Controller;
import kappa.view.KappaStage;
import kappa.model.CableCoreDataDB;
import kappa.model.Kappa;
import kappa.model.Watchlist;

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
        CableCoreDataDB cableCoreDataDB = new CableCoreDataDB();
        Watchlist watchlist = Watchlist.deserializeHashMap(cableCoreDataDB);
        KappaStage stage = new KappaStage(primaryStage, cableCoreDataDB, watchlist);
        Controller controller = new Controller(stage, cableCoreDataDB, watchlist);
    }
}