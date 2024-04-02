package kappa.view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.*;

public class MenuPane extends Region {
    
    // Attributes
    private FlowPane flowPane;
    private HBox hBox;
    private Button helpButton;
    private Button topWorkloadCableButton;
    private Button watchlistButton;
    private Button openNewWindow;
    private TextField searchCableTextField;
    private Button logOffButton;
    private Double iconSize = 20.0;
    private ImageView searchCableIcon;
    private Button searchCableButton;

    // Constructor
    public MenuPane() {
        this.flowPane = new FlowPane();
        this.hBox = new HBox();
        this.hBox.setMaxWidth(1000);
        this.hBox.setMinWidth(1000);
        ToolBar toolBar = createToolbar();
        this.flowPane.getChildren().add(toolBar);
        this.getChildren().add(flowPane);
        this.setStyle("");
        getSearchCableTextField().requestFocus();
    }

    private ToolBar createToolbar() {
        this.searchCableTextField = new TextField();
        searchCableTextField.setPromptText("Kabel suchen");
        this.searchCableButton = new Button();
        this.searchCableIcon = new ImageView("search.png");
        searchCableIcon.setFitWidth(iconSize);
        searchCableIcon.setFitHeight(iconSize);
        searchCableButton.setGraphic(searchCableIcon);
        searchCableButton.setMinHeight(iconSize);
        searchCableButton.setMinWidth(iconSize);
        searchCableButton.setPrefHeight(iconSize);
        searchCableButton.setPrefWidth(iconSize);
        searchCableButton.setMaxHeight(iconSize);
        searchCableButton.setMaxWidth(iconSize);
        searchCableButton.setStyle("-fx-background-color: white;");

        this.openNewWindow = new Button("Neues Fenster");
        this.openNewWindow.setStyle(Style.getStandardDesign());
        ImageView newWindowIcon = new ImageView("new-tab.png");
        newWindowIcon.setFitWidth(iconSize);
        newWindowIcon.setFitHeight(iconSize);
        openNewWindow.setGraphic(newWindowIcon);

        this.topWorkloadCableButton = new Button("Top ausgelastete Kabel");
        this.topWorkloadCableButton.setStyle(Style.getStandardDesign());
        ImageView nearCapicityCalbeIcon = new ImageView("back-to-top.png");
        nearCapicityCalbeIcon.setFitWidth(iconSize);
        nearCapicityCalbeIcon.setFitHeight(iconSize);
        topWorkloadCableButton.setGraphic(nearCapicityCalbeIcon);

        this.watchlistButton = new Button("Merkliste anzeigen");
        this.watchlistButton.setStyle(Style.getStandardDesign());
        ImageView watchlistIcon = new ImageView("bookmark.png");
        watchlistIcon.setFitWidth(iconSize);
        watchlistIcon.setFitHeight(iconSize);
        watchlistButton.setGraphic(watchlistIcon);

        this.helpButton = new Button("Hilfe");
        this.helpButton.setStyle(Style.getStandardDesign());
        ImageView helpIcon = new ImageView("help.png");
        helpIcon.setFitWidth(iconSize);
        helpIcon.setFitHeight(iconSize);
        helpButton.setGraphic(helpIcon);

        this.logOffButton = new Button("Abmelden");
        this.logOffButton.setStyle(Style.getStandardDesign());
        ImageView logOffIcon = new ImageView("sign-out.png");
        logOffIcon.setFitHeight(iconSize);
        logOffIcon.setFitWidth(iconSize);
        logOffButton.setGraphic(logOffIcon);

        ToolBar toolbar = new ToolBar();
        toolbar.getItems().addAll(searchCableButton, searchCableTextField, openNewWindow, topWorkloadCableButton,
                watchlistButton, helpButton, logOffButton);
        toolbar.setStyle("-fx-background-color: white;");
        return toolbar;
    }

    // Getter
    public Button getHelpButton() {
        return this.helpButton;
    }

    public Button getTopWorkloadCableButton() {
        return topWorkloadCableButton;
    }

    public Button getWatchlistButton() {
        return watchlistButton;
    }

    public Button getOpenNewWindow() {
        return openNewWindow;
    }

    public Button getLogOffButton() {
        return logOffButton;
    }

    public Button getSearchCableButton() {
        return searchCableButton;
    }

    public TextField getSearchCableTextField() {
        return searchCableTextField;
    }

    public ImageView getMagnifierIcon() {
        return searchCableIcon;
    }
}
