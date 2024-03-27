package kappa.view;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SignInPane extends Region {
    private Button signIn;
    private TextField userField;
    private PasswordField passwordField;
    private VBox vBoxLogInLayout;
    private Label loginFailed;

    public SignInPane() {
        // Create the login layout
        this.vBoxLogInLayout = new VBox();
        this.vBoxLogInLayout.setAlignment(Pos.CENTER);

        // Create the cover icon
        ImageView coverIcon = new ImageView("cover.jpeg");
        coverIcon.setFitWidth(250);
        coverIcon.setFitHeight(250);

        // Create the login form
        Label userLabel = new Label("Bitte gib deinen Butzernamen ein.");
        userLabel.setTextFill(javafx.scene.paint.Color.web((Style.getEweBlue())));
        this.userField = new TextField();
        userField.setPromptText("Benutzername");
        userField.setPrefWidth(200);
        Label passwordLabel = new Label("Bitte gib dein Passwort ein.");
        passwordLabel.setTextFill(javafx.scene.paint.Color.web((Style.getEweBlue())));
        this.passwordField = new PasswordField();
        passwordField.setPromptText("Passwort");
        passwordField.setPrefWidth(100);

        // Create the sign in button
        this.signIn = new Button("Anmelden");
        signIn.setTextFill(javafx.scene.paint.Color.web(Style.EWE_GREEN));
        signIn.setStyle(Style.getButtonDesing());

        // Add all elements to the login layout
        this.vBoxLogInLayout.getChildren().addAll(coverIcon, userLabel, userField, passwordLabel, passwordField,
                signIn);
        this.vBoxLogInLayout.setSpacing(10);
    }

    public Button getSignInButton() {
        return this.signIn;
    }

    public String getPasswordInput() {
        return this.passwordField.getText();
    }

    public String getUsernameInput() {
        return this.userField.getText();
    }

    public TextField getUserField() {
        return this.userField;
    }

    public PasswordField getPasswordField() {
        return this.passwordField;
    }

    public VBox getvBoxLogInLayout() {
        return this.vBoxLogInLayout;
    }

    /**
     * This method adds a login failed message to the login layout
     */
    public void addLoginFailedMessage() {
        this.loginFailed = new Label("Anmeldung fehlgeschlagen. Bitte versuche es erneut.");
        this.loginFailed.setTextFill(Color.RED);
        this.vBoxLogInLayout.getChildren().add(loginFailed);
    }

    public Label getLoginFailedMessage() {
        return this.loginFailed;
    }

    /**
     * This method checks if the login failed message is visible
     * 
     * @return true if the message is visible, false otherwise
     */
    public boolean isLogininFaildMessageVisible() {
        for (Node n : this.vBoxLogInLayout.getChildren()) {
            if (n.equals(this.loginFailed)) {
                return true;
            }
        }
        return false;
    }

    public void removeLoginFailedMessage() {
        if (this.vBoxLogInLayout.getChildren().contains(this.loginFailed)) {
            this.vBoxLogInLayout.getChildren().remove(this.loginFailed);
        }
    }
}
