package kappa.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class LoginRegisterPane  extends Region {

    private Scene loginScene;
    private Button signIn;
    private TextField userField;
    private PasswordField passwordField;
    public LoginRegisterPane(){
        VBox vBoxLogInLayout = new VBox();
        vBoxLogInLayout.setAlignment(Pos.CENTER);

        ImageView coverIcon = new ImageView("cover.jpeg");
        coverIcon.setFitWidth(250);
        coverIcon.setFitHeight(250);

        Label userLabel = new Label("Bitte gib deinen Butzernamen ein.");
        this.userField = new TextField();
        userField.setPromptText("TextField");
        userField.setPrefWidth(200);
        Label passwordLabel = new Label("Bitte gib dein Passwort ein.");
        this.passwordField = new PasswordField();
        passwordField.setPromptText("Passwort");
        passwordField.setPrefWidth(100);

        this.signIn = new Button("Anmelden");
        signIn.setTextFill(javafx.scene.paint.Color.web("#C8D200"));
        signIn.setStyle("-fx-background-color: #005F96;");

        vBoxLogInLayout.getChildren().addAll(coverIcon, userLabel, userField, passwordLabel, passwordField, signIn);
        vBoxLogInLayout.setSpacing(10);
        setLoginScene(new Scene(vBoxLogInLayout, 800, 600));
    }

    public Scene getLoginScene(){
        return this.loginScene;
    }
    public void setLoginScene(Scene scene){
        this.loginScene = scene;
    }
    public Button getSignInButton(){
        return this.signIn;
    }
    public String getPasswordInput(){
        return this.passwordField.getText();
    }
    public String getUsernameInput(){
        return this.userField.getText();
    }
}
