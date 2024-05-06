package kappa.view;

import javafx.stage.Stage;
import kappa.model.Cable;
import kappa.model.CableCoreDataDB;
import kappa.model.TopWorkloud;
import kappa.model.Watchlist;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class KappaStage extends Stage {
    // Attributes
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
    private TopWorkloud topWorkload;

    /**
     * Constructor for the KappaStage class
     * @param cableCoreDataDB cableCoreDataDB
     * @param watchlist watchlist
     * @param topWorkloud topWorkloud
     */
    public KappaStage(CableCoreDataDB cableCoreDataDB, Watchlist watchlist, TopWorkloud topWorkloud) {
        this.topWorkload = topWorkloud;
        this.cableCoreDataDB = cableCoreDataDB;
        this.watchlist = watchlist;
        this.kappaPane = new BorderPane();
        this.watchlistPane = new WatchlistPane(this.cableCoreDataDB, this);
        this.topWorkloadCablePane = new TopWorkloadCablePane(this.topWorkload, this);
        this.kappaPane.setStyle("-fx-background-color:  white;");
        this.kappaPane.setTop(this.menuPane = new MenuPane());
        this.previousViewedCablesPane = new PreviousViewedCablesPane();
        this.kappaPane.setLeft(this.previousViewedCablesPane);
        this.kappeScene = new Scene(this.kappaPane, 1000, 600);
        this.signInPane = new SignInPane();
    }

    // Methods

    /**
     *  This method shows the sign in scene
     */
    public void showSignInScene() {
        this.signInScene = new Scene(this.signInPane, 300, 450);
        this.setTitle("Kappa - Anmeldung");
        this.setScene(signInScene);
        this.show();
    }

    /**
     * This method shows the cable scene
     */
    public void showSignInSceneAfterLogout() {
        if (getSignInPane().isLogininFaildMessageVisible()) {
            getSignInPane().removeLoginFailedMessage();
        }
        this.setTitle("Kappa - Anmeldung");
        this.setScene(this.signInScene);
        this.show();
    }

    /**
     * This method shows the cable scene
     * @param cable cable
     */
    public void showCablePane(Cable cable) {
        this.cablePane = new CablePane(cable, watchlist);
        this.kappaPane.setCenter(cablePane);
        this.kappaPane.setLeft(this.previousViewedCablesPane);
        updateKappa("Kappa - Kabel Detailansicht " + cable.getId());
    }

    /**
     * This method shows the cable with the highest workload scene
     */
    public void showCableWithTopWorkloudScene() {
        this.kappaPane.setCenter(topWorkloadCablePane);
        updateKappa("Kappa - Kabel mit höchster Auslastung");
    }

    /**
     * This method shows the home scene
     */
    public void showHomeScene() {
        this.homePane = new HomePane();
        this.kappaPane.setCenter(homePane);
        updateKappa("Kappa - Willkommen");
    }

    /**
     *  This method shows the help scene
     */
    public void showHelpScene() {
        this.helpPane = new HelpPane();
        this.kappaPane.setCenter(helpPane.getvBoxHelpLayout());
        updateKappa("Kappa - Hilfe");
    }

    /**
     * This method shows the watchlist scene
     * @param cableCoreDataDB cableCoreDataDB
     */
    public void showWatchlistScene(CableCoreDataDB cableCoreDataDB) {
        this.kappaPane.setCenter(watchlistPane);
        updateKappa("Kappa - Merkliste");
    }

    /**
     * This method updates the primary stage with a new title and scene
     * It is meant to use after updating the KappaScene
     * 
     * @param title title
     */
    public void updateKappa(String title) {
        this.kappaPane.setLeft(this.previousViewedCablesPane);
        this.setTitle(title);
        this.setScene(getKappaScene());
        this.show();
    }

    // Getters and Setters
    /**
     *  Getter for the kappeScene attribute
     * @return kappeScene
     */
    public boolean isPreviousViewedCablesPaneVisible() {
        if (this.getKappaPane().getLeft() != null) {
            return this.getPreviousViewedCablesPane().isvBoxEmpty();
        }
        return false;
    }

    /**
     * Getter for the kappeScene attribute
     * @return kappeScene
     */
    private Scene getKappaScene() {
        return this.kappeScene;
    }

    /**
     * Getter for the signInPane attribute
     * @return signInPane
     */
    public SignInPane getSignInPane() {
        return this.signInPane;
    }

    /**
     * Getter for the homePane attribute
     * @return homePane
     */
    public HomePane getHomePane() {
        return this.homePane;
    }

    /**
     * Getter for the menuPane attribute
     * @return menuPane
     */
    public MenuPane getMenuPane() {
        return this.menuPane;
    }

    /**
     *  Getter for the cablePane attribute
     * @return cablePane
     */
    public CablePane getCablePane() {
        return this.cablePane;
    }

    /**
     * Getter for the watchlistPane attribute
     * @return watchlistPane
     */
    public WatchlistPane getWatchlistPane() {
        return this.watchlistPane;
    }

    /**
     * Getter for the kappaPane attribute
     * @return kappaPane
     */
    public BorderPane getKappaPane() {
        return this.kappaPane;
    }

    /**
     * Getter for the previousViewedCablesPane attribute
     * @return previousViewedCablesPane
     */
    public PreviousViewedCablesPane getPreviousViewedCablesPane() {
        return previousViewedCablesPane;
    }

    /**
     * Getter for the watchlist attribute
     * @return watchlist
     */
    public Watchlist getWatchlist() {
        return watchlist;
    }

    /**
     *  Getter for the topWorkloadCablePane attribute
     * @return topWorkloadCablePane
     */
    public TopWorkloadCablePane getTopWorkloadCablePane() {
        return topWorkloadCablePane;
    }
}
