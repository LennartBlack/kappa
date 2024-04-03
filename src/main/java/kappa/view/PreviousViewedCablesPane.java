package kappa.view;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import kappa.model.Cable;
import kappa.model.PreviousViewedCable;

public class PreviousViewedCablesPane extends VBox {

    // Attributes
    private PreviousViewedCable previousViewedCables = new PreviousViewedCable();

    // Constructor
    public PreviousViewedCablesPane() {
    }

    // Methods
    public Button createPreviousViewedCableButton(Cable cable) {
        Button cableButton = new Button();
        cableButton.setStyle(Style.getStandardDesign());
        cableButton.setText(cable.getId());
        cableButton.setPrefWidth(70);
        addButton(cableButton);
        return cableButton;
    }

    public void addButton(Button button) {
        this.addFirst(button);
    }

    public Button removeButtonByIndex(int index) {
        Button b = (Button) this.getChildren().get(index);
        this.getChildren().remove(index);
        return b;
    }

    public void putButtonOnTopOfList(int oldIndex) {
        addButton(removeButtonByIndex(oldIndex));
    }

    public void removeOldestButton() {
        this.getChildren().remove(this.getChildren().size() - 1);
    }

    public void addFirst(Button button) {
        this.getChildren().add(0, button);
    }

    // Getter
    public boolean isvBoxEmpty() {
        return this.getChildren().isEmpty();
    }

    public PreviousViewedCable getPreviousViewedCables() {
        return this.previousViewedCables;
    }
}
