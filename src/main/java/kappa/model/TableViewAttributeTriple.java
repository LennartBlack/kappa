package kappa.model;

public class TableViewAttributeTriple {

    // Attribute
    private String attribute;
    private String value;
    private String unit;

    /**
     * Constructor for the TableViewAttributeTriple class
     * @param attribute attribute
     * @param value value
     * @param unit unit
     */
    public TableViewAttributeTriple(String attribute, String value, String unit) {
        this.attribute = attribute;
        this.value = value;
        this.unit = unit;
    }

    // Getter
    /**
     * Getter for the attribute attribute
     * @return attribute
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * Getter for the value attribute
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Getter for the unit attribute
     * @return unit
     */
    public String getUnit() {
        return unit;
    }
}
