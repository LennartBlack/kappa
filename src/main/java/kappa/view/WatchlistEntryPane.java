package kappa.view;

import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import kappa.model.WatchlistElement;

public class WatchlistEntryPane extends HBox {

    // Attributes
    private WatchlistElement watchlistElement;
    private Label cableIdLabel;
    private Button editNoteButton = new Button("Edit Note");
    private Label noteLabel;

    // Constructor
    public WatchlistEntryPane(WatchlistElement watchlistElement) {
        super();
        this.watchlistElement = watchlistElement;
        this.cableIdLabel = new Label(watchlistElement.getCable().getId());
        if (watchlistElement.getNote() != null) {
            this.editNoteButton.setText("Edit Note");
            this.noteLabel = new Label(watchlistElement.getNote());
        } else {
            this.editNoteButton.setText("Add Note");
            watchlistElement.setNote("Notiz hinzufügen");
            System.out.println("Note: " + watchlistElement.getNote());
        }
        this.getChildren().addAll(cableIdLabel, editNoteButton);

    }

    public void updateWatchlistEntryPane() {
        TextField noteTextField = new TextField();
        this.noteLabel = new Label(watchlistElement.getNote());
        this.getChildren().add(noteTextField);
    }
    // Getter

}
