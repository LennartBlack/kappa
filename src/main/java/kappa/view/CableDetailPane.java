package kappa.view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import kappa.model.Cable;


public class CableDetailPane extends Region{
    private BorderPane cableDetailPane;

    public CableDetailPane(Cable cable) {
        this.cableDetailPane = new BorderPane();
        Label cableLabel = new Label(cable.getId());
        this.cableDetailPane.setTop(cableLabel);
    }

    public BorderPane getCableDetailPane() {
        return this.cableDetailPane;
    }
}
