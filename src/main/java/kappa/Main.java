package kappa;

import javafx.application.Application;
import javafx.stage.Stage;
import kappa.control.Controller;
import kappa.view.KappaStage;
import kappa.model.CableCoreDataDB;
import kappa.model.Watchlist;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stageToIgnore) {
        // Kappa kappa = new Kappa(primaryStage);
        CableCoreDataDB cableCoreDataDB = new CableCoreDataDB();
        Watchlist watchlist = Watchlist.deserializeHashMap(cableCoreDataDB);
        KappaStage stage = new KappaStage(cableCoreDataDB, watchlist);
        stage.showSignInScene();
        new Controller(stage, cableCoreDataDB, watchlist);
    }

    public void newSession() {
        CableCoreDataDB cableCoreDataDB = new CableCoreDataDB();
        Watchlist watchlist = Watchlist.deserializeHashMap(cableCoreDataDB);
        KappaStage newStage = new KappaStage(cableCoreDataDB, watchlist);
        newStage.showHomeScene();
        new Controller(newStage, cableCoreDataDB, watchlist);
    }
}