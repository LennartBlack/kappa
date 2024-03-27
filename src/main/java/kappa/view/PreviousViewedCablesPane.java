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
    private int maxAmountOfCables = 20;
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
        System.out.println("index ist" + index);
        Button temp = (Button) this.vBoxPreviousViewedCables.getChildren().get(index);
        System.out.println("text of index element" + temp.getText());
        vBoxPreviousViewedCables.getChildren().remove(index);
        vBoxPreviousViewedCables.getChildren().add(temp);
    }

    public Button createPreviousViewedCableButton(Cable cable) {
        Button cableButton = new Button();
        cableButton.setText(cable.getId());
        System.out.println("new Button created with text: " + cableButton.getText());
        return cableButton;
    }

    public void addButtonToVBox(Button button){
        this.vBoxPreviousViewedCables.getChildren().add(button);
    }

    public void removeOldestButton(){
        this.vBoxPreviousViewedCables.getChildren().remove(1);
    }

}
