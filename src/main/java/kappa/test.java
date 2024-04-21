package kappa;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Test extends Application {

    @Override
    public void start(Stage stage) {
        // Definiere die Achsen
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        
        // Erstelle das LineChart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Beispiel LineChart");
        
        // Erstelle die Datenreihe
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Datenreihe 1");
        
        
        
        // Füge Daten zur Datenreihe hinzu
        series.getData().add(new XYChart.Data<>(1, 23));
        series.getData().add(new XYChart.Data<>(2, 14));
        series.getData().add(new XYChart.Data<>(3, 15));
        series.getData().add(new XYChart.Data<>(4, 24));
        series.getData().add(new XYChart.Data<>(5, 34));
        
        // Füge die Datenreihe zum LineChart hinzu
        lineChart.getData().add(series);
        // Setze die Farbe der Linie auf Blau
        series.getNode().setStyle("-fx-stroke: blue;");
        // Erstelle die Szene und füge das LineChart hinzu
        Scene scene = new Scene(lineChart, 800, 600);
        
        // Setze die Szene in die Bühne
        stage.setScene(scene);
        
        // Setze den Titel der Bühne
        stage.setTitle("LineChart Beispiel");
        
        // Zeige die Bühne an
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
