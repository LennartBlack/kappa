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

    
    // Getter
    /**
     * Constructor for the Cable class
     * @return
     */
    public String getId() {
        return this.id;
    }

    /**
     * Getter for the start attribute
     * @return
     */
    public String getStart() {
        return start;
    }

    /**
     * Getter for the end attribute
     * @return
     */
    public String getEnd() {
        return end;
    }

    /**
     * Getter for the resistance attribute
     * @return
     */
    public Double getResistance() {
        return resistance;
    }

    /**
     * Getter for the reactance attribute
     * @return
     */
    public Double getReactance() {
        return reactance;
    }

    /**
     * Getter for the ampacity attribute
     * @return
     */
    public Double getAmpacity() {
        return ampacity;
    }

    /**
     * Getter for the length attribute
     * @return
     */
    public Double getLength() {
        return length;
    }

    /**
     * Getter for the yearOfConstruction attribute
     * @return
     */
    public int getYearOfConstruction() {
        return yearOfConstruction;
    }

    /**
     * Getter for the crossSection attribute
     * @return
     */
    public int getCrossSection() {
        return crossSection;
    }
}
