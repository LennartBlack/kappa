package kappa.model;

import java.io.Serializable;

public class Cable implements Serializable {
    private static final long serialVersionUID = 1L;
    private String identification;

    public Cable(String identification) {
        this.identification = identification;
    }

    public String getIdentification() {
        return this.identification;
    }
}
