package kappa.view;

import javafx.scene.layout.BorderPane;
import kappa.model.Cable;
import kappa.model.Watchlist;

public class CableDetailPane extends BorderPane {

    // Attributes
    private CableGraphActionPane graphActionPane;
    private CableGraphPane graphPane;
    private Cable cable;

    // Constructor
    public CableDetailPane(Cable cable, Watchlist watchlist) {
        this.cable = cable;
        this.graphActionPane = new CableGraphActionPane(this.cable, watchlist);
        this.graphPane = new CableGraphPane(this.cable);
        this.setBottom(graphActionPane);
        this.setCenter(graphPane);
    }

    // Getter
    public CableGraphActionPane getGraphActionPane() {
        return graphActionPane;
    }
    public CableGraphPane getGraphPane() {
        return graphPane;
    }

}
