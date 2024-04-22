package kappa.view;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import kappa.model.Cable;
import kappa.model.MySqlManager;
import kappa.model.WatchlistElement;

public class WatchlistEntryPane extends HBox {

    // Attributes
    private WatchlistElement watchlistElement;
    private TextArea noteLabel;
    private Button cableIdButton;
    private Button addNoteButton;
    private Button editNoteButton;
    private Button deleteNoteButton;
    private Button saveNoteButton;
    private Button removeFromWatchlistButton;
    private TextField noteTextField = new TextField();
    private VBox editDeletevBox;
    private Cable cable;
    private LineChart<String, Number> lineChart;
    private Map<LocalDateTime, Double> workloudData;
    private XYChart.Series<String, Number> workloudSeries;

    // Constructor
    public WatchlistEntryPane(WatchlistElement watchlistElement) {
        super();
        this.noteLabel = new TextArea();
        this.watchlistElement = watchlistElement;
        this.cableIdButton = new Button(watchlistElement.getCable().getId());
        this.addNoteButton = new Button("Notiz hinzufügen");
        this.editNoteButton = new Button("Notiz bearbeiten");
        this.deleteNoteButton = new Button("Notiz löschen");
        this.saveNoteButton = new Button("Notiz speichern");
        this.removeFromWatchlistButton = new Button("Aus Merkliste entfernen");
        this.editDeletevBox = new VBox(editNoteButton, deleteNoteButton);
        this.cable = watchlistElement.getCable();
        createGraph();

        setStyle();

        if (isNoteEmpty()) {
            createPaneWithoutNote();
        } else {
            createPaneWithNote();
        }
        workloudSeries.getNode().setStyle("-fx-stroke: " + Style.getEweBlue() + ";");

    }

    // Methods
    public void createPaneWithNote() {
        this.getChildren().clear();
        this.getChildren().addAll(cableIdButton, removeFromWatchlistButton, lineChart);
        workloudSeries.getNode().setStyle("-fx-stroke: " + Style.getEweBlue() + ";");

        this.noteLabel.setText(watchlistElement.getNote());
        this.getChildren().addAll(editDeletevBox, noteLabel);
    }
    public void createPaneWithoutNote() {
        this.getChildren().clear();
        this.getChildren().addAll(cableIdButton, removeFromWatchlistButton, lineChart);
        workloudSeries.getNode().setStyle("-fx-stroke: " + Style.getEweBlue() + ";");

        this.getChildren().add(addNoteButton);
    }
    public void createEditNotePane() {
        this.getChildren().clear();
        this.getChildren().addAll(cableIdButton, removeFromWatchlistButton, lineChart);
        workloudSeries.getNode().setStyle("-fx-stroke: " + Style.getEweBlue() + ";");
  
        this.noteTextField.setText(watchlistElement.getNote());
        this.getChildren().addAll(saveNoteButton, noteTextField);
    }
    
    private void setStyle() {
        this.setPadding(Style.getGap());
        WatchlistEntryPane.setMargin(cableIdButton, Style.getGap());
        WatchlistEntryPane.setMargin(addNoteButton, Style.getGap());
        WatchlistEntryPane.setMargin(editNoteButton, Style.getGap());
        WatchlistEntryPane.setMargin(deleteNoteButton, Style.getGap());
        WatchlistEntryPane.setMargin(saveNoteButton, Style.getGap());
        WatchlistEntryPane.setMargin(removeFromWatchlistButton, Style.getGap());
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPrefHeight(120);
        this.setStyle("-fx-border-color: #5A5F5F;");

        VBox.setMargin(editDeletevBox, Style.getGap());
        editDeletevBox.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(editNoteButton, Style.getGap());
        VBox.setMargin(deleteNoteButton, Style.getGap());
        editDeletevBox.setPadding(Style.getGap());

        this.cableIdButton.setStyle(Style.getStandardDesign());
        this.cableIdButton.setPadding(Style.getGap());

        this.addNoteButton.setStyle(Style.getStandardDesign());
        this.addNoteButton.setPadding(Style.getGap());

        this.editNoteButton.setStyle(Style.getStandardDesign());
        this.editNoteButton.setPadding(Style.getGap());

        this.deleteNoteButton.setStyle(Style.getStandardDesign());
        this.deleteNoteButton.setPadding(Style.getGap());

        this.saveNoteButton.setStyle(Style.getStandardDesign());
        this.saveNoteButton.setPadding(Style.getGap());

        this.removeFromWatchlistButton.setStyle(Style.getStandardDesign());
        this.removeFromWatchlistButton.setPadding(Style.getGap());
    }
        
    private void createGraph() {
        try{
            MySqlManager.getConnection();
            String query = MySqlManager.buildQuery(this.cable.getId());
            ResultSet rs = MySqlManager.executeQuery(query);
            mapData(rs);
            createChart();
            this.lineChart.getData().add(this.workloudSeries);
            this.lineChart.setCreateSymbols(false);
            this.getChildren().add(this.lineChart);
        } catch (Exception e) {
            System.out.println("Fehler beim Erstellen des Graphen.");
        }
    }
    private void mapData(ResultSet resultSet) {
        try{
            this.workloudData = new TreeMap<>();
            int count = 1; // Zählvariable für den Index des Ergebnis-Sets
            while (resultSet.next()) {
                if (resultSet.isFirst() || resultSet.isLast() || count % 24 == 0) {
                    addDataForGraph(resultSet);
                }
                count++;
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Mappen der Daten.");
        }
    }
    private void createChart(){
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
    private void addDataForGraph(ResultSet resultSet){
        try{
            double cableAmpacity = this.cable.getAmpacity();
            LocalDateTime dateTime = resultSet.getTimestamp("date").toLocalDateTime();
            double workloud = resultSet.getDouble("ampere") / cableAmpacity * 100.0;
            if(workloud < 0.0){
                workloud = workloud * -1;
            }
            this.workloudData.put(dateTime, workloud);
        }
        catch (SQLException e) {
            System.out.println("Fehler beim Hinzufügen der Daten zum Graphen.");
            e.printStackTrace();
        }
    }
    // Getter
    private boolean isNoteEmpty() {
        return (watchlistElement.getNote().equals(""));
    }
    public TextArea getNoteLabel() {
        return noteLabel;
    }
    public Button getCableIdButton() {
        return cableIdButton;
    }
    public Button getAddNoteButton() {
        return addNoteButton;
    }
    public Button getEditNoteButton() {
        return editNoteButton;
    }
    public Button getDeleteNoteButton() {
        return deleteNoteButton;
    }
    public Button getSaveNoteButton() {
        return saveNoteButton;
    }
    public Button getRemoveFromWatchlistButton() {
        return removeFromWatchlistButton;
    }
    public TextField getNoteTextField() {
        return noteTextField;
    }
    public WatchlistElement getWatchlistElement() {
        return watchlistElement;
    }
}
