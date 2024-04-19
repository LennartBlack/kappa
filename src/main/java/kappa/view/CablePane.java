package kappa.view;

import javafx.scene.layout.BorderPane;
import kappa.model.Cable;
import kappa.model.Watchlist;

public class CablePane extends BorderPane {

    // Attributes
    private CableInfoPane cableInfoPane;
    private CableDetailPane cableDetailPane;
    private Cable cable;

    // Constructor
    public CablePane(Cable cable, Watchlist watchlist) {
        this.cableInfoPane = new CableInfoPane(cable);
        this.cableDetailPane = new CableDetailPane(cable, watchlist);
        this.setLeft(this.cableInfoPane);
        this.setCenter(this.cableDetailPane);
    }

    // Getter
    public CableInfoPane getCableInfoPane() {
        return this.cableInfoPane;
    }

    public CableDetailPane getCableDetailPane() {
        return this.cableDetailPane;
    }

    public Cable getCable() {
        return this.cable;
    }
}
