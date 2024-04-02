package kappa.model;

import java.io.Serializable;

public class WatchlistElement implements Serializable {

    // Attributes
    private Cable cable;
    private String note;

    // Constructor
    public WatchlistElement(Cable cable) {
        this.cable = cable;
        this.note = "";
    }

    // Getter
    public String getNote() {
        return this.note;
    }

    public Cable getCable() {
        return this.cable;
    }

    // Setter
    public void setNote(String note) {
        this.note = note;
    }
}
