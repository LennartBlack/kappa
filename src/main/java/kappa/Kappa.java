package kappa;

import javafx.application.Application;
import javafx.stage.Stage;
import kappa.control.Controller;
import kappa.view.KappaStage;
import kappa.model.CableCoreDataDB;
import kappa.model.TopWorkloud;
import kappa.model.Watchlist;

public class Kappa extends Application {
    /**
     * @author Lennart Schwartz
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @SuppressWarnings("exports")
    @Override
    /**
     * This method starts the application
     */
    public void start(Stage stageToIgnore) {
        CableCoreDataDB cableCoreDataDB = new CableCoreDataDB();
        Watchlist watchlist = Watchlist.deserializeHashMap(cableCoreDataDB);
        TopWorkloud topWorkloud = new TopWorkloud(cableCoreDataDB);
        KappaStage stage = new KappaStage(cableCoreDataDB, watchlist, topWorkloud);
        new Controller(stage, cableCoreDataDB, watchlist);
        stage.showSignInScene();
    }

    /**
     * This method creates a new session
     */
    public void newSession() {
        CableCoreDataDB cableCoreDataDB = new CableCoreDataDB();
        Watchlist watchlist = Watchlist.deserializeHashMap(cableCoreDataDB);
        TopWorkloud topWorkloud = new TopWorkloud(cableCoreDataDB);
        KappaStage newStage = new KappaStage(cableCoreDataDB, watchlist, topWorkloud);
        newStage.showHomeScene();
        new Controller(newStage, cableCoreDataDB, watchlist);
    }
}