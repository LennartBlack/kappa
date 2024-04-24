package kappa.view;

import javafx.geometry.Insets;

public class Style {
    public static final String EWE_BLUE = "#005F96";

    public static final String EWE_GREEN = "#C8D200";

    private static final String EWE_GREY = "#5A5F5F";

    private static final String STANDARD_DESIGN = "-fx-background-color:" + EWE_BLUE
            + "; -fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: " + EWE_GREEN + ";";

    private static final String GRAPH_COLOR = "-fx-stroke: " + EWE_BLUE + ";";

    private static final Insets GAP = new Insets(5);

    public static String getEweBlue() {
        return EWE_BLUE;
    }

    public static String getEweGreen() {
        return EWE_GREEN;
    }

    public static String getStandardDesign() {
        return STANDARD_DESIGN;
    }

    public static String getEweGrey() {
        return EWE_GREY;
    }

    public static Insets getGap() {
        return GAP;
    }

    public static String getGraphColor() {
        return GRAPH_COLOR;
    }

}
