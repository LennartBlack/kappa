package kappa.view;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class HomePane extends Region {
    private VBox vBoxHomeLayout;

    public HomePane() {
        this.vBoxHomeLayout = new VBox();
        Label homeLabel = new Label("Willkommen bei Kappa");

        ImageView coverIcon = new ImageView("cover.jpeg");
        coverIcon.setFitWidth(250);
        coverIcon.setFitHeight(250);

        vBoxHomeLayout.getChildren().addAll(homeLabel, coverIcon);
    }

    public Parent getvBoxHomeLayout() {
        return this.vBoxHomeLayout;
    }

}
