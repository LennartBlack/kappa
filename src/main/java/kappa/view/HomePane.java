package kappa.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class HomePane extends VBox {

    public HomePane() {
        this.setAlignment(Pos.CENTER);
        Label titleLabel = new Label("Willkommen bei KAPPA");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: " + Style.EWE_BLUE + ";");
        Label subTitleLabel = new Label("dem KabelAuslastungsPlanungsProgrammAssistenten");
        subTitleLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: " + Style.EWE_BLUE + ";");

        ImageView coverIcon = new ImageView("cover.jpeg");
        coverIcon.setFitWidth(500);
        coverIcon.setFitHeight(500);

        this.getChildren().addAll(titleLabel, subTitleLabel, coverIcon);
    }
}
