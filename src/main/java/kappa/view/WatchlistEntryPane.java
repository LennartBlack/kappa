package kappa.view;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import kappa.model.WatchlistElement;

public class WatchlistEntryPane extends HBox {

    // Attributes
    private WatchlistElement watchlistElement;
    private Label noteLabel;
    private Button cableIdButton;
    private Button addNoteButton;
    private Button editNoteButton;
    private Button deleteNoteButton;
    private Button saveNoteButton;
    private Button removeFromWatchlistButton;
    private TextField noteTextField = new TextField();

    // Constructor
    public WatchlistEntryPane(WatchlistElement watchlistElement) {
        super();
        this.noteLabel = new Label();
        this.watchlistElement = watchlistElement;
        this.cableIdButton = new Button(watchlistElement.getCable().getId());
        this.addNoteButton = new Button("Notiz hinzufügen");
        this.editNoteButton = new Button("Notiz bearbeiten");
        this.deleteNoteButton = new Button("Notiz löschen");
        this.saveNoteButton = new Button("Notiz speichern");
        this.removeFromWatchlistButton = new Button("Aus Watchlist entfernen");
        this.setAlignment(Pos.CENTER_LEFT);
        setStyle();
        this.setPrefHeight(100);
        this.setStyle("-fx-border-color: #5A5F5F;");
        if (isNoteEmpty()) {
            createPaneWithoutNote();
        } else {
            createPaneWithNote();
        }
    }

    public void createPaneWithNote() {
        this.getChildren().clear();
        this.getChildren().addAll(cableIdButton, removeFromWatchlistButton);
        addGraphPreview();
        this.noteLabel.setText(watchlistElement.getNote());
        VBox editDeletevBox = new VBox(editNoteButton, deleteNoteButton);
        editDeletevBox.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(editDeletevBox, noteLabel); 
    }

    public void createPaneWithoutNote() {
        this.getChildren().clear();
        this.getChildren().addAll(cableIdButton, removeFromWatchlistButton);
        addGraphPreview();
        this.getChildren().add(addNoteButton);
    }

    public void createEditNotePane() {
        this.getChildren().clear();
        this.getChildren().addAll(cableIdButton, removeFromWatchlistButton);
        addGraphPreview();
        this.noteTextField.setText(watchlistElement.getNote());
        this.getChildren().addAll(saveNoteButton, noteTextField);
    }

    private void setStyle(){
        this.cableIdButton.setStyle(Style.getStandardDesign());
        this.addNoteButton.setStyle(Style.getStandardDesign());
        this.editNoteButton.setStyle(Style.getStandardDesign());
        this.deleteNoteButton.setStyle(Style.getStandardDesign());
        this.saveNoteButton.setStyle(Style.getStandardDesign());
        this.removeFromWatchlistButton.setStyle(Style.getStandardDesign());
    }
    private void addGraphPreview() {
    } 

    private boolean isNoteEmpty() {
        return(watchlistElement.getNote().equals(""));
    }
    
    // Getter
    public Label getNoteLabel() {
        return noteLabel;
    }

    public Button getCableIdButton() {
        return cableIdButton;
    }
    
    public Button getAddNoteButton() {
        return addNoteButton;
    }

    public Button getEditNoteButton() {
        return editNoteButton;
    }

    public Button getDeleteNoteButton() {
        return deleteNoteButton;
    }

    public Button getSaveNoteButton() {
        return saveNoteButton;
    }

    public Button getRemoveFromWatchlistButton() {
        return removeFromWatchlistButton;
    }

    public TextField getNoteTextField() {
        return noteTextField;
    }
    
    public WatchlistElement getWatchlistElement() {
        return watchlistElement;
    }
}
