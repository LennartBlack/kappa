package kappa.view;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SearchCablePane {
    private VBox vBoxSearchCableLayout;
    
    public SearchCablePane() {
        this.vBoxSearchCableLayout = new VBox();
        Button newB = new Button("Kabel 11");
        this.vBoxSearchCableLayout.getChildren().add(newB);
    }

    public VBox getvBoxSearchCableLayout() {
       return this.vBoxSearchCableLayout;
    }

}
