package kappa.view;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import kappa.model.TableViewAttributeTriple;
import kappa.model.Cable;

public class CableInfoPane extends VBox {
    private TableView<TableViewAttributeTriple> tableView;
    private TableColumn<TableViewAttributeTriple, String> attributeColumn;
    private TableColumn<TableViewAttributeTriple, String> valueColumn;
    private TableColumn<TableViewAttributeTriple, String> unitColumn;

    public CableInfoPane(Cable cable) {
        this.tableView = new TableView<>();

        this.attributeColumn = new TableColumn<>("Attribut");
        attributeColumn.setCellValueFactory(new PropertyValueFactory<>("attribute"));
        attributeColumn.setStyle("-fx-alignment: CENTER_LEFT;");

        this.valueColumn = new TableColumn<>("Wert");
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        valueColumn.setStyle("-fx-alignment: CENTER_RIGHT;");

        this.unitColumn = new TableColumn<>("Einheit");
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
        unitColumn.setStyle("-fx-alignment: CENTER_LEFT;");

        tableView.getColumns().add(attributeColumn);
        tableView.getColumns().add(valueColumn);
        tableView.getColumns().add(unitColumn);

        tableView.getItems().addAll(
                new TableViewAttributeTriple("ID", cable.getId(), ""),
                new TableViewAttributeTriple("Start", cable.getStart(), ""),
                new TableViewAttributeTriple("End", cable.getEnd(), ""),
                new TableViewAttributeTriple("Widerstand", cable.getResistance().toString(), "Ohm"),
                new TableViewAttributeTriple("Reaktanz", cable.getReactance().toString(), "Ohm"),
                new TableViewAttributeTriple("Nennstrom", cable.getAmpacity().toString(), "A"),
                new TableViewAttributeTriple("Länge", cable.getLength().toString(), "m"),
                new TableViewAttributeTriple("Baujahr", Integer.toString(cable.getYearOfConstruction()), ""),
                new TableViewAttributeTriple("Durchmesser", Integer.toString(cable.getCrossSection()), "mm²"));
        tableView.setFixedCellSize(24);
        Double verticalMutliplier = 11.09;
        Double horizontalMultiplier = 9.29;
        tableView.setPrefHeight(tableView.getFixedCellSize() * verticalMutliplier);
        tableView.setPrefWidth(tableView.getFixedCellSize() * horizontalMultiplier);
        // tableView.prefHeightProperty().bind(tableView.fixedCellSizeProperty().multiply(verticalMutliplier));
        // tableView.prefWidthProperty().bind(tableView.fixedCellSizeProperty().multiply(horizontalMultiplier));
        tableView.setMaxWidth(tableView.getFixedCellSize() * horizontalMultiplier);
        this.getChildren().add(tableView);
    }
}
