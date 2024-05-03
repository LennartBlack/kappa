package kappa.view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SignInPane extends VBox {
    private Button signIn;
    private TextField userField;
    private PasswordField passwordField;
    private Label loginFailed;
    /**
     * Constructor for the SignInPane class
     */
    public SignInPane() {
        this.setAlignment(Pos.CENTER);

        // Create the cover icon
        ImageView coverIcon = new ImageView("cover.jpeg");
        coverIcon.setFitWidth(250);
        coverIcon.setFitHeight(250);

        // Create the login form
        Label userLabel = new Label("Bitte gib deinen Butzernamen ein.");
        userLabel.setTextFill(javafx.scene.paint.Color.web((Style.getEweBlue())));
        this.userField = new TextField();
        userField.setPromptText("Benutzername");
        userField.setMinWidth(200);
        userField.setPrefWidth(200);
        userField.setMaxWidth(200);
        Label passwordLabel = new Label("Bitte gib dein Passwort ein.");
        passwordLabel.setTextFill(javafx.scene.paint.Color.web((Style.getEweBlue())));
        this.passwordField = new PasswordField();
        passwordField.setPromptText("Passwort");
        passwordField.setMinWidth(200);
        passwordField.setPrefWidth(200);
        passwordField.setMaxWidth(200);

        // Create the sign in button
        this.signIn = new Button("Anmelden");
        signIn.setTextFill(javafx.scene.paint.Color.web(Style.EWE_GREEN));
        signIn.setStyle(Style.getStandardDesign());

        // Add all elements to the login layout
        this.getChildren().addAll(coverIcon, userLabel, userField, passwordLabel, passwordField,
                signIn);
        this.setSpacing(10);
    }

    /**
     *  Getter for the signIn attribute
     * @return
     */
    public Button getSignInButton() {
        return this.signIn;
    }

    /**
     * Getter for the password input
     * @return
     */
    public String getPasswordInput() {
        return this.passwordField.getText();
    }

    /**
     * Getter for the username input
     * @return
     */
    public String getUsernameInput() {
        return this.userField.getText();
    }

    /**
     * Getter for the userField attribute
     * @return
     */
    public TextField getUserField() {
        return this.userField;
    }

    /**
     * Getter for the passwordField attribute
     * @return
     */
    public PasswordField getPasswordField() {
        return this.passwordField;
    }


    /**
     * This method adds a login failed message to the login layout
     */
    public void addLoginFailedMessage() {
        this.loginFailed = new Label("Anmeldung fehlgeschlagen. Bitte versuche es erneut.");
        this.loginFailed.setTextFill(Color.RED);
        this.getChildren().add(loginFailed);
    }

    /**
     * Getter for the loginFailed attribute
     * @return
     */
    public Label getLoginFailedMessage() {
        return this.loginFailed;
    }

    /**
     * This method checks if the login failed message is visible
     * 
     * @return true if the message is visible, false otherwise
     */
    public boolean isLogininFaildMessageVisible() {
        for (Node n : this.getChildren()) {
            if (n.equals(this.loginFailed)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method removes the login failed message from the login layout
     
     */
    public void removeLoginFailedMessage() {
        if (this.getChildren().contains(this.loginFailed)) {
            this.getChildren().remove(this.loginFailed);
        }
    }
}
