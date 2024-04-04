package kappa.view;

import javafx.stage.Stage;
import kappa.model.Cable;
import kappa.model.CableCoreDataDB;
import kappa.model.Watchlist;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class KappaStage extends Stage {

    // Panes
    private BorderPane kappaPane;
    private CablePane cablePane;
    private TopWorkloadCablePane topWorkloadCablePane;
    private SignInPane signInPane;
    private MenuPane menuPane;
    private HomePane homePane;
    private HelpPane helpPane;
    private WatchlistPane watchlistPane;
    private PreviousViewedCablesPane previousViewedCablesPane;

    // Scenes
    private Scene signInScene;
    private Scene kappeScene;

    // Data
    private Watchlist watchlist;
    private CableCoreDataDB cableCoreDataDB;

    public KappaStage(CableCoreDataDB cableCoreDataDB, Watchlist watchlist) {
        this.cableCoreDataDB = cableCoreDataDB;
        this.watchlist = watchlist;
        this.kappaPane = new BorderPane();
        this.watchlistPane = new WatchlistPane(this.cableCoreDataDB, this);
        this.kappaPane.setStyle("-fx-background-color:  white;");
        this.kappaPane.setTop(this.menuPane = new MenuPane());
        this.previousViewedCablesPane = new PreviousViewedCablesPane();
        this.kappaPane.setLeft(this.previousViewedCablesPane);
        this.kappeScene = new Scene(this.kappaPane, 1000, 600);
        this.signInPane = new SignInPane();
    }

    // This method sets the sign in scene
    public void showSignInScene() {
        this.signInScene = new Scene(this.signInPane, 300, 450);
        this.setTitle("Kappa - Anmeldung");
        this.setScene(signInScene);
        this.show();
    }

    // This method sets the sign in scene after logging out
    public void showSignInSceneAfterLogout() {
        if (getSignInPane().isLogininFaildMessageVisible()) {
            getSignInPane().removeLoginFailedMessage();
        }
        this.setTitle("Kappa - Anmeldung");
        this.setScene(this.signInScene);
        this.show();
    }

    // This method sets the cable detail scene
    public void showCablePane(Cable cable) {
        this.cablePane = new CablePane(cable);
        this.kappaPane.setCenter(cablePane);
        this.kappaPane.setLeft(this.previousViewedCablesPane);
        updateKappa("Kappa - Kabel Detailansicht" + cable.getId());
    }

    // This method sets the cable detail scene
    public void showCableWithTopWorkloudScene() {
        this.topWorkloadCablePane = new TopWorkloadCablePane();
        this.kappaPane.setCenter(topWorkloadCablePane.getvBoxTopWorkloadCableLayout());
        updateKappa("Kappa - Kabel mit höchster Auslastung");
    }

    // This method sets the home scene
    public void showHomeScene() {
        this.homePane = new HomePane();
        this.kappaPane.setCenter(homePane);
        updateKappa("Kappa - Willkommen");
    }

    // This method sets the help scene
    public void showHelpScene() {
        this.helpPane = new HelpPane();
        this.kappaPane.setCenter(helpPane.getvBoxHelpLayout());
        updateKappa("Kappa - Hilfe");
    }

    // This method sets the search cable scene
    public void showWatchlistScene(CableCoreDataDB cableCoreDataDB) {
        this.kappaPane.setCenter(watchlistPane);
        updateKappa("Kappa - Merkliste");
    }

    /**
     * This method updates the primary stage with a new title and scene
     * It is meant to use after updating the KappaScene
     * 
     * @param title
     */
    public void updateKappa(String title) {
        this.kappaPane.setLeft(this.previousViewedCablesPane);
        this.setTitle(title);
        this.setScene(getKappaScene());
        this.show();
    }

    // Getters and Setters
    public boolean isPreviousViewedCablesPaneVisible() {
        if (this.getKappaPane().getLeft() != null) {
            System.out.println(!this.getPreviousViewedCablesPane().isvBoxEmpty());
            return this.getPreviousViewedCablesPane().isvBoxEmpty();
        }
        return false;
    }

    private Scene getKappaScene() {
        return this.kappeScene;
    }

    public SignInPane getSignInPane() {
        return this.signInPane;
    }

    public HomePane getHomePane() {
        return this.homePane;
    }

    public MenuPane getMenuPane() {
        return this.menuPane;
    }

    public CablePane getCablePane() {
        return this.cablePane;
    }

    public WatchlistPane getWatchlistPane() {
        return this.watchlistPane;
    }

    public BorderPane getKappaPane() {
        return this.kappaPane;
    }

    public PreviousViewedCablesPane getPreviousViewedCablesPane() {
        return previousViewedCablesPane;
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }

}
