package kappa.view;

import javafx.scene.layout.BorderPane;
import kappa.model.Cable;
import kappa.model.Watchlist;

public class CableDetailPane extends BorderPane {

    // Attributes
    private CableGraphActionPane graphActionPane;
    private CableGraphPane graphPane;
    private Cable cable;

    /**
     * Constructor for the CableDetailPane class
     * @param cable
     * @param watchlist
     */
    public CableDetailPane(Cable cable, Watchlist watchlist) {
        this.cable = cable;
        this.graphActionPane = new CableGraphActionPane(this.cable, watchlist);
        this.graphPane = new CableGraphPane(this.cable);
        this.setBottom(graphActionPane);
        this.setCenter(graphPane);
    }

    // Getter
    /**
     * Getter for the graphActionPane attribute
     * @return
     */
    public CableGraphActionPane getGraphActionPane() {
        return graphActionPane;
    }
    /**
     * Getter for the graphPane attribute
     * @return
     */
    public CableGraphPane getGraphPane() {
        return graphPane;
    }

}
