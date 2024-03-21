package kappa.view;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class KappaStage extends Stage{
    private Stage primaryStage;
    
    //Panes
    private BorderPane kappaPane;
    private CableDetailPane cableDetailPane;
    private GraphActionPane graphActionPane;
    private TopWorkloadCablePane topWorkloadCablePane;
    private GraphPane graphPane;
    private SignInPane signInPane;
    private MenuPane menuPane;
    private HomePane homePane;
    private HelpPane helpPane;
    private WatchlistPane watchlistPane;

    //Scenes
    private Scene signInScene;
    private Scene kappeScene;

    private SearchCablePane searchCablePane;

    public KappaStage(Stage stage){
        this.primaryStage = stage;
        this.kappaPane = new BorderPane();
        this.kappaPane.setTop(this.menuPane = new MenuPane());
        this.kappeScene = new Scene(this.kappaPane, 800, 600);

        // Starting the application with the sign in scene
        showSignInScene();

        //Test area
        //this.signInPane = new SignInPane();
        //this.menuPane = new MenuPane();
        //showCabelDetailScene();
        //showHomeScene();
    }

    //This method sets the sign in scene
    public void showSignInScene(){
        this.signInPane = new SignInPane();
        this.signInScene = new Scene(this.signInPane.getvBoxLogInLayout(), 800, 600);
        this.primaryStage.setTitle("Kappa - Anmeldung");
        this.primaryStage.setScene(signInScene);
        this.primaryStage.show();
    }

    //This method sets the sign in scene after logging out
    public void showSignInSceneAfterLogout(){
        this.primaryStage.setTitle("Kappa - Anmeldung");
        this.primaryStage.setScene(this.signInScene);
        this.primaryStage.show();
    }
    
    // This method sets the cable detail scene
    public void showCabelDetailScene(){
        cableDetailPane = new CableDetailPane();
        graphActionPane = new GraphActionPane();
        graphPane = new GraphPane();
        menuPane = new MenuPane();

        BorderPane graphDetailLayout = new BorderPane();
        graphDetailLayout.setTop(menuPane);
        graphDetailLayout.setRight(graphPane);
        graphDetailLayout.setLeft(cableDetailPane);
        graphDetailLayout.setBottom(graphActionPane);

        Scene graphDetailScene = new Scene(graphDetailLayout, 1200, 600);
        this.primaryStage.setTitle("Kappa - Kabelauslastungsplanungsprogrammassistent");
        this.primaryStage.setScene(graphDetailScene);
        this.primaryStage.show();
    }
    
    //This method sets the cable detail scene
    public void showCableWithTopWorkloudScene(){
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
    
    //This method sets the admin home scene
    public void showAdminHomeScene() {
        //TODO: Implement admin home scene
    }
    
    //This method sets the help scene
    public void showHelpScene() {
        this.helpPane = new HelpPane();
        this.kappaPane.setCenter(helpPane.getvBoxHelpLayout());
        updateKappa("Kappa - Hilfe");
    }
   
    //This method sets the search cable scene
    public void showWatchlistScene() {
        this.watchlistPane = new WatchlistPane();
        this.kappaPane.setCenter(watchlistPane.getvBoxWatchlistLayout());
        updateKappa("Kappa - Merkliste");
    }

    //This method sets the search cable scene
    public void showSearchCableScene() {
        this.searchCablePane = new SearchCablePane();
        this.kappaPane.setCenter(searchCablePane.getvBoxSearchCableLayout());
        updateKappa("Kappa - Kabel suchen");
    }

    /**
     * This method updates the primary stage with a new title and scene
     * It is meant to use after updating the KappaScene
     * @param title
     */
    private void updateKappa(String title){
        this.primaryStage.setTitle(title);
        this.primaryStage.setScene(getKappaScene());
        this.primaryStage.show();
    }
    
    //Getters and Setters
    public SignInPane getSignInPane(){
        return this.signInPane;
    }
    public HomePane getHomePane() {
        return this.homePane;
    }
    public MenuPane getMenuPane(){
        return this.menuPane;
    }
    private Scene getKappaScene(){
        return this.kappeScene;
    }
}
