package kappa;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class test extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Erstellen der TableView
        TableView<Person> tableView = new TableView<>();

        // Erstellen der Spalten
        TableColumn<Person, String> attributeColumn = new TableColumn<>("Attribut");
        attributeColumn.setCellValueFactory(new PropertyValueFactory<>("attribute"));
        attributeColumn.setStyle("-fx-alignment: CENTER_LEFT;");

        TableColumn<Person, String> valueColumn = new TableColumn<>("Wert");
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        valueColumn.setStyle("-fx-alignment: CENTER_RIGHT;");

        // Hinzufügen der Spalten zur TableView
        tableView.getColumns().addAll(attributeColumn, valueColumn);

        // Hinzufügen von Daten zur TableView
        tableView.getItems().addAll(
                new Person("Name", "Lennart"),
                new Person("Alter", "23"),
                new Person("Ort", "Oldenburg")
        );

        // Erstellen der Szene und Hinzufügen der TableView zur Szene
        VBox root = new VBox(tableView);
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TableView Beispiel");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Modellklasse für Personen
    public static class Person {
        private String attribute;
        private String value;

        public Person(String attribute, String value) {
            this.attribute = attribute;
            this.value = value;
        }

        public String getAttribute() {
            return attribute;
        }

        public String getValue() {
            return value;
        }
    }
}
