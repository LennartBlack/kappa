package kappa.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private String period = "3 Months";

    // Constructor
    public CableGraphPane(Cable cable) {
        this.cable = cable;
        try {
            // Get Data from Database
            MySqlManager.getConnection();
            String startOfRecordsQuery = MySqlManager.buildQueryStartOfRecords(cable.getId());
            ResultSet resultSetStart = MySqlManager.executeQuery(startOfRecordsQuery);
            if(resultSetStart.next()){
                this.startOfRecords = resultSetStart.getTimestamp("date").toLocalDateTime();
            }

            String endOfRecordsQuery = MySqlManager.buildQueryEndOfRecords(cable.getId());
            ResultSet resultSetEnd = MySqlManager.executeQuery(endOfRecordsQuery);
            if(resultSetEnd.next()){
                this.endOfRecords = resultSetEnd.getTimestamp("date").toLocalDateTime();
            }
            
            String query = MySqlManager.buildQueryLastThreeMonths(cable.getId());
            ResultSet resultSet = MySqlManager.executeQuery(query);


            mapData(resultSet, cable);

            createGraph(cable);

            lineChart.getData().add(workloudSeries);
            this.lineChart.setCreateSymbols(false);
            this.getChildren().add(this.lineChart);
    } catch (SQLException e) {
        System.out.println("Error in cableGraphPane Constructor");
        e.printStackTrace();
    }
    }

    // Methods
    private void createGraph(Cable cable){
        this.workloudSeries = new XYChart.Series<>();
        this.workloudSeries.setName("prozentuale Auslastung");
        
        // Create Chart Axis
        this.xAxis = new CategoryAxis();
        this.yAxis = new NumberAxis();
        //yAxis.setLowerBound(0.0);
        //yAxis.setUpperBound(cable.getAmpacity() * 1.2);
        //yAxis.setUpperBound(10.0);
        this.xAxis.setLabel("Zeit");
        this.yAxis.setLabel("Prozentuale Auslastung");

        // Create LineChart
        this.lineChart = new LineChart<>(xAxis, yAxis);
        this.lineChart.setTitle(this.cable.getId());
        
        // Add Workload Data to Chart
        for (Map.Entry<LocalDateTime, Double> entry : this.workloudData.entrySet()) {
            this.workloudSeries.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
        }
    }
    
    private void mapData(ResultSet resultSet, Cable cable) {
        try{
            this.workloudData = new TreeMap<>();
            double cableAmpacity = cable.getAmpacity();
            double tempAmpere = Double.MIN_VALUE;
            while(resultSet.next()){
                if(resultSet.isFirst()){
                    this.currentStartDate = resultSet.getTimestamp("date").toLocalDateTime();              }
                double ampere = resultSet.getDouble("ampere");
                if(tempAmpere != ampere){
                    LocalDateTime dateTime = resultSet.getTimestamp("date").toLocalDateTime();
                    double workloud = resultSet.getDouble("ampere") / cableAmpacity * 100.0;
                    if(workloud < 0.0){
                        workloud = workloud * -1;
                    }
                    this.workloudData.put(dateTime, workloud);
                }
                tempAmpere = resultSet.getDouble("ampere");
                // Ensure a second entry is added so that not only the first entry is added if the values are all the same
                if(resultSet.isLast()){
                    LocalDateTime dateTime = resultSet.getTimestamp("date").toLocalDateTime();
                    this.currentEndDate = dateTime;
                    double workloud = resultSet.getDouble("ampere") / cableAmpacity * 100.0;
                    if(workloud < 0.0){
                        workloud = workloud * -1;
                    }
                    this.workloudData.put(dateTime, workloud);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void showPreviousPeriod(){
        System.out.println("start of Records:" + this.startOfRecords + " end of records: " + this.endOfRecords);
        if(currentStartDate.minusMonths(3).isBefore(startOfRecords)){
            System.out.println("überragt max range");
            this.currentStartDate = startOfRecords;
            this.currentEndDate = currentStartDate.plusMonths(3);
        } else {
            System.out.println("üist in range");
            this.currentStartDate = currentStartDate.minusMonths(3);
            this.currentEndDate = currentEndDate.minusMonths(3);
        }
        System.out.println("Start:" + currentStartDate + " End:" + currentEndDate);
        showGraphBetweenDates(currentStartDate, currentEndDate);
    }
    public void showPrevPeriod(){
        switch(period){
            case "5 Days":
                if(currentStartDate.minusDays(5).isBefore(startOfRecords)){
                    this.currentStartDate = startOfRecords;
                    this.currentEndDate = currentStartDate.plusDays(5);
                } else {
                    this.currentStartDate = currentStartDate.minusDays(5);
                    this.currentEndDate = currentEndDate.minusDays(5);
                }
                break;
            case "10 Days":
            if(currentStartDate.minusDays(10).isBefore(startOfRecords)){
                this.currentStartDate = startOfRecords;
                this.currentEndDate = currentStartDate.plusDays(10);
            } else {
                this.currentStartDate = currentStartDate.minusDays(10);
                this.currentEndDate = currentEndDate.minusDays(10);
            }
            break;
            case "1 Month":
                if(currentStartDate.minusMonths(1).isBefore(startOfRecords)){
                    System.out.println("überragt max range");
                    this.currentStartDate = startOfRecords;
                    this.currentEndDate = currentStartDate.plusMonths(1);
                } else {
                    System.out.println("üist in range");
                    this.currentStartDate = currentStartDate.minusMonths(1);
                    this.currentEndDate = currentEndDate.minusMonths(1);
                }
                break;
            case "3 Months":
            if(currentStartDate.minusMonths(3).isBefore(startOfRecords)){
                System.out.println("überragt max range");
                this.currentStartDate = startOfRecords;
                this.currentEndDate = currentStartDate.plusMonths(3);
            } else {
                System.out.println("üist in range");
                this.currentStartDate = currentStartDate.minusMonths(3);
                this.currentEndDate = currentEndDate.minusMonths(3);
            }
            break;
            case "6 Months":
            if(currentStartDate.minusMonths(6).isBefore(startOfRecords)){
                System.out.println("überragt max range");
                this.currentStartDate = startOfRecords;
                this.currentEndDate = currentStartDate.plusMonths(6);
            } else {
                System.out.println("üist in range");
                this.currentStartDate = currentStartDate.minusMonths(6);
                this.currentEndDate = currentEndDate.minusMonths(6);
            }
            break;
        }
        showGraphBetweenDates(currentStartDate, currentEndDate);

    }
    private void showGraphBetweenDates(LocalDateTime startDate, LocalDateTime endDate){
        this.lineChart.getData().clear();
        String query = MySqlManager.buildQueryBetweenDates(cable.getId(), startDate.toString(), endDate.toString());
        ResultSet resultSet = MySqlManager.executeQuery(query);
        mapData(resultSet, cable);
        createGraph(cable);
        lineChart.getData().add(workloudSeries);
        this.lineChart.setCreateSymbols(false);
        this.getChildren().remove(0);
        this.getChildren().add(this.lineChart);
    }
    public void showNextPeriod(){
        System.out.println("start of Records:" + this.startOfRecords + " end of records: " + this.endOfRecords);
    
        if(currentEndDate.plusMonths(3).isAfter(endOfRecords)){
            System.out.println("überragt max range");
            this.currentEndDate = endOfRecords;
            this.currentStartDate = currentEndDate.minusMonths(3);
        } else {
            System.out.println("üist in range");
            this.currentEndDate = currentEndDate.plusMonths(3);
            this.currentStartDate = currentStartDate.plusMonths(3);
        }
        System.out.println("Start:" + currentStartDate + " End:" + currentEndDate);
        showGraphBetweenDates(currentStartDate, currentEndDate);
    }
    public void showMaxPeriod(){
        this.lineChart.getData().clear();
        String query = MySqlManager.buildQueryFullRecordTime(this.cable.getId());
        ResultSet resultSet = MySqlManager.executeQuery(query);
        mapData(resultSet, this.cable);
        createGraph(cable);
        lineChart.getData().add(workloudSeries);
        this.lineChart.setCreateSymbols(false);
        this.getChildren().remove(0);
        this.getChildren().add(this.lineChart);
    }
    
    
    // Getter & Setter
    public String getPeriod() {
        return period;
    }
    public void setPeriod(String period) {
        this.period = period;
    }
}
