package kappa.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    // Attributes
    private Cable cable;
    private LocalDateTime currentStartDate;
    private LocalDateTime currentEndDate;
    private LocalDateTime startOfRecords;
    private LocalDateTime endOfRecords;
    private Map<LocalDateTime, Double> workloudData;
    private LineChart<String, Number> lineChart;
    private XYChart.Series<String, Number> workloudSeries;
    private String period = "3 Monate";

    // Constructor
    public CableGraphPane(Cable cable) {
        this.cable = cable;
        try {
            // Get Data from Database
            MySqlManager.getConnection();

            // Get start and end of records
            String startOfRecordsQuery = MySqlManager.buildQueryStartOfRecords(cable.getId());
            ResultSet resultSetStart = MySqlManager.executeQuery(startOfRecordsQuery);
            if (resultSetStart.next()) {
                this.startOfRecords = resultSetStart.getTimestamp("date").toLocalDateTime();
            }

            String endOfRecordsQuery = MySqlManager.buildQueryEndOfRecords(cable.getId());
            ResultSet resultSetEnd = MySqlManager.executeQuery(endOfRecordsQuery);
            if (resultSetEnd.next()) {
                this.endOfRecords = resultSetEnd.getTimestamp("date").toLocalDateTime();
            }

            // Get Data for initial Graph
            String query = MySqlManager.buildQueryLastThreeMonths(cable.getId());
            ResultSet resultSet = MySqlManager.executeQuery(query);

            mapData(resultSet);

            createGraph();

            this.lineChart.getData().add(workloudSeries);
            this.lineChart.setCreateSymbols(false);
            this.getChildren().add(this.lineChart);
            workloudSeries.getNode().setStyle("-fx-stroke: " + Style.getEweBlue() + ";");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Methods
    private void createGraph() {
        this.workloudSeries = new XYChart.Series<>();
        this.workloudSeries.setName("prozentuale Auslastung");

        // Create Chart Axis
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String currentStartDateToVisualize = currentStartDate.format(outputFormatter);
        String currentEndDateToVisualize = currentEndDate.format(outputFormatter);
        xAxis.setLabel("Zeit mit der Ausschnittgröße: " + this.period + " vom " + currentStartDateToVisualize
                + " bis zum " + currentEndDateToVisualize);
        yAxis.setLabel("Prozentuale Auslastung in %");

        // Create LineChart
        this.lineChart = new LineChart<>(xAxis, yAxis);
        this.lineChart.setTitle(this.cable.getId());
        this.lineChart.setLegendVisible(false);

        // Add Workload Data to Chart
        for (Map.Entry<LocalDateTime, Double> entry : this.workloudData.entrySet()) {
            XYChart.Data<String, Number> data = new XYChart.Data<>(entry.getKey().toString(), entry.getValue());
            this.workloudSeries.getData().add(data);
        }
    }

    private void mapData(ResultSet resultSet) {
        try {
            this.workloudData = new TreeMap<>();
            double cableAmpacity = this.cable.getAmpacity();
            double tempAmpere = Double.MIN_VALUE;
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    this.currentStartDate = resultSet.getTimestamp("date").toLocalDateTime();
                }
                double ampere = resultSet.getDouble("ampere");
                if (tempAmpere != ampere) {
                    LocalDateTime dateTime = resultSet.getTimestamp("date").toLocalDateTime();
                    double workloud = resultSet.getDouble("ampere") / cableAmpacity * 100.0;
                    if (workloud < 0.0) {
                        workloud = workloud * -1;
                    }
                    this.workloudData.put(dateTime, workloud);
                }
                tempAmpere = resultSet.getDouble("ampere");
                // Ensure a second entry is added so that not only the first entry is added if
                // the values are all the same
                if (resultSet.isLast()) {
                    LocalDateTime dateTime = resultSet.getTimestamp("date").toLocalDateTime();
                    this.currentEndDate = dateTime;
                    double workloud = resultSet.getDouble("ampere") / cableAmpacity * 100.0;
                    if (workloud < 0.0) {
                        workloud = workloud * -1;
                    }
                    this.workloudData.put(dateTime, workloud);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showPrevPeriod() {
        switch (period) {
            case "5 Tage":
                if (currentStartDate.minusDays(5).isBefore(startOfRecords)) {
                    this.currentStartDate = startOfRecords;
                    this.currentEndDate = currentStartDate.plusDays(5);
                } else {
                    this.currentStartDate = currentStartDate.minusDays(5);
                    this.currentEndDate = currentStartDate.plusDays(5);
                }
                break;
            case "10 Tage":
                if (currentStartDate.minusDays(10).isBefore(startOfRecords)) {
                    this.currentStartDate = startOfRecords;
                    this.currentEndDate = currentStartDate.plusDays(10);
                } else {
                    this.currentStartDate = currentStartDate.minusDays(10);
                    this.currentEndDate = currentStartDate.plusDays(10);
                }
                break;
            case "1 Monat":
                if (currentStartDate.minusMonths(1).isBefore(startOfRecords)) {
                    this.currentStartDate = startOfRecords;
                    this.currentEndDate = currentStartDate.plusMonths(1);
                } else {
                    this.currentStartDate = currentStartDate.minusMonths(1);
                    this.currentEndDate = currentStartDate.plusMonths(1);
                }
                break;
            case "3 Monate":
                if (currentStartDate.minusMonths(3).isBefore(startOfRecords)) {
                    this.currentStartDate = startOfRecords;
                    this.currentEndDate = currentStartDate.plusMonths(3);
                } else {
                    this.currentStartDate = currentStartDate.minusMonths(3);
                    this.currentEndDate = currentStartDate.plusMonths(3);
                }
                break;
            case "6 Monate":
                if (currentStartDate.minusMonths(6).isBefore(startOfRecords)) {
                    this.currentStartDate = startOfRecords;
                    this.currentEndDate = currentStartDate.plusMonths(6);
                } else {
                    this.currentStartDate = currentStartDate.minusMonths(6);
                    this.currentEndDate = currentStartDate.plusMonths(6);
                }
                break;
        }
        showGraphBetweenDates(currentStartDate, currentEndDate);

    }

    public void showNxtPerioud() {
        switch (period) {
            case "5 days":
                if (currentEndDate.plusDays(5).isAfter(endOfRecords)) {
                    this.currentEndDate = endOfRecords;
                    this.currentStartDate = currentEndDate.minusDays(5);
                } else {
                    this.currentStartDate = currentStartDate.plusDays(5);
                    this.currentEndDate = currentStartDate.plusDays(5);
                }
                break;
            case "10 days":
                if (currentEndDate.plusDays(10).isAfter(endOfRecords)) {
                    this.currentEndDate = endOfRecords;
                    this.currentStartDate = currentEndDate.minusDays(10);
                } else {
                    this.currentStartDate = currentStartDate.plusDays(10);
                    this.currentEndDate = currentStartDate.plusDays(10);
                }
                break;
            case "1 month":
                if (currentEndDate.plusMonths(1).isAfter(endOfRecords)) {
                    this.currentEndDate = endOfRecords;
                    this.currentStartDate = currentEndDate.minusMonths(1);
                } else {
                    this.currentStartDate = currentStartDate.plusMonths(1);
                    this.currentEndDate = currentStartDate.plusMonths(1);
                }
                break;
            case "3 months":
                if (currentEndDate.plusMonths(3).isAfter(endOfRecords)) {
                    this.currentEndDate = endOfRecords;
                    this.currentStartDate = currentEndDate.minusMonths(3);
                } else {
                    this.currentStartDate = currentStartDate.plusMonths(3);
                    this.currentEndDate = currentStartDate.plusMonths(3);
                }
                break;
            case "6 months":
                if (currentEndDate.plusMonths(6).isAfter(endOfRecords)) {
                    this.currentEndDate = endOfRecords;
                    this.currentStartDate = currentEndDate.minusMonths(6);
                } else {
                    this.currentStartDate = currentStartDate.plusMonths(6);
                    this.currentEndDate = currentStartDate.plusMonths(6);
                }
                break;
        }
        showGraphBetweenDates(currentStartDate, currentEndDate);
    }

    private void showGraphBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        String query = MySqlManager.buildQueryBetweenDates(cable.getId(), startDate.toString(), endDate.toString());
        ResultSet resultSet = MySqlManager.executeQuery(query);
        updateGraph(resultSet);
        workloudSeries.getNode().setStyle("-fx-stroke: " + Style.getEweBlue() + ";");
    }

    public void showMaxPeriod() {
        this.lineChart.getData().clear();
        String query = MySqlManager.buildQueryFullRecordTime(this.cable.getId());
        ResultSet resultSet = MySqlManager.executeQuery(query);
        mapData(resultSet);
        createGraph();
        lineChart.getData().add(workloudSeries);
        this.lineChart.setCreateSymbols(false);
        this.getChildren().remove(0);
        this.getChildren().add(this.lineChart);
        workloudSeries.getNode().setStyle("-fx-stroke: " + Style.getEweBlue() + ";");
    }

    private void updateGraph(ResultSet resultSet) {

        this.lineChart.getData().clear();
        mapData(resultSet);
        createGraph();
        lineChart.getData().add(workloudSeries);
        this.lineChart.setCreateSymbols(false);
        this.getChildren().remove(0);
        this.getChildren().add(this.lineChart);
        workloudSeries.getNode().setStyle("-fx-stroke: " + Style.getEweBlue() + ";");

    }

    // Getter & Setter
    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
