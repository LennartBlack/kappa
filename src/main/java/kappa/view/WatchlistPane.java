package kappa.view;

import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import kappa.model.CableCoreDataDB;
import kappa.model.Watchlist;
import kappa.model.WatchlistElement;

public class WatchlistPane extends ScrollPane {

    // Attributes
    private KappaStage stage;
    private Watchlist watchlist;
    private VBox watchlistVBox;
    private CableCoreDataDB cableCoreDataDB;
    private Label titleLabel;

    // Constructor
    public WatchlistPane(CableCoreDataDB cableCoreDataDB, KappaStage primaryStage) {
        this.stage = primaryStage;
        this.cableCoreDataDB = cableCoreDataDB;
        this.watchlist = Watchlist.deserializeHashMap(this.cableCoreDataDB);
        this.watchlistVBox = new VBox();
        this.setContent(watchlistVBox);
        constructWatchListPane();
    }

    public void removeWatchlistEntryPane(WatchlistEntryPane watchlistEntryPane) {
        watchlistVBox.getChildren().remove(watchlistEntryPane);
    }

    public void removeWatchlistEntryPane(String cableId) {
        String keyToRemove = null;
        for (Map.Entry<String, WatchlistElement> entry : watchlist.entrySet()) {
            if(entry.getValue().getCable().getId().equals(cableId)) {
                keyToRemove = entry.getKey();
            }
        }
        if(keyToRemove != null){
            watchlist.remove(keyToRemove);
        }
    }
    
    public void constructWatchListPane() {
        this.watchlist = Watchlist.deserializeHashMap(this.cableCoreDataDB);
        watchlistVBox.getChildren().clear();
        titleLabel = new Label("Merkliste");
        Double previousViewedCablePaneWidth = 0.0;
        if(this.stage.getPreviousViewedCablesPane() != null){
            previousViewedCablePaneWidth = this.stage.getPreviousViewedCablesPane().getWidth();
        }
        if(this.stage.isPreviousViewedCablesPaneVisible()) {
            titleLabel.setPrefWidth(998); 
        }
        else{
            titleLabel.setPrefWidth(this.stage.getWidth() - previousViewedCablePaneWidth - 19);
        }
        
        //titleLabel.setPrefWidth(Double.MAX_VALUE);
        titleLabel.setStyle(Style.getStandardDesign());
        titleLabel.setAlignment(Pos.CENTER);
        watchlistVBox.getChildren().add(titleLabel);
        
        for (Map.Entry<String, WatchlistElement> entry : watchlist.entrySet()) {
            WatchlistEntryPane watchlistEntryPane = new WatchlistEntryPane(entry.getValue());
            watchlistVBox.getChildren().add(watchlistEntryPane);
        }
    }

    // Getter
    public VBox getWatchlistVBox() {
        return this.watchlistVBox;
    }
}
