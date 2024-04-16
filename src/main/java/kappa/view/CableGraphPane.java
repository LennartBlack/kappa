package kappa.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import kappa.model.Cable;
import kappa.model.MySqlManager;

public class CableGraphPane extends VBox {

    public CableGraphPane(Cable cable) {
        try {
            // Get Data from Database
            MySqlManager.getConnection();
            String query = MySqlManager.buildQuery(cable.getId());
            ResultSet resultSet = MySqlManager.executeQuery(query);

            // Map Data with TreeMap so it is sorted by date
            Map<LocalDateTime, Double> data = new TreeMap<>();
            while(resultSet.next()){
                Timestamp timestamp = resultSet.getTimestamp("date");
                LocalDateTime dateTime = timestamp.toLocalDateTime();
                double ampere = resultSet.getDouble("ampere");
                data.put(dateTime, ampere);}

            // Create Chart
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(cable.getId());
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            yAxis.setLowerBound(0.0);
            yAxis.setUpperBound(100.0);
            LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
            lineChart.setTitle(cable.getId());
            xAxis.setLabel("Zeit");
            yAxis.setLabel("Amperewerte");

            // Add Data to Chart
            for (Map.Entry<LocalDateTime, Double> entry : data.entrySet()) {
                series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
            }
       
            lineChart.getData().add(series);
            lineChart.setCreateSymbols(false);

            // Add Chart to Pane
            this.getChildren().add(lineChart);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
}
