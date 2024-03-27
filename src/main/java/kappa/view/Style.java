package kappa.view;

public class Style {
    public static final String EWE_BLUE = "#005F96";
    
    public static final String EWE_GREEN = "#C8D200";

    private static final String EWE_GREY ="#5A5F5F";

    private static final String BUTTON_DESIGN = "-fx-background-color:" + EWE_BLUE + "; -fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: " + EWE_GREEN + ";";


    public static String getEweBlue() {
        return EWE_BLUE;
    }
    public static String getEweGreen() {
        return EWE_GREEN;
    }

    public static String getButtonDesing(){
        return BUTTON_DESIGN;
    }

    public static String getEweGrey(){
        return EWE_GREY;
    }
}
