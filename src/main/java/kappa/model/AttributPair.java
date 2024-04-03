package kappa.model;

public class AttributPair {
    
    // Attribute
    private String attribute;
    private String value;
    private String unit;

    // Constructor
    public AttributPair(String attribute, String value, String unit) {
        this.attribute = attribute;
        this.value = value;
        this.unit = unit;
    }

    // Getter
    public String getAttribute() {
        return attribute;
    }
    public String getValue() {
        return value;
    }    
    public String getUnit() {
        return unit;
    }
}
