package kappa.view;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import kappa.model.AttributPair;
import kappa.model.Cable;

public class CableInfoPane extends VBox {
/*
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
    */

    public CableInfoPane(Cable cable){
        TableView<AttributPair> tableView = new TableView<>();
        TableColumn<AttributPair, String> attributeColumn = new TableColumn<>("Attribut");
        attributeColumn.setCellValueFactory(new PropertyValueFactory<>("attribute"));
        attributeColumn.setStyle("-fx-alignment: CENTER_LEFT;");

        TableColumn<AttributPair, String> valueColumn = new TableColumn<>("Wert");
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        valueColumn.setStyle("-fx-alignment: CENTER_RIGHT;");

        TableColumn<AttributPair, String> unitColumn = new TableColumn<>("Einheit");
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
        unitColumn.setStyle("-fx-alignment: CENTER_LEFT;");

        tableView.getColumns().add(attributeColumn);
        tableView.getColumns().add(valueColumn);
        tableView.getColumns().add(unitColumn);

        tableView.getItems().addAll(
            new AttributPair("ID", cable.getId(), ""),
            new AttributPair("Start", cable.getStart(), ""),
            new AttributPair("End", cable.getEnd(), ""),
            new AttributPair("Widerstand", cable.getResistance().toString(), "Ohm"),
            new AttributPair("Reactance", cable.getReactance().toString(), "Ohm"),
            new AttributPair("Nennstrom", cable.getAmpacity().toString(), "A"),
            new AttributPair("Strom", cable.getElectricity().toString(), "kV"),
            new AttributPair("Länge", cable.getLength().toString(), "km"),
            new AttributPair("Baujahr", Integer.toString(cable.getYearOfConstruction()), ""),
            new AttributPair("Durchmesser", Integer.toString(cable.getCrossSection()), "mm²"));
        tableView.setFixedCellSize(24);
        Double verticalMutliplier = 11.09;
        Double horizontalMultiplier = 9.29;
        tableView.setPrefHeight(tableView.getFixedCellSize() * verticalMutliplier);
        tableView.setPrefWidth(tableView.getFixedCellSize() * horizontalMultiplier);
        //tableView.prefHeightProperty().bind(tableView.fixedCellSizeProperty().multiply(verticalMutliplier));
        //tableView.prefWidthProperty().bind(tableView.fixedCellSizeProperty().multiply(horizontalMultiplier));
        tableView.setMaxWidth(tableView.getFixedCellSize() * horizontalMultiplier);
        this.getChildren().add(tableView);
    }
}
