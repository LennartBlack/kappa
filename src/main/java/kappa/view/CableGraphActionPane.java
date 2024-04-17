package kappa.view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import kappa.model.Cable;



public class CableGraphActionPane extends VBox {

    // Attributes
    private Button addToWatchlistButton;
    private Button removeFromWatchlistButton;
    private Button fullRecordTime;
    private Button previousPeriod;
    private Button nextPeriod;
    private Button fiveDaysButton;
    private Button tenDaysButton;
    private Button oneMonthButton;
    private Button threeMonthsButton;
    private Button sixMonthsButton;
    

    // Constructor
    public CableGraphActionPane(Cable cable) {
        addButtons();

        HBox watchlistButtonPane = new HBox();

        this.addToWatchlistButton = new Button("Zur Merkliste hinzufügen");
        addToWatchlistButton.setStyle(Style.getStandardDesign());
        
        this.removeFromWatchlistButton = new Button("Von Merkliste entfernen");
        removeFromWatchlistButton.setStyle(Style.getStandardDesign());
        
        watchlistButtonPane.getChildren().addAll(this.addToWatchlistButton, this.removeFromWatchlistButton);
        
        this.getChildren().add(watchlistButtonPane);
        style();
    }

    // Methods
    private void addButtons() {
        HBox lastMonthsButtonPane = new HBox();
        lastMonthsButtonPane.setSpacing(4);

        this.fiveDaysButton = new Button("5 Tage");
        this.fiveDaysButton.setStyle(Style.getStandardDesign());
        this.fiveDaysButton.setPadding(Style.getGap());

        this.tenDaysButton = new Button("10 Tage");
        this.tenDaysButton.setStyle(Style.getStandardDesign());
        this.tenDaysButton.setPadding(Style.getGap());

        this.oneMonthButton = new Button("1 Monat");
        this.oneMonthButton.setStyle(Style.getStandardDesign());
        this.oneMonthButton.setPadding(Style.getGap());

        this.threeMonthsButton = new Button("3 Monate");
        this.threeMonthsButton.setStyle(Style.getStandardDesign());
        this.threeMonthsButton.setPadding(Style.getGap());

        this.sixMonthsButton = new Button("6 Monate");
        this.sixMonthsButton.setStyle(Style.getStandardDesign());
        this.sixMonthsButton.setPadding(Style.getGap());

        lastMonthsButtonPane.getChildren().addAll(fiveDaysButton, tenDaysButton, oneMonthButton, threeMonthsButton, sixMonthsButton);

        HBox resetGraphButtonPaneBox = new HBox();
        resetGraphButtonPaneBox.setSpacing(5);

        this.fullRecordTime = new Button("Gesamte Aufzeichnungszeit");
        fullRecordTime.setStyle(Style.getStandardDesign());
        fullRecordTime.setPadding(Style.getGap());

        this.previousPeriod = new Button("Vorherige Periode");
        previousPeriod.setStyle(Style.getStandardDesign());
        previousPeriod.setPadding(Style.getGap());
        
        this.nextPeriod = new Button("Nächste Periode");
        nextPeriod.setStyle(Style.getStandardDesign());
        nextPeriod.setPadding(Style.getGap());

        resetGraphButtonPaneBox.getChildren().addAll(previousPeriod, fullRecordTime, nextPeriod);
        
        CableDetailPane.setMargin(lastMonthsButtonPane, Style.getGap());
        CableDetailPane.setMargin(lastThreeMonths, Style.getGap());
        CableDetailPane.setMargin(resetGraphButtonPaneBox, Style.getGap());
        CableDetailPane.setMargin(previousPeriod, Style.getGap());

        Region spacer = new Region();
        spacer.setMaxHeight(10.0);
        VBox.setVgrow(spacer, Priority.ALWAYS);
        this.getChildren().addAll(lastMonthsButtonPane, spacer, resetGraphButtonPaneBox);
    }
    private void style() {
        CableGraphActionPane.setMargin(addToWatchlistButton, Style.getGap());
        CableGraphActionPane.setMargin(removeFromWatchlistButton, Style.getGap());
        this.addToWatchlistButton.setPadding(Style.getGap());
        this.removeFromWatchlistButton.setPadding(Style.getGap());
    }

    // Getter
    public Button getAddToWatchlistButton() {
        return this.addToWatchlistButton;
    }
    public Button getRemoveFromWatchlistButton() {
        return this.removeFromWatchlistButton;
    }
    public Button getFullRecordTime() {
        return fullRecordTime;
    }
    public Button getPreviousPeriod() {
        return previousPeriod;
    }
    public Button getNextPeriod() {
        return nextPeriod;
    }
   
}
