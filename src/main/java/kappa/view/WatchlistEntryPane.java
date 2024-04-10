package kappa.view;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import kappa.model.WatchlistElement;

public class WatchlistEntryPane extends HBox {

    // Attributes
    private WatchlistElement watchlistElement;
    private TextArea noteLabel;
    private Button cableIdButton;
    private Button addNoteButton;
    private Button editNoteButton;
    private Button deleteNoteButton;
    private Button saveNoteButton;
    private Button removeFromWatchlistButton;
    private TextField noteTextField = new TextField();
    private VBox editDeletevBox;

    // Constructor
    public WatchlistEntryPane(WatchlistElement watchlistElement) {
        super();
        this.noteLabel = new TextArea();
        this.watchlistElement = watchlistElement;
        this.cableIdButton = new Button(watchlistElement.getCable().getId());
        this.addNoteButton = new Button("Notiz hinzufügen");
        this.editNoteButton = new Button("Notiz bearbeiten");
        this.deleteNoteButton = new Button("Notiz löschen");
        this.saveNoteButton = new Button("Notiz speichern");
        this.removeFromWatchlistButton = new Button("Aus Merkliste entfernen");
        this.editDeletevBox = new VBox(editNoteButton, deleteNoteButton);

        setStyle();

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

    private void setStyle() {
        this.setPadding(Style.getGap());
        WatchlistEntryPane.setMargin(cableIdButton, Style.getGap());
        WatchlistEntryPane.setMargin(addNoteButton, Style.getGap());
        WatchlistEntryPane.setMargin(editNoteButton, Style.getGap());
        WatchlistEntryPane.setMargin(deleteNoteButton, Style.getGap());
        WatchlistEntryPane.setMargin(saveNoteButton, Style.getGap());
        WatchlistEntryPane.setMargin(removeFromWatchlistButton, Style.getGap());
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPrefHeight(120);
        this.setStyle("-fx-border-color: #5A5F5F;");

        VBox.setMargin(editDeletevBox, Style.getGap());
        editDeletevBox.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(editNoteButton, Style.getGap());
        VBox.setMargin(deleteNoteButton, Style.getGap());
        editDeletevBox.setPadding(Style.getGap());

        this.cableIdButton.setStyle(Style.getStandardDesign());
        this.cableIdButton.setPadding(Style.getGap());

        this.addNoteButton.setStyle(Style.getStandardDesign());
        this.addNoteButton.setPadding(Style.getGap());

        this.editNoteButton.setStyle(Style.getStandardDesign());
        this.editNoteButton.setPadding(Style.getGap());

        this.deleteNoteButton.setStyle(Style.getStandardDesign());
        this.deleteNoteButton.setPadding(Style.getGap());

        this.saveNoteButton.setStyle(Style.getStandardDesign());
        this.saveNoteButton.setPadding(Style.getGap());

        this.removeFromWatchlistButton.setStyle(Style.getStandardDesign());
        this.removeFromWatchlistButton.setPadding(Style.getGap());
    }

    private void addGraphPreview() {
    }

    private boolean isNoteEmpty() {
        return (watchlistElement.getNote().equals(""));
    }

    // Getter
    public TextArea getNoteLabel() {
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
