package kappa;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class test extends Application {

    @Override
    public void start(Stage stage) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Example Line Chart");
        xAxis.setLabel("Date");
        yAxis.setLabel("Value");

        // Beispiel-Daten hinzufügen
        lineChart.getData().add(createSeries());

        Scene scene = new Scene(lineChart, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    private XYChart.Series<Number, Number> createSeries() {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Data Series");

        // Beispiel-Daten hinzufügen
        series.getData().add(new XYChart.Data<>(toMilliseconds(LocalDateTime.of(2023, 1, 1, 0, 0)), 10));
        series.getData().add(new XYChart.Data<>(toMilliseconds(LocalDateTime.of(2023, 1, 2, 0, 0)), 20));
        series.getData().add(new XYChart.Data<>(toMilliseconds(LocalDateTime.of(2023, 1, 3, 0, 0)), 30));
        series.getData().add(new XYChart.Data<>(toMilliseconds(LocalDateTime.of(2023, 1, 4, 0, 0)), 25));

        return series;
    }

    private long toMilliseconds(LocalDateTime dateTime) {
        return dateTime.toEpochSecond(ZoneOffset.UTC) * 1000;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
