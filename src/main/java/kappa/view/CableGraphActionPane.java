package kappa.view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import kappa.model.Cable;
import kappa.model.Watchlist;



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
    private HBox periodPane;
    private HBox resetGraphButtonPaneBox;
    private HBox watchlistButtonPane;
    private Watchlist watchlist;
    

    // Constructor
    public CableGraphActionPane(Cable cable, Watchlist watchlist) {
        this.watchlist = watchlist;
        this.periodPane = new HBox();
        periodPane.setSpacing(4);

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

        periodPane.getChildren().addAll(fiveDaysButton, tenDaysButton, oneMonthButton, threeMonthsButton, sixMonthsButton);

        this.resetGraphButtonPaneBox = new HBox();
        this.resetGraphButtonPaneBox.setSpacing(5);

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

        this.watchlistButtonPane = new HBox();
        this.watchlistButtonPane.setSpacing(4);


        this.addToWatchlistButton = new Button("Zur Merkliste hinzufügen");
        addToWatchlistButton.setStyle(Style.getStandardDesign());
        addToWatchlistButton.setPadding(Style.getGap());
        
        this.removeFromWatchlistButton = new Button("Von Merkliste entfernen");
        removeFromWatchlistButton.setStyle(Style.getStandardDesign());
        removeFromWatchlistButton.setPadding(Style.getGap());

        watchlistButtonPane.getChildren().addAll(this.addToWatchlistButton, this.removeFromWatchlistButton);
        
        if(this.watchlist.containsCable(cable)) {
            watchlistButtonPane.getChildren().remove(this.addToWatchlistButton);
        } else {
            watchlistButtonPane.getChildren().remove(this.removeFromWatchlistButton);
        }
        
        Region spacer = new Region();
        spacer.setMaxHeight(10.0);
        VBox.setVgrow(spacer, Priority.ALWAYS);

        this.getChildren().addAll(periodPane, spacer, resetGraphButtonPaneBox, watchlistButtonPane);

        style();
    }

    // Methods
    private void style() {
        CableGraphActionPane.setMargin(periodPane, Style.getGap());
        CableGraphActionPane.setMargin(resetGraphButtonPaneBox, Style.getGap());
        CableGraphActionPane.setMargin(tenDaysButton, Style.getGap());
        CableGraphActionPane.setMargin(previousPeriod, Style.getGap());
        CableGraphActionPane.setMargin(addToWatchlistButton, Style.getGap());
        CableGraphActionPane.setMargin(removeFromWatchlistButton, Style.getGap());
        CableGraphActionPane.setMargin(watchlistButtonPane, Style.getGap());
    }
    public void updateWatchlistButtons(Cable cable) {
        if(this.watchlist.containsCable(cable)) {
            watchlistButtonPane.getChildren().add(this.removeFromWatchlistButton);
            watchlistButtonPane.getChildren().remove(this.addToWatchlistButton);
        } else {
            watchlistButtonPane.getChildren().remove(this.removeFromWatchlistButton);
            watchlistButtonPane.getChildren().add(this.addToWatchlistButton);
        }
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
    public Button getFiveDaysButton(){
        return this.fiveDaysButton;
    }
    public Button getTenDaysButton(){
        return this.tenDaysButton;
    }
    public Button getOneMonthButton(){
        return this.oneMonthButton;
    }
    public Button getThreeMonthButton(){
        return this.threeMonthsButton;
    }
    public Button getSixMonthButton(){
        return this.sixMonthsButton;
    }
}
