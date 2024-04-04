package kappa.view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import kappa.model.Cable;

public class CableGraphActionPane extends HBox {

    // Attributes
    private Button addToWatchlistButton;
    private Button removeFromWatchlistButton;

    // Constructor
    public CableGraphActionPane(Cable cable) {
        this.addToWatchlistButton = new Button("Zur Merkliste hinzufügen");
        addToWatchlistButton.setStyle(Style.getStandardDesign());
        this.removeFromWatchlistButton = new Button("Von Merkliste entfernen");
        removeFromWatchlistButton.setStyle(Style.getStandardDesign());
        this.getChildren().addAll(this.addToWatchlistButton, this.removeFromWatchlistButton);
        style();
    }

    // Methods
    private void style() {
        CableGraphActionPane.setMargin(addToWatchlistButton, Style.getGap());
        CableGraphActionPane.setMargin(removeFromWatchlistButton, Style.getGap());
        this.addToWatchlistButton.setPadding(Style.getGap());
        this.removeFromWatchlistButton.setPadding(Style.getGap());
    }

    // Getter
    public Button getAddToWatchlistButton() {
        return this.addToWatchlistButton;
    }

    public Button getRemoveFromWatchlistButton() {
        return this.removeFromWatchlistButton;
    }
}
