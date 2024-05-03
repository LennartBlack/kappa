package kappa.view;

import javafx.scene.layout.BorderPane;
import kappa.model.Cable;
import kappa.model.Watchlist;

public class CablePane extends BorderPane {

    // Attributes
    private CableInfoPane cableInfoPane;
    private CableDetailPane cableDetailPane;
    private Cable cable;

    /**
     * Constructor for the CablePane class
     * @param cable
     * @param watchlist
     */
    public CablePane(Cable cable, Watchlist watchlist) {
        this.cableInfoPane = new CableInfoPane(cable);
        this.cableDetailPane = new CableDetailPane(cable, watchlist);
        this.setLeft(this.cableInfoPane);
        this.setCenter(this.cableDetailPane);
    }

    // Getter
    /**
     * Getter for the cableInfoPane attribute
     * @return
     */
    public CableInfoPane getCableInfoPane() {
        return this.cableInfoPane;
    }

    /**
     * Getter for the cableDetailPane attribute
     * @return
     */
    public CableDetailPane getCableDetailPane() {
        return this.cableDetailPane;
    }

    /**
     * Getter for the cable attribute
     * @return
     */
    public Cable getCable() {
        return this.cable;
    }
}
