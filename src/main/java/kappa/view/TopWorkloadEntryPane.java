package kappa.view;

import javafx.scene.layout.HBox;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import kappa.model.Cable;
import kappa.model.MySqlManager;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class TopWorkloadEntryPane extends HBox {

    // Attributes
    private Button cableIdButton;
    private Label workloadLabel;
    private Cable cable;
    private LineChart<String, Number> lineChart;
    private Map<LocalDateTime, Double> workloudData;
    private XYChart.Series<String, Number> workloudSeries;

    // Constructor
    public TopWorkloadEntryPane(Cable cable, double workload) {
        this.cable = cable;

        this.cableIdButton = new Button(this.cable.getId());
        Integer roundedWorkload = (int) Math.round(workload);
        this.workloadLabel = new Label("Auslastung: " + roundedWorkload + "%");

        this.getChildren().addAll(this.cableIdButton, this.workloadLabel);

        addGraphPreview();
        setStyle();
    }

    // Methods
    private void setStyle() {
        this.setPadding(Style.getGap());
        WatchlistEntryPane.setMargin(cableIdButton, Style.getGap());
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPrefHeight(120);

        this.cableIdButton.setStyle(Style.getStandardDesign());
        this.cableIdButton.setPadding(Style.getGap());
        workloudSeries.getNode().setStyle("-fx-stroke: " + Style.getEweBlue() + ";");

    }

    private void addGraphPreview() {
        try {
            MySqlManager.getConnection();
            String query = MySqlManager.buildQuery(this.cable.getId());
            ResultSet rs = MySqlManager.executeQuery(query);
            mapData(rs);
            createGraph();
            this.lineChart.getData().add(this.workloudSeries);
            this.lineChart.setCreateSymbols(false);
            this.getChildren().add(this.lineChart);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mapData(ResultSet resultSet) {
        try {
            this.workloudData = new TreeMap<>();
            int count = 1; // Zählvariable für den Index des Ergebnis-Sets
            while (resultSet.next()) {
                if (resultSet.isFirst() || resultSet.isLast() || count % 24 == 0) {
                    addResultSetElementToMap(resultSet);
                }
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addResultSetElementToMap(ResultSet resultSet) {
        try {
            double cableAmpacity = this.cable.getAmpacity();
            LocalDateTime dateTime = resultSet.getTimestamp("date").toLocalDateTime();
            double workloud = resultSet.getDouble("ampere") / cableAmpacity * 100.0;
            if (workloud < 0.0) {
                workloud = workloud * -1;
            }
            this.workloudData.put(dateTime, workloud);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createGraph() {
        this.workloudSeries = new XYChart.Series<>();
        this.workloudSeries.setName("prozentuale Auslastung");

        // Create Chart Axis
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        xAxis.setTickMarkVisible(false);
        xAxis.setTickLabelsVisible(false);
        yAxis.setTickMarkVisible(false);
        yAxis.setTickLabelsVisible(false);

        // Create LineChart
        this.lineChart = new LineChart<>(xAxis, yAxis);
        this.lineChart.setLegendVisible(false);

        // Add Workload Data to Chart
        for (Map.Entry<LocalDateTime, Double> entry : this.workloudData.entrySet()) {
            XYChart.Data<String, Number> data = new XYChart.Data<>(entry.getKey().toString(), entry.getValue());
            this.workloudSeries.getData().add(data);
        }
    }

    // Getter
    public Button getCableIdButton() {
        return cableIdButton;
    }

    public Cable getCable() {
        return this.cable;
    }
}
