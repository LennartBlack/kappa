package kappa.utils;

public class AttributePair {
    private final String attribute;
    private final String value;

    public AttributePair(String attribute, String value) {
        this.attribute = attribute;
        this.value = value;
    }

    public String getAttribute() {
        return this.attribute;
    }
    public String getValue() {
        return this.value;
    }
}
