package kappa.view;

import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import kappa.model.Watchlist;

public class WatchlistPane extends Region{
    private VBox vBoxWatchlistLayout;
    public WatchlistPane(){
        
    }
    public VBox getvBoxWatchlistLayout(){
        return this.vBoxWatchlistLayout;
    }
}
