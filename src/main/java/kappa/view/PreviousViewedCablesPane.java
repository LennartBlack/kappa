package kappa.view;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import kappa.model.Cable;

public class PreviousViewedCablesPane extends Region {
    private VBox vBoxPreviousViewedCables;
    private int maxAmountOfCables = 10;
    private ArrayList<Cable> previousViewedCables = new ArrayList<Cable>();

    public PreviousViewedCablesPane() {
        this.vBoxPreviousViewedCables = new VBox();
        Label previousViewedCablesLabel = new Label("Zuletzt angesehene Kabel");
        this.vBoxPreviousViewedCables.getChildren().add(previousViewedCablesLabel);
    }

    public VBox getvBoxPreviousViewedCables() {
        return this.vBoxPreviousViewedCables;
    }

    public boolean checkIfCableIsInPreviousViewedCables(Cable cable) {
        boolean isCableInPreviousViewedCables = false;
        for (Cable element : this.previousViewedCables) {
            if (element.equals(cable)) {
                isCableInPreviousViewedCables = true;
            }
        }
        return isCableInPreviousViewedCables;
    }

    public boolean checkIfMaxAmountOfCablesIsReached() {
        if (previousViewedCables.size() >= maxAmountOfCables) {
            return true;
        } else {
            return false;
        }
    }

    public void addCableToPreviousViewedCables(Cable cable) {
        this.previousViewedCables.add(cable);
    }

    public void removeCableFromPreviousViewedCables(Cable cable) {
        this.previousViewedCables.remove(cable);
    }

}
