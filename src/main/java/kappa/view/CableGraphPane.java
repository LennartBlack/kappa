package kappa.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import javafx.collections.FXCollections;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import kappa.model.Cable;
import kappa.model.MySqlManager;

public class CableGraphPane extends VBox {

    // Attributes
    private LocalDateTime currentStartDate;
    private LocalDateTime currentEndDate;
    private Double Apamcity;

    // Constructor
    public CableGraphPane(Cable cable) {
        this.Apamcity = cable.getAmpacity();
        try {
            // Get Data from Database
            MySqlManager.getConnection();
            String query = MySqlManager.buildQueryLastTenDays(cable.getId());
            ResultSet resultSet = MySqlManager.executeQuery(query);

            // Map Data with TreeMap so it is sorted by date
            Map<LocalDateTime, Double> data = new TreeMap<>();
            
            while(resultSet.next()){
                Timestamp timestamp = resultSet.getTimestamp("date");
                LocalDateTime dateTime = timestamp.toLocalDateTime();
                double ampere = resultSet.getDouble("ampere");
                data.put(dateTime, ampere);
            }

            // Create Chart
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(cable.getId());

            // Create Axis
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            yAxis.setLowerBound(0.0);
            yAxis.setUpperBound(cable.getAmpacity() * 1.2);
            xAxis.setLabel("Zeit");
            yAxis.setLabel("Amperewerte");

            // Create LineChart
            LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
            lineChart.setTitle(cable.getId());

            // Add Data to Chart
            for (Map.Entry<LocalDateTime, Double> entry : data.entrySet()) {
                series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
            }
       
            lineChart.getData().add(series);
            lineChart.setCreateSymbols(false);

            // Add Chart to Pane
            this.getChildren().add(lineChart);
            addButtons();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    // Methods
    private void addButtons() {
        HBox lastMonthsButtonPane = new HBox();
        lastMonthsButtonPane.setSpacing(4);

        Button lastThreeMonths = new Button("Letzte 3 Monate");
        lastThreeMonths.setStyle(Style.getStandardDesign());
        lastThreeMonths.setPadding(Style.getGap());

        Button lastSixMonths = new Button("Letzte 6 Monate");
        lastSixMonths.setStyle(Style.getStandardDesign());
        lastSixMonths.setPadding(Style.getGap());

        Button lastNineMonths = new Button("Letzte 9 Monate");
        lastNineMonths.setStyle(Style.getStandardDesign());
        lastNineMonths.setPadding(Style.getGap());

        Button lastTwelveMonths = new Button("Letzte 12 Monate");
        lastTwelveMonths.setStyle(Style.getStandardDesign());
        lastTwelveMonths.setPadding(Style.getGap());

        lastMonthsButtonPane.getChildren().addAll(lastThreeMonths, lastSixMonths, lastNineMonths, lastTwelveMonths);

        HBox resetGraphButtonPaneBox = new HBox();
        resetGraphButtonPaneBox.setSpacing(5);

        Button fullRecordTime = new Button("Gesamte Aufzeichnungszeit");
        fullRecordTime.setStyle(Style.getStandardDesign());
        fullRecordTime.setPadding(Style.getGap());

        Button previousThreeMonths = new Button("Vorherige 3 Monate");
        previousThreeMonths.setStyle(Style.getStandardDesign());
        previousThreeMonths.setPadding(Style.getGap());
        
        Button nextThreeMonths = new Button("Nächste 3 Monate");
        nextThreeMonths.setStyle(Style.getStandardDesign());
        nextThreeMonths.setPadding(Style.getGap());

        resetGraphButtonPaneBox.getChildren().addAll(previousThreeMonths, fullRecordTime, nextThreeMonths);
        
        this.setMargin(lastMonthsButtonPane, Style.getGap());
        this.setMargin(lastThreeMonths, Style.getGap());
        this.setMargin(resetGraphButtonPaneBox, Style.getGap());
        this.setMargin(previousThreeMonths, Style.getGap());

        this.getChildren().addAll(lastMonthsButtonPane, resetGraphButtonPaneBox);
    }
    
}
