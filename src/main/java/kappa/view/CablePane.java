package kappa.view;

import javafx.scene.layout.BorderPane;
import kappa.model.Cable;

public class CablePane extends BorderPane {

    // Attributes
    private CableInfoPane cableInfoPane;
    private CableDetailPane cableDetailPane;
    private Cable cable;

    // Constructor
    public CablePane(Cable cable) {
        this.cableInfoPane = new CableInfoPane(cable);
        this.cableDetailPane = new CableDetailPane(cable);
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
