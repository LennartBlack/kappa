package kappa.view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

public class MenuPane extends Region {
    private FlowPane flowPane;
    private HBox hBox;
    private Button helpButton;
    private Button topWorkloadCableButton;
    private Button watchlistButton;
    private Button openNewWindow;
    private TextField searchCableTextField;
    private Button logOffButton;
    private VBox searchVBox;

    public MenuPane() {
        this.flowPane = new FlowPane();
        this.hBox = new HBox();
        this.hBox.setMaxWidth(1000);
        this.hBox.setMinWidth(1000);
        ToolBar toolBar = createToolbar();

        // this.hBox.setStyle("-fx-border-color:lightgrey;-fx-border-width:1px;
        // -fx-max-width:800;");
        // this.hBox.getChildren().addAll(toolBar);

        this.flowPane.getChildren().add(toolBar);
        this.flowPane.setStyle("-fx-background-color: " + Style.getEweGrey() + ";");
        this.getChildren().add(flowPane);
        this.setStyle("");
    }

    private ToolBar createToolbar() {
        this.searchCableTextField = new TextField();
        this.searchCableTextField.setPromptText("Kabel suchen");
        ImageView searchCableIcon = new ImageView("search.png");
        searchCableIcon.setFitWidth(16);
        searchCableIcon.setFitHeight(16);

        this.openNewWindow = new Button("Neues Fenster");
        this.openNewWindow.setStyle(Style.getButtonDesing());
        ImageView newWindowIcon = new ImageView("new-tab.png");
        newWindowIcon.setFitWidth(16);
        newWindowIcon.setFitHeight(16);
        openNewWindow.setGraphic(newWindowIcon);

        this.topWorkloadCableButton = new Button("Top ausgelastete Kabel");
        this.topWorkloadCableButton.setStyle(Style.getButtonDesing());
        ImageView nearCapicityCalbeIcon = new ImageView("back-to-top.png");
        nearCapicityCalbeIcon.setFitWidth(16);
        nearCapicityCalbeIcon.setFitHeight(16);
        topWorkloadCableButton.setGraphic(nearCapicityCalbeIcon);

        this.watchlistButton = new Button("Merkliste anzeigen");
        this.watchlistButton.setStyle(Style.getButtonDesing());
        ImageView watchlistIcon = new ImageView("bookmark.png");
        watchlistIcon.setFitWidth(16);
        watchlistIcon.setFitHeight(16);
        watchlistButton.setGraphic(watchlistIcon);

        this.helpButton = new Button("Hilfe");
        this.helpButton.setStyle(Style.getButtonDesing());
        ImageView helpIcon = new ImageView("help.png");
        helpIcon.setFitWidth(16);
        helpIcon.setFitHeight(16);
        helpButton.setGraphic(helpIcon);

        this.logOffButton = new Button("Abmelden");
        this.logOffButton.setStyle(Style.getButtonDesing());
        ImageView logOffIcon = new ImageView("sign-out.png");
        logOffIcon.setFitHeight(16);
        logOffIcon.setFitWidth(16);
        logOffButton.setGraphic(logOffIcon);

        ToolBar toolbar = new ToolBar();
        toolbar.getItems().addAll(searchCableIcon, searchCableTextField, openNewWindow, topWorkloadCableButton,
                watchlistButton, helpButton, logOffButton);
        toolbar.setStyle("-fx-background-color: white;");
        return toolbar;
    }

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

    public TextField getSearchCableTextField() {
        return searchCableTextField;
    }
}
