package kappa.view;

import javafx.scene.layout.VBox;
import kappa.model.Cable;
import kappa.model.TopWorkloud;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

public class TopWorkloadCablePane extends ScrollPane {

    // Attributes
    private TopWorkloud topWorkloud;
    private VBox topWorkloadVBox;
    private Label titleLabel;
    private KappaStage stage;

    // Constructor
    public TopWorkloadCablePane(TopWorkloud topWorkloud, KappaStage primaryStage) {
        this.stage = primaryStage;
        this.topWorkloud = topWorkloud;
        this.topWorkloadVBox = new VBox();
        this.setContent(this.topWorkloadVBox);

        this.titleLabel = new Label("Top ausgelastete Kabel");
        this.titleLabel.setPadding(Style.getGap());
        if (this.stage.isPreviousViewedCablesPaneVisible()) {
            titleLabel.setPrefWidth(928);
        } else {
            titleLabel.setPrefWidth(1000);
        }

        titleLabel.setStyle(Style.getStandardDesign());
        titleLabel.setAlignment(Pos.CENTER);
        this.topWorkloadVBox.getChildren().add(titleLabel);

        for (Map.Entry<Cable, Double> entry : this.topWorkloud.entrySet()) {
            TopWorkloadEntryPane topWorkloadEntryPane = new TopWorkloadEntryPane(entry.getKey(), entry.getValue());
            this.topWorkloadVBox.getChildren().add(topWorkloadEntryPane);
        }
    }

    // Getter
    public VBox getTopWorkloadVBox() {
        return topWorkloadVBox;
    }
}
