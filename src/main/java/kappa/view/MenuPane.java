package kappa.view;

import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class MenuPane  extends Region {
    private VBox vBox;

    public MenuPane(){
        this.vBox = new VBox();
        Label test = new Label
        this.vBox.setStyle("-fx-border-color:lightgrey;-fx-border-width:1px;");
        this.vBox.getChildren().addAll(test);
        this.getChildren().add(vBox);
    }
}
