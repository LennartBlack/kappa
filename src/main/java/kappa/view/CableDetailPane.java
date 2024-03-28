package kappa.view;

import javafx.scene.layout.BorderPane;
import kappa.model.Cable;

public class CableDetailPane extends BorderPane {

    // Attributes
    private CableGraphActionPane graphActionPane;
    private CableGraphPane graphPane;
    private Cable cable;

    // Constructor
    public CableDetailPane(Cable cable) {
        this.cable = cable;
        this.graphActionPane = new CableGraphActionPane(cable);
        this.graphPane = new CableGraphPane(cable);
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
