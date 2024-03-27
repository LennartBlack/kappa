package kappa.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import kappa.model.Cable;
import kappa.utils.AttributePair;

public class CableInfoPane extends Region{

    private HBox hBoxCableInfo;
   

    public CableInfoPane(Cable cable) {
        String[] attributes = {"ID", "Start", "End", "Resistance", "Reactance", "Ampacity", "Electricity", "Length", "Year of Construction", "Cross Section"};
        String[] units = {" ", "km", "km", "Ohm", "Ohm", "A", "kV", "km", " ", "mm²"};

        this.hBoxCableInfo = new HBox();
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
        this.hBoxCableInfo.getChildren().addAll(attributesCol, valuesCol, unitsCol);
    }
    public HBox getVBoxCableInfo() {
        return this.hBoxCableInfo;
    }
}
