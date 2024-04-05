package kappa.model;

public class TableViewAttributeTriple {

    // Attribute
    private String attribute;
    private String value;
    private String unit;

    // Constructor
    public TableViewAttributeTriple(String attribute, String value, String unit) {
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
