package kappa.view;

import java.util.Map;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import kappa.model.CableCoreDataDB;
import kappa.model.Watchlist;
import kappa.model.WatchlistElement;

public class WatchlistPane extends ScrollPane {

    // Attributes
    private Watchlist watchlist;
    private VBox watchlistVBox;

    // Constructor
    public WatchlistPane(CableCoreDataDB cableCoreDataDB) {
        this.watchlist = Watchlist.deserializeHashMap(cableCoreDataDB);
        this.watchlistVBox = new VBox();
        this.setContent(watchlistVBox);
        watchlistVBox.getChildren().add(new Label("Watchlist"));
        /*
         * int i = 0;
         * for (Map.Entry<String, WatchlistElement> entry : watchlist.entrySet()) {
         * HBox watchlistElementHBox = new HBox();
         * String cableId = entry.getKey();
         * WatchlistElement watchlistElement = entry.getValue();
         * Label cableIdLabel = new Label(cableId);
         * Label noteLabel = new Label(watchlistElement.getNote());
         * Button editNoteButton = new Button("Edit Note");
         * System.out.println(i + "Button in Pane: " + editNoteButton);
         * i++;
         * watchlistElementHBox.getChildren().addAll(cableIdLabel, editNoteButton,
         * noteLabel);
         * watchlistVBox.getChildren().add(watchlistElementHBox);
         * }
         */
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
