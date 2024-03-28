package kappa.view;

import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import kappa.model.Cable;
import kappa.model.PreviousViewedCable;

public class PreviousViewedCablesPane extends Region {
    private VBox vBoxPreviousViewedCables;
    private PreviousViewedCable previousViewedCables = new PreviousViewedCable();

    public PreviousViewedCablesPane() {
        this.vBoxPreviousViewedCables = new VBox();
    }

    public VBox getvBoxPreviousViewedCables() {
        return this.vBoxPreviousViewedCables;
    }

    public PreviousViewedCable getPreviousViewedCables() {
        return this.previousViewedCables;
    }

    public Button createPreviousViewedCableButton(Cable cable) {
        Button cableButton = new Button();
        cableButton.setText(cable.getId());
        addButtonToVBox(cableButton);
        return cableButton;
    }

    public void addButtonToVBox(Button button) {
        this.vBoxPreviousViewedCables.getChildren().addFirst(button);
    }

    public Button removeButtonByIndex(int index) {
        Button b = (Button) this.vBoxPreviousViewedCables.getChildren().get(index);
        this.vBoxPreviousViewedCables.getChildren().remove(index);
        return b;
    }

    public void putButtonOnTopOfList(int oldIndex) {
        addButtonToVBox(removeButtonByIndex(oldIndex));
    }

    public void removeOldestButton() {
        this.vBoxPreviousViewedCables.getChildren().remove(this.vBoxPreviousViewedCables.getChildren().size() - 1);
    }

}
