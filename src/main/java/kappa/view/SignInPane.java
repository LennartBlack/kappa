package kappa.view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SignInPane extends Region {
    private Button signIn;
    private TextField userField;
    private PasswordField passwordField;
    private VBox vBoxLogInLayout;
    private Label loginFailed;
    
    public SignInPane(){
        this.vBoxLogInLayout = new VBox();
        this.vBoxLogInLayout.setAlignment(Pos.CENTER);

        ImageView coverIcon = new ImageView("cover.jpeg");
        coverIcon.setFitWidth(250);
        coverIcon.setFitHeight(250);

        Label userLabel = new Label("Bitte gib deinen Butzernamen ein.");
        this.userField = new TextField();
        userField.setPromptText("Benutzername");
        userField.setPrefWidth(200);
        Label passwordLabel = new Label("Bitte gib dein Passwort ein.");
        this.passwordField = new PasswordField();
        passwordField.setPromptText("Passwort");
        passwordField.setPrefWidth(100);

        this.signIn = new Button("Anmelden");
        signIn.setTextFill(javafx.scene.paint.Color.web("#C8D200"));
        signIn.setStyle("-fx-background-color: #005F96;");

        this.vBoxLogInLayout.getChildren().addAll(coverIcon, userLabel, userField, passwordLabel, passwordField, signIn);
        this.vBoxLogInLayout.setSpacing(10);
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
    public TextField getUserField(){
        return this.userField;
    }
    public PasswordField getPasswordField(){
        return this.passwordField;
    }
    public VBox getvBoxLogInLayout(){
        return this.vBoxLogInLayout;
    }
    public void addLoginFailedMessage() {
        this.loginFailed = new Label("Anmeldung fehlgeschlagen. Bitte versuche es erneut.");
        this.loginFailed.setTextFill(Color.RED);
        this.vBoxLogInLayout.getChildren().add(loginFailed);
    }
    public Label getLoginFailedMessage(){
        return this.loginFailed;
    }
    public boolean isLogininFaildMessageVisible() {
        for (Node n : this.vBoxLogInLayout.getChildren()) {
            if (n.equals(this.loginFailed)) {
                return true;
            }
        }
        return false;
    }
}
