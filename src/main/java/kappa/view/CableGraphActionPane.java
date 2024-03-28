package kappa.view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import kappa.model.Cable;

public class CableGraphActionPane extends HBox {

    // Attributes
    private Button addToWatchlistButton;
    private Button removeFromWatchlistButton;

    // Constructor
    public CableGraphActionPane(Cable cable) {
        this.addToWatchlistButton = new Button("Zur Merkliste hinzufügen");
        this.removeFromWatchlistButton = new Button("Von Merkliste entfernen");
        this.getChildren().addAll(this.addToWatchlistButton, this.removeFromWatchlistButton);
    }

    // Getter
    public Button getAddToWatchlistButton() {
        return this.addToWatchlistButton;
    }

    public Button getRemoveFromWatchlistButton() {
        return this.removeFromWatchlistButton;
    }
}
