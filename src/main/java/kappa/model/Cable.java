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
     * @param id of the cable
     * @param start of the cable
     * @param end of the cable
     * @param resistance of the cable
     * @param reactance of the cable
     * @param ampacity of the cable
     * @param electricity of the cable
     * @param length of the cable
     * @param yearOfConstruction of the cable
     * @param crossSection of the cable
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
     * @return id of the cable
     */
    public String getId() {
        return this.id;
    }

    /**
     * Getter for the start attribute
     * @return start of the cable
     */
    public String getStart() {
        return start;
    }

    /**
     * Getter for the end attribute
     * @return end of the cable
     */
    public String getEnd() {
        return end;
    }

    /**
     * Getter for the resistance attribute
     * @return resistance of the cable
     */
    public Double getResistance() {
        return resistance;
    }

    /**
     * Getter for the reactance attribute
     * @return reactance of the cable
     */
    public Double getReactance() {
        return reactance;
    }

    /**
     * Getter for the ampacity attribute
     * @return ampacity of the cable
     */
    public Double getAmpacity() {
        return ampacity;
    }

    /**
     * Getter for the length attribute
     * @return length of the cable
     */
    public Double getLength() {
        return length;
    }

    /**
     * Getter for the yearOfConstruction attribute
     * @return yearOfConstruction of the cable
     */
    public int getYearOfConstruction() {
        return yearOfConstruction;
    }

    /**
     * Getter for the crossSection attribute
     * @return crossSection of the cable
     */
    public int getCrossSection() {
        return crossSection;
    }
}
