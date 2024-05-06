package kappa.model;

import java.io.Serializable;

public class WatchlistElement implements Serializable {

    // Attributes
    private Cable cable;
    private String note = "";

    /**
     * Constructor for the WatchlistElement class
     * @param cable cable
     */
    public WatchlistElement(Cable cable) {
        this.cable = cable;
    }

    // Getter and Setter
    /**
     * Getter for the note attribute
     * @return note
     */
    public String getNote() {
        return this.note;
    }
    /**
     * Getter for the cable attribute
     * @return cable
     */
    public Cable getCable() {
        return this.cable;
    }
    /**
     * Setter for the note attribute
     * @param note note
     */
    public void setNote(String note) {
        this.note = note;
    }
}
