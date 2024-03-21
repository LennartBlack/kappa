package kappa.view;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HomePane {
    private VBox vBoxHomeLayout;

    public HomePane() {
        this.vBoxHomeLayout = new VBox();
        Label homeLabel = new Label("Willkommen bei Kappa");
        vBoxHomeLayout.getChildren().add(homeLabel);
    }
    public Parent getvBoxHomeLayout() {
        return this.vBoxHomeLayout;
    }
    
}
