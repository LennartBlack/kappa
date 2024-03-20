package kappa.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
public class SignInPane extends Region {
    private HBox hBox;
    public SignInPane(){
        Button openRegisterPaneButton = new Button("Registrieren");
        Label anmeldenLabel = new Label("Anmelden");
        TextInputDialog emailInput = new TextInputDialog("E-Mail");
        Button emailVergessenButton =  new Button("E-Mail vergessen? Hier klicken!");
        TextInputDialog passwortInput = new TextInputDialog("Passwort");
        Button logInButton = new Button("Einloggen");

        this.hBox = new HBox();
        this.hBox.setStyle("-fx-border-color:lightgrey;-fx-border-width:1px; -fx-max-width:800;");
        this.hBox.getChildren().addAll(openRegisterPaneButton, anmeldenLabel, emailVergessenButton, logInButton);
        this.getChildren().add(hBox);
    }
}
