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
        System.out.println("cabledetailpane Konstruktor");
        this.graphActionPane = new CableGraphActionPane(this.cable);
        System.out.println("cablegrahppane Konstruktor");
        this.graphPane = new CableGraphPane(this.cable);
        System.out.println("Fehler");
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
