package kappa.view;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kappa.model.Kappa;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class KappaStage extends Stage{
    private CableDetailPane cableDetailPane;
    private GraphActionPane graphActionPane;
    private GraphPane graphPane;
    private SignInPane signInPane;
    private MenuPane menuPane;
    private Stage primaryStage;
    private HomePane homePane;

    private Scene signInScene;
    private Scene homeScene;
    public KappaStage(Stage stage){
        this.primaryStage = stage;
        showSignInScene();
        //showCabelDetailScene();
    }
    public void showSignInScene(){
        this.signInPane = new SignInPane();
        this.signInScene = new Scene(this.signInPane.getvBoxLogInLayout(), 800, 600);
        this.primaryStage.setTitle("Kappa - Anmeldung");
        this.primaryStage.setScene(signInScene);
        this.primaryStage.show();
    }
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

        Scene graphDetailScene = new Scene(graphDetailLayout, 800, 600);
        this.primaryStage.setTitle("Kappa - Kabelauslastungsplanungsprogrammassistent");
        this.primaryStage.setScene(graphDetailScene);
        this.primaryStage.show();
    }
    public void showCableWithTopWorkloud(){

    }
    public void showHomeScene() {
        this.homePane = new HomePane();
        this.homeScene = new Scene(this.homePane.getvBoxHomeLayout(), 800, 600);
        this.primaryStage.setTitle("Kappa - Willkommen");
        this.primaryStage.setScene(homeScene);
        this.primaryStage.show(); 
    }
    public void showAdminHomeScene() {
        
    }
    public SignInPane getSignInPane(){
        return this.signInPane;
    }
}
