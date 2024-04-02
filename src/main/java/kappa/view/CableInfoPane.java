package kappa.view;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import kappa.model.Cable;

public class CableInfoPane extends HBox {

    public CableInfoPane(Cable cable) {
        String[] attributes = { "ID", "Start", "End", "Widerstand", "Reactance", "Nennstrom", "Strom", "Länge",
                "Baujahr", "Durchmesser" };
        String[] units = { " ", "km", "km", "Ohm", "Ohm", "A", "kV", "km", " ", "mm²" };

        VBox attributesCol = new VBox();
        VBox valuesCol = new VBox();
        VBox unitsCol = new VBox();
        unitsCol.setAlignment(Pos.TOP_CENTER);
        unitsCol.setStyle(" -fx-alignment: baseline-right");

        for (String attribute : attributes) {
            Label attributeLabel = new Label(attribute);
            Label valueLabel = new Label(cable.getCableValue(attribute));
            Label unitLabel = new Label(units[0]);
            unitLabel.setAlignment(Pos.BASELINE_RIGHT);
            unitLabel.setStyle(" -fx-alignment: baseline-right");
            attributesCol.getChildren().addAll(attributeLabel);
            valuesCol.getChildren().addAll(valueLabel);
            unitsCol.getChildren().addAll(unitLabel);
        }
        this.getChildren().addAll(attributesCol, valuesCol, unitsCol);
    }
}
