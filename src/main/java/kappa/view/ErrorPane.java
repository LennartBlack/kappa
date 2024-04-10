package kappa.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ErrorPane extends VBox{
    public ErrorPane(String error){
        Label info = new Label("Ein kritischer Fehler ist aufgetreten: ");
        Label errorLabel = new Label(error);
        Label info2 = new Label("Bitte kontaktieren Sie den Applikationsverantwortlichen.");
        Label info3 = new Label("Dieser ist im SIS-Bestellsystem hinterlegt.");
        Label info4 = new Label("Die Applikation wird nun beendet.");
        this.getChildren().addAll(info, errorLabel, info2, info3, info4);
    }
    
}
