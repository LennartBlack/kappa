package kappa.view;


import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;




public class MenuPane  extends Region {
    private VBox vBox;
    private HBox hBox;
    private Button helpButton;
    private Button topWorkloadCableButton;
    private Button watchlistButton;
    private Button openNewWindow;
    private TextField searchCableTextField;
    private Button logOffButton;
    private VBox searchVBox;

    public MenuPane(){
        this.hBox = new HBox();

        ToolBar toolBar = createToolbar();

        this.hBox.setStyle("-fx-border-color:lightgrey;-fx-border-width:1px; -fx-max-width:800;");
        this.hBox.getChildren().addAll(toolBar);
        this.getChildren().add(hBox);
    }

    private ToolBar createToolbar() {
        this.searchCableTextField = new TextField();
        this.searchCableTextField.setPromptText("Kabel suchen");
        ImageView searchCableIcon = new ImageView("search.png");
        searchCableIcon.setFitWidth(16);
        searchCableIcon.setFitHeight(16);
        
        
        
        this.openNewWindow = new Button("Neues Fenster");
        ImageView newWindowIcon = new ImageView("new-tab.png");
        newWindowIcon.setFitWidth(16);
        newWindowIcon.setFitHeight(16);
        openNewWindow.setGraphic(newWindowIcon);
    

        this.topWorkloadCableButton = new Button("Top ausgelastete Kabel");
        ImageView nearCapicityCalbeIcon = new ImageView("back-to-top.png");
        nearCapicityCalbeIcon.setFitWidth(16);
        nearCapicityCalbeIcon.setFitHeight(16);
        topWorkloadCableButton.setGraphic(nearCapicityCalbeIcon);

        this.watchlistButton = new Button("Merkliste anzeigen");
        ImageView watchlistIcon = new ImageView("bookmark.png");
        watchlistIcon.setFitWidth(16);
        watchlistIcon.setFitHeight(16);
        watchlistButton.setGraphic(watchlistIcon);

        this.helpButton = new Button("Hilfe");
        ImageView helpIcon = new ImageView("help.png");
        helpIcon.setFitWidth(16);
        helpIcon.setFitHeight(16);
        helpButton.setGraphic(helpIcon);

        this.logOffButton = new Button("Abmelden");
        ImageView logOffIcon = new ImageView("sign-out.png");
        logOffIcon.setFitHeight(16);
        logOffIcon.setFitWidth(16);
        logOffButton.setGraphic(logOffIcon);

        ToolBar toolbar = new ToolBar();
        toolbar.setStyle("-fx-border-color:lightgrey;-fx-border-width:1px;");
        toolbar.getItems().addAll(searchCableIcon, searchCableTextField, openNewWindow, topWorkloadCableButton, watchlistButton, helpButton, logOffButton);
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
