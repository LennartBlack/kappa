package kappa.view;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import kappa.model.Cable;
import kappa.model.PreviousViewedCable;

public class PreviousViewedCablesPane extends Region {
    private VBox vBoxPreviousViewedCables;
    private int maxAmountOfCables = 10;
    private PreviousViewedCable previousViewedCables = new PreviousViewedCable();

    public PreviousViewedCablesPane() {
        this.vBoxPreviousViewedCables = new VBox();
        Label previousViewedCablesLabel = new Label("Zuletzt angesehene Kabel");
        this.vBoxPreviousViewedCables.getChildren().add(previousViewedCablesLabel);
    }

    public VBox getvBoxPreviousViewedCables() {
        return this.vBoxPreviousViewedCables;
    }

    public PreviousViewedCable getPreviousViewedCables() {
        return this.previousViewedCables;
    }

    public void putFirst(int index){
        Button temp = (Button) this.vBoxPreviousViewedCables.getChildren().get(index + 1);
        vBoxPreviousViewedCables.getChildren().add(temp);
        vBoxPreviousViewedCables.getChildren().remove(index + 1);
    }

    public Button addPreviousViewedCableButton(Cable cable) {
        Button cableButton = new Button(cable.getIdentification());
        this.vBoxPreviousViewedCables.getChildren().add(cableButton);
        return cableButton;
    }

    public void removeLastButton(){
        this.vBoxPreviousViewedCables.getChildren().remove(1);
    }

}
