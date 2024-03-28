package kappa.view;

import javafx.stage.Stage;
import kappa.model.Cable;
import kappa.model.Watchlist;

import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class KappaStage extends Stage {
    private Stage primaryStage;

    // Panes
    private BorderPane kappaPane;
    private CablePane cablePane;
    private CableGraphActionPane graphActionPane;
    private TopWorkloadCablePane topWorkloadCablePane;
    private CableGraphPane graphPane;
    private SignInPane signInPane;
    private MenuPane menuPane;
    private HomePane homePane;
    private HelpPane helpPane;
    private WatchlistPane watchlistPane;
    private PreviousViewedCablesPane previousViewedCablesPane;

    // Scenes
    private Scene signInScene;
    private Scene kappeScene;

    private SearchCablePane searchCablePane;

    public KappaStage(Stage stage) {
        this.primaryStage = stage;
        this.kappaPane = new BorderPane();
        this.kappaPane.setStyle("-fx-background-color:  white;");
        this.kappaPane.setTop(this.menuPane = new MenuPane());
        this.previousViewedCablesPane = new PreviousViewedCablesPane();
        this.kappaPane.setLeft(this.previousViewedCablesPane.getvBoxPreviousViewedCables());
        this.kappeScene = new Scene(this.kappaPane, 1000, 600);

        // Starting the application with the sign in scene
        showSignInScene();

    }

    // This method sets the sign in scene
    public void showSignInScene() {
        this.signInPane = new SignInPane();
        this.signInScene = new Scene(this.signInPane.getvBoxLogInLayout(), 800, 600);
        this.primaryStage.setTitle("Kappa - Anmeldung");
        this.primaryStage.setScene(signInScene);
        this.primaryStage.show();
    }

    // This method sets the sign in scene after logging out
    public void showSignInSceneAfterLogout() {
        if (getSignInPane().isLogininFaildMessageVisible()) {
            getSignInPane().removeLoginFailedMessage();
        }
        this.primaryStage.setTitle("Kappa - Anmeldung");
        this.primaryStage.setScene(this.signInScene);
        this.primaryStage.show();
    }

    // This method sets the cable detail scene
    public void showCablePane(Cable cable) {
        this.cablePane = new CablePane(cable);
        this.kappaPane.setCenter(cablePane);
        this.kappaPane.setLeft(this.previousViewedCablesPane.getvBoxPreviousViewedCables());
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
        this.kappaPane.setCenter(homePane.getvBoxHomeLayout());
        updateKappa("Kappa - Willkommen");
    }

    // This method sets the help scene
    public void showHelpScene() {
        this.helpPane = new HelpPane();
        this.kappaPane.setCenter(helpPane.getvBoxHelpLayout());
        updateKappa("Kappa - Hilfe");
    }

    // This method sets the search cable scene
    public void showWatchlistScene() {
        this.watchlistPane = new WatchlistPane();
        this.kappaPane.setCenter(watchlistPane.getvBoxWatchlistLayout());
        updateKappa("Kappa - Merkliste");
    }

    // This method sets the search cable scene
    public void showSearchCableScene() {
        this.searchCablePane = new SearchCablePane();
        this.kappaPane.setCenter(searchCablePane.getvBoxSearchCableLayout());
        updateKappa("Kappa - Kabel suchen");
    }

    /**
     * This method updates the primary stage with a new title and scene
     * It is meant to use after updating the KappaScene
     * 
     * @param title
     */
    public void updateKappa(String title) {
        this.kappaPane.setLeft(this.previousViewedCablesPane.getvBoxPreviousViewedCables());
        this.primaryStage.setTitle(title);
        this.primaryStage.setScene(getKappaScene());
        this.primaryStage.show();
    }

    // Getters and Setters
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

    public PreviousViewedCablesPane getPreviousViewedCablesPane() {
        return previousViewedCablesPane;
    }

}
