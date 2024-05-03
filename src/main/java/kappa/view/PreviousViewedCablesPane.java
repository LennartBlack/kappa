package kappa.view;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import kappa.model.Cable;
import kappa.model.PreviousViewedCable;

public class PreviousViewedCablesPane extends VBox {

    // Attributes
    private PreviousViewedCable previousViewedCables = new PreviousViewedCable();

    /**
     * Constructor for the PreviousViewedCablesPane class
     */
    public PreviousViewedCablesPane() {
    }

    // Methods
    /**
     *  Method to create a button for the previous viewed cables
     * @param cable
     * @return
     */
    public Button createPreviousViewedCableButton(Cable cable) {
        Button cableButton = new Button();
        cableButton.setStyle(Style.getStandardDesign());
        cableButton.setText(cable.getId());
        cableButton.setPrefWidth(70);
        style(cableButton);
        addButton(cableButton);
        return cableButton;
    }

    /**
     * Method to add a button to the vBox
     * @param button
     */
    public void addButton(Button button) {
        this.addFirst(button);
    }

    /**
     * Method to remove a button by index
     * @param index
     * @return
     */
    public Button removeButtonByIndex(int index) {
        Button b = (Button) this.getChildren().get(index);
        this.getChildren().remove(index);
        return b;
    }

    /**
     * Method to put a button on top of the list
     * @param oldIndex
     */
    public void putButtonOnTopOfList(int oldIndex) {
        addButton(removeButtonByIndex(oldIndex));
    }

    /**
     * Method to remove the oldest button
     */
    public void removeOldestButton() {
        this.getChildren().remove(this.getChildren().size() - 1);
    }

    /**
     * Method to add a button to the vBox
     * @param button
     */
    public void addFirst(Button button) {
        this.getChildren().add(0, button);
    }

    /**
     * Method to style the button
     * @param button
     */
    private void style(Button button) {
        PreviousViewedCablesPane.setMargin(button, Style.getGap());
    }

    // Getter
    /**
     * Method to check if the vBox is empty
     * @return
     */
    public boolean isvBoxEmpty() {
        return this.getChildren().isEmpty();
    }

    /**
     * Getter for the previousViewedCables attribute
     * @return
     */
    public PreviousViewedCable getPreviousViewedCables() {
        return this.previousViewedCables;
    }
}
