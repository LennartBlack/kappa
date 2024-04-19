package kappa.model;

import java.io.Serializable;

public class Cable implements Serializable {

    // Attribute
    private static final long serialVersionUID = 1L;
    private String id;
    private String start;
    private String end;
    private Double resistance;
    private Double reactance;
    private Double ampacity;
    private Double length;
    private int yearOfConstruction;
    private int crossSection;

    // Constructor
    /**
     * Constructor for the Cable class
     * 
     * @param id
     * @param start
     * @param end
     * @param resistance
     * @param reactance
     * @param ampacity
     * @param electricity
     * @param length
     * @param yearOfConstruction
     * @param crossSection
     */
    public Cable(String id, String start, String end, Double resistance, Double reactance, Double ampacity, Double length, int yearOfConstruction, int crossSection) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.resistance = resistance;
        this.reactance = reactance;
        this.ampacity = ampacity;
        this.length = length;
        this.yearOfConstruction = yearOfConstruction;
        this.crossSection = crossSection;
    }

    public String getId() {
        return this.id;
    }

    // Getter
    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public Double getResistance() {
        return resistance;
    }

    public Double getReactance() {
        return reactance;
    }

    public Double getAmpacity() {
        return ampacity;
    }

    public Double getLength() {
        return length;
    }

    public int getYearOfConstruction() {
        return yearOfConstruction;
    }

    public int getCrossSection() {
        return crossSection;
    }
}
