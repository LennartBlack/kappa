package kappa.model;

import java.io.Serializable;

public class WatchlistElement implements Serializable {

    // Attributes
    private Cable cable;
    private String note = "";

    // Constructor
    public WatchlistElement(Cable cable) {
        this.cable = cable;
    }

    // Getter and Setter
    public String getNote() {
        return this.note;
    }
    public Cable getCable() {
        return this.cable;
    }
    public void setNote(String note) {
        this.note = note;
    }
}
