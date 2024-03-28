package kappa.view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import kappa.model.Cable;

public class CablePane extends Region {
    private BorderPane cableDetailPane;
    private CableInfoPane cableInfoPane;

    public CablePane(Cable cable) {
        this.cableDetailPane = new BorderPane();
        this.cableInfoPane = new CableInfoPane(cable);
        this.cableDetailPane.setLeft(this.cableInfoPane.getVBoxCableInfo());
    }

    public BorderPane getCablePane() {
        return this.cableDetailPane;
    }
}
