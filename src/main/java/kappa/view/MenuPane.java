package kappa.view;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.HBox;


public class MenuPane  extends Region {
    private HBox hBox;

    public MenuPane(){
        this.hBox = new HBox();
        ToolBar toolBar = createToolbar();

        this.hBox.setStyle("-fx-border-color:lightgrey;-fx-border-width:1px; -fx-max-width:800;");
        this.hBox.getChildren().addAll(toolBar);
        this.getChildren().add(hBox);
    }

    private ToolBar createToolbar() {
        Button newWindowSearchButton = new Button("Kabel in neuem Fenster suchen");
        ImageView newWindowSearchIcon = new ImageView("search.png");
        newWindowSearchIcon.setFitWidth(16);
        newWindowSearchIcon.setFitHeight(16);
        newWindowSearchButton.setGraphic(newWindowSearchIcon);

        Button searchCableButton = new Button("Neues Kabel suchen");
        ImageView searchCableIcon = new ImageView("new-tab.png");
        searchCableIcon.setFitWidth(16);
        searchCableIcon.setFitHeight(16);
        searchCableButton.setGraphic(searchCableIcon);

        Button showNearCapicityCalbeButton = new Button("Top ausgelastete Kabel");
        ImageView showNearCapicityCalbeIcon = new ImageView("back-to-top.png");
        showNearCapicityCalbeIcon.setFitWidth(16);
        showNearCapicityCalbeIcon.setFitWidth(16);
        showNearCapicityCalbeButton.setGraphic(showNearCapicityCalbeIcon);

        Button showWatchlistButton = new Button("Merkliste anzeigen");
        ImageView showWatchlistIcon = new ImageView("bookmark.png");
        showWatchlistIcon.setFitWidth(16);
        showWatchlistIcon.setFitHeight(16);
        showNearCapicityCalbeButton.setGraphic(showWatchlistIcon);


        Button logOffButton = new Button("Abmelden");
        ImageView logOffIcon = new ImageView("sign-out.png");
        logOffIcon.setFitHeight(16);
        logOffIcon.setFitWidth(16);
        logOffButton.setGraphic(logOffIcon);

        ToolBar toolbar = new ToolBar();
        toolbar.setStyle("-fx-border-color:lightgrey;-fx-border-width:1px;");
        toolbar.getItems().addAll(newWindowSearchButton, searchCableButton, showNearCapicityCalbeButton, logOffButton);
        return toolbar;
    }
}
