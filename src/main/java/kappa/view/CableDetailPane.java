package kappa.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

public class CableDetailPane extends Region {

    private BorderPane cableDetailPane;
    private CableGraphActionPane graphActionPane;
    private CableGraphPane graphPane;

    public CableDetailPane() {
        this.cableDetailPane = new BorderPane();
        this.graphActionPane = new CableGraphActionPane();
        this.graphPane = new CableGraphPane();
        this.cableDetailPane.setBottom(graphActionPane);
        this.cableDetailPane.setCenter(graphPane);
    }

}
