package kappa.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Cable extends ArrayList implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String start;
    private String end;
    private Double resistance;
    private Double reactance;
    private Double ampacity;
    private Double electricity;
    private Double length;
    private int yearOfConstruction;
    private int CrossSection;


    public Cable(String identification) {
        this.id = identification;
    }

    /**
     * Constructor for the Cable class
     * @param id
     * @param start
     * @param end
     * @param resistance
     * @param reactance
     * @param ampacity
     * @param electricity
     * @param length
     * @param yearOfConstruction
     * @param CrossSection
     */
    public Cable(String id, String start, String end, Double resistance, Double reactance, Double ampacity, Double electricity, Double length, int yearOfConstruction, int CrossSection) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.resistance = resistance;
        this.reactance = reactance;
        this.ampacity = ampacity;
        this.electricity = electricity;
        this.length = length;
        this.yearOfConstruction = yearOfConstruction;
        this.CrossSection = CrossSection;
    }

    public String getId() {
        return this.id;
    }
    
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

    public Double getElectricity() {
        return electricity;
    }

    public Double getLength() {
        return length;
    }

    public int getYearOfConstruction() {
        return yearOfConstruction;
    }

    public int getCrossSection() {
        return CrossSection;
    }
}
