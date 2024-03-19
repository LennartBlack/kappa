package kappa.view;

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
        cableDetailPane = new CableDetailPane();
        graphActionPane = new GraphActionPane();
        graphPane = new GraphPane();
        loginRegisterPane = new LoginRegisterPane();
        menuPane = new MenuPane();

        BorderPane graphDetailLayout = new BorderPane();
        graphDetailLayout.setTop(menuPane);
        graphDetailLayout.setRight(graphPane);
        graphDetailLayout.setLeft(cableDetailPane);
        graphDetailLayout.setBottom(graphActionPane);

        Scene graphDetailScene = new Scene(graphDetailLayout, 800, 600);
        stage.setTitle("Kappa - Kabelauslastungsplanungsprogrammassistent");
        stage.setScene(graphDetailScene);
        stage.show();
    }
}
