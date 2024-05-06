package kappa.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;


public class HelpPane extends Region{
    private Label anwenderdoku;
    private VBox vBoxHelpLayout;
    /**
     * Constructor for the HelpPane class
     */
    public HelpPane() {
        this.vBoxHelpLayout = new VBox();
        this.anwenderdoku = new Label(  "Anwenderdokumentation");
        vBoxHelpLayout.getChildren().add(anwenderdoku);
    }
    /**
     * Getter for the vBoxHelpLayout attribute
     * @return vBoxHelpLayout
     */
    public VBox getvBoxHelpLayout() {
        return vBoxHelpLayout;
    }
}
