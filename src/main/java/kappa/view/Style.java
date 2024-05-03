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

    /**
     * Getter for the EWE_BLUE attribute
     * @return
     */
    public static String getEweBlue() {
        return EWE_BLUE;
    }

    /**
     * Getter for the EWE_GREEN attribute
     * @return
     */
    public static String getEweGreen() {
        return EWE_GREEN;
    }
    /**
     * Getter for the STANDARD_DESIGN attribute
     * @return
     */
    public static String getStandardDesign() {
        return STANDARD_DESIGN;
    }
    /**
     * Getter for the EWE_GREY attribute
     * @return
     */
    public static String getEweGrey() {
        return EWE_GREY;
    }

    /**
     * /**
     */
    public static Insets getGap() {
        return GAP;
    }

    /**
     * Getter for the GRAPH_COLOR attribute
     * @return
     */
    public static String getGraphColor() {
        return GRAPH_COLOR;
    }

}
