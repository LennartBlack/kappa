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
    private LoginRegisterPane loginRegisterPane;
    private MenuPane menuPane;
    private Stage primaryStage;
    public KappaStage(Stage stage){
        this.primaryStage = stage;
        showSignInScene();
        //showCabelDetailScene();
    }

    public void showSignInScene(){
        this.loginRegisterPane = new LoginRegisterPane();
        this.primaryStage.setTitle("Kappa - Anmeldung");
        this.primaryStage.setScene(this.loginRegisterPane.getLoginScene());
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

    public LoginRegisterPane getLoginRegisterPane(){
        return this.loginRegisterPane;
    }
}
