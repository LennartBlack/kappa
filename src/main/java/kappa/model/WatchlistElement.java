package kappa.model;

public class WatchlistElement {

    // Attributes
    private Cable cable;
    private String note;

    // Constructor
    public WatchlistElement(Cable cable) {
        this.cable = cable;
    }

    // Getter
    public String getNote() {
        return this.note;
    }

    // Setter
    public void setNote(String note) {
        this.note = note;
    }
}
