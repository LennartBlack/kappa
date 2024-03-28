package kappa.view;

import javafx.scene.control.ScrollPane;
import kappa.model.Watchlist;

public class WatchlistPane extends ScrollPane {

    // Attributes
    private Watchlist watchlist;

    // Constructor
    public WatchlistPane() {
        this.watchlist = Watchlist.loadFromFile();
    }
}
